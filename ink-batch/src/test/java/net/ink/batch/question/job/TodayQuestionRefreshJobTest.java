package net.ink.batch.question.job;

import net.ink.batch.AbstractJobTest;
import net.ink.batch.core.config.TestBatchConfig;
import net.ink.batch.todayexpression.refresh.job.TodayExpressionRefreshJobConductor;
import net.ink.batch.todayexpression.refresh.service.TodayUsefulExpressionRefreshService;
import net.ink.batch.todayexpression.refresh.service.TodayUsefulExpressionRefreshServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TodayExpressionRefreshJobConductor.class, TestBatchConfig.class,
        TodayUsefulExpressionRefreshService.class, TodayUsefulExpressionRefreshServiceImpl.class})
class TodayQuestionRefreshJobTest extends AbstractJobTest {
    @BeforeEach
    public void clearMetadata() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    public void 오늘의_표현_새로고침_테스트() throws Exception {
        // given
        JobParameters jobParameters =
                jobLauncherTestUtils.getUniqueJobParameters();

        // when
        JobExecution jobExecution =
                jobLauncherTestUtils.launchJob(jobParameters);

        // then
        assertEquals(ExitStatus.COMPLETED,
                jobExecution.getExitStatus());
    }
}