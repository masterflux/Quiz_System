package uk.ac.ncl.vib.test.vib.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.vib.Questions.FreeResponseQuestion;
import uk.ac.ncl.vib.Questions.MultipleChoiceQuestion;
import uk.ac.ncl.vib.Questions.Question;
import uk.ac.ncl.vib.Questions.QuestionPool;
import uk.ac.ncl.vib.Student.Student;
import uk.ac.ncl.vib.Student.StudentStatistics;
import uk.ac.ncl.vib.Quiz.QuizManager;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: QuizManagerTest contains all the testing scenarios for QuizManager class
 * @author Vibhav
 * @date 11/10/2024
 **/

class QuizManagerTest {
    private final Calendar cal =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
    private QuizManager quizManager;
    private Student student;
    private List<Question> questionList;
    private List<String> answers;

    @BeforeEach
    /**
     * @Description: Creating a setUp to preload some required variables and objects
     **/
    public void setUp() {
        quizManager = new QuizManager();
        cal.set(1999,06, 04);
        Date birth = cal.getTime();
        student = new Student("Vibhav", "Mahajan",birth);


        Question question1 = QuestionPool.getQuestions().get(0); // using index so questions dont shuffle for testing
        Question question2 = QuestionPool.getQuestions().get(1);
        Question question3 = QuestionPool.getQuestions().get(2);
        Question question4 = QuestionPool.getQuestions().get(6);

        questionList = new ArrayList<>();
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        questionList.add(question4);

        answers = new ArrayList<>();
        answers.add("Delhi");
        answers.add("2,3,5");
        answers.add("Jupiter");
        answers.add("Java,Python");
    }

