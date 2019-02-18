package es.codeurjc.daw.library.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.daw.library.book.Book;
import es.codeurjc.daw.library.book.BookService;
import es.codeurjc.daw.library.user.UserComponent;

@Controller
public class BookWebController {

	@Autowired
	private BookService service;
	
	@Autowired
	private UserComponent userComponent;

	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			model.addAttribute("userName",userComponent.getLoggedUser().getName());
		}
	}
	
	@GetMapping("/")
	public String showBooks(Model model) {

		model.addAttribute("books", service.findAll());
	
		return "books";
	}
	
	@GetMapping("/books/{id}")
	public String showBook(Model model, @PathVariable long id) {
		
		Optional<Book> book = service.findOne(id);

		if(book.isPresent()) {
			model.addAttribute("book", book.get());
		}

		return "book";
	}
	
	@GetMapping("/newBook")
	public String newBook(Model model) {
		return "bookForm";
	}
	
	@GetMapping("/editBook/{id}")
	public String newBook(Model model, @PathVariable long id) {
		
		Optional<Book> book = service.findOne(id);
		
		if(book.isPresent()) {
			model.addAttribute("book", book.get());
		}
		
		return "bookForm";
	}
	
	@PostMapping("/saveBook")
	public String saveBook(Model model, Book book) {
		
		service.save(book);
		
		return "bookCreated";
	}
	
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(Model model, @PathVariable long id) {
		
		service.delete(id);
		
		return "bookDeleted";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("hideLogin", true);
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String loginError() {
		return "loginerror";
	}
}
