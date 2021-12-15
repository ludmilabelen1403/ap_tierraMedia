package persitence;

public class MissingDataException extends RuntimeException{

	
	
	private static final long serialVersionUID = 2707773507133107634L;

	public MissingDataException(Exception e) {
		super(e);
		
	}
	
}
