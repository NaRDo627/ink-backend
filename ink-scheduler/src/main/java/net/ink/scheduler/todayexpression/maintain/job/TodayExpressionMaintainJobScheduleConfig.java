package net.ink.scheduler.todayexpression.maintain.job;

import lombok.RequiredArgsConstructor;
import net.ink.scheduler.core.component.SchedulerRegisterHelper;
import net.ink.scheduler.util.CronExpression;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@RequiredArgsConstructor
public class TodayExpressionMaintainJobScheduleConfig {
    private final SchedulerRegisterHelper schedulerRegisterHelper;

    private static final String JOB_NAME = "todayExpressionMaintainJob";
    private static final String JOB_GROUP = "DEFAULT";

    @Bean
    public JobDetailFactoryBean todayExpressionMaintainJobDetail() {
        return schedulerRegisterHelper.createJobDetailFactoryBean(TodayExpressionMaintainJob.class);
    }

    @Bean
    public CronTriggerFactoryBean todayExpressionMaintainJobTrigger(JobDetail todayExpressionMaintainJobDetail) {
        CronTriggerFactoryBean factoryBean =
                schedulerRegisterHelper.createCronTriggerFactoryBean(todayExpressionMaintainJobDetail, CronExpression.EVERY_5_SECONDS);
        factoryBean.setName(JOB_NAME);
        factoryBean.setGroup(JOB_GROUP);
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean todayExpressionMaintainJobScheduler(Trigger todayExpressionMaintainJobTrigger) {
        return schedulerRegisterHelper.createSchedulerFactoryBean(todayExpressionMaintainJobTrigger);
    }
}
