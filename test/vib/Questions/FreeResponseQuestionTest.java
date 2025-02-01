package uk.ac.ncl.vib.test.vib.Questions;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.vib.Questions.FreeResponseQuestion;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: Testing class FreeReponseQuestion
 * @author Vibhav
 * @date 11/10/2024
 **/
class FreeResponseQuestionTest {

    /**
     * @Description: checking if the answered questions are correct while ignoring whitespaces and case sensitivity
     **/

    @Test
    public void testCorrectAnswer() {
        // Arrange
        String questionTxt = "What is the capital of France?";
        String correctAnswer = "Paris";
        FreeResponseQuestion question = new FreeResponseQuestion(questionTxt, correctAnswer);

        // Act & Assert
        assertTrue(question.checkAnswer("Paris"), "Answer should be correct.");
        assertTrue(question.checkAnswer("  PARIS  "), "Answer should be correct ignoring case and whitespace.");
        assertTrue(question.checkAnswer("paris "), "Answer should be correct ignoring case and whitespace.");
    }

    /**
     * @Description: checking if the answered questions are incorrect
     **/
    @Test
    public void testIncorrectAnswer() {
        // Arrange
        String questionTxt = "What is the capital of France?";
        String correctAnswer = "Paris";
        FreeResponseQuestion question = new FreeResponseQuestion(questionTxt, correctAnswer);

        // Act & Assert
        assertFalse(question.checkAnswer("London"), "Answer should be incorrect.");
        assertTrue(question.checkAnswer("paris "), "Answer should fail if correctAnswer is not transformed correctly.");
    }

    /**
     * @Description: testing if empty answer throws an error
     **/
    @Test
    public void testNullAnswer() {
        // Arrange
        String questionTxt = "What is the capital of France?";
        String correctAnswer = "Paris";
        FreeResponseQuestion question = new FreeResponseQuestion(questionTxt, correctAnswer);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            question.checkAnswer(null);
        }, "Should throw IllegalArgumentException for null input.");
    }

    /**
     * @Description: testing Overridden toString Method
     **/
    @Test
    public void testToString() {
        // Arrange
        String questionTxt = "What is the capital of France?";
        FreeResponseQuestion question = new FreeResponseQuestion(questionTxt, "Paris");

        // Act
        String expected = "FreeResponseQuestion : What is the capital of France?";
        String actual = question.toString();

        // Assert
        assertEquals(expected, actual, "method should return properly formatted string.");
    }
}