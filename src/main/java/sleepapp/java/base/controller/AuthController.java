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
import sleepapp.java.base.service.JwtService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping(value="/autenticate")
	public ResponseEntity<?> autenticate(@RequestBody RequestAuth requestAuth) {
		
		UserDomain user = userDao.findByEmail(requestAuth.getUsername());
		boolean Authenticated = false;
		
		
		if (user == null) {
			authService.createUser(requestAuth);
			Authenticated = true;
		}else {
			Authenticated = authService.authenticate(requestAuth);
		}
		
		String jwt = jwtService.createJwt(requestAuth.getUsername());
		
		jwtService.parseKey(jwt);
		
		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}
}
