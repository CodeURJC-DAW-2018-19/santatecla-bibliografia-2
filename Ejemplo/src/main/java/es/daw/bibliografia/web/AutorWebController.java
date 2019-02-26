package es.daw.bibliografia.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.user.Tabs;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class AutorWebController {

	@Autowired
	private ObraService obraService;

	

	@Autowired
	private AutorService autorService;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private BookWebController webController;

	
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
		autorService.delete(autorService.findOneByNombre(autor.getNombre()).get().getId());

		webController.addUserToModel(model);
		webController.deleteTab("Autor " + autor.getNombre());

		return "redirect:/";
	}

	@RequestMapping("/autor/guardado")
	public String addAutor(Model model, Autor autor, @RequestParam Optional<Long[]> obras) {
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

	
}
