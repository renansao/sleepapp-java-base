package sleepapp.java.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import sleepapp.java.base.service.BaseService;

@RestController
public class BaseController {
	
	@Autowired
	BaseService baseService;
	
	@PostMapping(value="/receiveEncodedAudio")
	public String sendEncodedAudio() {
		
		return baseService.receiveEncodedAudio();
	}
	
}
