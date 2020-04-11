package sleepapp.java.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sleepapp.java.base.domain.UserDomain;
import sleepapp.java.base.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping(value="/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserDomain userRequested) {
		
		UserDomain userRetrieved = new UserDomain();
		
		try {
			
			userRetrieved = loginService.registerUser(userRequested);
			
		}catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(userRetrieved, HttpStatus.OK);
	}
}
