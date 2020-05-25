package com.pawfriendz.api.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.pawfriendz.dto.PhotoDTO;

@Component
public class VaildationUtils {
	
	private static final String ALPHANUMERIC_REGEX_PATTERN = "^[a-zA-Z0-9]+$";
		
	public boolean areSinglePhotoParametersValid(String userId, String photoId) {
		return isValidStringForRegex(userId, ALPHANUMERIC_REGEX_PATTERN) &&
				isValidStringForRegex(photoId, ALPHANUMERIC_REGEX_PATTERN);
	}
	
	public boolean isUserIdValid(String userId) {
		return isValidStringForRegex(userId, ALPHANUMERIC_REGEX_PATTERN);
	}
	
	public boolean validatePhotoDTO(PhotoDTO photoDTO) {
		boolean isPhotoDTOValid = true;
		if (StringUtils.isEmpty(photoDTO.getUserId()) || photoDTO.getPhoto() == null)
				isPhotoDTOValid = false;
		return isPhotoDTOValid;
	}
	
	private boolean isValidStringForRegex(String string, String regex) {
		return Pattern.compile(regex).matcher(string).matches();
	}
}
