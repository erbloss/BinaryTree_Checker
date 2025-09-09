package project_3;
/**
 * To throw exception if tree input
 * is in some way invalid. 
 
 * 
 * author: Elizabeth Bloss
 * UMGC CMSC315
 * June 2024
 * Project 3
 * 
 */
public class InvalidSyntaxException extends Exception{

	private static final long serialVersionUID = 1L;
	
	// constructor
	public InvalidSyntaxException(String message) {
		super(message);
	}

}
