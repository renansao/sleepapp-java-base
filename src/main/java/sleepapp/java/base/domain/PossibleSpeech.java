package sleepapp.java.base.domain;

import java.math.BigDecimal;

public class PossibleSpeech {
	
	private String speech;
	
	private BigDecimal confidence;

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public BigDecimal getAccuracy() {
		return confidence;
	}

	public void setAccuracy(BigDecimal confidence) {
		this.confidence = confidence;
	}
	
}