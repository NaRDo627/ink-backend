package net.ink.api.todayexpression.web;

import net.ink.api.annotation.WithMockInkUser;
import net.ink.api.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsefulExpressionControllerTest extends AbstractControllerTest {

    @Test
    @WithMockInkUser
    public void 오늘의_표현_가져오기_테스트() throws Exception {
        mockMvc.perform(
                get("/api/today-expression")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].expId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].expression").value("I'm sure that ~"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].meaning").value("~라는 것을 확신해"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].expressionExample").value("I'm sure that I will achieve my goal."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].expressionExampleMeaning").value("난 내 목표를 달성할 거라고 확신해."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].expId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].expression").value("I'm happy to ~"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].meaning").value("~해서 기뻐"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].expressionExample").value("I'm happy to see you again."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].expressionExampleMeaning").value("널 다시 보게 돼서 기뻐."))
                .andDo(print());
    }
}
