package net.ink.batch.core.job;

import org.quartz.InterruptableJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

public abstract class InkQuartzJob extends QuartzJobBean implements InterruptableJob {
}
