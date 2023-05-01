package net.ink.scheduler.batch.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.core.member.entity.Member;
import net.ink.core.member.repository.MemberRepository;
import net.ink.core.question.service.TodayQuestionSelectionService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TodayQuestionRefreshBatchJobConfig { // 00 am 실행
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TodayQuestionSelectionService todayQuestionSelectionService;
    private final MemberRepository memberRepository;

    @Bean
    public Job todayQuestionRefreshJob() {
        return jobBuilderFactory.get("todayQuestionRefreshJob")
                .start(todayQuestionRefreshStep())
                .build();
    }

    @Bean
    public Step todayQuestionRefreshStep() {
        return stepBuilderFactory.get("todayQuestionRefreshStep")
                .<Member, Member>chunk(1000)
                .reader(todayQuestionRefreshReader())
                .processor(todayQuestionRefreshProcessor())
                .writer(todayQuestionRefreshWriter())
                .build();
    }

    @Bean
    public RepositoryItemReader<Member> todayQuestionRefreshReader() {
        return new RepositoryItemReaderBuilder<Member>()
                .name("todayQuestionRefreshReader")
                .repository(memberRepository)
                .methodName("findAll")
                .sorts(Map.of("memberId", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemProcessor<Member, Member> todayQuestionRefreshProcessor() {
        return member -> {
            todayQuestionSelectionService.reselectTodayQuestion(member);
            return member;
        };
    }

    @Bean
    public ItemWriter<Member> todayQuestionRefreshWriter() {
        RepositoryItemWriter<Member> writer = new RepositoryItemWriter<>();
        writer.setRepository(memberRepository);
        writer.setMethodName("save");
        return writer;
    }
}
