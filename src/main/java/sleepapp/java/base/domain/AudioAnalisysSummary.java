package sleepapp.java.base.domain;

public class AudioAnalisysSummary {
	
	private String username;
	
	private int spoke;
	
	private int inconclusive;
	
	private int didntSpeak;
	
	private int error;
	
	private String shouldGoToDoctor;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getSpoke() {
		return spoke;
	}

	public void setSpoke(int spoke) {
		this.spoke = spoke;
	}

	public int getInconclusive() {
		return inconclusive;
	}

	public void setInconclusive(int inconclusive) {
		this.inconclusive = inconclusive;
	}

	public int getDidntSpeak() {
		return didntSpeak;
	}

	public void setDidntSpeak(int didntSpeak) {
		this.didntSpeak = didntSpeak;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getShouldGoToDoctor() {
		return shouldGoToDoctor;
	}

	public void setShouldGoToDoctor(String shouldGoToDoctor) {
		this.shouldGoToDoctor = shouldGoToDoctor;
	}
	
}
