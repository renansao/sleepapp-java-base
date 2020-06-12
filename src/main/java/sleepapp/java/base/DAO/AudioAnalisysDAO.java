package sleepapp.java.base.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;

import sleepapp.java.base.domain.AudioAnalisysDomain;

public interface AudioAnalisysDAO extends MongoRepository<AudioAnalisysDomain, String>{
	
}
