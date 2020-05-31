package sleepapp.java.base.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonService {
	
	public String toJson (Object objeto) throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.writeValueAsString(objeto);
	}
}
