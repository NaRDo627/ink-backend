package net.ink.scheduler.todayexpression.maintain.job;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.scheduler.todayexpression.maintain.service.TodayUsefulExpressionMaintainService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TodayExpressionMaintainJob extends QuartzJobBean {
    private final TodayUsefulExpressionMaintainService todayUsefulExpressionMaintainService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("TodayExpressionMaintainJob executeInternal :: before");
        todayUsefulExpressionMaintainService.maintain();
        log.info("TodayExpressionMaintainJob executeInternal :: after");
    }
}
