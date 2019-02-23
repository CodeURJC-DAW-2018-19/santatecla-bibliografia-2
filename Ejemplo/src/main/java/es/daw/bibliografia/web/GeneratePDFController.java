package es.daw.bibliografia.web;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.user.User;
import es.daw.bibliografia.user.UserComponent;


@Controller
public class GeneratePDFController {
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/tema/{nombreTema}/generatePDF")
	public void PdfController(HttpServletResponse response, @PathVariable String nombreTema) throws IOException {
		User user = userComponent.getLoggedUser();
		
		String namePdf="tema-"+nombreTema+".pdf";
		Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "files");
		Path pdf = FILES_FOLDER.resolve(namePdf);
		
		if (Files.exists(pdf)) {
			response.setContentType("application/pdf");
			response.setContentLength((int) pdf.toFile().length());
			FileCopyUtils.copy(Files.newInputStream(pdf), response.getOutputStream());

		} else {
			response.sendError(500);
		}
		
	}
	
	
}
