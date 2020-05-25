package com.pawfriendz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pawfriendz.api.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {
	
}
