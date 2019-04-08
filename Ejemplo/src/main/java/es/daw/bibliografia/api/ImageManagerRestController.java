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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.imageloader.ImageDownloadService;
import es.daw.bibliografia.imageloader.ImageUploadService;
import es.daw.bibliografia.user.UserComponent;
	
	@RestController
	public class ImageManagerRestController {
		
		@Autowired
		private UserComponent userComponent;
		
		@Autowired
		private ImageUploadService imageUploadService;
		
		@Autowired
		private ImageDownloadService imageDownloadService;
		
		@Autowired
		private ObraService obraService;

		private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
				
		@PostConstruct
		public void init() throws IOException {

			if (!Files.exists(FILES_FOLDER)) {
				Files.createDirectories(FILES_FOLDER);
			}
		}

		// NOTE: The url format "/image/{fileName:.+}" avoid Spring MVC remove file
		// extension.
		@RequestMapping(value = "/api/obras/{title}/image/portada", method= RequestMethod.GET)
		public void handlePortadaDownload(HttpServletResponse res, @PathVariable String title) throws FileNotFoundException, IOException {
			Long id = obraService.findOneByTitle(title).get().getId();
			imageDownloadService.handleImageDownload(res, id, "portada");
		}
		
		
		@RequestMapping(value = "api/obras/{title}/image/editorial" , method=RequestMethod.GET)
		public void handleEditorialDownload(HttpServletResponse res, @PathVariable String title) throws FileNotFoundException, IOException{
			Long id = obraService.findOneByTitle(title).get().getId();
			imageDownloadService.handleImageDownload(res, id, "editorial");
		}
		
//		@RequestMapping(value = "/api/obras/{id}/imageDownload")
//		public void handlePortadaDownload(HttpServletResponse res, @PathVariable Long id, @RequestParam String fileName)
//				throws FileNotFoundException, IOException {
//			String file = "img-"+fileName + "-"+ id + ".jpg";
//			System.out.println("Intentando con:" + Paths.get(System.getProperty("user.dir"), "images"));
//			Path image = FILES_FOLDER.resolve(file);
//
//			if (Files.exists(image)) {
//				res.setContentType("image/jpeg");
//				res.setContentLength((int) image.toFile().length());
//				System.out.println("Devolviendo: "+file);
//				FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());
//				
//			} else {
//				System.out.println("No se ha encontrado "+file);
//			}
//		}

		
		@RequestMapping(value="/api/obras/{title}/image/portada", method=RequestMethod.POST)
		public String handlePortadaUpload(@RequestParam ("file") MultipartFile file, @PathVariable String title) {
			Long id = obraService.findOneByTitle(title).get().getId();
			System.out.println("PortadaUpload invocado");
			String uploaded = imageUploadService.handleFileUpload(userComponent.getLoggedUser(),file, "portada-"+ id);
			System.out.println("Ya se ha subido: "  + uploaded);
			return "/api/obras/" + title + "/image/portada";
		}
		
		@RequestMapping(value="/api/obras/{title}/image/editorial", method=RequestMethod.POST)
		public String handleEditorialUpload(@RequestParam ("file") MultipartFile file, @PathVariable String title) {
			Long id = obraService.findOneByTitle(title).get().getId();
			System.out.println("EditorialUpload invocado");
			String uploaded = imageUploadService.handleFileUpload(userComponent.getLoggedUser(),file, "editorial-"+ id);
			System.out.println("Ya se ha subido: " + uploaded);
			return "/api/obras/" + title + "/image/editorial";
		}
}

