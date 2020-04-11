package sleepapp.java.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sleepapp.java.base.DAO.UserDAO;
import sleepapp.java.base.domain.UserDomain;

@Service
public class LoginService {
	
	@Autowired
	private UserDAO userDAO;
	
	public UserDomain registerUser(UserDomain userRequested) {
		
		if (userDAO.existsByEmail(userRequested.getEmail())) {
			
			UserDomain userRegistered = userDAO.findByEmail(userRequested.getEmail());
			return userRegistered;
		}
		
		userDAO.insert(userRequested);
		
		return userDAO.findByEmail(userRequested.getEmail());
	}
}
