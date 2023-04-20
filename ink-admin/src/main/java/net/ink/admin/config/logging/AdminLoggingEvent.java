package net.ink.admin.config.logging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import net.ink.admin.entity.AdminMember;
import net.ink.core.member.entity.Member;

@AllArgsConstructor
@Getter
public class AdminLoggingEvent {
    private AdminMember actionedAdminMember;
    private String action;
    private String actionQuery;
}
