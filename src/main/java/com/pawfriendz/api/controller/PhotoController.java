package com.pawfriendz.api.controller;


import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawfriendz.api.model.Photo;
import com.pawfriendz.api.service.PhotoService;
import com.pawfriendz.api.util.VaildationUtils;
import com.pawfriendz.dto.PhotoDTO;

@RestController
public class PhotoController {	
	@Autowired
	private PhotoService photoService;
	@Autowired
	private VaildationUtils valdiationUtils;
	
	@GetMapping(path="/photos/{userId}")
	public ResponseEntity<Collection<Photo>> getAllPhotosForUser(@PathVariable("userId") String userId) {
		if (valdiationUtils.isUserIdValid(userId)) {
			return ResponseEntity.ok().body(photoService.findByUserId(userId));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path="/photos/{userId}/{photoId}")
	public ResponseEntity<Photo> getSinglePhotoForUserById(@PathVariable("userId") String userId,
																		 @PathVariable("photoId") String photoId) {
		if (valdiationUtils.areSinglePhotoParametersValid(userId, photoId)) {
			return retrievePhoto(userId, photoId);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping(path="/photos/upload")
	public ResponseEntity<Photo> uploadPhoto(@ModelAttribute PhotoDTO photoDTO) throws IOException {
		if (valdiationUtils.validatePhotoDTO(photoDTO)) {
			return persistPhotoDTO(photoDTO);
		} else {
			throw new NullPointerException("Null UserId or Image");
		}
	}
	
	private ResponseEntity<Photo> retrievePhoto(String userId, String photoId) {
		Optional<Photo> photoOptional = photoService.findByUserIdAndPhotoId(userId, photoId);
		if (photoOptional.isPresent()) {
			return ResponseEntity.ok().body(photoOptional.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	private ResponseEntity<Photo> persistPhotoDTO(PhotoDTO photoDTO) {
		Optional<Photo> photoOptional = photoService.savePhoto(photoDTO);
		if (photoOptional.isPresent()) {
			return ResponseEntity.created(null).body(photoOptional.get());
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
}
