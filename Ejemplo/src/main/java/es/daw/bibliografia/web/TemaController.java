package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.user.Tabs;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class TemaController {

	@Autowired
	private CitaService quotes;

	@Autowired
	private ObraService works;

	@Autowired
	private AutorService authors;

	@Autowired
	private BookWebController webController;

	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/tema")
	public String accederTema(Model model) {
		model.addAttribute("quotes", quotes.findAll());
		model.addAttribute("works", works.findAll());
		model.addAttribute("authors", authors.findAll());

		webController.addUserToModel(model);

		return "tema";
	}

	private void userTabs(Model model, String url, String name, boolean active) {
		Tabs tab = new Tabs(url, name, active);
		if (!sameTab(tab)) {
			if (this.userComponent.isLoggedUser()) {
				this.userComponent.getLoggedUser().addTab(tab);
				model.addAttribute("tabs", this.userComponent.getLoggedUser().getTabs());
			}
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

}
