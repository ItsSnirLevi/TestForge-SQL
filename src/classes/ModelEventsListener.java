package classes;

public interface ModelEventsListener {

	void showQuestionStockModelEvent(String str);
	void addedOpenQueModelEvent(String s);
	void addedAmericanQueModelEvent(String s);
	void updateExistingQueModelEvent(String s);
	void updateAmericanAnsModelEvent();
	void updateOpenAnsModelEvent();
	void addedIDUpdateAnsModelEvent();
	void UpdateOpenAnsModelEvent(String s);
	void UpdateAmericanAnsModelEvent(String s);
	void addedIDDeleteAnsModelEvent(String s);
	void cloneModelEvent(String s);
	void automaticTestGeneratorModelEvent(String s);
	void showAutoTestModelEvent(String s);
	void addIDCreateTestModelEvent(String s);
	void addIDCreateTestIsAmericanModelEvent();
	void doneAddingAmeAnsModelEvent();
	void createTestGeneratorModelEvent(String s);
	void showCreateTestModelEvent(String s);
	void savedQSModelEvent(String s);
	void updateQuestionsInCreateTestModelEvent(int amount);
	void cancelCreateTestModelEvent();
}
