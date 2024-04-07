package classes;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Vector;

public interface MenuInterface {

	void createACloneOfTest() throws FileNotFoundException, CloneNotSupportedException;
	
	void automaticTestGenerator(int queAmo) throws AddAnswersException, InputMismatchException, FileNotFoundException, CloneNotSupportedException;
	
	void createTest() throws AddAnswersException, InputMismatchException, FileNotFoundException, CloneNotSupportedException;
	
	void deleteAnAnswer(int id, int ansNum) throws AddAnswersException, InputMismatchException;
	
	void questionTypeById(int id);
	
	void updateOpenExistingAnswerText(int id, String ansText) throws InputMismatchException, CloneNotSupportedException;
	
	void updateAmericanExistingAnswerText(int id, String ansText, int ansNum) throws InputMismatchException;
	
	void updateExistingQuestionText(Integer id, String txt) throws InputMismatchException;
	
	void addOpenQuestionAndAnswer(String s1, String s2) throws AddAnswersException, InputMismatchException;
	
	void addAmericanQuestionAndAnswer(String str, Vector<String> ans, Vector<Boolean> isCorrect) throws AddAnswersException, InputMismatchException;
	
	void printQuestionStock();

}
