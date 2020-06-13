package sleepapp.java.base.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sleepapp.java.base.DAO.AudioAnalisysDAO;
import sleepapp.java.base.DAO.AudioDAO;
import sleepapp.java.base.DAO.UserDAO;
import sleepapp.java.base.domain.AudioAnalisysDomain;
import sleepapp.java.base.domain.AudioDomain;
import sleepapp.java.base.domain.SpeechDomain;
import sleepapp.java.base.domain.UserDomain;
import sleepapp.java.base.domain.utils.AudioAnalysisDomainRequest;

@Service
public class AudioService {
	
	@Autowired
	private AudioDAO audioDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private JsonService jsonService;
	
	@Autowired 
	private AudioAnalisysDAO audioAnalisysDAO;
	
	public void receiveEncodedAudio(String token, String username, AudioDomain requestedAudio) throws Exception {
		
		UserDomain user = userDao.findByEmail(username);
		
		if (user == null ) {
			throw new Exception("User Not Found");
		}
		
		requestedAudio.setUsername(username);
		AudioDomain audioToBeInserted = new AudioDomain();
		
		audioToBeInserted.setUsername(username);
		audioToBeInserted.setInclusionDate(new Date());
		audioToBeInserted.setStatus("A");
		AudioDomain audioInserted = audioDao.insert(audioToBeInserted);
		
		String url = "https://sleepapp-py3-audio-analysis.herokuapp.com/analyseAudio?token=";
		url += token;
		
		AudioAnalysisDomainRequest audioRequest = new AudioAnalysisDomainRequest();
		
		audioRequest.setAudioId(audioInserted.getAudioId());
		audioRequest.setEncodedAudio(requestedAudio.getAudioDetails().getEncodedAudio());
		
		String json = jsonService.toJson(audioRequest);
		
		try {
			
			//Call PYTHON API WITH requestedAudio
			String response = apiService.post(url, json);
			
			System.out.println("Resposta API (audioAnalisys): " + response);
			
			SpeechDomain speech = (SpeechDomain) jsonService.toObject(response, SpeechDomain.class);
			
			AudioAnalisysDomain audioAnalysis = new AudioAnalisysDomain();
			audioAnalysis.setAudioId(audioInserted.getAudioId());
			audioAnalysis.setPossibleSpeech(speech.getSpeech());
			
			audioAnalisysDAO.insert(audioAnalysis);
			
			audioToBeInserted.setStatus("F");
			audioDao.save(audioInserted);
		}catch(Exception e) {
			audioInserted.setStatus("E");
			audioDao.save(audioInserted);
		}
		
	}
}
