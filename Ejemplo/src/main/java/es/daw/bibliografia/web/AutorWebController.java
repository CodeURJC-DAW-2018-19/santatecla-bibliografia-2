package es.daw.bibliografia.web;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.Tabs;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class AutorWebController {

	@Autowired
	private ObraService obraService;

	@Autowired
	private TemaService temaService;

	@Autowired
	private CitaService citaService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private BookWebController webController;

	/*
	 * @RequestMapping("/autor") public String showAutor(Model model) {
	 * model.addAttribute("obras", obraService.findAll());
	 * model.addAttribute("temas", temaService.findAll());
	 * model.addAttribute("citas", citaService.findAll()); return "autor"; }
	 */
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

	public void updateActiveTabs(boolean active) {
		if (active == true) {
			this.userComponent.getLoggedUser().inactiveAllTabs();
		}
	}

	public void deleteTab(String name) {
		this.userComponent.getLoggedUser().deleteTabByName(name);
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

	@RequestMapping("/autor/edit")
	public String editObra(Model model, Autor autor) {
		// userTabs(model, "/obra/guardada", "Obra guardada", true);

		Optional<Autor> autor2 = autorService.findOneByNombre(autor.getNombre());

		if (autor2.isPresent()) {

			autor2.get().setNombre(autor.getNombre());
			autor2.get().setFecha_def(autor.getFecha_def());
			autor2.get().setFecha_nac(autor.getFecha_nac());
			autor2.get().setLugar(autor.getLugar());
			autor2.get().setUrl_foto(autor.getUrl_foto());
			autor2.get().setUrl_mapa(autor.getUrl_mapa());

			autorService.save(autor2.get());
			return "redirect:/autor/".concat(autor.getNombre());
		} else {
			return "autorError";
		}
	}

	@RequestMapping("/autor/borrar")
	public String deleteObra(Model model, Autor autor) {
		// userTabs(model, "/obra/guardada", "Obra guardada", true);
		autorService.delete(autorService.findOneByNombre(autor.getNombre()).get().getId());

		webController.addUserToModel(model);
		webController.deleteTab("Autor " + autor.getNombre());

		return "redirect:/";
	}

	@RequestMapping("/autor/guardado")
	public String addAutor(Model model, Autor autor, @RequestParam Optional<Long[]> obras) {
		// userTabs(model, "/obra/guardada", "Obra guardada", true);
		webController.deleteTab("Nuevo autor");
		autorService.save(autor);
		webController.addUserToModel(model);

		if (obras.isPresent()) {
			for (Long o : obras.get()) {
				Obra obra = obraService.findOne(o).get();
				obra.getAutores().add(autor);
				obraService.save(obra);
			}
		}

		return "redirect:/autorshow/".concat(autor.getNombre());
	}

	/*
	 * @GetMapping("/autor/{id}") public String showBook(Model model, @PathVariable
	 * long id) {
	 * 
	 * userTabs(model, (String) ("/autor/" + id), (String) ("Autor " + id), true);
	 * 
	 * Optional<Autor> autor = autorService.findOne(id);
	 * webController.addUserToModel(model);
	 * 
	 * 
	 * Optional<Autor> a1 = autorService.findOneByNombre("William Shakespeare"); if
	 * (a1.isPresent()) { System.out.println(a1.get().getNombre()); }
	 * 
	 * 
	 * Optional<Obra> o = obraService.findOneByTitle("Hamlet"); if (o.isPresent()) {
	 * System.out.println(o.get().getTitle()); } Optional<Cita> c =
	 * citaService.findOneByContenido("Cita Hamlet"); if (c.isPresent()) {
	 * System.out.println(c.get().getContenido()); } Optional<Tema> t =
	 * temaService.findOneByContenido("Tema Hamlet"); if (t.isPresent()) {
	 * System.out.println(t.get().getContenido()); }
	 * 
	 * 
	 * if (autor.isPresent()) { List<Obra> obras =
	 * obraService.findByAuthor(autor.get()); List<Tema> temas = new ArrayList<>();
	 * List<Cita> citas = new ArrayList<>(); for (int i = 0; i < obras.size(); i++)
	 * { temas.add(temaService.findByObra(obras.get(i))); citas =
	 * Stream.concat(citas.stream(),
	 * obras.get(i).getCitas().stream()).collect(Collectors.toList()); //
	 * temaService.findByObra(obras.get(i)) //
	 * System.out.println(obras.get(i).getTitle()); } model.addAttribute("obras",
	 * obras); model.addAttribute("temas", temas); model.addAttribute("citas",
	 * citas);
	 * 
	 * model.addAttribute("nombreAutor", autor.get().getNombre());
	 * model.addAttribute("urlFotoAutor", autor.get().getUrl_foto());
	 * model.addAttribute("nacimientoAutor", autor.get().getFecha_nac());
	 * model.addAttribute("muerteAutor", autor.get().getFecha_def());
	 * model.addAttribute("urlMapa", autor.get().getUrl_mapa());
	 * model.addAttribute("lugarAutor", autor.get().getLugar());
	 * 
	 * return "autor"; } else return "autorError";
	 * 
	 * }
	 */
}
