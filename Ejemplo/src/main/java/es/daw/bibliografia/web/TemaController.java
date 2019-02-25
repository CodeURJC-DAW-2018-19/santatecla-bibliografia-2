package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.Tabs;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class TemaController {

	@Autowired
	private TemaService temaService;

	@Autowired
	private CitaService citaService;

	@Autowired
	private ObraService obraService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private BookWebController webController;

	@Autowired
	private UserComponent userComponent;

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
		temaService.save(tema);

		webController.addUserToModel(model);

		return "redirect:/tema/".concat(tema.getContenido());
	}

}
