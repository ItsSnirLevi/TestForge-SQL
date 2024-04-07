package classes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProjectView {
	private Vector<ViewEventsListener> listeners = new Vector<>();
	private Vector<String> americanAnswers = new Vector<>();
	private Vector<Boolean> americanAnswersIsCorrect = new Vector<>();
	private TabPane tpRoot = new TabPane();
	private GridPane showQuestionStockGPRoot = new GridPane();
	private Label questionStockLbl = new Label();
	private GridPane addQuestionAndAnswerGPRoot = new GridPane();
	private GridPane updateExistingQuestionTextGPRoot = new GridPane();
	private GridPane updateExistingAnswerTextGPRoot = new GridPane();
	private Button updateOpenAnsBT = new Button("Update");		
	private Button updateAmericanAnsBT = new Button("Update");		
	private TextField ansTxtTF = new TextField();
	private TextField ansNumTF = new TextField();
	private Button nextBT = new Button("Next");		
	private TextField idAnsTF = new TextField();
	private GridPane deleteAnAnswerGPRoot = new GridPane();
	private GridPane createTestGPRoot = new GridPane();
	private TextField ansToKeepTF = new TextField();
	private TextField idCreateTestTF = new TextField();		
	private Button addCreateTestBT = new Button("Add");
	private Button addAnsToKeepBT = new Button("Add");
	private Label ansToKeepLbl = new Label("Enter answer number to keep: ");
	private Button doneAddingAnsBT = new Button("Done");
	private Label createTestLbl = new Label();
	private Label amountOfQueCreateTestLbl = new Label("Questions added: 0");
	private Button createBT = new Button("Create");
	private GridPane automaticTestGeneratorGPRoot = new GridPane();
	private Label autoTestLbl = new Label();
	private GridPane createACloneOfTestGPRoot = new GridPane();
	
	
	
	public ProjectView(Stage theStage) {
		theStage.setTitle("Project");
		theStage.getIcons().add(new Image("file:icon.jpg"));
		
		Map<String, Tab> allTabs = new LinkedHashMap<String, Tab>();
		allTabs.put("showQuestionStockTab", new Tab("Show Question Stock"));		
		allTabs.put("addQuestionAndAnswerTab", new Tab("Add Question and Answer"));
		allTabs.put("updateExistingQuestionTextTab", new Tab("Update Existing Question Text"));
		allTabs.put("updateExistingAnswerTextTab", new Tab("Update Existing Answer Text"));
		allTabs.put("deleteAnAnswerTab", new Tab("Delete an Answer"));
		allTabs.put("createTestTab", new Tab("Create Test"));
		allTabs.put("automaticTestGeneratorTab", new Tab("Automatic Test Generator"));
		allTabs.put("createACloneOfTestTab", new Tab("Create a Clone of Test"));		
		
		
//---------------------------showQuestionStock		
		showQuestionStockGPRoot.setPadding(new Insets(10));
		showQuestionStockGPRoot.setHgap(10);
		showQuestionStockGPRoot.setVgap(10);
		
		Label showQuestionStockLbl= new Label();
		Button showQuestionStockBtn = new Button("Show question stock");	
		
		showQuestionStockBtn.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				for (ViewEventsListener l : listeners) {
					l.ctrAddQuestionStockToUIEvent();	
				}
				showQuestionStockLbl.setText("\tQUESTION STOCK\n\n" + questionStockLbl.getText());
				showQuestionStockBtn.setText("Update question stock");
			} 
	
		});
		
		showQuestionStockGPRoot.add(showQuestionStockBtn, 0, 1);
		showQuestionStockGPRoot.add(showQuestionStockLbl, 0, 0);	
		
		ScrollPane showQuestionStockSP = new ScrollPane();
		showQuestionStockSP.setContent(showQuestionStockGPRoot);
		allTabs.get("showQuestionStockTab").setContent(showQuestionStockSP);
		
