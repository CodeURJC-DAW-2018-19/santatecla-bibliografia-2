package es.daw.bibliografia.pdfgenerator;

import org.springframework.util.FileCopyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.TemaService;

import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.io.IOException;


@Controller
public class PDFController {

	TemaService serviceTema;
	
 	@RequestMapping("/crearPDF/{contenido}")
	public void handleFileDownloadPDF(HttpServletResponse res, @PathVariable String contenido) throws FileNotFoundException, IOException {
 		
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