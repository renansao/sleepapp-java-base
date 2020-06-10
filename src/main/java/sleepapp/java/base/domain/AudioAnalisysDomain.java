package sleepapp.java.base.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("AudioAnalisysCollection")
public class AudioAnalisysDomain {
	
	@NotNull
	private String audioId;
	
	private AudioAnalisys1 audioAnalisys1;

	public String getAudioId() {
		return audioId;
	}

	public void setAudioId(String audioId) {
		this.audioId = audioId;
	}

	public AudioAnalisys1 getAudioAnalisys1() {
		return audioAnalisys1;
	}

	public void setAudioAnalisys1(AudioAnalisys1 audioAnalisys1) {
		this.audioAnalisys1 = audioAnalisys1;
	}
	
}