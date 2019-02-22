package es.daw.bibliografia.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorWebController {
	@RequestMapping("/error")
	public String showError(Model model, HttpServletResponse httpResponse) {
//		String errorMsg= "Lo sentimos. Ha ocurrido un error :(";
//		int httpErrorCode = httpResponse.getStatus();
//		switch (httpErrorCode) {
//	        case 400: {
//	            /rrorMsg = "Error 400: Solicitud errónea";
//	            break;
//	        }
//	        case 401: {
//	        	errorMsg = "Error 401: Acceso no autorizado";
//	            break;
//	        }
//	        case 404: {
//	        	errorMsg = "Error 404: Not Found :(";
//	            break;
//	        }
//	        case 500: {
//	        	errorMsg = "Error 500: Error interno del servidor (sorry!)";
//	            break;
//	        }
//		}
//		model.addAttribute("errorMsg", errorMsg);
		return "error";
	}
}
