package uk.ac.ncl.vib.test.vib.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.vib.Student.StudentStatistics;
import uk.ac.ncl.vib.Questions.Question;
import java.text.ParseException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: StudentStatistics Test contains all the testing scenarios for StudentStatistics Class
 * @author Vibhav
 * @date 12/10/2024
 **/
class StudentStatisticsTest {

    private final Calendar cal =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
    private uk.ac.ncl.vib.Student.StudentStatistics StudentStatistics;
    private Date birth;

    /**
     * @Description: Creating a setUp to preload some values
     **/
    @BeforeEach
    void setUp() throws ParseException {

        cal.set(1999,06, 04);
        birth = cal.getTime();

        // Initialize a student object
        StudentStatistics = new StudentStatistics("John", "Wick", birth);
    }

    /**
     * @Description: test to track regular Quiz Attempts with different scenarios
     **/
    @Test
    void testTrackRegularQuizAttempts() {

        StudentStatistics.trackQuizAttempts(0.7, false); // Passed
        StudentStatistics.trackQuizAttempts(0.4, false); // Failed

        List<Double> scores = StudentStatistics.getRegularQuizScore();
        assertEquals(2, scores.size(), "Regular quiz scores should be tracked correctly");
        assertEquals(0.7, scores.get(0), "First score should be 0.7");
        assertEquals(0.4, scores.get(1), "Second score should be 0.4");
        assertEquals(2, StudentStatistics.getRegularQuizAttempts(), "Regular quiz attempts should be 2");
    }

    /**
     * @Description: test to track revision Quiz Attempts with different scenarios
     **/
    @Test
    void testTrackRevisionQuizAttempts() {

        StudentStatistics.trackQuizAttempts(0.8, true); // Passed
        StudentStatistics.trackQuizAttempts(0.3, true); // Failed

        List<Double> scores = StudentStatistics.getRevisionQuizScore();
        assertEquals(2, scores.size(), "Revision quiz scores should be tracked correctly");
        assertEquals(0.8, scores.get(0), "First revision score should be 0.8");
        assertEquals(0.3, scores.get(1), "Second revision score should be 0.3");
        assertEquals(2, StudentStatistics.getRevisionQuizAttempts(), "Revision quiz attempts should be 2");
    }

    /**
     * @Description: test for passOrFail method for regular quiz with different scenarios
     **/
    @Test
    void testPassOrFailRegularQuiz() {

        String result = StudentStatistics.passOrFail(0.6, "RegularQuiz");
        assertEquals("", result, "Final decision should be determined");


        StudentStatistics.trackQuizAttempts(0.7, false);
        StudentStatistics.getRegularQuizAttempts();
        assertEquals("Student Has Passed the Regular Quiz in 0 attempt", StudentStatistics.getFinalDecision());

        StudentStatistics.passOrFail(0.4, "RegularQuiz");
        assertEquals("Failed the Regular Quiz but can attempt one last time", StudentStatistics.getFinalDecision());

        StudentStatistics.passOrFail(0.3, "RegularQuiz");
        assertEquals("Failed the Regular Quiz but can attempt one last time", StudentStatistics.getFinalDecision());
    }

    /**
     * @Description: test for passOrFail method for revision quiz with different scenarios
     **/
    @Test
    void testPassOrFailRevisionQuiz() {

        String result = StudentStatistics.passOrFail(0.7, "RevisionQuiz");
        assertEquals("", result, "Final decision should be determined");

        assertEquals("Student Has Passed the Revision Quiz in 0 attempt", StudentStatistics.getFinalDecision());

        StudentStatistics.passOrFail(0.4, "RevisionQuiz");
        assertEquals("Failed the Revision Quiz but can attempt one last time", StudentStatistics.getFinalDecision());

        StudentStatistics.passOrFail(0.2, "RevisionQuiz");
        assertEquals("Failed the Revision Quiz but can attempt one last time", StudentStatistics.getFinalDecision());
    }
    /**
     * @Description: test for Finaldecision method  with different scenarios
     **/
    @Test
    void testFinalDecision() {

        StudentStatistics.setFinalDecision("Passed");
        assertEquals("Passed", StudentStatistics.getFinalDecision(), "Final decision should be 'Passed'");

        StudentStatistics.setFinalDecision("Failed");
        assertEquals("Failed", StudentStatistics.getFinalDecision(), "Final decision should be 'Failed'");
    }
    /**
     * @Description: test for getRegularQuizScore method with different scenarios
     **/
    @Test
    void testGetRegularQuizScore() {

        StudentStatistics.trackQuizAttempts(0.8, false);
        StudentStatistics.trackQuizAttempts(0.6, false);

        List<Double> scores = StudentStatistics.getRegularQuizScore();
        assertEquals(2, scores.size(), "There should be 2 regular quiz scores tracked");
        assertEquals(0.8, scores.get(0), 0.01, "First score should be 0.8");
        assertEquals(0.6, scores.get(1), 0.01, "Second score should be 0.6");
    }

    /**
     * @Description: test for getRevisionQuizScore method with different scenarios
     **/
    @Test
    void testGetRevisionQuizScore() {

        StudentStatistics.trackQuizAttempts(0.9, true);
        StudentStatistics.trackQuizAttempts(0.4, true);

        List<Double> scores = StudentStatistics.getRevisionQuizScore();
        assertEquals(2, scores.size(), "There should be 2 revision quiz scores tracked");
        assertEquals(0.9, scores.get(0), 0.01, "First revision score should be 0.9");
        assertEquals(0.4, scores.get(1), 0.01, "Second revision score should be 0.4");
    }

    /**
     * @Description: test for getRegularQuizAttempts method with different scenarios
     **/
    @Test
    void testGetRegularQuizAttempts() {

        StudentStatistics.trackQuizAttempts(0.75, false);
        StudentStatistics.trackQuizAttempts(0.85, false);

        assertEquals(2, StudentStatistics.getRegularQuizAttempts(), "There should be 2 regular quiz attempts");
    }

    /**
     * @Description: test for getRevisionQuizAttempts method with different scenarios
     **/
    @Test
    void testGetRevisionQuizAttempts() {

        StudentStatistics.trackQuizAttempts(0.65, true);
        StudentStatistics.trackQuizAttempts(0.55, true);

        assertEquals(2, StudentStatistics.getRevisionQuizAttempts(), "There should be 2 revision quiz attempts");
    }

    @Test
    void testGetFinalDecision() {

        StudentStatistics.setFinalDecision("Passed");
        assertEquals("Passed", StudentStatistics.getFinalDecision(), "Final decision should be 'Passed'");
    }
}