package uk.ac.ncl.vib.Quiz;

import uk.ac.ncl.vib.Questions.FreeResponseQuestion;
import uk.ac.ncl.vib.Questions.MultipleChoiceQuestion;
import uk.ac.ncl.vib.Questions.Question;
import uk.ac.ncl.vib.Questions.QuestionPool;
import uk.ac.ncl.vib.Student.Student;
import uk.ac.ncl.vib.Student.StudentStatistics;
import java.util.*;

/**
 * @Description: QuizManager class take care of multiple functions related to quiz, Questions and student statistics
 * @author Vibhav
 * @date 09/10/2024
 */

public final class QuizManager {
  private final Map<Student, StudentStatistics> statisticsMap = new HashMap<>();
  private final static int MAX_QUIZ_ATTEMPTS = 2;
  private static final int MAX_NO_OF_QUESTIONS = 15;

    /**
     * @Description: Generates Questions for Regular Quiz depending upon the input
     * @param noOfQuestions - Enter the number of questions to be generated for the quiz should be less than  or equal to 15
     * @return List<Question> - Returns the list of question by randomly picking unique questions from question pool based on the input
     * @throws IllegalArgumentException - throws an error if number of questions are more than 15
     */
  public List<Question> generateQuiz(int noOfQuestions){

      if(noOfQuestions >= MAX_NO_OF_QUESTIONS){
          throw new IllegalArgumentException("Please select less than 15 Questions");
      }

      List<Question> questionBank = new ArrayList<>(QuestionPool.getQuestions());
      Collections.shuffle(questionBank);
      List<Question> selectedQuestions = new ArrayList<>();

      for(Question question : questionBank){
          if((question instanceof FreeResponseQuestion || question instanceof MultipleChoiceQuestion) && (selectedQuestions.size() < noOfQuestions)){
              selectedQuestions.add(question);
          }
      }

      return selectedQuestions;

  }
    /**
     * @Description: this method conducts the quiz by checking their answers against list of question
     * and returns scores also keep tracks of incorrect question or unseen questions also updates the stats for the student accordingly
     * @param student - Keeps the track of Student if the student details exists or creates a new student
     * @param questions - contains list of question generated in previous method (generateQuiz)
     * @param answers - contains list of anwers provided by the student
     * @return double - returns the score
     * @throws IllegalArgumentException - if number of question are more than 15 or if max quiz attempts are more than 2
     */
  public double takeQuiz(Student student, List<Question> questions, List<String> answers){
      StudentStatistics studentStatistics = statisticsMap.get(student);

      if(studentStatistics == null){
          studentStatistics = new StudentStatistics(student.getFirstName(), student.getSurName(), student.getDateOfBirth());
      }
      statisticsMap.put(student, studentStatistics);

      int regularAttempt = studentStatistics.getRegularQuizAttempts();
      if(regularAttempt > MAX_QUIZ_ATTEMPTS){
          throw new IllegalArgumentException("You have exceeded the limit to attempt Regular Quiz");
      }
      List<Question> incorrectRespondedQuestions = new ArrayList<>();

      int correctAnswersCounter=0;

      for(int i=0; i<questions.size(); i++){
          Question question = questions.get(i);
          String answer = answers.get(i);

          if(question.checkAnswer(answer)){
              correctAnswersCounter++;
          }else {
              incorrectRespondedQuestions.add(question);
          }
      }
      double score = (double) correctAnswersCounter / questions.size();
      score = Math.round(score * 10.0) / 10.0;

      studentStatistics.trackQuizAttempts(score,false);
      studentStatistics.setUnseenOrIncorrectQues(incorrectRespondedQuestions);
      studentStatistics.passOrFail(score,"RegularQuiz");

      return score;
  }
    /**
     * @Description: This method is keeps track of student stats and generates question based on the input provided
     * it fetches the questions from unseen and incorrect questions attempted by the student in the regular quiz before
     * it also ensures that no questions are repeated and generates a list of question for revesion quiz
     * @param student - Keeps the track of Student
     * @param noOfQuestions - number of questions that needs to generated but should be less than or equal to 15
     * @return List - Returns the list of question generated based on the input
     * @throws IllegalArgumentException - if number of question are more than 15 or if max quiz attempts are more than 2
     */
  public List<Question> revise(Student student, int noOfQuestions){

      if(noOfQuestions >= MAX_NO_OF_QUESTIONS){
          throw new IllegalArgumentException("Please select less than 15 Questions");
      }

      StudentStatistics studentStatistics = statisticsMap.get(student);

     int ReviseAttempts = studentStatistics.getRevisionQuizAttempts();
     if(ReviseAttempts > MAX_QUIZ_ATTEMPTS){
         throw new IllegalArgumentException("You have exceeded the limit to attempt revision Quiz");
     }

      if(studentStatistics == null){
          studentStatistics = new StudentStatistics(student.getFirstName(), student.getSurName(), student.getDateOfBirth());
      }
      statisticsMap.put(student, studentStatistics);

      List<Question> incorrectRespondedQuestions = studentStatistics.getUnseenOrIncorrectQues();
      Set<Question> reviseQuestionSet = new HashSet<>(incorrectRespondedQuestions);

      List<Question> reviseQuestionList = new ArrayList<>(reviseQuestionSet);
      Collections.shuffle(reviseQuestionList);

      return reviseQuestionList.subList(0, Math.min(noOfQuestions, reviseQuestionList.size()));
  }

