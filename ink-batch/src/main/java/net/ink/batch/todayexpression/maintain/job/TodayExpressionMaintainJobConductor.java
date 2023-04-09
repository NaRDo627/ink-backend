package net.ink.batch.todayexpression.maintain.job;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.batch.core.job.AbstractJobConductor;
import net.ink.batch.todayexpression.maintain.service.TodayUsefulExpressionMaintainService;
import net.ink.batch.util.CronExpression;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class TodayExpressionMaintainJobConductor extends AbstractJobConductor {
    public static final String TODAY_EXPRESSION_MAINTAIN_JOB_NAME = "todayExpressionMaintainJob";
    public static final String TODAY_EXPRESSION_MAINTAIN_JOB_LAUNCHER_NAME = TODAY_EXPRESSION_MAINTAIN_JOB_NAME + "Launcher";
    public static final String TODAY_EXPRESSION_MAINTAIN_JOB_CRON = CronExpression.EVERY_5_MINUTES;

    private TodayUsefulExpressionMaintainService todayUsefulExpressionMaintainService;

    @Autowired
    public void setTodayUsefulExpressionMaintainService(TodayUsefulExpressionMaintainService todayUsefulExpressionMaintainService) {
        this.todayUsefulExpressionMaintainService = todayUsefulExpressionMaintainService;
    }

    @Override
    public String getJobLauncherName() {
        return TODAY_EXPRESSION_MAINTAIN_JOB_LAUNCHER_NAME;
    }

    @Override
    protected String getJobName() {
        return TODAY_EXPRESSION_MAINTAIN_JOB_NAME;
    }

    @Override
    protected String getCronExpression() {
        return TODAY_EXPRESSION_MAINTAIN_JOB_CRON;
    }

    @Override
    protected Job getJob() {
        return jobBuilderFactory.get(TODAY_EXPRESSION_MAINTAIN_JOB_NAME)
                .start(todayExpressionMaintainStep())
                .build();
    }

    public Step todayExpressionMaintainStep() {
        return stepBuilderFactory.get("todayExpressionMaintainStep")
                .tasklet((contribution, context) -> {
                    log.info("test");
                    todayUsefulExpressionMaintainService.maintain();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
