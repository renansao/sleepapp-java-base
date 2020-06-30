package sleepapp.java.base.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sleepapp.java.base.domain.AudioDomain;

@Repository
public interface AudioDAO extends MongoRepository<AudioDomain, String>{
	
	public List<AudioDomain> findByUsername(String username);
	
	public AudioDomain findByAudioId (String audioId);
}
