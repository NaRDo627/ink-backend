package net.ink.admin.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ink.admin.annotation.WithMockInkAdminUser;
import net.ink.admin.common.DtoCreator;
import net.ink.admin.dto.QuestionDto;
import net.ink.admin.dto.mapper.QuestionMapper;
import net.ink.admin.web.AbstractControllerTest;
import net.ink.core.question.entity.Question;
import net.ink.core.question.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuestionApiControllerTest extends AbstractControllerTest {
    private static final long QUESTION_ID = 1L;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockInkAdminUser
    @DisplayName("질문을 수정한다.")
    public void testUpdateQuestion() throws Exception {
        QuestionDto updatedQuestion = DtoCreator.createQuestionDto();
        updatedQuestion.setContent("Updated question");
        updatedQuestion.setKoContent("업데이트된 질문");

        mockMvc.perform(put("/api/question/" + QUESTION_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedQuestion)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockInkAdminUser
    @DisplayName("질문을 삭제한다.")
    public void testDeleteQuestion() throws Exception {
        mockMvc.perform(delete("/api/question/" + QUESTION_ID))
                .andExpect(status().isOk());
    }
}