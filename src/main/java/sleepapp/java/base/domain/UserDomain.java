package sleepapp.java.base.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("UserCollection")
public class UserDomain {
	
	@Id
	private String userId;
	
	private String nome;
	
	private String email;
	
	public String getUserId() {
		return userId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}