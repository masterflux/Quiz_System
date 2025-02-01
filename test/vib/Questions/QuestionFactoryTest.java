package uk.ac.ncl.vib.test.vib.Questions;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.vib.Questions.FreeResponseQuestion;
import uk.ac.ncl.vib.Questions.MultipleChoiceQuestion;
import uk.ac.ncl.vib.Questions.Question;
import uk.ac.ncl.vib.Questions.QuestionFactory;

import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: Testing class QuestionFactory
 * @author Vibhav
 * @date 11/10/2024
 **/
class QuestionFactoryTest {
    /**
     * @Description: checking getInstance method to create new object of FreeReponseQuestion
     **/
    @Test
    public void testGetInstanceFreeResponseQuestion() {

        String input = "What is the capital of France?|free|Paris";
        Question question = QuestionFactory.getInstance(input);

        assertTrue(question instanceof FreeResponseQuestion, "The question should be a FreeResponseQuestion.");
        assertEquals("What is the capital of France?", question.getQuestionTxt(), "The question text should be correct.");
        assertTrue(question.checkAnswer("Paris"), "The correct answer should be 'Paris'.");
    }
    /**
     * @Description: checking getInstance method to create new object of MultipleChoiceQuestion
     **/
    @Test
    public void testGetInstanceMultipleChoiceQuestion() {

        String input = "Which of the following are programming languages?|multi|Java,C++,Python|Java,Python";
        Question question = QuestionFactory.getInstance(input);

        assertTrue(question instanceof MultipleChoiceQuestion, "The question should be a MultipleChoiceQuestion.");
        assertEquals("Which of the following are programming languages?", question.getQuestionTxt(), "The question text should be correct.");

        List<String> expectedOptions = List.of("Java", "C++", "Python");
        Set<String> expectedAnswers = Set.of("java", "python");

        MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) question;
        assertEquals(expectedOptions, mcq.getOptionList(), "The options list should be correct.");
        assertEquals(expectedAnswers, mcq.getCorrectAnswerSet(), "The correct answer set should be correct.");

        assertTrue(question.checkAnswer("Java,Python"), "The correct answer should be 'Java,Python'.");
    }

    /**
     * @Description: checking getInstance method by providing incorrect Question type
     **/
    @Test
    public void testGetInstanceInvalidQuestionType() {

        String input = "What is 2+2?|random|4";
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.getInstance(input);
        }, "An exception should be thrown for an invalid question type.");
    }

    /**
     * @Description: checking getInstance method by providing format for FreeResponseQuestion
     **/
    @Test
    public void testGetInstanceInvalidFreeResponseFormat() {

        String input = "What is the capital of France?|multi";
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.getInstance(input);
        }, "An exception should be thrown for an invalid FreeResponseQuestion format.");
    }

    /**
     * @Description: checking getInstance method by providing format for MultipleChoiceQuestion
     **/
    @Test
    public void testGetInstanceInvalidMultipleChoiceFormat() {

        String input = "Which of the following are programming languages?|xyz|Java,C++";
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.getInstance(input);
        }, "An exception should be thrown for an invalid MultipleChoiceQuestion format.");
    }

    /**
     * @Description: checking getInstance method by providing incomplete inputString
     **/
    @Test
    public void testGetInstanceIcompleteString() {

        String input = "What is the capital of France?|free";
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.getInstance(input);
        }, "An exception should be thrown when the input has fewer than 3 parts.");
    }

    /**
     * @Description: checking getInstance method by providing extra Parts in  inputString
     **/
    @Test
    public void testGetInstanceExcessStringParts() {

        String input = "What is the capital of France?|free|Paris|extraPart";
        Question question = QuestionFactory.getInstance(input);
        assertTrue(question instanceof FreeResponseQuestion, "The question should be a FreeResponseQuestion.");
        assertEquals("What is the capital of France?", question.getQuestionTxt(), "The question text should be correct.");
    }

    /**
     * @Description: checking getQuestiontxt
     **/
    @Test
    public void testGetQuestionTxt() {

        String input = "What is the capital of France?|free|Paris";
        Question question = QuestionFactory.getInstance(input);
        assertEquals("What is the capital of France?", question.getQuestionTxt(), "The question text should be correct.");
    }
}