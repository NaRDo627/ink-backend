package net.ink.scheduler.todayexpression.refresh.job;

import lombok.RequiredArgsConstructor;
import net.ink.scheduler.core.component.SchedulerRegisterHelper;
import net.ink.scheduler.util.CronExpression;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@RequiredArgsConstructor
public class TodayExpressionRefreshJobScheduleConfig {
    private final SchedulerRegisterHelper schedulerRegisterHelper;

    private static final String JOB_NAME = "todayExpressionRefreshJob";
    private static final String JOB_GROUP = "DEFAULT";

    @Bean
    public JobDetailFactoryBean todayExpressionRefreshJobDetail() {
        return schedulerRegisterHelper.createJobDetailFactoryBean(TodayExpressionRefreshJob.class);
    }

    @Bean
    public CronTriggerFactoryBean todayExpressionRefreshJobTrigger(JobDetail todayExpressionRefreshJobDetail) {
        CronTriggerFactoryBean factoryBean =
                schedulerRegisterHelper.createCronTriggerFactoryBean(todayExpressionRefreshJobDetail, CronExpression.EVERY_DAY_00_AM);
        factoryBean.setName(JOB_NAME);
        factoryBean.setGroup(JOB_GROUP);
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean todayExpressionRefreshJobScheduler(Trigger todayExpressionRefreshJobTrigger) {
        return schedulerRegisterHelper.createSchedulerFactoryBean(todayExpressionRefreshJobTrigger);
    }
}
