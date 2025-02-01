package uk.ac.ncl.vib.Questions;
/**
 * @Description: Question interface
 * @author Vibhav
 * @date 02/10/2024
 */
public interface Question {

    /**
	 * @Description: get the question.
	 * @return question as string
	 * @author Vibhav
	 * @date 02/10/2024
	 **/

	// to retrieve question text
    String getQuestionTxt();

    /**
	 * @Description: check if the given answer is correct or not.
	 * @param selectedOption - Selected answer
	 * @return boolean
	 * @author Vibhav
	 * @date 02/10/2024
	 **/
	// to check given answer
    boolean checkAnswer(String selectedOption);
}