//---------------------------addQuestionAndAnswer	
	
		addQuestionAndAnswerGPRoot.setPadding(new Insets(10));
		addQuestionAndAnswerGPRoot.setHgap(10);
		addQuestionAndAnswerGPRoot.setVgap(10);
		
		ScrollPane addQueAnsAnsStockSP = new ScrollPane();
		addQueAnsAnsStockSP.setPrefViewportWidth(700);
		addQueAnsAnsStockSP.setPrefViewportHeight(500);
		GridPane.setRowSpan(addQueAnsAnsStockSP, 20);
		
		ToggleGroup addQuestionAndAnswerTG = new ToggleGroup();
		RadioButton ameQue = new RadioButton("Add american question");
		RadioButton opeQue = new RadioButton("Add open question");
		ameQue.setToggleGroup(addQuestionAndAnswerTG);
		opeQue.setToggleGroup(addQuestionAndAnswerTG);
		
		Button canceladdQuestionAndAnswerBT = new Button("Cancel");
		Button opeQueBTFinish = new Button("Finish");
		Label opeQueLbl = new Label("Enter question text: ");
		Label opeAnsLbl = new Label("Enter answer text: ");
		TextField opeQueTF = new TextField();
		TextField opeAnsTF = new TextField();
		opeQueTF.setDisable(true);
		opeAnsTF.setDisable(true);
		opeQueBTFinish.setDisable(true);
		
		Button ameQueBTFinish = new Button("Finish");
		Button ameQueBTNext = new Button("Next");
		Button ameQueBTAdd = new Button("Add");
		Label ameQueLbl = new Label("Enter question text: ");
		Label ameAnsLbl = new Label("Enter answer text: ");
		TextField ameQueTF = new TextField();
		TextField ameAnsTF = new TextField();
		opeQueTF.setDisable(true);
		opeAnsTF.setDisable(true);
		opeQueBTFinish.setDisable(true);
		ameQueBTNext.setDisable(true);
		ameQueBTAdd.setDisable(true);
		ameQueTF.setDisable(true);
		ameAnsTF.setDisable(true);	
		ameQueBTFinish.setDisable(true);
		ToggleGroup isCorrectTG = new ToggleGroup();
		RadioButton correctRB = new RadioButton("Correct");
		RadioButton wrongRB = new RadioButton("Wrong");
		correctRB.setToggleGroup(isCorrectTG);
		wrongRB.setToggleGroup(isCorrectTG);
		correctRB.setDisable(true);
		wrongRB.setDisable(true);
		
		opeQue.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				opeQueTF.setDisable(false);
				opeAnsTF.setDisable(false);	
				opeQueBTFinish.setDisable(false);
				ameQueTF.setDisable(true);
				ameAnsTF.setDisable(true);	
				ameQueBTFinish.setDisable(true);
				ameQueBTNext.setDisable(true);
				correctRB.setDisable(true);
				wrongRB.setDisable(true);
				ameQueBTAdd.setDisable(true);
				if(!ameQueTF.getText().isBlank()) {
					ameQueTF.clear();
					isCorrectTG.selectToggle(null);
					americanAnswers.clear();
					americanAnswersIsCorrect.clear();	
				}
			} 	
	
		});
		
		opeQueBTFinish.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				if(!opeQueTF.getText().isBlank() && !opeAnsTF.getText().isBlank() ) {
					for (ViewEventsListener l : listeners) 
						l.ctrAddOpenQuesAndAnsToUIEvent(opeQueTF.getText(), opeAnsTF.getText());
					for (ViewEventsListener l : listeners) {
						l.ctrAddQuestionStockToUIEvent();	
					}
					addQueAnsAnsStockSP.setContent(new Label(questionStockLbl.getText()));
					opeAnsTF.clear();
					opeQueTF.clear();
				} else {
					popMessageToUI("Can't add empty question / answer!");
				}
			}
		});
		
			
		ameQue.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				opeQueTF.setDisable(true);
				opeAnsTF.setDisable(true);	
				opeQueBTFinish.setDisable(true);
				ameQueTF.setDisable(false);
				ameQueBTNext.setDisable(false);								
			} 
		});
		
		ameQueBTNext.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				if(!ameQueTF.getText().isBlank() ) {				
					ameAnsTF.setDisable(false);	
					ameQueBTFinish.setDisable(false);				
					correctRB.setDisable(false);
					wrongRB.setDisable(false);
					ameQueTF.setDisable(true);
					ameQueBTNext.setDisable(true);
					ameQueBTAdd.setDisable(false);
				}
			}
		});
		
		ameQueBTAdd.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				if(!ameAnsTF.getText().isBlank() && (isCorrectTG.getSelectedToggle() != null) && (americanAnswers.size() != 10)) {				
					americanAnswers.add(ameAnsTF.getText());
					if(((RadioButton)isCorrectTG.getSelectedToggle()).getText().equals("Correct")) {
						americanAnswersIsCorrect.add(true);
					} else {
						americanAnswersIsCorrect.add(false);
					}
					isCorrectTG.selectToggle(null);
					ameAnsTF.clear();
				} else if (isCorrectTG.getSelectedToggle() == null) {
					popMessageToUI("Is the answer correct/wrong?");
				} else if (americanAnswers.size() == 10) {
					popMessageToUI("can't add more than 10 answers!\n adding the question to the stock.");
					addAmericanQue(ameQueTF.getText());
					ameQueTF.clear();
					isCorrectTG.selectToggle(null);
				}
			}
		});
		
		ameQueBTFinish.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				if(americanAnswers.size() != 0) {
					addAmericanQue(ameQueTF.getText());
					ameQueTF.clear();
					isCorrectTG.selectToggle(null);
					for (ViewEventsListener l : listeners) {
						l.ctrAddQuestionStockToUIEvent();	
					}
					addQueAnsAnsStockSP.setContent(new Label(questionStockLbl.getText()));
					opeQueTF.setDisable(true);
					opeAnsTF.setDisable(true);	
					opeQueBTFinish.setDisable(true);
					ameQueTF.setDisable(false);
					ameQueBTNext.setDisable(false);
					ameAnsTF.setDisable(true);
					correctRB.setDisable(true);
					wrongRB.setDisable(true);
					ameQueBTAdd.setDisable(true);
					
				} else {
					popMessageToUI("Must add at least one answer!");
				}
			}
		});
		
		canceladdQuestionAndAnswerBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				if(americanAnswers.size() != 0) {
					americanAnswers.clear();
					americanAnswersIsCorrect.clear();
					ameQueTF.clear();
					isCorrectTG.selectToggle(null);
					addQueAnsAnsStockSP.setContent(new Label(questionStockLbl.getText()));
				} else {
					popMessageToUI("Must add at least one answer!");
				}
			}
		});
		
		allTabs.get("addQuestionAndAnswerTab").setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				for (ViewEventsListener l : listeners) 
					l.ctrAddQuestionStockToUIEvent();	
				
				addQueAnsAnsStockSP.setContent(new Label(questionStockLbl.getText()));			
			}
			
		});
		
		addQuestionAndAnswerGPRoot.add(opeQue, 0, 0);
		addQuestionAndAnswerGPRoot.add(opeQueLbl, 0, 1);
		addQuestionAndAnswerGPRoot.add(opeQueTF, 1, 1);
		addQuestionAndAnswerGPRoot.add(opeAnsLbl, 0, 2);
		addQuestionAndAnswerGPRoot.add(opeAnsTF, 1, 2);
		addQuestionAndAnswerGPRoot.add(opeQueBTFinish, 1, 3);
		
		addQuestionAndAnswerGPRoot.add(ameQue, 0, 5);
		addQuestionAndAnswerGPRoot.add(ameQueLbl, 0, 6);
		addQuestionAndAnswerGPRoot.add(ameQueTF, 1, 6);
		addQuestionAndAnswerGPRoot.add(ameQueBTNext, 2, 6);
		addQuestionAndAnswerGPRoot.add(ameAnsLbl, 0, 7);
		addQuestionAndAnswerGPRoot.add(ameAnsTF, 1, 7);
		addQuestionAndAnswerGPRoot.add(ameQueBTAdd, 2, 7);
		addQuestionAndAnswerGPRoot.add(correctRB,0,8);
		addQuestionAndAnswerGPRoot.add(wrongRB,0,9);
		addQuestionAndAnswerGPRoot.add(ameQueBTFinish, 1, 10);
				
		addQuestionAndAnswerGPRoot.add(addQueAnsAnsStockSP, 5, 0);	
		
		ScrollPane addQuestionAndAnswerSP = new ScrollPane();
		addQuestionAndAnswerSP.setContent(addQuestionAndAnswerGPRoot);		
		allTabs.get("addQuestionAndAnswerTab").setContent(addQuestionAndAnswerSP);
		
