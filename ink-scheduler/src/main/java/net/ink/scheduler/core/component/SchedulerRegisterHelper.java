package net.ink.scheduler.core.component;

import lombok.RequiredArgsConstructor;
import net.ink.scheduler.core.config.AutowiringSpringBeanJobFactory;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.TimeZone;

/**
 * Scheduler 관련 Bean 을 생성하는 Helper 클래스입니다.
 */
@Component
@RequiredArgsConstructor
public class SchedulerRegisterHelper {
    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;
    private final ApplicationContext applicationContext;
    private final Properties quartzProperties;

    /**
     * 입력받은 jobClass에 대한 JobDetail 을 생성합니다.
     * @param jobClass JobDetail 을 생성할 Job 클래스
     * @return org.springframework.scheduling.quartz.JobDetailFactoryBean
     */
    public JobDetailFactoryBean createJobDetailFactoryBean(Class<? extends Job> jobClass) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(jobClass);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    /**
     * 입력받은 jobDetail에 대한 CronTriggerFactoryBean 을 생성합니다.
     *
     * @param jobDetail CronTriggerFactoryBean 을 생성할 JobDetail
     * @param cronExpression CronTriggerFactoryBean 의 cronExpression
     * @return org.springframework.scheduling.quartz.CronTriggerFactoryBean
     */
    public CronTriggerFactoryBean createCronTriggerFactoryBean(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(SimpleTriggerImpl.MISFIRE_INSTRUCTION_FIRE_NOW);
        factoryBean.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return factoryBean;
    }

    /**
     * SchedulerFactoryBean 을 생성합니다.
     *
     * @param trigger SchedulerFactoryBean 의 Trigger
     * @return org.springframework.scheduling.quartz.SchedulerFactoryBean
     */
    public SchedulerFactoryBean createSchedulerFactoryBean(Trigger trigger) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setTransactionManager(transactionManager);
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setQuartzProperties(quartzProperties);
        schedulerFactoryBean.setTriggers(trigger);
        return schedulerFactoryBean;
    }
}