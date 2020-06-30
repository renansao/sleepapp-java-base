package sleepapp.java.base.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document("AudioCollection")
public class AudioDomain {
	
	@Id
	private String audioId;
	
	@NotNull
	private String audioName;
	
	@Null
	private String username;
	
	@NotNull
	private AudioDetails audioDetails;
	
	@NotNull
	private String status;
	
	@DateTimeFormat
	@Null
	private Date inclusionDate;
	
	@Null
	@DateTimeFormat
	private Date finishedProcessingDate;
	
	@Null
	private AudioAnalisysDomain audioAnalisys;
	
	public AudioAnalisysDomain getAudioAnalisys() {
		return audioAnalisys;
	}

	public void setAudioAnalisys(AudioAnalisysDomain audioAnalisys) {
		this.audioAnalisys = audioAnalisys;
	}
	
	public Date getInclusionDate() {
		return inclusionDate;
	}

	public void setInclusionDate(Date queueInclusionDate) {
		this.inclusionDate = queueInclusionDate;
	}
	
	public Date getFinishedProcessingDate() {
		return finishedProcessingDate;
	}

	public void setFinishedProcessingDate(Date finishedProcessingDate) {
		this.finishedProcessingDate = finishedProcessingDate;
	}

	public String getAudioId() {
		return audioId;
	}

	public AudioDetails getAudioDetails() {
		return audioDetails;
	}

	public void setAudioDetails(AudioDetails audioDetails) {
		this.audioDetails = audioDetails;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAudioName() {
		return audioName;
	}

	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	
	
	
}
