package classes.models;
/**
 * The InsufficientFundsException class represents an exception that is thrown
 * when a user attempts an operation but doesn't have enough funds to perform it.
 */
public class InsufficientFundsException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1232770453111146828L;

	/**
     * Constructs a new InsufficientFundsException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
