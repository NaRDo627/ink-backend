package net.ink.api.member.web;

import net.ink.api.annotation.WithMockInkUser;
import net.ink.api.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberAchievementControllerTest extends AbstractControllerTest {
    private static final long MEMBER_ID = 1L;

    @Test
    @WithMockInkUser
    public void 사용자_성과_가져오기_테스트() throws Exception{
        mockMvc.perform(
                get("/api/member/{memberId}/achievement", MEMBER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.member.memberId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.member.email").value("test@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.badges", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.requesterProfile").value("true"))
                .andDo(print());
    }

}
