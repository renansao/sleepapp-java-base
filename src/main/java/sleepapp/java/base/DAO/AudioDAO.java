package sleepapp.java.base.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;

import sleepapp.java.base.domain.AudioDomain;

public interface AudioDAO extends MongoRepository<AudioDomain, String>{

}
