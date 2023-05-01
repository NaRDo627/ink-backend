//package net.ink.scheduler.push.job;
//
//import net.ink.scheduler.AbstractJobTest;
//import net.ink.scheduler.core.config.TestBatchConfig;
//import net.ink.scheduler.batch.push.service.FcmDailyPushService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(classes = {FcmDailyPushJobConductor.class, TestBatchConfig.class})
//class FcmDailyPushJobTest extends AbstractJobTest {
//    @MockBean
//    private FcmDailyPushService fcmDailyPushService;
//
//    @BeforeEach
//    public void clearMetadata() {
//        jobRepositoryTestUtils.removeJobExecutions();
//    }
//
//    @Test
//    public void 푸쉬_알림_테스트() throws Exception {
//        // given
//        JobParameters jobParameters =
//                jobLauncherTestUtils.getUniqueJobParameters();
//
//        // when
//        JobExecution jobExecution =
//                jobLauncherTestUtils.launchJob(jobParameters);
//
//        // then
//        assertEquals(ExitStatus.COMPLETED,
//                jobExecution.getExitStatus());
//    }
//}