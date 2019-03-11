package es.daw.bibliografia.imageloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import es.daw.bibliografia.book.Obra;


	@Controller
	public class ImageManagerController {

		private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
				
		@PostConstruct
		public void init() throws IOException {

			if (!Files.exists(FILES_FOLDER)) {
				Files.createDirectories(FILES_FOLDER);
			}
		}

		// NOTE: The url format "/image/{fileName:.+}" avoid Spring MVC remove file
		// extension.

		@RequestMapping("/imageDownload/portada")
		public void handlePortadaDownload(HttpServletResponse res, Obra obra)
				throws FileNotFoundException, IOException {
			String fileName ="portada-"+ obra.getId() + "-img.jpg";
			System.out.println("Intentando con:" + Paths.get(System.getProperty("user.dir"), "images"));
			Path image = FILES_FOLDER.resolve(fileName);

			if (Files.exists(image)) {
				res.setContentType("image/jpeg");
				res.setContentLength((int) image.toFile().length());
				System.out.println("Devolviendo: "+fileName);
				FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());
				
			} else {
				System.out.println("No se ha encontrado "+fileName);
			}
		}
		
		@RequestMapping("/imageDownload/editorial")
		public void handleEditorialDownload(HttpServletResponse res, Obra obra)
				throws FileNotFoundException, IOException {
			String fileName ="editorial-"+ obra.getId() + "-img.jpg";
			System.out.println("Intentando con:" + Paths.get(System.getProperty("user.dir"), "images"));
			Path image = FILES_FOLDER.resolve(fileName);

			if (Files.exists(image)) {
				res.setContentType("image/jpeg");
				res.setContentLength((int) image.toFile().length());
				System.out.println("Devolviendo: "+fileName);
				FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());
				
			} else {
				System.out.println("No se ha encontrado "+fileName);
			}
		}
}
