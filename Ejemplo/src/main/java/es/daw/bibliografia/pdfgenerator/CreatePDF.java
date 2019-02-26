package es.daw.bibliografia.pdfgenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfWriter;

import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.user.User;

@Service
public class CreatePDF {

	public String generatePDF(User user, List<Obra> obras) {
		String title = "PDF-tema.pdf";

		Document document = new Document();

		try {
			Paragraph paragraph = new Paragraph();

			Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "files");
			if (!Files.exists(FILES_FOLDER))
				Files.createDirectories(FILES_FOLDER);

			PdfWriter.getInstance(document, new FileOutputStream(new File(FILES_FOLDER.toFile(), title)));

			document.open();
			for (int i = 0; i < obras.size(); i++) {
				paragraph.clear();

				for (int j = 0; j < obras.get(i).getAutores().size(); j++) {

					document.add(new Paragraph(
							obras.get(i).getAutores().get(j).getNombre() + " en <<" + obras.get(i).getTitle() + ">>"));

					for (int k = 0; k < obras.get(i).getCitas().size(); k++) {
						paragraph.setTabSettings(new TabSettings(56f));
						paragraph.add(Chunk.TABBING);
						paragraph.add(new Chunk("''" + obras.get(i).getCitas().get(k).getContenido() + "''\n"));
						paragraph.setSpacingAfter(40);

					}

				}

				document.add(paragraph);
			}

			if (obras.isEmpty()) {
				document.add(new Paragraph("El fichero no contiene datos"));
			}

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "/";
		}

		return "/crearPDF";
	}

}
