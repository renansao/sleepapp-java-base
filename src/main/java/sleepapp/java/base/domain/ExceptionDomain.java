package sleepapp.java.base.domain;

public class ExceptionDomain {
	
	public ExceptionDomain() {
		
	}
	
	public ExceptionDomain(String errorMessage, Integer statusCode) {
		super();
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}
	
	private String errorMessage;
	
	private Integer statusCode;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
}