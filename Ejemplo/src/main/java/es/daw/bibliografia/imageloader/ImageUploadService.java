package es.daw.bibliografia.imageloader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.user.User;

@Service
public class ImageUploadService {
	
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	
	public String handleFileUpload(User user, MultipartFile file, String name) {
		
		String fileName = "img-" + name + ".jpg";
		System.out.println("Intentando subir: "+ fileName);
		if (file!=null) {
			try {

				File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
				file.transferTo(uploadedFile);
				System.out.println(uploadedFile.getAbsolutePath());
				
				String path = uploadedFile.getAbsolutePath();
				return path;

			} catch (Exception e) {
				System.out.println("Exception occured\n");
				
			}
		} else {
			return "File provided is null";
		}
		return "error";
	}

}
