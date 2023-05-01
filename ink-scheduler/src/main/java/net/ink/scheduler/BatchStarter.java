package net.ink.scheduler;

import lombok.RequiredArgsConstructor;
import net.ink.scheduler.core.job.JobRequestService;
import net.ink.scheduler.core.job.ScheduledJobFactory;
import net.ink.scheduler.core.scheduler.ScheduleService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BatchStarter implements ApplicationListener<ContextRefreshedEvent> {
    private final ScheduleService scheduleService;
    private final ScheduledJobFactory jobFactory;
    private final List<JobRequestService> jobRequestServices;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (JobRequestService service : jobRequestServices) {
            scheduleService.addJob(
                    jobFactory.buildScheduledJob(service.create())
            );
        }
    }
}
