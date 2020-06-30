package sleepapp.java.base.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
		
		audioToBeInserted.setAudioName(requestedAudio.getAudioName());
		audioToBeInserted.setUsername(username);
		audioToBeInserted.setInclusionDate(new Date());
		audioToBeInserted.setStatus("A");
		
		AudioDomain audioInserted = audioDao.insert(audioToBeInserted);
		
		String url = "http://127.0.0.1:5000/analyseAudio?token=";
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
			audioAnalysis.setDidSpeak(this.didSpeak(speech.getSpeech()));
			
			audioAnalisysDAO.insert(audioAnalysis);
			
			audioToBeInserted.setStatus("F");
			audioInserted.setFinishedProcessingDate(new Date());
			audioDao.save(audioInserted);
		}catch(Exception e) {
			audioInserted.setStatus("E");
			audioDao.save(audioInserted);
		}
		
	}

	public List<AudioDomain> retrieveAudioList(String username) {
		
		List<AudioDomain> audioList = new ArrayList<AudioDomain>();
		
		for (AudioDomain audio : audioDao.findByUsername(username)  ) {
			
			AudioAnalisysDomain audioAnalisys = audioAnalisysDAO.findByAudioId(audio.getAudioId());
			AudioDomain audioResponse = new AudioDomain();
			
			audioResponse = audio;
			audioResponse.setAudioAnalisys(audioAnalisys);
			
			audioList.add(audioResponse);
		}
		
		Collections.reverse(audioList);
		
		return audioList;
		
	}

	public AudioAnalisysDomain retrieveAudioAnalisys(String username, String audioId) throws Exception {
		
		AudioDomain audioDomain = audioDao.findByAudioId(audioId);
		
		if (!audioDomain.getUsername().equalsIgnoreCase(username)) {
			throw new Exception("Token subject does not match with username");
		}
		
		AudioAnalisysDomain audioAnalysis = audioAnalisysDAO.findByAudioId(audioId);

		return audioAnalysis;
	}
	
	private String didSpeak (String possibleSpeech) {
		
		String[] words;
		words = possibleSpeech.split(" ");
		
		if (words.length > 3) {
			return "S";
			
		}else if (words.length == 0) {
			return "N";
			
		}else {
			
			return "I";
		}

	}
}
