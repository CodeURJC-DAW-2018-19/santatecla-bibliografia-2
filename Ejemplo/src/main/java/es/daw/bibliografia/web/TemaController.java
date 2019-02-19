package es.daw.bibliografia.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemaController {
	
	@RequestMapping("/tema")
	public String accederTema(Model model) {
		return "tema";
	}
	
}