//---------------------------updateExistingQuestionText	
		
		updateExistingQuestionTextGPRoot.setPadding(new Insets(10));
		updateExistingQuestionTextGPRoot.setHgap(10);
		updateExistingQuestionTextGPRoot.setVgap(10);
		
		TextField idTF = new TextField();
		TextField txtTF = new TextField();
		Button updateBT = new Button("Update");
		
		ScrollPane updateExistingQuestionTextStockSP = new ScrollPane();
		updateExistingQuestionTextStockSP.setPrefViewportWidth(700);
		updateExistingQuestionTextStockSP.setPrefViewportHeight(500);
		GridPane.setRowSpan(updateExistingQuestionTextStockSP, 20);
		
		updateBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				try {				
				if(!idTF.getText().isBlank() && !txtTF.getText().isBlank()) {					
					for (ViewEventsListener l : listeners) 
						l.ctrUpdateQuestionUIEvent(Integer.valueOf(idTF.getText()), txtTF.getText());
					for (ViewEventsListener l : listeners) {
						l.ctrAddQuestionStockToUIEvent();	
					}
					updateExistingQuestionTextStockSP.setContent(new Label(questionStockLbl.getText()));
					idTF.clear();
					txtTF.clear();
				} else {
					popMessageToUI("Please fill both text fields.");
				}
				} catch (NumberFormatException e) {
					idTF.clear();
					popMessageToUI("Please enter a number as id.");
				}
			}
		});
		
		allTabs.get("updateExistingQuestionTextTab").setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				for (ViewEventsListener l : listeners) 
					l.ctrAddQuestionStockToUIEvent();	
				
				updateExistingQuestionTextStockSP.setContent(new Label(questionStockLbl.getText()));			
			}
			
		});
		
		updateExistingQuestionTextGPRoot.add(new Label("Enter question ID: "), 0, 0);
		updateExistingQuestionTextGPRoot.add(idTF, 1, 0);
		updateExistingQuestionTextGPRoot.add(new Label("Enter new question text: "), 0, 1);
		updateExistingQuestionTextGPRoot.add(txtTF, 1, 1);
		updateExistingQuestionTextGPRoot.add(updateBT, 0, 2);
		updateExistingQuestionTextGPRoot.add(updateExistingQuestionTextStockSP, 5, 0);
		
		ScrollPane updateExistingQuestionTextSP = new ScrollPane();
		updateExistingQuestionTextSP.setContent(updateExistingQuestionTextGPRoot);	
		allTabs.get("updateExistingQuestionTextTab").setContent(updateExistingQuestionTextSP);
		
