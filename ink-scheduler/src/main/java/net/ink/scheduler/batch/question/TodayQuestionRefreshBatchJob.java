package net.ink.scheduler.batch.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TodayQuestionRefreshBatchJob {
    private final JobLauncher jobLauncher;
    private final Job todayQuestionRefreshJob;

    public void execute() {
        try {
            JobExecution jobExecution = jobLauncher.run(todayQuestionRefreshJob, new JobParameters());
            log.info("job : {} executed successfully, status : {}", todayQuestionRefreshJob.getName(), jobExecution.getStatus());
        } catch (Exception e) {
            log.error("error occurred while executing job : {}", todayQuestionRefreshJob.getName(), e);
        }
    }
}
