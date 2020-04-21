package sleepapp.java.base.domain;

import javax.validation.constraints.NotNull;

public class AudioDetails {
	
	@NotNull
	private String encodedAudio;
	
	@NotNull
	private String audioFormat;

	public String getEncodedAudio() {
		return encodedAudio;
	}

	public void setEncodedAudio(String encodedAudio) {
		this.encodedAudio = encodedAudio;
	}

	public String getAudioFormat() {
		return audioFormat;
	}

	public void setAudioFormat(String audioFormat) {
		this.audioFormat = audioFormat;
	}
	
}
