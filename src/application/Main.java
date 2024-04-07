package application;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import classes.ProjectController;
import classes.ProjectModel;
import classes.ProjectView;
import classes.QuestionStock;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("QuestionStock.dat"));
		QuestionStock qs = (QuestionStock)inFile.readObject();
		inFile.close();
				
		ProjectModel pm = new ProjectModel(qs);
		ProjectView pv = new ProjectView(new Stage());
		@SuppressWarnings("unused")
		ProjectController ctr = new ProjectController(pv, pm);
				
		} catch(Exception e){
			System.out.println(e.getMessage());			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
