package net.ink.batch.core.job;

public interface InkBatchJobService {
    InkJobLauncherProvider getProvider();
    JobRequestService getJobRequestService();
}
