package sleepapp.java.base.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

@Service
public class ApiService {
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	public String post(String url, String json) throws IOException {
		  RequestBody body = RequestBody.create(JSON, json);
		  
		  OkHttpClient client = new OkHttpClient();
		  
		  Request request = new Request.Builder()
		      .url(url)
		      .post(body)
		      .build();
		 
		  Response response = client.newCall(request).execute();
		  
		  return response.body().string();
		  
		}
	
}
