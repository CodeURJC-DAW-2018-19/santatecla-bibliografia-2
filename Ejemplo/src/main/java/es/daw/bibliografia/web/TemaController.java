package es.daw.bibliografia.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.pdfgenerator.CreatePDF;
import es.daw.bibliografia.user.Tabs;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class TemaController {

	@Autowired
	private TemaService temaService;

	@Autowired
	private CitaService citaService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private BookWebController webController;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private CreatePDF createPDF;

	private void userTabs(Model model, String url, String name, boolean active) {
		Tabs tab = new Tabs(url, name, active);

		if (!sameTab(tab)) {
			updateActiveTabs(active);
			if (this.userComponent.isLoggedUser()) {
				this.userComponent.getLoggedUser().addTab(tab);
			}
		}
		modelTabs(model);
	}

	public void modelTabs(Model model) {
		if (!this.userComponent.getLoggedUser().getTabs().isEmpty()) {
			model.addAttribute("tabs", this.userComponent.getLoggedUser().getTabs());
		}
	}

	public void deleteTab(String name) {
		this.userComponent.getLoggedUser().deleteTabByName(name);
	}

	public void updateActiveTabs(boolean active) {
		if (active == true) {
			this.userComponent.getLoggedUser().inactiveAllTabs();
		}
	}

	public boolean sameTab(Tabs tab) {
		for (int i = 0; i < this.userComponent.getLoggedUser().getTabs().size(); i++) {
			if (this.userComponent.getLoggedUser().getTabs().get(i).getName().equalsIgnoreCase(tab.getName())
					&& this.userComponent.getLoggedUser().getTabs().get(i).getUrl().equalsIgnoreCase(tab.getUrl())) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping("/tema/borrarCita")
	public String deleteCita(Model model, @RequestParam long id) {

		citaService.delete(id);
		webController.addUserToModel(model);

		return "redirect:/";
	}

	@RequestMapping("/tema/borrar")
	public String deleteTema(Model model, @RequestParam String contenido) {

		temaService.delete(temaService.findOneByContenido(contenido).get().getId());

		webController.addUserToModel(model);
		webController.deleteTab("Tema: " + contenido);

		return "redirect:/";
	}

	@RequestMapping("/tema/guardado")
	public String addAutor(Model model, Tema tema) {
		// userTabs(model, "/obra/guardada", "Obra guardada", true);
		webController.deleteTab("Nueva tema");
		temaService.save(tema);

		webController.addUserToModel(model);

		return "redirect:/temashow/".concat(tema.getContenido());
	}

	@RequestMapping("/tema/{nombreTema}/borrar/autor")
	public String deleteTema2(Model model, @PathVariable("nombreTema") String nombreObra,
			@RequestParam("nombreAutor") String autor) {
		temaService.delete(temaService.findOneByContenido(nombreObra).get().getId());

		webController.addUserToModel(model);
		webController.deleteTab("Tema: " + nombreObra);

		return "redirect:/autorshow/".concat(autor);
	}
	
	@RequestMapping("/temashow/{contenido}")
	public String accederTema(Model model, @PathVariable("contenido") String contenido) {

		Optional<Tema> tema = temaService.findOneByContenido(contenido);

		webController.addUserToModel(model);

		if (tema.isPresent()) {

			userTabs(model, "/temashow/" + contenido, "Tema: " + contenido, true);

			Tema theme = tema.get();
			List<Obra> obras = theme.getObras();
			List<Cita> citas = new ArrayList<>();
			List<Autor> autores = new ArrayList<>();
			for (int i = 0; i < obras.size(); i++) {
				citas = Stream.concat(citas.stream(), obras.get(i).getCitas().stream()).collect(Collectors.toList());
				autores = Stream.concat(autores.stream(), obras.get(i).getAutores().stream())
						.collect(Collectors.toList());

			}

			model.addAttribute("tema", tema);
			model.addAttribute("obras", obras);
			model.addAttribute("citas", citas);
			model.addAttribute("autores", autores);

			createPDF.generatePDF(userComponent.getLoggedUser(), obras);
			return "tema";
		} else {
			return "temaShowError";
		}
	}
	
	@RequestMapping(value = "/tema/new")
	public String goTema(Model model) {
		userTabs(model, "/obra/new", "Nuevo tema", true);

		model.addAttribute("obras", temaService.findAll());
		model.addAttribute("autores", autorService.findAll());
		model.addAttribute("citas", citaService.findAll());
		webController.addUserToModel(model);

		return "temaNew";
	}
}
