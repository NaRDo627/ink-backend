package net.ink.batch.core.trigger;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.batch.core.job.CronJobRequest;
import net.ink.batch.core.job.JobRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TriggerProviderFactory {
    private final SimpleTriggerProvider simpleTriggerProvider;
    private final CronTriggerProvider cronTriggerProvider;

    public TriggerProvider getInstance(JobRequest jobRequest) {
        if (jobRequest instanceof CronJobRequest) {
            return this.cronTriggerProvider;
        }

        return this.simpleTriggerProvider;
    }
}
