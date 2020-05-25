package com.pawfriendz.api.serviceimpl;

import java.io.IOException;
import java.util.Optional;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawfriendz.api.model.Photo;
import com.pawfriendz.api.service.PhotoService;
import com.pawfriendz.dto.PhotoDTO;
import com.pawfriendz.repository.PhotoRepository;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
	@Autowired
	protected PhotoRepository photoRepository;

	@Override
	public Optional<Photo> savePhoto(PhotoDTO photoDTO) {
		Optional<Photo> photoOptional = Optional.empty();
		try {
			photoOptional = Optional.ofNullable(photoRepository.save(new Photo(photoDTO.getUserId(),
																	 new Binary(photoDTO.getPhoto().getBytes()))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return photoOptional;
	}
}
