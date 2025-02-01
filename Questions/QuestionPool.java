package uk.ac.ncl.vib.Questions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: QuestionPool contains list of all available Questions
 * @author Vibhav
 * @date 05/10/2024
 */
public class QuestionPool {
    private static final List<Question> questionPool = new ArrayList<>();
    private static final Set<String> addUniqueQuesSet = new HashSet<>();

    static {
        questionPool.add(QuestionFactory.getInstance("What is the capital of India?|free|Delhi"));
        questionPool.add(QuestionFactory.getInstance("Which of these are prime numbers?|multi|2,3,4,5|2,3,5"));
        questionPool.add(QuestionFactory.getInstance("What is the largest planet in our solar system?|free|Jupiter"));
        questionPool.add(QuestionFactory.getInstance("What is the smallest prime number?|free|2"));
        questionPool.add(QuestionFactory.getInstance("What gas do plants absorb from the atmosphere?|free|Carbon dioxide"));
        questionPool.add(QuestionFactory.getInstance("What is the currency of Japan?|free|Yen"));
        questionPool.add(QuestionFactory.getInstance("Which of these is a programming language?|multi|HTML, CSS, Java, Python|Java,Python"));
        questionPool.add(QuestionFactory.getInstance("What is the longest river in the world?|multi|Nile, Amazon, Yangtze|Nile"));
        questionPool.add(QuestionFactory.getInstance("What is the hardest natural substance on Earth?|multi|Gold, Iron, Diamond|Diamond"));
        questionPool.add(QuestionFactory.getInstance("Which planet is known for its rings?|multi|Earth, Saturn, Jupiter|Saturn"));
        questionPool.add(QuestionFactory.getInstance("What is the currency used in the United States?|free|Dollar"));
        questionPool.add(QuestionFactory.getInstance("What is the capital of Germany?|free|Berlin"));
        questionPool.add(QuestionFactory.getInstance("Which of these is not a programming language?|multi|Python, Java, Excel|Excel"));
        questionPool.add(QuestionFactory.getInstance("Which of these animals are mammals?|multi|Whale, Shark, Elephant|Whale, Elephant"));
        questionPool.add(QuestionFactory.getInstance("Which of these are parts of a plant?|multi|Root, Leaf, Stone|Root, Leaf"));
        questionPool.add(QuestionFactory.getInstance("Which of the following are types of vehicles?|multi|Car, Bicycle, Computer|Car, Bicycle"));
        questionPool.add(QuestionFactory.getInstance("What is the tallest mountain in the world?|free|Mount Everest"));
        questionPool.add(QuestionFactory.getInstance("What is the main ingredient in guacamole?|free|Avocado"));
    }

    /**
     * @Description: returns the questions from question pool
     * @return ArrayList
     **/
    public static List<Question> getQuestions() {
        return new ArrayList<>(questionPool);
    }

    /**
     * @Description: This method prevents adding duplicate question to questionpool
     * @param question - enter the question which is to be added to the pool
     * @throws IllegalArgumentException - if the question already exists in the pool
     **/
    private static void addQuestion(Question question) {
        String questiontxt =  question.getQuestionTxt();

        if(!addUniqueQuesSet.contains(questiontxt)){
            questionPool.add(question);
            addUniqueQuesSet.add(questiontxt);
        }else {
            throw new IllegalArgumentException(questiontxt + " already exists");
        }
    }
}
