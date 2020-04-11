package sleepapp.java.base.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sleepapp.java.base.domain.UserDomain;

@Repository
public interface UserDAO extends MongoRepository<UserDomain, String>{
	
	boolean existsByEmail (String email);

	UserDomain findByEmail(String email);
}
