package tabs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Inicio{

	@RequestMapping("/")
	public String greeting(Model model) {

		return "inicio";
	}
	
	@RequestMapping("/SearchTemas")
	public String searchTemas(Model model) {

		return "inicio";
	}
	@RequestMapping("/SearchObras")
	public String searchObras(Model model) {

		return "inicio";
	}
	@RequestMapping("/SearchAutores")
	public String searchAutores(Model model) {

		return "inicio";
	}
	
	

}