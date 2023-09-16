package com.taskhub.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.taskhub.model.User;

public class Utils {
	
	public static String getImageURL(User user,  MultipartFile profilePhoto) throws IOException {
		String rootFolder = "D:/Parimal/TaskHub/taskhub-frontend/src/";
		String folder = "assets/" + user.getUserName() + "/images/";
		Files.createDirectories(Paths.get(rootFolder + folder));
		
		String fileName = profilePhoto.getOriginalFilename();
		Path filePath = Paths.get(rootFolder + folder, fileName);
		
		profilePhoto.transferTo(filePath);
		
		return folder + fileName;
	}
}
