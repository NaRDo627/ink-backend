package net.ink.scheduler.util;

import net.ink.scheduler.core.job.InkJobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class JobLauncherUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        // TODO Auto-generated method stub
        context = applicationContext;
    }

    public static InkJobLauncher getBeanName(String beanName) {
        return context.getBean(beanName, InkJobLauncher.class);
    }
}

