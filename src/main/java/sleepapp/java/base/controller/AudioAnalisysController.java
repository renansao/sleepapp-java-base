package sleepapp.java.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sleepapp.java.base.domain.AudioAnalisysDomain;

@RestController
public class AudioAnalisysController {

	@PostMapping("/receiveAudioAnalisys")
	public ResponseEntity<?> receiveAudioAnalisys(@RequestBody AudioAnalisysDomain audioAnalisysRequest){
		return null;
	}
}