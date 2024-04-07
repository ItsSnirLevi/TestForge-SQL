package classes;

import java.io.Serializable;
import java.util.Vector;

public class Test implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Question> questions;
	
	public Test() {
		questions = new Vector<Question>();
	}
	
	@SuppressWarnings("unchecked")
	public Test(Vector<Question> questions) throws CloneNotSupportedException {
		this.questions = (Vector<Question>)questions.clone();
		setQuestions(questions);
	}
		
	
	public void setQuestions(Vector<Question> questions) throws CloneNotSupportedException {
		for (int i = 0; i < questions.size(); i++) {
			if(questions.get(i) instanceof OpenQuestion) {
				this.questions.set(i, (OpenQuestion)questions.get(i).clone());
			} else {
				this.questions.set(i, (AmericanQuestion)questions.get(i).clone());
			}		
		}
	}
		
	public Vector<Question> getQuestions() {
		return questions;
	}

	@Override
	public Test clone() throws CloneNotSupportedException {
		Test tmp = new Test(this.questions);
		return tmp;
	}
	
	public String toString() {
		String ret = "";
		for (int i = 0; i < questions.size(); i++) {
			ret += questions.get(i).toString() + "\n";
		}
		return ret;
	}
}

