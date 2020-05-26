package com.pawfriendz.api.controller;


import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawfriendz.api.model.Photo;
import com.pawfriendz.api.service.PhotoService;
import com.pawfriendz.dto.PhotoDTO;

@RestController
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@PostMapping(path="/photos/upload")
	public ResponseEntity<Photo> uploadPhoto(@ModelAttribute PhotoDTO photoDTO) throws IOException {
		if (validatePhotoDTO(photoDTO)) {
			return persistPhotoDTO(photoDTO);
		} else {
			throw new NullPointerException("Null UserId or Image");
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
	
	private boolean validatePhotoDTO(PhotoDTO photoDTO) {
		boolean isPhotoDTOValid = true;
		if (StringUtils.isEmpty(photoDTO.getUserId()) || photoDTO.getPhoto() == null)
				isPhotoDTOValid = false;
		return isPhotoDTOValid;
	}
}
