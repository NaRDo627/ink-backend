package net.ink.core.question.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import net.ink.core.annotation.InkDataTest;
import net.ink.core.member.repository.MemberRepository;
import net.ink.core.question.entity.Question;
import net.ink.core.question.entity.WordHint;
import net.ink.core.reply.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@InkDataTest
@DatabaseSetup({
        "classpath:dbunit/entity/member.xml",
        "classpath:dbunit/entity/question.xml",
        "classpath:dbunit/entity/word_hint.xml",
        "classpath:dbunit/entity/reply.xml"
})
public class QuestionRepositoryTest {

    private static final Long MEMBER_ID = 1L;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WordHintRepository wordHintRepository;

    @Test
    public void 전체_질문_목록_조회(){
        List<Question> questions = questionRepository.findAll();

        assertEquals(3, questions.size());

        Question question = questions.get(0);

        assertEquals(MEMBER_ID, question.getAuthor().getMemberId());
        assertEquals("what is your favorite color?", question.getContent());
        assertEquals("당신이 가장 좋아하는 색은 무엇인가요?", question.getKoContent());
    }

    @Test
    public void 멤버로_질문_목록_조회(){
        List<Question> questions = questionRepository.findAllByAuthorMemberIdOrderByRegDateDesc(MEMBER_ID);

        assertEquals(2, questions.size());

        assertEquals(MEMBER_ID, questions.get(0).getAuthor().getMemberId());
        assertEquals("How did you feel when you first heard the ink?", questions.get(0).getContent());
        assertEquals("잉크을 처음 들었을 때 어떤 느낌이 들었나요?", questions.get(0).getKoContent());

        assertEquals(MEMBER_ID, questions.get(1).getAuthor().getMemberId());
        assertEquals("what is your favorite color?", questions.get(1).getContent());
        assertEquals("당신이 가장 좋아하는 색은 무엇인가요?", questions.get(1).getKoContent());
    }

    @Test
    public void 이미_답변한_질문인지_테스트(){
        long alreadyRepliedMemberId = 1;
        long notRepliedMemberId = 3;

        boolean isExist = questionRepository.existsByQuestionIdAndAuthorMemberId(1L, alreadyRepliedMemberId);
        boolean isExist2 = questionRepository.existsByQuestionIdAndAuthorMemberId(1L, notRepliedMemberId);

        assertTrue(isExist);
        assertFalse(isExist2);
    }

    @Test
    public void 질문_목록_인기순_페이징_조회_테스트(){
        int page = 0;
        int size = 3;
        int id = 1;

        Page<Question> questionPage = questionRepository.findAllOrderByRepliesSizeDesc(PageRequest.of(page, size));
        List<Question> questionList = questionPage.getContent();

        assertEquals(3, questionPage.getNumberOfElements());
        assertEquals(3, questionList.size());

        for(Question question : questionList){
            assertEquals(id++, question.getQuestionId());
        }
    }

    @Test
    public void 질문_목록_최신순_페이징_조회_테스트(){
        int page = 0;
        int size = 3;
        int id = 3;

        Page<Question> questionPage = questionRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDate")));
        List<Question> questionList = questionPage.getContent();

        assertEquals(3, questionPage.getNumberOfElements());
        assertEquals(3, questionList.size());

        for(Question question : questionList){
            assertEquals(id--, question.getQuestionId());
        }
    }

    @Test
    public void 연쇄적으로_삭제되는지_테스트() {
        // Prepare data
        Long questionId = 1L;

        // Check if question, word hints, and replies exist before deletion
        Optional<Question> questionBeforeDeletion = questionRepository.findById(questionId);
        assertTrue(questionBeforeDeletion.isPresent());
        assertFalse(questionBeforeDeletion.get().getWordHints().isEmpty());
        assertFalse(questionBeforeDeletion.get().getReplies().isEmpty());

        // Delete the question
        questionRepository.deleteById(questionId);
        questionRepository.flush();

        // Check if question is deleted
        Optional<Question> questionAfterDeletion = questionRepository.findById(questionId);
        assertFalse(questionAfterDeletion.isPresent());

        Optional<WordHint> wordHint = wordHintRepository.findById(1L);
        assertFalse(wordHint.isPresent());
    }

}
