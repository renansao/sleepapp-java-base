package sleepapp.java.base.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonService {
	
	public String toJson (Object objeto) throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.writeValueAsString(objeto);
	}
	
	public Object toObject (String json, Class<?> classe) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.readValue(json, classe);
	}
}
