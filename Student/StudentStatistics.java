package uk.ac.ncl.vib.Student;

import uk.ac.ncl.vib.Questions.Question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: Stores all the data related to quizes student has taken
 * @author Vibhav
 * @date 09/10/2024
 */

public class StudentStatistics extends Student  {

    private final List<Double> trackRegularQuizScore = new ArrayList<>();
    private final List<Double> trackRevisionQuizScore = new ArrayList<>();
    private final List<Question> unseenOrIncorrectQues = new ArrayList<>();
    private String FINAL_DECISION = "Scores To Be Determined";
    private  String HAS_PASSED = "Passed";
    private  String HAS_FAILED = "Failed";
    private int regularQuizAttempts = 0;
    private int revisionQuizAttempts =0;


    public StudentStatistics(String firstName, String surName, Date dateOfBirth) {
        super(firstName, surName, dateOfBirth);
    }


    /**
     * @Description: tracks the quizes attempted by the student
     * @param quizScore - the quiz score
     * @param isRevision - boolean to check if the quiz is revision or regular
     **/
    public void trackQuizAttempts(double quizScore, boolean isRevision) {

        if(isRevision == true){
            revisionQuizAttempts++;
            trackRevisionQuizScore.add(quizScore);
        }else {
            regularQuizAttempts++;
            trackRegularQuizScore.add(quizScore);
        }
    }

    /**
     * @Description: Checks whether the student has passed or failed the exam based on score
     * @param score - enter the score
     * @param QuizType - enter the type of quiz
     * @return String
     **/

    public String passOrFail(double score, String QuizType){
        //return finalDecision.equals(hasPassed) || finalDecision.equals(hasFailed);
        if(score >= 0.5 && QuizType.equals("RegularQuiz")){
            FINAL_DECISION = HAS_PASSED;
            setFinalDecision("Student Has Passed the Regular Quiz in "+getRegularQuizAttempts()+" "+"attempt");

        }else if(score < 0.5 && QuizType.equals("RegularQuiz") && getRegularQuizAttempts() < 2){
            FINAL_DECISION = HAS_FAILED;
            setFinalDecision("Failed the Regular Quiz but can attempt one last time");
        }

        if(score >= 0.5 && QuizType.equals("RevisionQuiz")){
            FINAL_DECISION = HAS_PASSED;
            setFinalDecision("Student Has Passed the Revision Quiz in "+getRevisionQuizAttempts()+" "+"attempt");

        }else if(score < 0.5 && QuizType.equals("RevisionQuiz") && getRevisionQuizAttempts() < 2){
            FINAL_DECISION = HAS_FAILED;
            setFinalDecision("Failed the Revision Quiz but can attempt one last time");
        }

        return "";
    }

    /**
     * @Description: Returns Regular quiz score
     * @return List
     **/
    public List<Double> getRegularQuizScore() {
        return trackRegularQuizScore;
    }

    /**
     * @Description: Returns Revision quiz score
     * @return List
     **/
    public List<Double> getRevisionQuizScore() {return trackRevisionQuizScore; }
    /**
     * @Description: return regular quiz attempted by student
     * @return int
     **/
    public int getRegularQuizAttempts() {
        return regularQuizAttempts;
    }

    /**
     * @Description: return revision quiz attempted by student
     * @return int
     **/
    public int getRevisionQuizAttempts() {
        return revisionQuizAttempts;
    }

    /**
     * @Description: return unseen and incorrect quesion which student attempted during regular quiz
     * @return List
     **/
    public List<Question> getUnseenOrIncorrectQues() {
        return unseenOrIncorrectQues;
    }

    /**
     * @Description: adds the unseen and incorrect to a list
     * @param unseenOrIncorrectQues - enter the questions which are unseen or incorrect to be added to the pool
     **/
    public void setUnseenOrIncorrectQues(List<Question> unseenOrIncorrectQues) {
        this.unseenOrIncorrectQues.clear();
        this.unseenOrIncorrectQues.addAll(unseenOrIncorrectQues);
    }

    /**
     * @Description: Sets the verdict ( pass or fail)
     * @param verdict - final verdict
     **/
    public void setFinalDecision(String verdict) {
        this.FINAL_DECISION = verdict;
    }

    /**
     * @Description: returns the verdict (pass or fail)
     * @return String
     **/
    public String getFinalDecision() {
        return FINAL_DECISION;
    }

}
