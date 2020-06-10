package sleepapp.java.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sleepapp.java.base.DAO.AudioDAO;
import sleepapp.java.base.DAO.UserDAO;
import sleepapp.java.base.domain.AudioDomain;
import sleepapp.java.base.domain.UserDomain;
import sleepapp.java.base.domain.utils.AudioAnalysisDomain;

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
	
	public void receiveEncodedAudio(String token, String username, AudioDomain requestedAudio) throws Exception {
		
		UserDomain user = userDao.findByEmail(username);
		
		if (user == null ) {
			throw new Exception("User Not Found");
		}
		
		requestedAudio.setUsername(username);
		
		
		AudioDomain audioToBeInserted = new AudioDomain();
		
		audioToBeInserted.setUsername(username);
		AudioDomain audioInserted = audioDao.insert(audioToBeInserted);
		
		
		
		String url = "https://sleepapp-py3-audio-analysis.herokuapp.com/analyseAudio?token=";
		url += token;
		
		AudioAnalysisDomain audioRequest = new AudioAnalysisDomain();
		
		audioRequest.setAudioId(audioInserted.getAudioId());
		audioRequest.setEncodedAudio(requestedAudio.getAudioDetails().getEncodedAudio());
		
		String json = jsonService.toJson(audioRequest);
		apiService.post(url, json);
		//Call PYTHON API WITH requestedAudio
	}
}
