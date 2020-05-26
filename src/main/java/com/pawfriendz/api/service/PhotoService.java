package com.pawfriendz.api.service;

import java.util.Optional;

import com.pawfriendz.api.model.Photo;
import com.pawfriendz.dto.PhotoDTO;

public interface PhotoService {
	public Optional<Photo> savePhoto(PhotoDTO photoDTO);
}