//---------------------------updateExistingAnswerText
		
		updateExistingAnswerTextGPRoot.setPadding(new Insets(10));
		updateExistingAnswerTextGPRoot.setHgap(10);
		updateExistingAnswerTextGPRoot.setVgap(10);
		
		Button cancelUpdateAnsBT = new Button("Cancel");
		ansNumTF.setDisable(true);
		ansTxtTF.setDisable(true);
		updateOpenAnsBT.setVisible(false);
		updateAmericanAnsBT.setVisible(false);
		cancelUpdateAnsBT.setVisible(false);
		
		ScrollPane updateExistingAnswerTextStockSP = new ScrollPane();
		updateExistingAnswerTextStockSP.setPrefViewportWidth(700);
		updateExistingAnswerTextStockSP.setPrefViewportHeight(500);
		GridPane.setRowSpan(updateExistingAnswerTextStockSP, 20);
		
		nextBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {				
				try {					
					if(!idAnsTF.getText().isBlank()) {
						for (ViewEventsListener l : listeners) {
							l.addedIDDeleteAnsUIEvent(Integer.valueOf(idAnsTF.getText()));
						}		
						cancelUpdateAnsBT.setVisible(true);
					} else {
						popMessageToUI("Please enter an ID!");
					}
				
				} catch (NumberFormatException e) {
					idAnsTF.clear();;
					popMessageToUI("Please enter a number as id.");
				}
			}
		});
		
		
		updateOpenAnsBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {				
				if(!ansTxtTF.getText().isBlank()) {
					for (ViewEventsListener l : listeners) 
						l.updateOpenAnsUIEvent(Integer.valueOf(idAnsTF.getText()), ansTxtTF.getText());
					resetUpdateAns();
					cancelUpdateAnsBT.setVisible(false);
					for (ViewEventsListener l : listeners) {
						l.ctrAddQuestionStockToUIEvent();	
					}
					updateExistingAnswerTextStockSP.setContent(new Label(questionStockLbl.getText()));
				}
			}
		});
		
		updateAmericanAnsBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {	
				try {
					if(!ansTxtTF.getText().isBlank() && !ansNumTF.getText().isBlank()) {
						for (ViewEventsListener l : listeners) 
							l.updateAmericanAnsUIEvent(Integer.valueOf(idAnsTF.getText()), ansTxtTF.getText(), Integer.valueOf(ansNumTF.getText()));
						resetUpdateAns();
						cancelUpdateAnsBT.setVisible(false);
						for (ViewEventsListener l : listeners) {
							l.ctrAddQuestionStockToUIEvent();	
						}
						updateExistingAnswerTextStockSP.setContent(new Label(questionStockLbl.getText()));
					} 
				} catch (NumberFormatException e) {
					ansNumTF.clear();;
					popMessageToUI("Please enter a number as an answer number.");
				}
			}
		});
		
		cancelUpdateAnsBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {				
				resetUpdateAns();
				cancelUpdateAnsBT.setVisible(false);
			}
		});
		
		allTabs.get("updateExistingAnswerTextTab").setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				for (ViewEventsListener l : listeners) 
					l.ctrAddQuestionStockToUIEvent();	
				
				updateExistingAnswerTextStockSP.setContent(new Label(questionStockLbl.getText()));			
			}
			
		});		
		
		updateExistingAnswerTextGPRoot.add(new Label("Enter question ID:"), 0, 0);
		updateExistingAnswerTextGPRoot.add(idAnsTF, 1, 0);
		updateExistingAnswerTextGPRoot.add(nextBT, 2, 0);
		updateExistingAnswerTextGPRoot.add(new Label("Enter new answer text: "), 0, 1);
		updateExistingAnswerTextGPRoot.add(ansTxtTF, 1, 1);
		updateExistingAnswerTextGPRoot.add(updateOpenAnsBT, 1, 3);
		updateExistingAnswerTextGPRoot.add(new Label("Enter answer number: "), 0, 2);
		updateExistingAnswerTextGPRoot.add(ansNumTF, 1, 2);
		updateExistingAnswerTextGPRoot.add(updateAmericanAnsBT, 1, 3);
		updateExistingAnswerTextGPRoot.add(cancelUpdateAnsBT, 2, 3);
		updateExistingAnswerTextGPRoot.add(updateExistingAnswerTextStockSP, 5, 0);
		
		ScrollPane updateExistingAnswerTextSP = new ScrollPane();
		updateExistingAnswerTextSP.setContent(updateExistingAnswerTextGPRoot);	
		allTabs.get("updateExistingAnswerTextTab").setContent(updateExistingAnswerTextSP);
		
