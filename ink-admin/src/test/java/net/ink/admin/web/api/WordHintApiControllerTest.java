package net.ink.admin.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ink.admin.annotation.WithMockInkAdminUser;
import net.ink.admin.common.DtoCreator;
import net.ink.admin.dto.WordHintDto;
import net.ink.admin.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WordHintApiControllerTest extends AbstractControllerTest {
    private static final long QUESTION_ID = 1L;
    private static final long HINT_ID = 1L;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockInkAdminUser
    public void testAddWordHint() throws Exception {
        WordHintDto newWordHint = DtoCreator.createWordHintDto();
        newWordHint.setHintId(null);

        mockMvc.perform(post("/api/question/" + QUESTION_ID + "/word-hint")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newWordHint)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockInkAdminUser
    public void testUpdateWordHint() throws Exception {
        WordHintDto updatedWordHint = new WordHintDto(HINT_ID, "updated", "업데이트된");

        mockMvc.perform(put("/api/word-hint/" + HINT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedWordHint)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockInkAdminUser
    public void testDeleteWordHint() throws Exception {
        mockMvc.perform(delete("/api/word-hint/" + HINT_ID))
                .andExpect(status().isOk());
    }
}