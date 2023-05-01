package net.ink.scheduler.question;

import lombok.RequiredArgsConstructor;
import net.ink.scheduler.core.component.SchedulerRegisterHelper;
import net.ink.scheduler.util.CronExpression;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.TimeZone;

@Configuration
@RequiredArgsConstructor
public class TodayQuestionRefreshJobScheduleConfig {
    private final SchedulerRegisterHelper schedulerRegisterHelper;

    private static final String JOB_NAME = "todayQuestionRefreshJob";
    private static final String JOB_GROUP = "DEFAULT";

    @Bean
    public JobDetailFactoryBean todayQuestionRefreshJobDetail() {
        return schedulerRegisterHelper.createJobDetailFactoryBean(TodayQuestionRefreshJob.class);
    }

    @Bean
    public CronTriggerFactoryBean todayQuestionRefreshJobTrigger(JobDetail todayQuestionRefreshJobDetail) {
        CronTriggerFactoryBean factoryBean =
                schedulerRegisterHelper.createCronTriggerFactoryBean(todayQuestionRefreshJobDetail, CronExpression.EVERY_DAY_00_AM);
        factoryBean.setName(JOB_NAME);
        factoryBean.setGroup(JOB_GROUP);
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean todayQuestionRefreshJobScheduler(Trigger todayQuestionRefreshJobTrigger) {
        return schedulerRegisterHelper.createSchedulerFactoryBean(todayQuestionRefreshJobTrigger);
    }
}