//---------------------------deleteAnAnswer
		
		deleteAnAnswerGPRoot.setPadding(new Insets(10));
		deleteAnAnswerGPRoot.setHgap(10);
		deleteAnAnswerGPRoot.setVgap(10);
		
		TextField queIdTF = new TextField();
		TextField ansNumTF = new TextField();
		Button deleteAnsBT = new Button("Delete");
		ScrollPane deleteAnAnswerStockSP = new ScrollPane();
		deleteAnAnswerStockSP.setPrefViewportWidth(700);
		deleteAnAnswerStockSP.setPrefViewportHeight(500);
		GridPane.setRowSpan(deleteAnAnswerStockSP, 20);
		
		
		deleteAnsBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {				
				try {
					if(!queIdTF.getText().isBlank() && !ansNumTF.getText().isBlank()) {
						for (ViewEventsListener l : listeners) 
							l.deleteAnsUIEvent(Integer.valueOf(queIdTF.getText()), Integer.valueOf(ansNumTF.getText()));						
						for (ViewEventsListener l : listeners) 
							l.ctrAddQuestionStockToUIEvent();	
						
						deleteAnAnswerStockSP.setContent(new Label(questionStockLbl.getText()));
						queIdTF.clear();
						ansNumTF.clear();
					} else {
						popMessageToUI("Please fill both text boxes.");
					}
					
				} catch (NumberFormatException e) {
					queIdTF.clear();
					ansNumTF.clear();
					popMessageToUI("Please enter numbers.");
				} 
			}
		});
		
		allTabs.get("deleteAnAnswerTab").setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				for (ViewEventsListener l : listeners) 
					l.ctrAddQuestionStockToUIEvent();	
				
				deleteAnAnswerStockSP.setContent(new Label(questionStockLbl.getText()));			
			}
			
		});		
		
		deleteAnAnswerGPRoot.add(new Label("Enter question ID:"), 0, 0);
		deleteAnAnswerGPRoot.add(queIdTF, 1, 0);
		deleteAnAnswerGPRoot.add(new Label("(American questions only!)"), 2, 0);
		deleteAnAnswerGPRoot.add(new Label("Enter answer number: "), 0, 1);
		deleteAnAnswerGPRoot.add(ansNumTF, 1, 1);
		deleteAnAnswerGPRoot.add(deleteAnsBT, 1, 2);
		deleteAnAnswerGPRoot.add(deleteAnAnswerStockSP, 5, 0);
		
		ScrollPane deleteAnAnswerSP = new ScrollPane();
		deleteAnAnswerSP.setContent(deleteAnAnswerGPRoot);
		allTabs.get("deleteAnAnswerTab").setContent(deleteAnAnswerSP);
		
