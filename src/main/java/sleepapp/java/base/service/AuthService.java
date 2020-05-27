package sleepapp.java.base.service;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;
import static com.kosprov.jargon2.api.Jargon2.jargon2Verifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosprov.jargon2.api.Jargon2.Hasher;
import com.kosprov.jargon2.api.Jargon2.Type;
import com.kosprov.jargon2.api.Jargon2.Verifier;

import sleepapp.java.base.DAO.UidDAO;
import sleepapp.java.base.DAO.UserDAO;
import sleepapp.java.base.domain.RequestAuth;
import sleepapp.java.base.domain.UidDomain;
import sleepapp.java.base.domain.UserDomain;

@Service
public class AuthService {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired 
	private UidDAO uidDao;
	
	public void createUser(RequestAuth requestAuth) {

		String uidHahsed = hashPassword(requestAuth.getUid());
		
		UserDomain userDomain = new UserDomain();
		userDomain.setNome(requestAuth.getName());
		userDomain.setEmail(requestAuth.getUsername());
		
		UidDomain uidDomain = new UidDomain();
		uidDomain.setUsername(requestAuth.getUsername());
		uidDomain.setUid(uidHahsed.substring(30));
		
		//userDomain.setUid(uidHahsed.substring(30));
		
		userDao.insert(userDomain);
		uidDao.insert(uidDomain);
		
	}

	public boolean authenticate(RequestAuth requestAuth) {
		
		//UserDomain user = userDao.findByEmail(requestAuth.getUsername());
		UidDomain uidDomain = uidDao.findByUsername(requestAuth.getUsername());
		
		Verifier verifier = jargon2Verifier();
		
		byte[] uidByte = requestAuth.getUid().getBytes();
		
		return verifier.hash("$argon2d$v=19$m=65536,t=3,p=4$"+uidDomain.getUid()).password(uidByte).verifyEncoded();
		
	}
	
	private String hashPassword(String passwordStr) {
		
		byte[] password = passwordStr.getBytes();
		
		// Configure the hasher
        Hasher hasher = jargon2Hasher()
                .type(Type.ARGON2d) // Data-dependent hashing
                .memoryCost(65536)  // 64MB memory cost
                .timeCost(3)        // 3 passes through memory
                .parallelism(4)     // use 4 lanes and 4 threads
                .saltLength(16)     // 16 random bytes salt
                .hashLength(16);    // 16 bytes output hash
        
        

        // Set the password and calculate the encoded hash
        String encodedHash = hasher.password(password).encodedHash();

        //System.out.printf("Hash: %s%n", encodedHash.substring(30));

        // Just get a hold on the verifier. No special configuration needed
        //Verifier verifier = jargon2Verifier();
        
        // Set the encoded hash, the password and verify
        //boolean matches = verifier.hash("$argon2d$v=19$m=65536,t=3,p=4$"+encodedHash.substring(30)).password(password).verifyEncoded();
		
        //System.out.printf("Matches: %s%n", matches);
        
		return encodedHash;
		
	}

}