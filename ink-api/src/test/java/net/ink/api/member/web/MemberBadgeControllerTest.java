package net.ink.api.member.web;

import net.ink.api.annotation.WithMockInkUser;
import net.ink.api.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberBadgeControllerTest extends AbstractControllerTest {
    @Test
    @WithMockInkUser
    public void 사용자_뱃지_획득_확인_수정_테스트() throws Exception {
        mockMvc.perform(
                put("/api/member/me/check/badge/{badgeId}",  1)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("뱃지 획득을 확인하였습니다."))
                .andDo(print());
    }

    @Test
    @WithMockInkUser
    public void 사용자_뱃지_획득_확인_테스트() throws Exception {
        mockMvc.perform(
                get("/api/member/me/check/badge/{badgeId}",  1)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.checked").value(true))
                .andDo(print());
    }
}
