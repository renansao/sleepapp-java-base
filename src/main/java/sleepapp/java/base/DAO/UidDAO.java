package sleepapp.java.base.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sleepapp.java.base.domain.UidDomain;

@Repository
public interface UidDAO extends MongoRepository<UidDomain, String>{
	
	UidDomain findByUsername(String username);
}
