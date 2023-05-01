package net.ink.scheduler.todayexpression.refresh.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ink.scheduler.todayexpression.maintain.service.TodayUsefulExpressionMaintainService;
import net.ink.scheduler.todayexpression.refresh.service.TodayUsefulExpressionRefreshService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TodayExpressionRefreshJob extends QuartzJobBean {
    private final TodayUsefulExpressionRefreshService todayUsefulExpressionRefreshService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("TodayExpressionRefreshJob executeInternal :: before");
        todayUsefulExpressionRefreshService.reselectTodayUsefulExpression();
        log.info("TodayExpressionRefreshJob executeInternal :: after");
    }
}
