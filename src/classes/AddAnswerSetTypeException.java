package classes;

public class AddAnswerSetTypeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddAnswerSetTypeException(String msg) {
		super(msg);
	}

	public AddAnswerSetTypeException() {
		super("Adding Answers Exception: incorrect type.");
	}
}
