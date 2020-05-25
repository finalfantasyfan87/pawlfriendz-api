package com.pawfriendz.api.service;

import java.util.Collection;
import java.util.Optional;

import com.pawfriendz.api.model.Photo;
import com.pawfriendz.dto.PhotoDTO;

public interface PhotoService {
	public Optional<Photo> savePhoto(PhotoDTO photoDTO);
	public Optional<Photo> findByUserIdAndPhotoId(String userId, String photoId);
	public Collection<Photo> findByUserId(String userId);
}
