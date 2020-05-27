package sleepapp.java.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sleepapp.java.base.domain.AudioDomain;
import sleepapp.java.base.service.AudioService;

@RestController
public class AudioController {
	
	@Autowired
	private AudioService baseService;
	
	@PostMapping(value="/receiveEncodedAudio")
	public ResponseEntity<?> receiveEncodedAudio(@RequestBody AudioDomain requestedAudio) {
		
		try {
			baseService.receiveEncodedAudio(requestedAudio);
		}catch(Exception e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
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
