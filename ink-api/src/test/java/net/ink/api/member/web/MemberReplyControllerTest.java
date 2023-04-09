package net.ink.api.member.web;

import net.ink.api.annotation.WithMockInkUser;
import net.ink.api.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberReplyControllerTest extends AbstractControllerTest {
    @Test
    @WithMockInkUser
    public void 사용자_답변_목록_조회_테스트() throws Exception {
        mockMvc.perform(
                get("/api/member/{memberId}/replies", 1)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].replyId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].content").value("this is test reply 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].replyId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].content").value("this is test reply 1"))
                .andDo(print());
    }

    @Test
    @WithMockInkUser
    public void 사용자_오늘_답변했는지_테스트() throws Exception {
        mockMvc.perform(
                get("/api/member/check-replied-today")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.replied").value(false))
                .andDo(print());
    }
}