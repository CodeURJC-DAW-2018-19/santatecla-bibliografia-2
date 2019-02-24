package es.daw.bibliografia.pdfgenerator;

import org.springframework.stereotype.Service;

import es.daw.bibliografia.user.User;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfWriter;

import java.nio.file.Path;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.nio.file.Files;

@Service
public class CreatePDF {
	
	public String generatePDF(User user) {
		String title = "PDF-tema.pdf";

 		try {
 			Document document = new Document();
			Paragraph paragraph = new Paragraph();
 			
			Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "files");
			if (!Files.exists(FILES_FOLDER))
				Files.createDirectories(FILES_FOLDER);

			PdfWriter.getInstance(document, new FileOutputStream(new File(FILES_FOLDER.toFile(), title)));
			
			document.open();
 			document.add(new Paragraph("Hola daviss"));
			
			paragraph.setTabSettings(new TabSettings(56f));
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("adios davis"));	
			
			document.add(paragraph);				      
 			document.close();
 			
		} catch (Exception e) {
			return "/";
		}

 		return "/crearPDF";
	}


}
