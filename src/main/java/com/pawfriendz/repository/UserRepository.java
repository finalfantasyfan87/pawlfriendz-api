package com.pawfriendz.repository;

import com.pawfriendz.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
