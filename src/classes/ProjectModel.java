//Snir Levi- 208322966
//Stav Harpaz- 207082280

//			 Different Subjects Support
//to support a number of subject we can create an array of QuestionStock
//and manage each cell as a different subject
package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProjectModel implements MenuInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Scanner s = new Scanner(System.in);
	private Vector<ModelEventsListener> listeners;
	private QuestionStock qs;
	private Map<Integer,Vector<Integer>> createTestQuestions = new LinkedHashMap<>();
	private Vector<Integer> answersToKeep = new Vector<>();
	
	public ProjectModel(QuestionStock qs) throws CloneNotSupportedException {
		listeners = new Vector<ModelEventsListener>();
		this.qs = qs.clone();
		if (!qs.getQuestions().isEmpty()) {
			qs.getQuestions().get(0).setSerialNumber(qs.getLastId() + 1);
		}
	}

	public void registerListener(ModelEventsListener listener) {
		listeners.add(listener);
	}

	public void saveQSToFile() {
		try {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("QuestionStock.dat"));
		outFile.writeObject(qs);
		outFile.close();
		} catch ( IOException e) {
			System.out.println(e.getMessage());			
		}
		for (ModelEventsListener l : listeners)
			l.savedQSModelEvent("Question stock has been saved.");
	}
	
	@Override
	public void createACloneOfTest() {
		try {
			qs.cloneTest(qs.getTest());
			for (ModelEventsListener l : listeners)
				l.cloneModelEvent("Success!");
		} catch (CloneNotSupportedException e) {
			for (ModelEventsListener l : listeners)
				l.cloneModelEvent(e.getMessage());
		}
	}

	@Override
	public void automaticTestGenerator(int queAmo) {

		try {
			if (queAmo > qs.getNumOfQue() || queAmo <= 0) {
				for (ModelEventsListener l : listeners)
					l.automaticTestGeneratorModelEvent("Cannot create test with " + queAmo
							+ " questions, please enter amount of questions again: (<=" + qs.getNumOfQue() + ")");
				return;
			}

			LocalDate ld = LocalDate.now();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss");
			String formattedDate = ld.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
			String queFileName = "Exam_" + formattedDate + "-" + sdf.format(cal.getTime()) + ".txt";
			String ansFileName = "Solution_" + formattedDate + "-" + sdf.format(cal.getTime()) + ".txt";

			PrintWriter pwQue = new PrintWriter(new File(queFileName));
			PrintWriter pwAns = new PrintWriter(new File(ansFileName));

			QuestionStock test = new QuestionStock();
			if (queAmo != 0) {
				if (!qs.canYouGenerateTestFromQuestions(queAmo)) {
					for (ModelEventsListener l : listeners)
						l.automaticTestGeneratorModelEvent("Cannot make legal test with " + queAmo
								+ " questions, there are not enough wrong answers or not enough answers\nfor the american questions.");
					return;
				}

				int numOfQueAdded = 0;
				while (numOfQueAdded < queAmo) {
					int rand = (int) (1 + Math.random() * qs.getNumOfQue());
					if (test.getIndexByID(rand) == -1) { // if the question does not exists in test
						if (qs.isOpenQue(qs.getQuestions().get(rand - 1))) {
							test.addOpenQuestion(qs.getQuestions().get(rand - 1));
							numOfQueAdded++;
						} else if (qs.canYouGenerateLegalQuestion(qs.getQuestions().get(rand - 1))) {
							test.addAmericanQuestion(qs.getQuestions().get(rand - 1));
							int numOfAns = (test
									.getNumOfAnsAmericanQuestion(test.getQuestions().get(test.getIndexByID(rand))));
							if (numOfAns > 4) {
								Vector<Integer> vec = new Vector<>();

								boolean correctAnsFlag = false;
								while (vec.size() < 4) {
									Integer randAns = (int) (1 + Math.random() * numOfAns);
									if (!vec.contains(randAns)) {
										if (!test.isAnswerCorrect(test.getQuestions().get(test.getIndexByID(rand)),
												randAns)) {
											vec.add(randAns);
										} else if (!correctAnsFlag) {
											vec.add(randAns);
											correctAnsFlag = true;
										}
									}
								}
								test.keepAnswers(rand, vec);
							}
							numOfQueAdded++;
						}
					}
				}
			}
			qs.cloneTestFromVector(test.getQuestions());
			// test.sortQuestionsAlph();
			test.sortByAnsLength();
			test.saveQuestions(pwQue);
			test.saveAnswers(pwAns);
			for (ModelEventsListener l : listeners)
				l.automaticTestGeneratorModelEvent(
						"Your test is ready!\nYour test questions has been saved as a file under the name: "
								+ queFileName + "\nYour test answers has been saved as a file under the name: "
								+ ansFileName);
			for (ModelEventsListener l : listeners)
				l.showAutoTestModelEvent(test.toString());

			pwQue.close();
			pwAns.close();
		} catch (AddAnswersException | InputMismatchException | FileNotFoundException | CloneNotSupportedException e) {
			for (ModelEventsListener l : listeners)
				l.automaticTestGeneratorModelEvent(e.getMessage());
		}
	}

	public void addIDCreateTest(int id) {
		
		if (qs.getIndexByID(id) == -1) {
			for (ModelEventsListener l : listeners)
				l.addIDCreateTestModelEvent("ID not found.");
			return;
		}	
		if(createTestQuestions.size() == qs.getNumOfQue()) {
			for (ModelEventsListener l : listeners)
				l.addIDCreateTestModelEvent("No more questions in stock.\nCreating test... ");
				createTest();
			return;
		}
		
		if(createTestQuestions.containsKey(id)) {
			for (ModelEventsListener l : listeners)
				l.addIDCreateTestModelEvent("Question already exists.");
			return;
		}
		
		createTestQuestions.put(id, null);
		if (qs.isAmericanQue(qs.getQuestions().get(id - 1))) {
			for (ModelEventsListener l : listeners)
				l.addIDCreateTestIsAmericanModelEvent();
		} else {
			for (ModelEventsListener l : listeners) {
				l.updateQuestionsInCreateTestModelEvent(createTestQuestions.size());
			}
		}
		
	}
		
	public void addAnswerNumberCreateTest(int ansNum, int id) {
		if(answersToKeep.size() == ((AmericanQuestion)qs.getQuestions().get(id - 1)).getNumOfAns()) {
			for (ModelEventsListener l : listeners)
				l.addIDCreateTestModelEvent("no more answers to this question.\nAdding the question...");
			doneAddingAmeAns(id);
			return;
		}
		if(ansNum < 1  || (ansNum > ((AmericanQuestion)qs.getQuestions().get(id - 1)).getNumOfAns())) {
			for (ModelEventsListener l : listeners)
				l.addIDCreateTestModelEvent("this answer doesn't exist.");
		}
			
		if(answersToKeep.contains(ansNum)) {
			for (ModelEventsListener l : listeners)
				l.addIDCreateTestModelEvent("Answer already exists.");
			return;
		}
		answersToKeep.add(ansNum);		
	}

	@SuppressWarnings("unchecked")
	public void doneAddingAmeAns(int id) {
		if(answersToKeep.size() == 0) {
			for (ModelEventsListener l : listeners)
				l.addIDCreateTestModelEvent("Must choose one answer at least.");
			return;
		}
		
		createTestQuestions.put(id, (Vector<Integer>)answersToKeep.clone());
		answersToKeep.clear();
		for (ModelEventsListener l : listeners) {
			l.addIDCreateTestModelEvent("Question added!");		
			l.doneAddingAmeAnsModelEvent();
			l.updateQuestionsInCreateTestModelEvent(createTestQuestions.size());
		}
	}
	
	public void cancelCreateTest() {
		createTestQuestions.clear();
		answersToKeep.clear();
		for (ModelEventsListener l : listeners) {
			l.cancelCreateTestModelEvent();
			l.updateQuestionsInCreateTestModelEvent(0);
		}
	}
	
	@Override
	public void createTest() {
		
		if(createTestQuestions.size() == 0) {
			for (ModelEventsListener l : listeners) 
				l.addIDCreateTestModelEvent("Cannot create test without questions.");
			return;
		}
		
		try {
			LocalDate ld = LocalDate.now();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss");
			String formattedDate = ld.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
			String queFileName = "Exam_" + formattedDate + "-" + sdf.format(cal.getTime()) + ".txt";
			String ansFileName = "Solution_" + formattedDate + "-" + sdf.format(cal.getTime()) + ".txt";
			
			PrintWriter pwQue = new PrintWriter(new File(queFileName));
			PrintWriter pwAns = new PrintWriter(new File(ansFileName));

			QuestionStock test = new QuestionStock(); 

			Question q;
			for(Integer id : createTestQuestions.keySet()) {
				q = qs.getQuestions().get(qs.getIndexByID(id)); 
				if (qs.isOpenQue(q)) {
					test.addOpenQuestion(q);
				} else {
					test.addAmericanQuestion(q);
					test.keepAnswers(id, createTestQuestions.get(id));
				}
				
			}
				
			createTestQuestions.clear();
			
			qs.cloneTestFromVector(test.getQuestions());
			test.sortByAnsLength();
			test.saveQuestions(pwQue);
			test.saveAnswers(pwAns);
			for (ModelEventsListener l : listeners) {
				l.createTestGeneratorModelEvent(
						"Your test is ready!\nYour test questions has been saved as a file under the name: "
								+ queFileName + "\nYour test answers has been saved as a file under the name: "
								+ ansFileName);
				l.showCreateTestModelEvent(test.toString());
				l.updateQuestionsInCreateTestModelEvent(0);
			}
			pwQue.close();
			pwAns.close();
		} catch (AddAnswersException | InputMismatchException | FileNotFoundException | CloneNotSupportedException e) {
			for (ModelEventsListener l : listeners)
				l.createTestGeneratorModelEvent(e.getMessage());
		}
	
	}
	
	
	

	@Override
	public void deleteAnAnswer(int id, int ansNum) {
		try {
			if (qs.getIndexByID(id) == -1) {
				for (ModelEventsListener l : listeners)
					l.addedIDDeleteAnsModelEvent("ID not found.");
				return;
			}
			if (!qs.isAmericanQue(qs.getQuestions().get(id - 1))) {
				for (ModelEventsListener l : listeners)
					l.addedIDDeleteAnsModelEvent("This is an open question.");
				return;
			}

			if (!qs.deleteAnswer(id, ansNum))
				for (ModelEventsListener l : listeners)
					l.addedIDDeleteAnsModelEvent(
							"Operation unsuccessful: answer number " + ansNum + " does not exist.");
			else
				for (ModelEventsListener l : listeners)
					l.addedIDDeleteAnsModelEvent("Success!");
		} catch (InputMismatchException | AddAnswersException e) {
			for (ModelEventsListener l : listeners)
				l.addedIDDeleteAnsModelEvent(e.getMessage());
		}
	}

	@Override
	public void updateOpenExistingAnswerText(int id, String ansText) {
		try {
			if (!qs.setOpenAnswer(ansText, id)) {
				for (ModelEventsListener l : listeners)
					l.UpdateOpenAnsModelEvent("Operation unsuccessful: the question ID does not exist.");
			} else
				for (ModelEventsListener l : listeners)
					l.UpdateOpenAnsModelEvent("Success!");
		} catch (CloneNotSupportedException | InputMismatchException e) {
			for (ModelEventsListener l : listeners)
				l.UpdateOpenAnsModelEvent(e.getMessage());
		}
	}

	@Override
	public void updateAmericanExistingAnswerText(int id, String ansText, int ansNum) {
		try {
			if (!qs.setAmericanAnswer(ansText, id, ansNum))
				for (ModelEventsListener l : listeners)
					l.UpdateAmericanAnsModelEvent(
							"Operation unsuccessful: answer number " + ansNum + " does not exist.");
			else
				for (ModelEventsListener l : listeners)
					l.UpdateAmericanAnsModelEvent("Success!");

		} catch (InputMismatchException e) {
			for (ModelEventsListener l : listeners)
				l.UpdateOpenAnsModelEvent(e.getMessage());
		}
	}

	public void questionTypeById(int id) {
		if (qs.getIndexByID(id) == -1) {
			for (ModelEventsListener l : listeners)
				l.addedIDUpdateAnsModelEvent();
			return;
		}
		if (qs.isAmericanQue(qs.getQuestions().get(id - 1))) {
			for (ModelEventsListener l : listeners)
				l.updateAmericanAnsModelEvent();
		} else {
			for (ModelEventsListener l : listeners)
				l.updateOpenAnsModelEvent();
		}
	}

	@Override
	public void updateExistingQuestionText(Integer id, String queText) throws InputMismatchException {
		if (!qs.setQuestion(queText, id)) {
			for (ModelEventsListener l : listeners)
				l.updateExistingQueModelEvent(
						"Operation unsuccessful: either the question ID does not exist or that this question already exists.");
		} else {
			for (ModelEventsListener l : listeners)
				l.updateExistingQueModelEvent("Success!");
		}
	}

	@Override
	public void addOpenQuestionAndAnswer(String queText, String ansText)
			throws AddAnswersException, InputMismatchException {
		if (qs.isExists(queText, 1)) {
			for (ModelEventsListener l : listeners)
				l.addedOpenQueModelEvent("Question already exists!");
			return;
		}
		qs.addOpenQuestion(queText, ansText);
		for (ModelEventsListener l : listeners)
			l.addedOpenQueModelEvent("Added open question successfully");
	}

	public void addAmericanQuestionAndAnswer(String queText, Vector<String> ansText, Vector<Boolean> isCorrect)
			throws AddAnswersException, InputMismatchException {
		if (qs.isExists(queText, 2)) {
			for (ModelEventsListener l : listeners)
				l.addedOpenQueModelEvent("Question already exists!");
			return;
		}
		qs.addAmericanQuestion(queText, ansText, isCorrect);
		for (ModelEventsListener l : listeners)
			l.addedAmericanQueModelEvent("Added american question successfully");
	}

	@Override
	public void printQuestionStock() { // print question stock
		String ret = qs.toString();
		for (ModelEventsListener l : listeners)
			l.showQuestionStockModelEvent(ret);

	}

}// class
