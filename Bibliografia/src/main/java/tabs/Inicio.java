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

}