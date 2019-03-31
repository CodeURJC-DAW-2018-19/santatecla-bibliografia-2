package es.daw.bibliografia.imageloader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ImageDownloadService {
	
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	
	public void handleImageDownload(HttpServletResponse res, @PathVariable Long id, @RequestParam String fileName)
			throws FileNotFoundException, IOException {
		String file = "img-"+fileName + "-"+ id + ".jpg";
		System.out.println("Intentando con: " + Paths.get(System.getProperty("user.dir"), "images"));
		Path image = FILES_FOLDER.resolve(file);

		if (Files.exists(image)) {
			res.setContentType("image/jpeg");
			res.setContentLength((int) image.toFile().length());
			System.out.println("Devolviendo: "+file);
			FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());
			
		} else {
			System.out.println("No se ha encontrado "+file);
		}
	}
	
	
}
