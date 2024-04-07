package classes;

import java.util.Vector;

public interface ViewEventsListener {
	
	void ctrAddQuestionStockToUIEvent();
	void ctrAddAmericanQuesAndAnsToUIEvent(String que, Vector<String> ans, Vector<Boolean> isCorrect);
	void ctrAddOpenQuesAndAnsToUIEvent(String que, String ans);
	void ctrUpdateQuestionUIEvent(Integer id, String txt);
	void addedIDUpdateAnsUIEvent(int id);
	void updateOpenAnsUIEvent(int id, String txt);
	void updateAmericanAnsUIEvent(int id, String txt, int ansNum);
	void addedIDDeleteAnsUIEvent(int id);
	void deleteAnsUIEvent(int id, int ansNum);
	void cloneUIEvent();
	void autoTestUIEvent(int queAmo);
	void addedIDCreateTestUIEvent(int id);
	void addAmeAnsCreateTestUIEvent(int ansNum, int id);
	void doneAddingAmeAnsCreateTestUIEvent(int id);
	void CreateTestUIEvent();
	void exitUIEvent();
	void cancelCreateTestUIEvent();
}
