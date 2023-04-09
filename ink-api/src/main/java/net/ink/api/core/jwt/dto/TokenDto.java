package net.ink.api.core.jwt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class TokenDto {
    private TokenDto() { }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @ApiModel(value="Provider Token Dto", description="Provider 토큰 모델")
    public static class Provider {
        @ApiModelProperty(value = "Provider 이름 (Kakao / Google)")
        private TokenProvider providerName;

        @ApiModelProperty(value = "Provider 제공 Access Token")
        private String providerAccessToken;

        public void setProviderName(String providerName) {
            this.providerName = TokenProvider.create(providerName);
        }
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @ApiModel(value="Ink Token Dto", description="Ink 제공 JWT 및 Refresh 토큰 모델")
    public static class Ink {
        @ApiModelProperty(value = "Ink 제공 Access Token (jwt)")
        private String inkAccessToken;

        @ApiModelProperty(value = "Ink 제공 Refresh Token (jwt)")
        private String inkRefreshToken;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @ApiModel(value="Ink Refresh Token Dto", description="Ink 제공 JWT 및 Refresh 토큰 모델")
    public static class InkRefresh {
        @ApiModelProperty(value = "Ink 제공 Refresh Token (jwt)")
        private String inkRefreshToken;
    }
}
