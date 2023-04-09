package net.ink.batch.core.trigger;

import net.ink.batch.core.job.JobRequest;
import org.quartz.Trigger;

public interface TriggerProvider {
    Trigger getTrigger(JobRequest jobRequest);
}
