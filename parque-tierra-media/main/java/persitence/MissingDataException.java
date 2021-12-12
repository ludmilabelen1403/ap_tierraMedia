package persitence;

public class MissingDataException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingDataException(Exception e) {
		super(e);
		
	}
	
}
