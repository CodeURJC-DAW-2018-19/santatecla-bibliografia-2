package es.daw.bibliografia.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
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
	public String addObra(Model model, Obra obra) {
		//userTabs(model, "/obra/guardada", "Obra guardada", true);
		service.save(obra);

		webController.addUserToModel(model);

		return "redirect:/obra/".concat(obra.getTitle());
	}
	
	@RequestMapping("/obra/edit")
	public String editObra(Model model, Obra obra) {
		//userTabs(model, "/obra/guardada", "Obra guardada", true);
		
		Optional<Obra> obra2 = service.findOneByTitle(obra.getTitle());
		
		if(obra2.isPresent()) {
			
			obra2.get().setAutores(obra.getAutores());
			obra2.get().setCitas(obra.getCitas());
			obra2.get().setDate(obra.getDate());
			obra2.get().setEditorial(obra.getEditorial());
			obra2.get().setURL(obra.getURL());
			obra2.get().setUrl_editorial(obra.getUrl_editorial());
			
			service.save(obra2.get());
			return "redirect:/obra/".concat(obra.getTitle());
		}else {
			
		}
		service.save(obra);

		webController.addUserToModel(model);

		return "redirect:/obra/".concat(obra.getTitle());
	}
	
//	@RequestMapping(value = "/obra/new", method = RequestMethod.POST)//PUT IN BOOKWEEBCONTROLER
//	public String goObra(Model model) {
//		
//		model.addAttribute("temas", serviceTema.findAll());
//		model.addAttribute("obras", service.findAll());
//		model.addAttribute("autores", serviceAutor.findAll());
//		
//		webController.addUserToModel(model);
//		
//		return "obra";
//	}
//	@GetMapping ("/obra/{id}")//PUT IN BOOKWEEBCONTROLER
//	public String openObra(Model model, @PathVariable long id) {
//		
//		Optional<Obra> obra= service.findOne(id);
//		
//		model.addAttribute("autores", serviceAutor.findAll());
//		model.addAttribute("temas", serviceTema.findAll());
//		model.addAttribute("citas", serviceCita.findAll());
//		
//		webController.addUserToModel(model);
//		
//		if(obra.isPresent()) {
//			
//			model.addAttribute("title", obra.get().getTitle());
//			model.addAttribute("URL", obra.get().getURL());
//			model.addAttribute("date", obra.get().getDate());
//			model.addAttribute("editorial", obra.get().getEditorial());
//			model.addAttribute("url_editorial", obra.get().getUrl_editorial());
//			return "obraShow"; 
//		}else {
//			return "obraShowError"; 
//		}		
//	}


//	@RequestMapping(value = "/obra/new", method = RequestMethod.POST) // PUT IN BOOKWEEBCONTROLER
//	public String goObra(Model model) {
//		userTabs(model, "/obra/new", "Nueva obra", true);
//
//		model.addAttribute("temas", serviceTema.findAll());
//		model.addAttribute("obras", service.findAll());
//		model.addAttribute("autores", serviceAutor.findAll());
//
//		webController.addUserToModel(model);
//
//		return "obra";
//	}

	private void userTabs(Model model, String url, String name, boolean active) {
		Tabs tab = new Tabs(url, name, active);
		
		if (!sameTab(tab)) {
			updateActiveTabs(active);
			if (this.userComponent.isLoggedUser()) {
				this.userComponent.getLoggedUser().addTab(tab);
				model.addAttribute("tabs", this.userComponent.getLoggedUser().getTabs());
			}
		}
	}
	
	public void deleteTab(String url) {
		this.userComponent.getLoggedUser().deleteTabByUrl(url);
	}
	
	public void updateActiveTabs(boolean active) {
		if (active==true) {
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

//	@GetMapping("/obra/{id}") // PUT IN BOOKWEEBCONTROLER
//	public String openObra(Model model, @PathVariable long id) {
//		userTabs(model, (String) ("/obra/" + id), (String) ("Obra " + id) , true);
//		Optional<Obra> obra = service.findOne(id);
//
//		model.addAttribute("autores", serviceAutor.findAll());
//		model.addAttribute("temas", serviceTema.findAll());
//		model.addAttribute("citas", serviceCita.findAll());
//
//		webController.addUserToModel(model);
//
//		if (obra.isPresent()) {
//
//			model.addAttribute("title", obra.get().getTitle());
//			model.addAttribute("URL", obra.get().getURL());
//			model.addAttribute("date", obra.get().getDate());
//			model.addAttribute("editorial", obra.get().getEditorial());
//			model.addAttribute("url_editorial", obra.get().getUrl_editorial());
//			return "obraShow";
//		} else {
//			return "error";
//		}
//	}

//	@RequestMapping("obra/guardada")
//	public String saveObra(Model model, @PathVariable long id, @PathVariable String title, @PathVariable String URL,
//			@PathVariable String date, @PathVariable String editorial, @PathVariable String url_editorial) {
//		
//		userTabs(model, "obra/guardada", "Obra editada", true);
//		
//		Optional<Obra> obra = service.findOne(id);
//		webController.addUserToModel(model);
//		if (obra.get().getTitle() != null)
//			model.addAttribute("title", title);
//		if (obra.get().getURL() != null)
//			model.addAttribute("URL", URL);
//		if (obra.get().getDate() != null)
//			model.addAttribute("date", date);
//		if (obra.get().getEditorial() != null)
//			model.addAttribute("editorial", editorial);
//		if (obra.get().getUrl_editorial() != null)
//			model.addAttribute("url_editorial", url_editorial);
//		return "obra";
//	}
	
	
}
