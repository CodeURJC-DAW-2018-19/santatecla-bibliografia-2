package es.daw.bibliografia.web;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.io.File;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.jndi.toolkit.url.Uri;

import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Image;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.TemaService;

@Controller
public class ObraController {
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");


	@Autowired
	private ObraService service;
	
	@Autowired
	private TemaService serviceTema;

	@Autowired
	private AutorService serviceAutor;
	
	@Autowired
	private CitaService serviceCita;
	
	@Autowired
	private BookWebController webController;
	
	
	@RequestMapping(value = "/obra/upload", method = RequestMethod.POST)
	public String handleObraUpload(Model model, @RequestParam("title") String obraTitle,
			 @RequestParam("URL") String obraUrl,
			 @RequestParam("date") String obraDate,
			 @RequestParam("editorial") String obraEditorial,
			 @RequestParam("url_editorial") String obra_Url_Editorial,
			@RequestParam("filePortada") MultipartFile portadaPhoto,
			@RequestParam("fileEditorial") MultipartFile editorialPhoto) {

		
		String fileNamePortada = "portada.jpg";

		if (!portadaPhoto.isEmpty()) {
			try {

		        String sql = "INSERT INTO imagen (nombre, tipo, tamano, pixel) VALUES(?, ?, ?, ?)";

		        String nombre = portadaPhoto.getOriginalFilename();
		        String tipo   = portadaPhoto.getContentType();
		        Long tamano   = portadaPhoto.getSize();
		        byte[] pixel  = portadaPhoto.getBytes();

		        jdbc.update(sql, nombre, tipo, tamano, pixel);
				
				File uploadedFilePortada = new File(FILES_FOLDER.toFile(), fileNamePortada);
				portadaPhoto.transferTo(uploadedFilePortada);
				
				Image picture=new Image(obraTitle);
				Obra newToDataBaseObra= new Obra(obraTitle,obraUrl,obraDate,obraEditorial,picture);

				return "uploaded";

			} catch (Exception e) {

				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

				return "uploaded";
			}
		} else {
			
			model.addAttribute("error", "The file is empty");

			return "uploaded";
		}
	}
	
	@RequestMapping("/obra/guardada") 
	public String addObra(Model model, Obra obra) {
		service.save(obra);
		
		webController.addUserToModel(model);
		
		return webController.showBooks(model); 
	}
	
	@RequestMapping("/obra/new")//PUT IN BOOKWEEBCONTROLER
	public String goObra(Model model) {
		
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", service.findAll());
		model.addAttribute("autores", serviceAutor.findAll());
		
		webController.addUserToModel(model);
		
		return "obra";
	}
	@GetMapping ("/obra/{id}")//PUT IN BOOKWEEBCONTROLER
	public String openObra(Model model, @PathVariable long id) {
		
		Optional<Obra> obra= service.findOne(id);
		
		model.addAttribute("autores", serviceAutor.findAll());
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("citas", serviceCita.findAll());
		
		webController.addUserToModel(model);
		
		if(obra.isPresent()) {
			
			model.addAttribute("title", obra.get().getTitle());
			model.addAttribute("URL", obra.get().getURL());
			model.addAttribute("date", obra.get().getDate());
			model.addAttribute("editorial", obra.get().getEditorial());
			model.addAttribute("url_editorial", obra.get().getUrl_editorial());
			return "obraShow"; 
		}else {
			return "obraShowError"; 
		}		
	}
	
	@RequestMapping("obra/editada") 
	public String saveObra(Model model, @PathVariable long id, @PathVariable String title, @PathVariable String URL, @PathVariable String date, @PathVariable String editorial, @PathVariable String url_editorial) {
		Optional<Obra> obra= service.findOne(id);
		webController.addUserToModel(model);
		if(obra.get().getTitle()!=null)
			model.addAttribute("title", title);
		if(obra.get().getURL()!=null)
			model.addAttribute("URL", URL);
		if(obra.get().getDate()!=null)
			model.addAttribute("date", date);
		if(obra.get().getEditorial()!=null)
			model.addAttribute("editorial", editorial);
		if(obra.get().getUrl_editorial()!=null)
			model.addAttribute("url_editorial", url_editorial);
		return "obra"; 
	}
}
