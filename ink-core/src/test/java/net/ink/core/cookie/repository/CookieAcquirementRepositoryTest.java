package net.ink.core.cookie.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import net.ink.core.annotation.InkDataTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@InkDataTest
@DatabaseSetup({
        "classpath:dbunit/entity/member.xml",
        "classpath:dbunit/entity/cookie_acquirement.xml"
})
public class CookieAcquirementRepositoryTest {
    @Autowired
    private CookieAcquirementRepository cookieRepository;

    public static final Long MEMBER_ID = 1L;

    @Test
    public void 오늘_쿠키_획득했는지_확인(){
        LocalDate date = LocalDate.of(2020, 10,15);

        assertTrue(cookieRepository.existsByRegDateAndMemberMemberId(date, MEMBER_ID));
    }
}
