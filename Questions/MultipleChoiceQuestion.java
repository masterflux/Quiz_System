package uk.ac.ncl.vib.Questions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: MultipleChoiceQuestion class to implement logic for Multiple Choice type Questions
 * @author Vibhav
 * @date 04/10/2024
 * @throws IllegalArgumentException - option range must be in 2 - 4
 **/
public class MultipleChoiceQuestion extends QuestionFactory {

    // making these references immutable
    private final List<String> optionsList;
    private final Set<String> correctAnswerSet;

    public MultipleChoiceQuestion(String questionTxt, List<String> optionsList, Set<String> correctAnswerSet) {
        super(questionTxt);
        if(optionsList.size() < 2 || optionsList.size() > 4){
            throw new IllegalArgumentException("**** Options must be in Range of 2 to 4! ****");
        }
        this.optionsList = optionsList;
        this.correctAnswerSet = new HashSet<>();
        for(String answerTemp : correctAnswerSet){
            this.correctAnswerSet.add(answerTemp.toLowerCase());
        }
    }

    /**
     * @Description: Gets the options list
     * @return List
     * **/
    public List<String> getOptionList() {

        return optionsList;
    }

    /**
     * @Description: Gets the Answer set
     * @return Set
     **/
    public Set<String> getCorrectAnswerSet() {

        return correctAnswerSet;
    }

    /**
     * @Description: checks if the given answer is correct by doing transformations
     * @param  selectedOption - answer given by student
     * @return Boolean
     * @throws IllegalArgumentException - if empty answer is provided
     **/
    @Override
    public boolean checkAnswer(String selectedOption) {

        if(selectedOption.isEmpty()){
            throw new IllegalArgumentException("**** Selected Options cannot be empty! ****");
        }

        Set<String> providedAnswers =  new HashSet<>();
        for (String ans : selectedOption.split("\\s*,\\s*")) {
            providedAnswers.add(ans.toLowerCase().trim());
        }
        return providedAnswers.equals(correctAnswerSet);

    }
    /**
     * @Description: Overriding toString to make string readable
     * @return String
     **/
    @Override
    public String toString() {
        return "MultipleChoiceQuestion: " + " " +getQuestionTxt() +" Options : " + " " + optionsList;
    }

    /**
     * @Description: Overriding equals
     * @param o
     * @return boolean
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleChoiceQuestion that = (MultipleChoiceQuestion) o;
        return getQuestionTxt().equals(that.getQuestionTxt()) &&
                optionsList.equals(that.optionsList) &&
                correctAnswerSet.equals(that.correctAnswerSet);
    }

    /**
     * @Description: Overriding hashcode
     * @return int
     **/
    @Override
    public int hashCode() {
       int hash = 7;
       hash = 31 * hash +(getQuestionTxt() != null ? getQuestionTxt().hashCode() : 0)
       +(getOptionList() != null ? getOptionList().hashCode() : 0)
         +(getCorrectAnswerSet() != null ? getCorrectAnswerSet().hashCode() : 0);
       return 31*hash;
    }


}
