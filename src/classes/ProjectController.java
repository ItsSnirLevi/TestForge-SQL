package classes;

import java.util.Vector;

public class ProjectController implements ModelEventsListener, ViewEventsListener {

	private ProjectView projectView;
	private ProjectModel projectModel;

	public ProjectController(ProjectView view, ProjectModel model) {
		projectView = view;
		projectModel = model;

		projectModel.registerListener(this);
		projectView.registerListener(this);
	}

	@Override
	public void ctrAddQuestionStockToUIEvent() {
		projectModel.printQuestionStock();
	}

	@Override
	public void showQuestionStockModelEvent(String str) {
		projectView.showQuestionStockUI(str);
	}

	@Override
	public void ctrAddAmericanQuesAndAnsToUIEvent(String que, Vector<String> ans, Vector<Boolean> isCorrect) {
		try {
			projectModel.addAmericanQuestionAndAnswer(que, ans, isCorrect);
		} catch (AddAnswersException e) {
			projectView.popMessageToUI(e.getMessage());
		}
	}

	public void ctrAddOpenQuesAndAnsToUIEvent(String que, String ans) {
		try {
			projectModel.addOpenQuestionAndAnswer(que, ans);
		} catch (AddAnswersException e) {
			projectView.popMessageToUI(e.getMessage());
		}
	}

	@Override
	public void addedAmericanQueModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void addedOpenQueModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void updateExistingQueModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void addedIDUpdateAnsUIEvent(int id) {
		projectModel.questionTypeById(id);
	}

	@Override
	public void addedIDDeleteAnsUIEvent(int id) {
		projectModel.questionTypeById(id);
	}

	@Override
	public void addedIDUpdateAnsModelEvent() {
		projectView.popMessageToUI("This ID does not exist.");
	}

	@Override
	public void ctrUpdateQuestionUIEvent(Integer id, String txt) {
		projectModel.updateExistingQuestionText(id, txt);
	}

	@Override
	public void updateAmericanAnsModelEvent() {
		projectView.updateAmericanAnsToUI();
	}

	@Override
	public void updateOpenAnsModelEvent() {
		projectView.updateOpenAnsToUI();
	}

	@Override
	public void updateOpenAnsUIEvent(int id, String txt) {
		projectModel.updateOpenExistingAnswerText(id, txt);
	}

	@Override
	public void UpdateOpenAnsModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void UpdateAmericanAnsModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void updateAmericanAnsUIEvent(int id, String txt, int ansNum) {
		projectModel.updateAmericanExistingAnswerText(id, txt, ansNum);
	}

	@Override
	public void addedIDDeleteAnsModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void deleteAnsUIEvent(int id, int ansNum) {
		projectModel.deleteAnAnswer(id, ansNum);
	}

	@Override
	public void cloneUIEvent() {
		projectModel.createACloneOfTest();

	}

	@Override
	public void cloneModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void automaticTestGeneratorModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void showAutoTestModelEvent(String s) {
		projectView.showAutoTest(s);
	}

	@Override
	public void autoTestUIEvent(int queAmo) {
		projectModel.automaticTestGenerator(queAmo);
	}

	@Override
	public void addedIDCreateTestUIEvent(int id) {
		projectModel.addIDCreateTest(id);
	}

	@Override
	public void addIDCreateTestModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void addIDCreateTestIsAmericanModelEvent() {
		projectView.createTestQueAme();

	}

	@Override
	public void addAmeAnsCreateTestUIEvent(int ansNum, int id) {
		projectModel.addAnswerNumberCreateTest(ansNum, id);
	}

	@Override
	public void doneAddingAmeAnsCreateTestUIEvent(int id) {
		projectModel.doneAddingAmeAns(id);
	}

	@Override
	public void doneAddingAmeAnsModelEvent() {
		projectView.doneAddingAmeAnsToUI();
	}

	@Override
	public void CreateTestUIEvent() {
		projectModel.createTest();
	}

	@Override
	public void createTestGeneratorModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void showCreateTestModelEvent(String s) {
		projectView.showCreateTest(s);
	}

	@Override
	public void exitUIEvent() {
		projectModel.saveQSToFile();
	}

	@Override
	public void savedQSModelEvent(String s) {
		projectView.popMessageToUI(s);
	}

	@Override
	public void updateQuestionsInCreateTestModelEvent(int amount) {
		projectView.updateQuestionsInCreateTestToUI(amount);
	}

	@Override
	public void cancelCreateTestUIEvent() {
		projectModel.cancelCreateTest();
	}

	@Override
	public void cancelCreateTestModelEvent() {
		projectView.doneAddingAmeAnsToUI();
		
	}
}
