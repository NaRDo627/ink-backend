package net.ink.admin.config.logging;

import lombok.RequiredArgsConstructor;
import net.ink.admin.entity.AdminLog;
import net.ink.admin.repository.AdminLogRepository;
import net.ink.admin.service.AdminLogService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class AdminLoggingEventListener {
    private final AdminLogService adminLogService;

    @Async
    @EventListener
    public void handleAdminLoggingEvent(AdminLoggingEvent event) {
        AdminLog adminLog = AdminLog.builder()
                .actionedAdminMember(event.getActionedAdminMember())
                .action(event.getAction())
                .actionQuery(event.getActionQuery())
                .build();

        adminLogService.insertLog(adminLog);
    }
}
