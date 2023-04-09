package net.ink.core.question.service;

import net.ink.core.member.entity.Member;
import net.ink.core.question.entity.Question;

public interface TodayQuestionSelectionService {
    Question reselectTodayQuestion(Member member);
}
