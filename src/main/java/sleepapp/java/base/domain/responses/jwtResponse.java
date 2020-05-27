package sleepapp.java.base.domain.responses;

public class jwtResponse {
	
	public jwtResponse() {
	}
	
	public jwtResponse(String jwt) {
		super();
		this.setJwt(jwt);
	}
	
	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}