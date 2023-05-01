package net.ink.scheduler.batch.question;

import net.ink.scheduler.AbstractJobTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TodayQuestionRefreshBatchJobTest extends AbstractJobTest {
    @Autowired
    private Job todayQuestionRefreshJob;

    @BeforeEach
    void setUp() {
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        jobLauncherTestUtils.setJob(todayQuestionRefreshJob);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("오늘의 질문 갱신 배치 테스트")
    void testTodayRefreshBatchJob() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParameters());

        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }
}