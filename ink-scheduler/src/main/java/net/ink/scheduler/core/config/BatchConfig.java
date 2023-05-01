package net.ink.scheduler.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.scheduler.core.job.InkJobLauncher;
import net.ink.scheduler.core.job.InkJobLauncherProvider;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class BatchConfig {
    private final GenericApplicationContext applicationContext;
    private final List<InkJobLauncherProvider> inkJobLauncherProviders;

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }

    @PostConstruct
    public void init() {
        for (InkJobLauncherProvider provider : inkJobLauncherProviders) {
            applicationContext.registerBean(provider.getJobLauncherName(), InkJobLauncher.class, provider::getLauncher);
            applicationContext.registerBean(provider.getLauncher().getJob().getName(), Job.class, () -> provider.getLauncher().getJob());
        }
    }

}