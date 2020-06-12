package sleepapp.java.base.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("AudioAnalisysCollection")
public class AudioAnalisysDomain {
	
	@NotNull
	private String audioId;
	
	private String possibleSpeech;

	public String getAudioId() {
		return audioId;
	}

	public void setAudioId(String audioId) {
		this.audioId = audioId;
	}

	public String getPossibleSpeech() {
		return possibleSpeech;
	}

	public void setPossibleSpeech(String possibleSpeech) {
		this.possibleSpeech = possibleSpeech;
	}
	
}