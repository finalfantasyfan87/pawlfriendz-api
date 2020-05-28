package com.pawfriendz.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pawfriendz.api.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {
	Optional<Photo> findByUserIdAndId(String userId, String photoId);
	Collection<Optional<Photo>> findByUserId(String userId);
}
