package net.ink.batch.core.job;

/**
 * 이 인터페이스를 구현하여 빈으로 등록해두면,
 * {@link net.ink.batch.BatchStarter}에 의해 자동으로 등록됨
 *
 * @see net.ink.batch.BatchStarter
 */
public interface JobRequestService {
    JobRequest create();
}
