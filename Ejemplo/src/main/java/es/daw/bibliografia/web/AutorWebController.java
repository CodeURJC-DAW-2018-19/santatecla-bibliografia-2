package es.daw.bibliografia.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AutorWebController {
	
	@RequestMapping("/autor")
	public String showAutor(Model model) {
		return "autor";
	}
	
}
