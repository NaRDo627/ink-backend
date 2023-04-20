package net.ink.admin.web.api;

import net.ink.admin.annotation.WithMockInkAdminUser;
import net.ink.admin.web.AbstractControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminApiControllerTest extends AbstractControllerTest {

    @Test
    @WithMockInkAdminUser
    @DisplayName("관리자 회원을 삭제한다.")
    void deleteMember() throws Exception {
        mockMvc.perform(delete("/api/admin-member/2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockInkAdminUser
    @DisplayName("관리자 회원을 삭제할 때, 슈퍼관리자는 삭제할 수 없다.")
    void deleteMemberButSupervisorCannotBeDeleted() throws Exception {
        mockMvc.perform(delete("/api/admin-member/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockInkAdminUser
    @DisplayName("관리자 회원을 승급한다.")
    void promoteMember() throws Exception {
        mockMvc.perform(post("/api/admin-member/2/promote"))
                .andExpect(status().isOk());
    }
}