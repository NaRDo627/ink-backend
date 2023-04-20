package net.ink.admin.repository;

import net.ink.admin.entity.AdminLog;
import net.ink.admin.entity.AdminMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {
}
