package net.ink.api.member.web;

import net.ink.api.annotation.WithMockInkUser;
import net.ink.api.core.jwt.dto.TokenDto;
import net.ink.api.core.jwt.dto.TokenProvider;
import net.ink.api.core.jwt.service.JwtService;
import net.ink.api.core.oauth2.dto.OAuth2Profile;
import net.ink.api.core.oauth2.service.OAuth2Service;
import net.ink.api.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberAuthControllerTest extends AbstractControllerTest {
    private static final String TEST_PROVIDER_ACCESS_TOKEN = "testProviderToken";
    private static final String TEST_INK_ACCESS_TOKEN = "testInkAccessToken";
    private static final String TEST_INK_REFRESH_TOKEN = "testInkRefreshToken";
    private static final String TEST_IDENTIFIER = "identity";
    private static final String TEST_PROVIDER_ACCESS_TOKEN_JSON = String.format("{\"providerName\":\"kakao\", " +
            "\"providerAccessToken\":\"%s\"}", TEST_PROVIDER_ACCESS_TOKEN);

    @MockBean
    private OAuth2Service oAuth2Service;

    @MockBean
    private JwtService jwtService;

    @Test
    void 사용자_존재체크_테스트() throws Exception {
        TokenDto.Provider provider = new TokenDto.Provider(TokenProvider.KAKAO, TEST_PROVIDER_ACCESS_TOKEN);
        when(oAuth2Service.getProfile(eq(provider))).thenReturn(new OAuth2Profile(TEST_IDENTIFIER));

        mockMvc.perform(
                post("/api/member/user-exists")
                        .content(TEST_PROVIDER_ACCESS_TOKEN_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.identifier").value(TEST_IDENTIFIER))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.exist").value(true))
                .andDo(print());
    }

    @Test
    void 사용자_로그인_테스트() throws Exception {
        TokenDto.Provider provider = new TokenDto.Provider(TokenProvider.KAKAO, TEST_PROVIDER_ACCESS_TOKEN);
        when(jwtService.issue(eq(provider))).thenReturn(
                new TokenDto.Ink(TEST_INK_ACCESS_TOKEN, TEST_INK_REFRESH_TOKEN));

        mockMvc.perform(
                post("/api/member/signin")
                        .content(TEST_PROVIDER_ACCESS_TOKEN_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.inkAccessToken").value(TEST_INK_ACCESS_TOKEN))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.inkRefreshToken").value(TEST_INK_REFRESH_TOKEN))
                .andDo(print());
    }

    @Test
    @WithMockInkUser
    void 사용자_리프레시_테스트() throws Exception {
        TokenDto.InkRefresh refresh = new TokenDto.InkRefresh(TEST_INK_REFRESH_TOKEN);
        when(jwtService.refresh(eq(refresh))).thenReturn(
                new TokenDto.Ink(TEST_INK_ACCESS_TOKEN, TEST_INK_REFRESH_TOKEN));
        String testJson = String.format("{\"inkRefreshToken\": \"%s\"}", TEST_INK_REFRESH_TOKEN);

        mockMvc.perform(
                post("/api/member/refresh")
                        .content(testJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.inkAccessToken").value(TEST_INK_ACCESS_TOKEN))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.inkRefreshToken").value(TEST_INK_REFRESH_TOKEN))
                .andDo(print());
    }
}