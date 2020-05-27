package sleepapp.java.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import sleepapp.java.base.DAO.UserDAO;
import sleepapp.java.base.domain.ExceptionDomain;
import sleepapp.java.base.domain.RequestAuth;
import sleepapp.java.base.domain.UserDomain;
import sleepapp.java.base.domain.responses.jwtResponse;
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
	
	@PostMapping(value="/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody RequestAuth requestAuth) throws JsonProcessingException {
		
		UserDomain user = userDao.findByEmail(requestAuth.getUsername());
		
		if (user == null) {
			authService.createUser(requestAuth);
		}else {
			try {
				authService.authenticate(requestAuth);
			} catch (Exception e) {
				ExceptionDomain exceptionDomain = new ExceptionDomain(e.getMessage(), 403);
				return new ResponseEntity<>(exceptionDomain, HttpStatus.FORBIDDEN);
			}
		}
		
		String jwt = jwtService.createJwt(requestAuth.getUsername());
		
		try {
			jwtService.parseKey(jwt);
		}catch (Exception e) {
			ExceptionDomain exceptionDomain = new ExceptionDomain(e.getMessage(), 403);
			return new ResponseEntity<>(exceptionDomain, HttpStatus.FORBIDDEN);
		}
		
		jwtResponse jwtResponse = new jwtResponse(jwt);
		
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
	}
}
