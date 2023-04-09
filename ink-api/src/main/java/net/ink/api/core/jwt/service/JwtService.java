package net.ink.api.core.jwt.service;

import lombok.RequiredArgsConstructor;
import net.ink.api.core.jwt.component.JwtCreator;
import net.ink.api.core.jwt.component.JwtResolver;
import net.ink.api.core.jwt.component.JwtValidator;
import net.ink.api.core.jwt.dto.TokenDto;
import net.ink.api.core.oauth2.service.OAuth2Service;
import net.ink.core.core.component.DateFactory;
import net.ink.core.core.exception.UnauthorizedException;
import net.ink.core.member.entity.Member;
import net.ink.core.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtCreator jwtCreator;
    private final JwtResolver jwtResolver;
    private final JwtValidator jwtValidator;
    private final OAuth2Service oAuth2Service;
    private final MemberService memberService;
    private final DateFactory dateFactory;

    @Deprecated
    @Transactional
    public TokenDto.Ink issue(String identifier){
        Member member = memberService.findByIdentifier(identifier);

        TokenDto.Ink token = new TokenDto.Ink();
        token.setInkAccessToken(jwtCreator.createAccessToken(member));
        token.setInkRefreshToken(jwtCreator.createRefreshToken(member));
        return token;
    }

    @Transactional
    public TokenDto.Ink issue(TokenDto.Provider providerToken){
        String identifier = oAuth2Service.getProfile(providerToken).getIdentifier();
        Member member = memberService.findByIdentifier(identifier);

        String accessToken = jwtCreator.createAccessToken(member);
        String refreshToken = jwtCreator.createRefreshToken(member);
        return new TokenDto.Ink(accessToken, refreshToken);
    }

    @Transactional
    public TokenDto.Ink refresh(TokenDto.InkRefresh inkRefreshToken){
        if (isExpired(inkRefreshToken)) {
            throw new UnauthorizedException("Refresh Token이 만료되었습니다. 다시 로그인 해주세요.");
        }

        String identifier = jwtResolver.getUserIdentifier(inkRefreshToken.getInkRefreshToken());
        Member member = memberService.findByIdentifier(identifier);

        String accessToken = jwtCreator.createAccessToken(member);
        String refreshToken = inkRefreshToken.getInkRefreshToken();
        if (isRefreshable(inkRefreshToken)) {
            refreshToken = jwtCreator.createRefreshToken(member);
        }

        return new TokenDto.Ink(accessToken, refreshToken);
    }

    private boolean isExpired(TokenDto.InkRefresh inkRefreshToken) {
        return ! jwtValidator.validateToken(inkRefreshToken.getInkRefreshToken());
    }

    private boolean isRefreshable(TokenDto.InkRefresh inkRefreshToken) {
        // 유효기간이 1달 이내로 남았다면 리프레시 대상
        Date datePlusOneMonth = dateFactory.addFromNow(Calendar.MONTH, 1);
        return ! jwtValidator.validateTokenBefore(inkRefreshToken.getInkRefreshToken(), datePlusOneMonth);
    }
}
