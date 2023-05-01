package net.ink.scheduler.batch.push.service;

import lombok.RequiredArgsConstructor;
import net.ink.core.member.entity.FcmToken;
import net.ink.core.member.entity.Member;
import net.ink.core.question.service.TodayQuestionService;
import net.ink.core.reply.service.ReplyService;
import net.ink.push.dto.NotificationRequest;
import net.ink.push.service.FcmService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmDailyPushService {
    private static final String DAILY_PUSH_TITLE = "오늘의 영어 질문";
    private static final String DAILY_PUSH_CLICK_ACTION = "MAIN";
    private static final String DAILY_PUSH_CHANNEL_ID = "DAILY_CHANNEL";

    private final FcmService fcmService;
    private final TodayQuestionService todayQuestionService;
    private final ReplyService replyService;

    public void push(FcmToken fcmToken) {
        Member member = fcmToken.getMember();
        if (member == null || ! member.getMemberSetting().isDailyPushActive()) {
            return;
        }

        if (replyService.isAlreadyRepliedToday(member.getMemberId())) {
            return;
        }

        fcmService.send(NotificationRequest.builder()
                .token(fcmToken.getToken())
                .title(DAILY_PUSH_TITLE)
                .message(todayQuestionService.getTodayQuestionByMemberId(member.getMemberId()).getContent())
                .clickAction(DAILY_PUSH_CLICK_ACTION)
                .channelId(DAILY_PUSH_CHANNEL_ID)
                .build());
    }
}
