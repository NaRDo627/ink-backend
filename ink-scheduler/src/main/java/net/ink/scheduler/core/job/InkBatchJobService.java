package net.ink.scheduler.core.job;

public interface InkBatchJobService {
    InkJobLauncherProvider getProvider();
    JobRequestService getJobRequestService();
}