    /**
     * @Description: this method conducts the revision quiz by using the list of question crated in previous method (revise)
     * checking their answers against list of question and returns scores also keep tracks of incorrect question or
     * unseen questions also updates the stats for the student accordingly
     * @param student - Keeps the track of Student
     * @param questions - contains list of question generated in previous method (revise)
     * @param answers - contains list of answers provided by the student
     * @return double  - returns the score
     */
  public double takeRevisionQuiz(Student student, List<Question> questions, List<String> answers){
      StudentStatistics studentStatistics = statisticsMap.get(student);

      if(studentStatistics == null){
          studentStatistics = new StudentStatistics(student.getFirstName(), student.getSurName(), student.getDateOfBirth());
      }
      statisticsMap.put(student, studentStatistics);

    int correctAnswersCounter=0;

    for(int i=0; i<questions.size(); i++){
        Question question = questions.get(i);
        String answer = answers.get(i);

        if(question.checkAnswer(answer)){
            correctAnswersCounter++;
        }
    }

    double score = (double) correctAnswersCounter / questions.size();
      score = Math.round(score * 10.0) / 10.0;
    studentStatistics.trackQuizAttempts(score,true);
    studentStatistics.passOrFail(score,"RevisionQuiz");

    return score;
  }

    /**
     * @Description: This method tracks all the stats for that particular student
     * @param student - to check if student exists and if the student exists fetch their details
     * @return StudentStatistics  - returns the student stats
     */

  public StudentStatistics getStudentStatistics(Student student){
    StudentStatistics studentStatistics = statisticsMap.get(student);

    System.out.println(student+" "+"Regular Quiz attempts are :"+studentStatistics.getRegularQuizAttempts());
    System.out.println(student+" "+"Revision Quiz attempts are :"+studentStatistics.getRevisionQuizAttempts());
    System.out.println(student+" "+"Regular Quiz score is  :"+studentStatistics.getRegularQuizScore());
    System.out.println(student+" "+"Revision Quiz score is  :"+studentStatistics.getRevisionQuizScore());
    System.out.println(student+" "+"has "+studentStatistics.getFinalDecision());

    return studentStatistics;
  }

    /**
     * @Description: This method is used to store the student in a map
     * @param student - to store the student
     * @param studentStatistics - to store the student statistics
     * @throws IllegalArgumentException - if input student is empty
     */

    public void setStudentInMap(Student student, StudentStatistics studentStatistics) {
        if (student == null || studentStatistics == null) {
            throw new IllegalArgumentException("Student and StudentStatistics cannot be null");
        }
        statisticsMap.put(student, studentStatistics);
    }

    /**
     * @Description: This method is used to store the student in a map
     * @param student - Student details to check if they exist in the maps
     * @return StudentStatistics - returns student stats if they exists or else returns null
     * @throws IllegalArgumentException - if input student is empty
     */
    public StudentStatistics checkStudentExist(Student student){
        if(student == null){
            throw new IllegalArgumentException("Student cannot be null");
        }
        if(statisticsMap.containsKey(student)){
            return statisticsMap.get(student);
        }
        return null;
    }
}

