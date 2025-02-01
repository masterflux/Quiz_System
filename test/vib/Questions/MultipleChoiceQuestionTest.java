package uk.ac.ncl.vib.test.vib.Questions;

import org.junit.jupiter.api.Test;
import uk.ac.ncl.vib.Questions.MultipleChoiceQuestion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: Testing class MultipleChoiceQuestion
 * @author Vibhav
 * @date 11/10/2024
 **/
class MultipleChoiceQuestionTest {

    /**
     * @Description: checking if the answered questions are correct while ignoring whitespaces and case sensitivity
     **/

    @Test
    public void testCorrectAnswer() {

        String questionTxt = "Which of the following are fruits?";
        List<String> options = Arrays.asList("Apple", "Carrot", "Banana", "Potato");
        Set<String> correctAnswers = new HashSet<>(Arrays.asList("Apple", "Banana"));
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionTxt, options, correctAnswers);

        assertTrue(question.checkAnswer("Apple, Banana"), "Answer should be correct.");
        assertTrue(question.checkAnswer("banana, apple"), "Answer should be correct ignoring case and order.");
        assertTrue(question.checkAnswer("  Apple ,   Banana  "), "Answer should be correct ignoring whitespace.");
    }

    /**
     * @Description: checking the method providing incorrect answers and selecting incomplete answers
     **/
    @Test
    public void testIncorrectAnswer() {

        String questionTxt = "Which of the following are fruits?";
        List<String> options = Arrays.asList("Apple", "Carrot", "Banana", "Potato");
        Set<String> correctAnswers = new HashSet<>(Arrays.asList("Apple", "Banana"));
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionTxt, options, correctAnswers);


        assertFalse(question.checkAnswer("Apple, Potato"), "Answer should be incorrect.");
        assertFalse(question.checkAnswer("Apple"), "Answer should be incorrect if not all correct answers are provided.");
    }

    /**
     * @Description: testing the method by providing empty answer
     **/
    @Test
    public void testEmptyAnswer() {

        String questionTxt = "Which of the following are fruits?";
        List<String> options = Arrays.asList("Apple", "Carrot", "Banana", "Potato");
        Set<String> correctAnswers = new HashSet<>(Arrays.asList("Apple", "Banana"));
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionTxt, options, correctAnswers);


        assertThrows(IllegalArgumentException.class, () -> {
            question.checkAnswer("");
        }, "Should throw IllegalArgumentException for empty answer.");
    }

    /**
     * @Description: testing the method while adding new questions to the question pool options provided for MultipleChoiceQuestion should be in range 2-4
     **/
    @Test
    public void testInvalidOptionsList() {

        String questionTxt = "Which of the following are fruits?";
        List<String> invalidOptions = Arrays.asList("Apple");
        Set<String> correctAnswers = new HashSet<>(Arrays.asList("Apple"));


        assertThrows(IllegalArgumentException.class, () -> {
            new MultipleChoiceQuestion(questionTxt, invalidOptions, correctAnswers);
        }, "Should throw IllegalArgumentException if options list has less than 2 items.");

        List<String> tooManyOptions = Arrays.asList("Apple", "Banana", "Carrot", "Potato", "Orange");
        assertThrows(IllegalArgumentException.class, () -> {
            new MultipleChoiceQuestion(questionTxt, tooManyOptions, correctAnswers);
        }, "Should throw IllegalArgumentException if options list has more than 4 items.");
    }
    /**
     * @Description: testing Overridden toString Method
     **/
    @Test
    public void testToString() {

        String questionTxt = "Which of the following are fruits?";
        List<String> options = Arrays.asList("Apple", "Carrot", "Banana", "Potato");
        Set<String> correctAnswers = new HashSet<>(Arrays.asList("Apple", "Banana"));
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(questionTxt, options, correctAnswers);

        String expected = "MultipleChoiceQuestion:  Which of the following are fruits? Options :  [Apple, Carrot, Banana, Potato]";
        String actual = question.toString();

        assertEquals(expected, actual, "toString() method should return properly formatted string.");
    }
}