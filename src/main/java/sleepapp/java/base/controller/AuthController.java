package sleepapp.java.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sleepapp.java.base.DAO.UserDAO;
import sleepapp.java.base.domain.RequestAuth;
import sleepapp.java.base.domain.UserDomain;
import sleepapp.java.base.service.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserDAO userDao;
	
	@PostMapping(value="/autenticate")
	public ResponseEntity<?> test(@RequestBody RequestAuth requestAuth) {
		
		UserDomain user = userDao.findByEmail(requestAuth.getUsername());
		boolean Authenticated = false;
		
		
		if (user == null) {
			authService.createUser(requestAuth);
			Authenticated = true;
		}else {
			Authenticated = authService.authenticate(requestAuth);
		}
		
		//try {
		//	hash = authService.hashPassword(password);
		//}catch(Exception e) {
		//	
		//	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		//}
		
		return new ResponseEntity<>(Authenticated, HttpStatus.OK);
	}
}
