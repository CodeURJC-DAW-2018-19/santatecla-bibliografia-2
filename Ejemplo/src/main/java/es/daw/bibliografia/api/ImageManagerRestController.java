package es.daw.bibliografia.api;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import es.daw.bibliografia.imageloader.ImageUploadService;
import es.daw.bibliografia.user.UserComponent;
	
	@RestController
	public class ImageManagerRestController {
		
		@Autowired
		private UserComponent userComponent;
		
		@Autowired
		private ImageUploadService imageUploadService;

		private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
				
		@PostConstruct
		public void init() throws IOException {

			if (!Files.exists(FILES_FOLDER)) {
				Files.createDirectories(FILES_FOLDER);
			}
		}

		// NOTE: The url format "/image/{fileName:.+}" avoid Spring MVC remove file
		// extension.

		@RequestMapping("/api/obras/{id}/imageDownload")
		public void handlePortadaDownload(HttpServletResponse res, @PathVariable Long id, @RequestParam String fileName)
				throws FileNotFoundException, IOException {
			String file = "img-"+fileName + "-"+ id + ".jpg";
			System.out.println("Intentando con:" + Paths.get(System.getProperty("user.dir"), "images"));
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

		
		@RequestMapping(value="/api/obras/{id}/imageUpload", method=RequestMethod.POST)
		public String handleFileUpload(@RequestParam ("file") MultipartFile file, @PathVariable Long id, @RequestParam String fileName) {
			System.out.println("imageUpload invocado");
			String uploaded = imageUploadService.handleFileUpload(userComponent.getLoggedUser(),file, fileName + "-"+ id);
			System.out.println("Ya se ha subido :" + uploaded);
			return "/api/obras/" + id + "/imageDownload?fileName=" + fileName;
		}
}

