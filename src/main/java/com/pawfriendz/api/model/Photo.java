package com.pawfriendz.api.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Document(collection="photos")
public class Photo {
	@Id
	private String id;
	@NonNull
	@lombok.NonNull
	private String userId;
	@NonNull
	@lombok.NonNull
	private Binary image;
}
