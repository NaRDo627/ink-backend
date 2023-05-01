package net.ink.scheduler.core.trigger;

import net.ink.scheduler.core.job.JobRequest;
import org.quartz.Trigger;

public interface TriggerProvider {
    Trigger getTrigger(JobRequest jobRequest);
}
