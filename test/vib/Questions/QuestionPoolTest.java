package uk.ac.ncl.vib.test.vib.Questions;

import org.junit.jupiter.api.Test;
import uk.ac.ncl.vib.Questions.Question;
import uk.ac.ncl.vib.Questions.QuestionPool;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: Testing class QuestionPool
 * @author Vibhav
 * @date 11/10/2024
 **/
class QuestionPoolTest {

    /**
     * @Description: checking getQuestionMethod to return all the questions in the pool
     **/
    @Test
    void testGetQuestionsReturnsAllQuestions() {
        // Get the questions from the question pool
        List<Question> questions = QuestionPool.getQuestions();

        // Check if the correct number of questions are retrieved
        assertEquals(18, questions.size(), "The question pool should contain 18 questions.");

        // Check if specific questions exist in the question pool
        assertTrue(questions.stream().anyMatch(q -> q.getQuestionTxt().equals("What is the capital of India?")),
                "Expected question is missing from the pool.");
        assertTrue(questions.stream().anyMatch(q -> q.getQuestionTxt().equals("What is the currency of Japan?")),
                "Expected question is missing from the pool.");
    }
}