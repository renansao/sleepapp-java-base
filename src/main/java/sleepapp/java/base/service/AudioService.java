package sleepapp.java.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sleepapp.java.base.DAO.AudioDAO;
import sleepapp.java.base.DAO.UserDAO;
import sleepapp.java.base.domain.AudioDomain;
import sleepapp.java.base.domain.UserDomain;

@Service
public class AudioService {
	
	@Autowired
	private AudioDAO audioDao;
	
	@Autowired
	private UserDAO userDao;
	
	public void receiveEncodedAudio(String username, AudioDomain requestedAudio) throws Exception {
		
		UserDomain user = userDao.findByEmail(username);
		
		if (user == null ) {
			throw new Exception("User Not Found");
		}
		
		requestedAudio.setUsername(username);

		audioDao.insert(requestedAudio);

	}
}
