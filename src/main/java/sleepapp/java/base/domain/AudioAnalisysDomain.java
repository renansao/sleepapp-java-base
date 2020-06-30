package sleepapp.java.base.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("AudioAnalisysCollection")
public class AudioAnalisysDomain {
	
	@NotNull
	private String audioId;
	
	private String possibleSpeech;
	
	private String didSpeak;
	
	public String getAuidoId() {
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

	public String getDidSpeak() {
		return didSpeak;
	}

	public void setDidSpeak(String didSpeak) {
		this.didSpeak = didSpeak;
	}
	
}