//---------------------------createTest
		
		createTestGPRoot.setPadding(new Insets(10));
		createTestGPRoot.setHgap(10);
		createTestGPRoot.setVgap(10);

		Button cancelcreateTestBT = new Button("Cancel");
		ansToKeepTF.setVisible(false);	
		addAnsToKeepBT.setVisible(false);
		ansToKeepLbl.setVisible(false);
		doneAddingAnsBT.setVisible(false);
		ScrollPane createTestStockSP = new ScrollPane();
		createTestStockSP.setPrefViewportWidth(700);
		createTestStockSP.setPrefViewportHeight(500);
		GridPane.setRowSpan(createTestStockSP, 20);
		
		addCreateTestBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {				
				try {
					if(!idCreateTestTF.getText().isBlank()) {
						for (ViewEventsListener l : listeners) 
							l.addedIDCreateTestUIEvent(Integer.valueOf(idCreateTestTF.getText()));					
						if(!addAnsToKeepBT.isVisible()) {
							idCreateTestTF.clear();
						}						
					}
					
				} catch (NumberFormatException e) {
					idCreateTestTF.clear();
					popMessageToUI("Please enter an ID.");
				} 
			}
		});
		
		addAnsToKeepBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {				
				try {
					if(!ansToKeepTF.getText().isBlank()) {
						for (ViewEventsListener l : listeners) 
							l.addAmeAnsCreateTestUIEvent(Integer.valueOf(ansToKeepTF.getText()), Integer.valueOf(idCreateTestTF.getText()));	
						ansToKeepTF.clear();
					} else {
						popMessageToUI("Please enter a number.");
					}
					
				} catch (NumberFormatException e) {
					idCreateTestTF.clear();
					popMessageToUI("Please enter a number.");
				} 
			}
		});
		
		doneAddingAnsBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {								
					for (ViewEventsListener l : listeners) {
						l.doneAddingAmeAnsCreateTestUIEvent(Integer.valueOf(idCreateTestTF.getText()));
					}
								
			}
		});
		
		createBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				for (ViewEventsListener l : listeners) 
					l.CreateTestUIEvent();
				for (ViewEventsListener l : listeners) 
					l.ctrAddQuestionStockToUIEvent();				
				createTestStockSP.setContent(new Label(questionStockLbl.getText()));
			}
		});
			
		cancelcreateTestBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {
				for (ViewEventsListener l : listeners) 
					l.cancelCreateTestUIEvent();
			}
		});
		
		allTabs.get("createTestTab").setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				for (ViewEventsListener l : listeners) 
					l.ctrAddQuestionStockToUIEvent();	
				
				createTestStockSP.setContent(new Label(questionStockLbl.getText()));
				createTestLbl.setText("");
			}			
		});	
		
		createTestGPRoot.add(new Label("Enter question ID:"), 0, 0);
		createTestGPRoot.add(idCreateTestTF, 1, 0);
		createTestGPRoot.add(addCreateTestBT, 2, 0); 
		createTestGPRoot.add(amountOfQueCreateTestLbl, 3, 0);
		createTestGPRoot.add(ansToKeepLbl, 0, 1);
		createTestGPRoot.add(ansToKeepTF, 1, 1);	
		createTestGPRoot.add(addAnsToKeepBT, 2, 1);
		createTestGPRoot.add(doneAddingAnsBT, 1, 2);
		createTestGPRoot.add(createBT, 1, 3);
		createTestGPRoot.add(createTestLbl, 0, 4);
		createTestGPRoot.add(cancelcreateTestBT, 2, 3);
		createTestGPRoot.add(createTestStockSP, 5, 0);
		
		
		ScrollPane createTestSP = new ScrollPane();
		createTestSP.setContent(createTestGPRoot);
		allTabs.get("createTestTab").setContent(createTestSP);
		
