package uk.ac.ncl.vib.Questions;

/**
 * @Description: FreeResponseQuestion class to implement logic for FreeResponse type Questions
 * @author Vibhav
 * @date 04/10/2024
 **/

public class FreeResponseQuestion extends QuestionFactory {

    // making these references immutable
    private final String correctAnswer;

    public FreeResponseQuestion(String questionTxt, String correctAnswer) {
        super(questionTxt);

        // using regex to remove whitespaces and trimming the correctAnswer and converting everything to lowercase
        this.correctAnswer = correctAnswer.trim().replaceAll("\\s+", "").toLowerCase();
    }

    /**
     * @Description: checks if the given answer is correct by doing transformations
     * @param  selectedOption - answer given by student
     * @return Boolean
     * @throws IllegalArgumentException - if proper option is not selected
     **/
    @Override
    public boolean checkAnswer(String selectedOption) {
        //checking if options == null
        if(selectedOption == null ){
            throw new IllegalArgumentException("**** Proper Option not selected ****");
        }
        // using regex to remove whitespaces and triming the correctAnswer and converting everything to lowercase
        String FinalAnswer = selectedOption.trim().replaceAll("\\s+", "").toLowerCase();
        return this.correctAnswer.equals(FinalAnswer);
    }

    /**
     * @Description: Overriding toString to make string readable
     * @return String
     **/

    @Override
    public String toString(){
        return "FreeResponseQuestion :"+" "+getQuestionTxt();
    }

    /**
     * @Description: Overriding equals
     * @param o - object which is to be checked
     * @return boolean
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FreeResponseQuestion that = (FreeResponseQuestion) o;

        return this.getQuestionTxt().equals(that.getQuestionTxt()) &&
                this.correctAnswer.equals(that.correctAnswer);
    }
    /**
     * @Description: Overriding hashcode
     * @return int
     **/

    @Override
    public int hashCode() {
       int hash = 7;
       hash = 31 * hash + (this.getQuestionTxt() != null ? this.getQuestionTxt().hashCode() : 0)
               +(this.correctAnswer != null ? this.correctAnswer.hashCode() : 0);
        return 31*hash;
    }
}
