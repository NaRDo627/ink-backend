package net.ink.core.badge.service;

public interface BadgeAccomplishedService {
    boolean createInk3Days(Long memberId);
    boolean createCelebrityInk(Long replyId);
    boolean createAcademicInk(Long memberId);
    boolean createInkSet(Long memberId);
    boolean isAlreadyCheckedCelebrityInk(Long memberId);
}