//---------------------------automaticTestGenerator
		
		automaticTestGeneratorGPRoot.setPadding(new Insets(10));
		automaticTestGeneratorGPRoot.setHgap(10);
		automaticTestGeneratorGPRoot.setVgap(10);
		
		TextField queAmoTF = new TextField();
		Button generateAutoTestBT = new Button("Generate");
		ScrollPane automaticTestGeneratorStockSP = new ScrollPane();
		automaticTestGeneratorStockSP.setPrefViewportWidth(700);
		automaticTestGeneratorStockSP.setPrefViewportHeight(500);
		GridPane.setRowSpan(automaticTestGeneratorStockSP, 20);
		
		generateAutoTestBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {				
				try {
					if(!queAmoTF.getText().isBlank()) {
						for (ViewEventsListener l : listeners) 
							l.autoTestUIEvent(Integer.valueOf(queAmoTF.getText()));
						for (ViewEventsListener l : listeners) 
							l.ctrAddQuestionStockToUIEvent();				
						automaticTestGeneratorStockSP.setContent(new Label(questionStockLbl.getText()));
						queAmoTF.clear();						
					}
					
				} catch (NumberFormatException e) {
					queAmoTF.clear();
					popMessageToUI("Please enter a number.");
				} 
			}
		});
		
		allTabs.get("automaticTestGeneratorTab").setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				for (ViewEventsListener l : listeners) 
					l.ctrAddQuestionStockToUIEvent();	
				
				automaticTestGeneratorStockSP.setContent(new Label(questionStockLbl.getText()));
				autoTestLbl.setText("");
			}
		});	
			
		automaticTestGeneratorGPRoot.add(new Label("Enter amount of questions:"), 0, 0);
		automaticTestGeneratorGPRoot.add(queAmoTF, 1, 0);
		automaticTestGeneratorGPRoot.add(generateAutoTestBT, 2, 0);
		automaticTestGeneratorGPRoot.add(autoTestLbl, 0, 2);
		automaticTestGeneratorGPRoot.add(automaticTestGeneratorStockSP, 5, 0);
		
		ScrollPane automaticTestGeneratorSP = new ScrollPane();
		automaticTestGeneratorSP.setContent(automaticTestGeneratorGPRoot);
		allTabs.get("automaticTestGeneratorTab").setContent(automaticTestGeneratorSP);
		
