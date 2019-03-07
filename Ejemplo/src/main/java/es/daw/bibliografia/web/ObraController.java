package es.daw.bibliografia.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.Tabs;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class ObraController {

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

	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/obra/guardada")
	public String addObra(Model model, Obra obra, @RequestParam Optional<Long[]> autores,
			@RequestParam Optional<Long> tema, @RequestParam("URLpor") File portada,
			@RequestParam("URLed") File editorial) {
		webController.deleteTab("Nueva obra");
		obra.setURL("../imgs/" + portada.getPath());
		obra.setUrl_editorial("../imgs/" + editorial.getPath());

		ArrayList<Autor> aAutores = new ArrayList<Autor>();

		if (autores.isPresent()) {
			for (Long id : autores.get()) {
				Autor autor = serviceAutor.findOne(id).get();
				aAutores.add(autor);
			}
		}

		obra.setAutores(aAutores);
		service.save(obra);

		if (tema.isPresent()) {
			Tema t = serviceTema.findOne(tema.get()).get();
			t.getObras().add(obra);
			serviceTema.save(t);
		}

		webController.addUserToModel(model);

		return "redirect:/obrashow/".concat(obra.getTitle());
	}

	@RequestMapping("/obra/edit")
	public String editObra(Model model, Obra obra) {

		Optional<Obra> obra2 = service.findOneByTitle(obra.getTitle());

		if (obra2.isPresent()) {

			// obra2.get().setAutores(obra.getAutores());
			// obra2.get().setCitas(obra.getCitas());
			obra2.get().setDate(obra.getDate());
			obra2.get().setEditorial(obra.getEditorial());
			obra2.get().setURL(obra.getURL());
			obra2.get().setUrl_editorial(obra.getUrl_editorial());

			service.save(obra2.get());
			return "redirect:/obra/".concat(obra.getTitle());
		} else {

		}
		service.save(obra);

		webController.addUserToModel(model);

		return "redirect:/obrashow/".concat(obra.getTitle());
	}

	@RequestMapping("/obra/borrar")
	public String deleteObra(Model model, Obra obra) {
		// userTabs(model, "/obra/guardada", "Obra guardada", true);
		service.deleteObra(obra);

		webController.addUserToModel(model);
		webController.deleteTab("Obra  " + obra.getTitle());

		return "redirect:/";
	}

	@RequestMapping("/obra/{nombreObra}/borrar/autor")
	public String deleteObraInAutor(Model model, @PathVariable("nombreObra") String nombreObra,
			@RequestParam("nombreAutor") String autor) {
		// userTabs(model, "/obra/guardada", "Obra guardada", true);
		service.delete(service.findOneByTitle(nombreObra).get().getId());

		webController.addUserToModel(model);
		webController.deleteTab("Obra  " + nombreObra);

		return "redirect:/autorshow/".concat(autor);
	}

	@RequestMapping("/obra/{nombreObra}/borrar/tema")
	public String deleteObraInTema(Model model, @PathVariable("nombreObra") String nombreObra,
			@RequestParam("nombreTema") String autor) {
		// userTabs(model, "/obra/guardada", "Obra guardada", true);
		service.delete(service.findOneByTitle(nombreObra).get().getId());

		webController.addUserToModel(model);
		webController.deleteTab("Obra  " + nombreObra);

		return "redirect:/temashow/".concat(autor);
	}
	
	@RequestMapping("/obra/{nombreObra}/cita")
	public String createcita(Model model, Cita cita, @PathVariable("nombreObra") String nombreObra) {
		// userTabs(model, "/obra/guardada", "Obra guardada", true);
		service.findOneByTitle(nombreObra).get().getCitas().add(cita);
		serviceCita.save(cita);

		webController.addUserToModel(model);

		return "redirect:/obrashow/".concat(nombreObra);
	}


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

	@RequestMapping(value = "/obrashow/{nombreObra}") // PUT IN BOOKWEEBCONTROLER
	public String openObra(Model model, @PathVariable("nombreObra") String nombreObra) {

		Optional<Obra> obra = service.findOneByTitle(nombreObra);

		webController.addUserToModel(model);
		//serviceCita.save(cita);
		if (obra.isPresent()) {

			userTabs(model, "/obrashow/" + nombreObra, "Obra  " + nombreObra, true);

			Tema tema = serviceTema.findByObra(obra.get());
			List<Cita> citas = obra.get().getCitas();
			List<Autor> autores = obra.get().getAutores();

			model.addAttribute("autores", autores);
			model.addAttribute("temas", tema);
			model.addAttribute("citas", citas);

			model.addAttribute("title", obra.get().getTitle());
			model.addAttribute("URL", obra.get().getURL());
			model.addAttribute("date", obra.get().getDate());
			model.addAttribute("editorial", obra.get().getEditorial());
			model.addAttribute("url_editorial", obra.get().getUrl_editorial());
			return "obraShow";
		} else {
			return "obraShowError";
		}
	}

	@RequestMapping(value = "/obra/new")
	public String goObra(Model model) {
		userTabs(model, "/obra/new", "Nueva obra", true);

		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", service.findAll());
		model.addAttribute("autores", serviceAutor.findAll());

		webController.addUserToModel(model);

		return "obra";
	}
}
