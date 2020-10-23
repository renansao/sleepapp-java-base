package sleepapp.java.base.domain;

import javax.validation.constraints.NotNull;

public class FileKeySaveDomain {
	
	@NotNull
	private String audioId;
	
	@NotNull
	private String pdfKey;
	
	@NotNull
	private String audioKey;

	public String getAudioId() {
		return audioId;
	}

	public void setAudioId(String audioId) {
		this.audioId = audioId;
	}

	public String getPdfKey() {
		return pdfKey;
	}

	public void setPdfKey(String pdfKey) {
		this.pdfKey = pdfKey;
	}

	public String getAudioKey() {
		return audioKey;
	}

	public void setAudioKey(String audioKey) {
		this.audioKey = audioKey;
	}
	
}