    /**
     * @Description: Test for generate quiz and this test should generate 4 random questions from question pool
     **/
    @Test
    public void testGenerateQuiz() {

        int noOfQuestions = 4;
        List<Question> generatedQuiz = quizManager.generateQuiz(noOfQuestions);
        assertEquals(noOfQuestions, generatedQuiz.size(),"Output should contain 4 generated questions");
        System.out.println(generatedQuiz);
    }
    /**
     * @Description: Test for generate quiz and an exceptions should occur when entering questions more than 15
     **/
    @Test //testing to check if exceptions occurs when entering 15
    public void testGenerateQuizMoreThanMaxQuestions() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            quizManager.generateQuiz(16);
        });
        assertEquals("Please select less than 15 Questions", exception.getMessage());
    }

    /**
     * @Description: Test the takeQuiz method with proper answers
     **/

    @Test
    public void testTakeQuiz() {

        double score = quizManager.takeQuiz(student, questionList, answers);

        assertEquals(1.0, score);  // expecting 100% score since answers are correct
        StudentStatistics statistics = quizManager.getStudentStatistics(student);
        assertEquals(1, statistics.getRegularQuizAttempts());
        //assertTrue(statistics.passOrFail(score));
    }

    /**
     * @Description: Test the takeQuiz method by providing 2 incorrect answers
     **/
    @Test
    public void testTakeQuizWithIncorrectAnswers() {

        answers.clear();
        answers.add("Delhi");
        answers.add("2,3");
        answers.add("Mars");
        answers.add("Java");

        double score = quizManager.takeQuiz(student, questionList, answers);

        assertEquals(0.3, score);
        StudentStatistics statistics = quizManager.getStudentStatistics(student);
        assertEquals(1, statistics.getRegularQuizAttempts());

    }
    /**
     * @Description: For testing adding the entire question pool into list of unseen or incorrect question
     * and creating new question which are not seen by student
     **/
    @Test
    public void testReviseQuiz() {
        cal.set(1999,06, 04);
        Date birth = cal.getTime();

        // creating a student and storing it in hashmap so all the unseen or incorrect questions will be related to this student
        StudentStatistics statistics = new StudentStatistics("Vibhav", "Mahajan", birth);
        quizManager.setStudentInMap(student,statistics);

        // initiating list of unseenquestion
        List<Question> unseenOrIncorrectQuestions = new ArrayList<>();
        unseenOrIncorrectQuestions = QuestionPool.getQuestions();
        statistics.setUnseenOrIncorrectQues(unseenOrIncorrectQuestions);

        //Creating a pool of question
        List<Question> questionList = new ArrayList<>();
        Question question1 = new FreeResponseQuestion("What is the highest mountain in the world?","Mount Everest");
        Question question2 = new FreeResponseQuestion("What is the square root of 16?","4");
        Question question3 = new FreeResponseQuestion("What is the smallest prime number?","2"); // this questions exists in question pool
        Question question4 = new MultipleChoiceQuestion("Which of these are fruits?",List.of("Apple","Carrot","Orange"),Set.of("Apple","Orange"));
        Question question5 = new MultipleChoiceQuestion("What is the longest river in the world?",List.of("Nile", "Amazon", "Yangtze"),Set.of("Nile")); // this questions exists in question pool

        answers.clear();
        answers.add("Mount Everest");
        answers.add("4");
        answers.add("2");
        answers.add("Apple,Orange");
        answers.add("Nile");

        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        questionList.add(question4);
        questionList.add(question5);


        List<Question> reviseQues =  quizManager.revise(student,5);
        quizManager.takeRevisionQuiz(student, questionList, answers);

        //checking the total question in question pool
        List<Question> incorrectQuestions = statistics.getUnseenOrIncorrectQues();
        assertEquals(18, incorrectQuestions.size());

        //Should generate 5 questions from unseenpool
        List<Question> reviseQuestions = quizManager.revise(student, 5);
        assertEquals(5, reviseQuestions.size());
    }

    /**
     * @Description: test to check if no of questions generated in revision quiz are no more than 15
     **/
    @Test
    public void testReviseQuizWithMoreThanMaxQuestions() {

        quizManager.takeQuiz(student, questionList, answers);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            quizManager.revise(student, 16);  // more than 15 questions
        });

        assertEquals("Please select less than 15 Questions", exception.getMessage());
    }
    /**
     * @Description:  Test to check revisionQuiz method with correct answers
     **/
    @Test // Test to check revision quiz logic
    public void testTakeRevisionQuiz() {

        cal.set(1999,06, 04);
        Date birth = cal.getTime();

        // creating a student and storing it in hashmap so all the unseen or incorrect questions will be related to this student
        StudentStatistics statistics = new StudentStatistics("Vibhav", "Mahajan", birth);
        quizManager.setStudentInMap(student,statistics);

        // initiating list of unseenquestion
        List<Question> unseenOrIncorrectQuestions = new ArrayList<>();
        unseenOrIncorrectQuestions.addAll(QuestionPool.getQuestions());

        Question ques1 = unseenOrIncorrectQuestions.get(8);
        Question ques2 = unseenOrIncorrectQuestions.get(9);
        Question ques3 = unseenOrIncorrectQuestions.get(10);
        Question ques4 = unseenOrIncorrectQuestions.get(11);

        List<Question> reviseQues = new ArrayList<>();
        reviseQues.add(ques1);
        reviseQues.add(ques2);
        reviseQues.add(ques3);
        reviseQues.add(ques4);

        answers.clear();
        answers.add("Diamond");
        answers.add("Saturn");
        answers.add("Dollar");
        answers.add("Berlin");

        double score = quizManager.takeRevisionQuiz(student, reviseQues, answers);
        assertEquals(1.0, score);  //
         statistics = quizManager.getStudentStatistics(student);
         assertEquals(1, statistics.getRevisionQuizAttempts());
    }

    /**
     * @Description:  Test to check getStudentStatistics to generate stats for the student
     **/
    @Test
    public void testGetStudentStatistics() {
        cal.set(1999,06, 04);
        Date birth = cal.getTime();
       // StudentStatistics statistics =
        StudentStatistics statistics = new StudentStatistics("Vibhav", "Mahajan", birth);
        quizManager.setStudentInMap(student,statistics);
        StudentStatistics getStatistics = quizManager.getStudentStatistics(student);

        quizManager.takeQuiz(student, questionList, answers);

        assertNotNull(statistics);
    }
}