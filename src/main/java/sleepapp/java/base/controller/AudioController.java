package sleepapp.java.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sleepapp.java.base.domain.AudioDomain;
import sleepapp.java.base.service.AudioService;
import sleepapp.java.base.service.JwtService;

@RestController
public class AudioController {
	
	@Autowired
	private AudioService baseService;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping(value="/receiveEncodedAudio")
	public ResponseEntity<?> receiveEncodedAudio(HttpServletRequest req, @RequestBody AudioDomain requestedAudio) {
		
		System.out.println(req.getHeader("Authorization"));
		System.out.println(checkJWTToken(req));
		String tokenSubject = jwtService.retrieveSub(req);
		System.out.println(tokenSubject);
		
		
		
		try {
			baseService.receiveEncodedAudio(jwtService.retrieveToken(req), tokenSubject, requestedAudio);
		}catch(Exception e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private boolean checkJWTToken(HttpServletRequest request) {
		String HEADER = "Authorization";
		String PREFIX = "Bearer ";
		
		String authenticationHeader = request.getHeader(HEADER);
		
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}
	
	//@GetMapping(value="/retrieveAudioList/{userId}")
	//public ResponseEntity<?> retrieveAudioList(@PathVariable String userId){
	//
	//	List<AudioDomain>
	//	
	//	try {
	//		baseService.retrieveAudioList(userId);
	//	}
	//}
	
}
