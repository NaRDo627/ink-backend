package net.ink.scheduler.core.scheduler;

import net.ink.scheduler.core.job.ScheduledJob;
import org.quartz.JobKey;

public interface ScheduleService {
    boolean isJobRunning(JobKey jobKey);
    boolean isJobExists(JobKey jobKey);
    boolean addJob(ScheduledJob scheduledJob);
    boolean deleteJob(JobKey jobKey);
    boolean pauseJob(JobKey jobKey);
    boolean resumeJob(JobKey jobKey);
    String getJobState(JobKey jobKey);
}
