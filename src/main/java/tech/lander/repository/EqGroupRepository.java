package tech.lander.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.lander.domain.Qgroup;

import java.util.List;

public interface EqGroupRepository extends MongoRepository<Qgroup, String> {

    List<Qgroup> findAll();
}
