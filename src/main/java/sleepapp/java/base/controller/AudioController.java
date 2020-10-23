package sleepapp.java.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sleepapp.java.base.domain.AudioAnalisysDomain;
import sleepapp.java.base.domain.AudioAnalisysSummary;
import sleepapp.java.base.domain.AudioDomain;
import sleepapp.java.base.domain.FileKeySaveDomain;
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
	
	@GetMapping(value="/retrieveAudioList")
	public ResponseEntity<?> retrieveAudioList(HttpServletRequest req){
	
		List<AudioDomain> usersAudio = new ArrayList<AudioDomain>();
		
		try {
			usersAudio = baseService.retrieveAudioList(jwtService.retrieveSub(req));
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(usersAudio, HttpStatus.OK);
	}
	
	@GetMapping(value="/retrieveAudioListSummary")
	public ResponseEntity<?> retrieveAudioListSummary (HttpServletRequest req){
		
		AudioAnalisysSummary summary = new AudioAnalisysSummary();
		
		try {
			summary = baseService.retrieveAudioListSummary(jwtService.retrieveSub(req));
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
	
	@PostMapping(value="/saveFilesKey")
	public ResponseEntity<?> saveFilesKey (HttpServletRequest req, @RequestBody FileKeySaveDomain fileKeySaveDomain){
		
		try {
			baseService.saveFilesKey(jwtService.retrieveSub(req), fileKeySaveDomain);
		}catch (Exception e) {
			
		}
		return null;
		
	}
	
	//@GetMapping(value="/retrieveAudioAnalisys/{audioId}")
	//public ResponseEntity<?> retrieveAudioAnalisys(HttpServletRequest req, @PathVariable String audioId){
	//	
	//	AudioAnalisysDomain audioAnalisys = new AudioAnalisysDomain();
	//	
	//	try {
	//		audioAnalisys = baseService.retrieveAudioAnalisys(jwtService.retrieveSub(req), audioId);
	//	}catch (Exception e) {
	//		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	//	}
	//	
	//	return new ResponseEntity<>(audioAnalisys, HttpStatus.OK);
	//}
	
}
