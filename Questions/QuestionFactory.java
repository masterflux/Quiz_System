package uk.ac.ncl.vib.Questions;

import java.util.*;

/**
 * @Description: QuestionFactory class sorts the question type based on the input string provided
 * @author Vibhav
 * @date 03/10/2024
 */

public abstract class QuestionFactory implements Question {

    private final String questionTxt;
    public static final String FREE_QUES = "free";
    public static final String MCQ = "multi";

    QuestionFactory(String questionTxt){
        this.questionTxt = questionTxt;
    }


    /**
     * @Description: static Question.QuestionFactory method to get instance of question
     * @param inputString contains a string in the form 'Questiontext|type|answers' for FreeResponseQuestions
     *        inputString contains a string in the form 'Questiontext|type|optionslist|answers' for MultipleChoiceQuestions
     * @return uk.ac.ncl.vib.coursework.Questions
     * @throws IllegalArgumentException - if proper input string format is not entered
     * @throws IllegalArgumentException - for multiple choice question if the input string has more than 4 parts
     * @throws IllegalArgumentException - if invalid question type is provided
     */

    public static Question getInstance(String inputString) {
        String [] parts = inputString.split("\\|");

        if (parts.length < 3) {
            throw  new IllegalArgumentException("The Input String is invalid. Expected String : 'Questiontext|type|answers' ");
        }

        String questionText = parts[0].trim();  // contains question text
        String questionType = parts[1].trim().toLowerCase(); // contains the type of question
        String correctAnswer = "";
        Question tempQues =null;

        if(questionType.contains(FREE_QUES)){
            correctAnswer = parts[2].trim(); // contains answer
            tempQues = new FreeResponseQuestion(questionText, correctAnswer);

        }else if(questionType.contains(MCQ)) {
                if(parts.length < 4){
                    throw new IllegalArgumentException("Parts length should be 4, Expected -> questiontext|type|options|answers");
                }
            String options = parts[2].trim(); //contains options
            correctAnswer = parts[3].trim(); // contains answer

            List<String> optionList = Arrays.asList(options.split(",")); // storing options in list after splitting
            Set<String> correctAnswerSet = new HashSet<>(Arrays.asList(correctAnswer.split(","))); // storing answers in list after splitting

            tempQues = new MultipleChoiceQuestion(questionText, optionList, correctAnswerSet);


        }else{
            throw new IllegalArgumentException("Invalid Question.Question Type Select");
        }

        return tempQues;

    }


    /**
     * @Description: Gets the question context
     * @return String
     **/
    public String getQuestionTxt() {
        return this.questionTxt;
    }
}