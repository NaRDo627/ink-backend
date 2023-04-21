package net.ink.admin.web.api;

import net.ink.admin.annotation.WithMockInkAdminUser;
import net.ink.admin.web.AbstractControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReplyDeleteControllerTest extends AbstractControllerTest {
    private static final Long REPLY_ID = 1L;

    @Test
    @DisplayName("삭제 요청 시 200 상태 코드가 반환된다.")
    @WithMockInkAdminUser
    public void testDeleteReply() throws Exception {
        mockMvc.perform(delete("/api/reply/{replyId}", REPLY_ID))
                .andExpect(status().isOk());
    }
}