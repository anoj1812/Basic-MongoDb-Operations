package com.finsurge.Task39to41.repository;
import com.finsurge.Task39to41.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
