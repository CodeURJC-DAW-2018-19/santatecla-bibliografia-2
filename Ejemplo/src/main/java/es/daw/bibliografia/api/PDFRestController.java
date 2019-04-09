package es.daw.bibliografia.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PDFRestController {
	
	
	@RequestMapping("/api/temas/{contenido}/PDF")
	public void handleFileDownloadPDF(HttpServletResponse res, @PathVariable String contenido)
			throws FileNotFoundException, IOException {

		String title = "PDF-tema.pdf";

		Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "files");
		Path pdf = FILES_FOLDER.resolve(title);

		if (Files.exists(pdf)) {
			res.setContentType("application/pdf");
			res.setContentLength((int) pdf.toFile().length());
			FileCopyUtils.copy(Files.newInputStream(pdf), res.getOutputStream());

		} else {
			res.sendError(404);
		}
	}
}
