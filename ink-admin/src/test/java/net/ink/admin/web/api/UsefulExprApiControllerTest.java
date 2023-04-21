package net.ink.admin.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ink.admin.annotation.WithMockInkAdminUser;
import net.ink.admin.dto.UsefulExpressionDto;
import net.ink.admin.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UsefulExprApiControllerTest extends AbstractControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockInkAdminUser
    void deleteUsefulExpression() throws Exception {
        mockMvc.perform(delete("/api/useful-expression/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockInkAdminUser
    void addUsefulExpression() throws Exception {
        UsefulExpressionDto usefulExpressionDto = UsefulExpressionDto.builder()
                .expression("test")
                .meaning("테스트")
                .expressionExample("this is test")
                .expressionExampleMeaning("이것은 테스트입니다.")
                .build();

        mockMvc.perform(post("/api/useful-expression")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usefulExpressionDto)))
                .andExpect(status().isOk());
    }
}