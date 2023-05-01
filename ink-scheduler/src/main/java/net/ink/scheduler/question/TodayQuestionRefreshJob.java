package net.ink.scheduler.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.scheduler.batch.question.TodayQuestionRefreshBatchJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@RequiredArgsConstructor
@Slf4j
public class TodayQuestionRefreshJob extends QuartzJobBean {
    private final TodayQuestionRefreshBatchJob todayQuestionRefreshBatchJob;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("TodayQuestionRefreshJob executeInternal :: before");
        todayQuestionRefreshBatchJob.execute();
        log.info("TodayQuestionRefreshJob executeInternal :: after");
    }
}
