package net.ink.admin.service;

import lombok.RequiredArgsConstructor;
import net.ink.admin.entity.AdminLog;
import net.ink.admin.repository.AdminLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminLogService {
    private final AdminLogRepository adminLogRepository;

    @Transactional
    public void insertLog(AdminLog adminLog) {
        adminLogRepository.save(adminLog);
    }
}
