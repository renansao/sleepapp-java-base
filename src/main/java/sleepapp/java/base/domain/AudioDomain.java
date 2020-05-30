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
	
	@Null
	private String username;
	
	@NotNull
	private AudioDetails audioDetails;
	
	@DateTimeFormat
	@Null
	private Date queueInclusionDate;
	
	@Null
	@DateTimeFormat
	private Date finishedProcessingDate;
	
	public Date getQueueInclusionDate() {
		return queueInclusionDate;
	}

	public void setQueueInclusionDate(Date queueInclusionDate) {
		this.queueInclusionDate = queueInclusionDate;
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
	
}
