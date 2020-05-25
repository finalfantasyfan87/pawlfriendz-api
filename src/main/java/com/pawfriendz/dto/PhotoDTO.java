package com.pawfriendz.dto;


import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PhotoDTO {
	private String id;
	@NotNull
	private String userId;
	@NotNull
	private MultipartFile photo;
}
