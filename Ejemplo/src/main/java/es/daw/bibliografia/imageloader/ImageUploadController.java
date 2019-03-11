package es.daw.bibliografia.imageloader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.user.User;

@Service
public class ImageUploadController {
	
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	
	public String handleFileUpload(User user, 
			@RequestParam("file") MultipartFile file, String name) {
		
		String fileName = name + "-img.jpg";

		if (!file.isEmpty()) {
			try {

				File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
				file.transferTo(uploadedFile);
				System.out.println(uploadedFile.getAbsolutePath());
				
				String path = uploadedFile.getAbsolutePath();
				return path;

			} catch (Exception e) {

				
			}
		} else {
			return "";
		}
		return "error";
	}

}
