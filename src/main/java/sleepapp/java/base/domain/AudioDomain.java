package sleepapp.java.base.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document("AudioCollection")
public class AudioDomain {
	
	@Id
	private String audioId;
	
	@NotNull
	private String userId;
	
	@NotNull
	private AudioDetails audioDetails;
	
	@DateTimeFormat
	private Date queueInclusionDate;
	
	@DateTimeFormat
	private Date finishedProcessingDate;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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
	
}