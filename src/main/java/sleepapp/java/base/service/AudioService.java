package sleepapp.java.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sleepapp.java.base.DAO.AudioDAO;
import sleepapp.java.base.DAO.UserDAO;
import sleepapp.java.base.domain.AudioDomain;

@Service
public class AudioService {
	
	@Autowired
	private AudioDAO audioDao;
	
	@Autowired
	private UserDAO userDao;
	
	public void receiveEncodedAudio(AudioDomain requestedAudio) throws Exception {
		
		if (userDao.findByUserId(requestedAudio.getUserId()) == null ) {
			
			throw new Exception("User Not Found");
		}
		
		audioDao.insert(requestedAudio);

	}
}