//---------------------------createACloneOfTest
		
		createACloneOfTestGPRoot.setPadding(new Insets(10));
		createACloneOfTestGPRoot.setHgap(10);
		createACloneOfTestGPRoot.setVgap(10);
		
		Button cloneBT = new Button("Clone");

		
		cloneBT.setOnAction(new EventHandler<ActionEvent>() {						
			public void handle(ActionEvent action) {				
				for (ViewEventsListener l : listeners) {
					l.cloneUIEvent();
				}
			}
		});
		
		createACloneOfTestGPRoot.add(cloneBT, 0, 0);
		
		ScrollPane createACloneOfTestSP = new ScrollPane();
		createACloneOfTestSP.setContent(createACloneOfTestGPRoot);
		allTabs.get("createACloneOfTestTab").setContent(createACloneOfTestSP);
		
//---------------------------
		
		for(String s: allTabs.keySet()) {
			allTabs.get(s).setClosable(false);
			tpRoot.getTabs().add(allTabs.get(s));
		}		
		Tab quitTB = new Tab("Save and Quit");
				
		quitTB.setOnSelectionChanged(new EventHandler<Event>() {
			public void handle(Event e) {
				for (ViewEventsListener l : listeners)
					l.exitUIEvent();
				Platform.exit();
			}
		});

		tpRoot.getTabs().add(quitTB);
		
		theStage.setScene(new Scene(tpRoot,1200,600));
		theStage.show();
	}
	
	public void addAmericanQue(String s) {
		for (ViewEventsListener l : listeners) 
			l.ctrAddAmericanQuesAndAnsToUIEvent(s, americanAnswers, americanAnswersIsCorrect);
			americanAnswers.clear();
			americanAnswersIsCorrect.clear();	
		
	}
	
	public void popMessageToUI(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void registerListener(ViewEventsListener listener) {
		listeners.add(listener);
	}

	public void showQuestionStockUI(String str) {
		questionStockLbl.setText(str);	
	}
	
	public void updateAmericanAnsToUI() {
		updateAmericanAnsBT.setVisible(true);
		updateOpenAnsBT.setVisible(false);
		ansNumTF.setDisable(false);
		ansTxtTF.setDisable(false);
		idAnsTF.setDisable(true);
		nextBT.setDisable(true);
	}
	
	public void updateOpenAnsToUI() {
		updateOpenAnsBT.setVisible(true);
		ansNumTF.setDisable(true);
		updateAmericanAnsBT.setVisible(false);
		ansTxtTF.setDisable(false);
		idAnsTF.setDisable(true);
		nextBT.setDisable(true);
	}
	
	public void resetUpdateAns() {
		ansTxtTF.clear();
		idAnsTF.clear();
		ansNumTF.clear();
		idAnsTF.setDisable(false);
		nextBT.setDisable(false);
		ansNumTF.setDisable(true);
		ansTxtTF.setDisable(true);
		updateOpenAnsBT.setVisible(false);
		updateAmericanAnsBT.setVisible(false);				
	}
	
	public void showAutoTest(String test) {
		autoTestLbl.setText("Test:\n\n" + test);
		GridPane.setColumnSpan(autoTestLbl, 3);
	}
	
	public void createTestQueAme() {
		ansNumTF.setVisible(true);	
		addAnsToKeepBT.setVisible(true);
		ansToKeepTF.setVisible(true);
		ansToKeepLbl.setVisible(true);
		doneAddingAnsBT.setVisible(true);
		idCreateTestTF.setDisable(true);
		addCreateTestBT.setDisable(true);
		createBT.setDisable(true);
	}
	
	public void doneAddingAmeAnsToUI() {
		ansNumTF.setVisible(false);	
		addAnsToKeepBT.setVisible(false);
		ansToKeepTF.setVisible(false);
		ansToKeepLbl.setVisible(false);
		doneAddingAnsBT.setVisible(false);
		idCreateTestTF.setDisable(false);
		addCreateTestBT.setDisable(false);
		createBT.setDisable(false);
		idCreateTestTF.clear();
	}
	
	public void showCreateTest(String test) {
		createTestLbl.setText("Test:\n\n" + test);
		GridPane.setColumnSpan(createTestLbl, 3);
	}
	
	public void updateQuestionsInCreateTestToUI(int amount) {
		amountOfQueCreateTestLbl.setText("Questions added: " + amount);
	}
}
