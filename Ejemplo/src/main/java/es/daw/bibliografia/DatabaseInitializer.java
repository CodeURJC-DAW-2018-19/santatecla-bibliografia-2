package es.daw.bibliografia;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorRepository;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Book;
import es.daw.bibliografia.book.BookRepository;
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaRepository;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraRepository;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaRepository;
import es.daw.bibliografia.user.User;
import es.daw.bibliografia.user.UserRepository;

@Component
public class DatabaseInitializer {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private CitaRepository citaRepository;

	@Autowired
	private ObraRepository obraRepository;

	@Autowired
	private TemaRepository temaRepository;

	@PostConstruct
	public void init() {

		// Sample Autor-Cita
		Autor a1 = new Autor("Shakespeare", 
				"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/255px-Shakespeare.jpg", 
				"https://media-cdn.tripadvisor.com/media/photo-s/0d/f4/e1/3e/england-in-one-day-stonehenge.jpg", 
				"26 de abril de 1564", "23 de abril 1616", "Stratford-upon-Avon, Inglaterra");
		//Autor a1 = new Autor("Shakespeare");
		Autor a2 = new Autor("Vargas Llosa");
		Autor a3 = new Autor("Garcilaso");
		Autor a4 = new Autor("Cervantes");

		Obra o1 = new Obra("Hamlet", "url foto", "fecha", "editorial", "url editorial");
		Obra o2 = new Obra("Romeo y Julieta", "url foto", "fecha", "editorial", "url editorial");
		Obra o3 = new Obra("Enrique 4", "url foto", "fecha", "editorial", "url editorial");
		Obra o4 = new Obra("La ciudad y los perros", "url foto", "fecha", "editorial", "url editorial");
		Obra o5 = new Obra("Don Quijote", "url foto", "fecha", "editorial", "url editorial");
		Obra o6 = new Obra("Poesias castellanas", "url foto", "fecha", "editorial", "url editorial");

		Cita c1 = new Cita("Cita Hamlet");
		Cita c2 = new Cita("Cita Romeo y Julieta");
		Cita c3 = new Cita("Cita Enrique 4");
		Cita c4 = new Cita("Cita La ciudad y los perros");
		Cita c5 = new Cita("Cita Don Quijote");
		Cita c6 = new Cita("Cita Poesias castellanas");

		Tema t1 = new Tema("Tema Hamlet");
		Tema t2 = new Tema("Tema Romeo y Julieta");
		Tema t3 = new Tema("Tema Enrique 4");
		Tema t4 = new Tema("Tema La ciudad y los perros");
		Tema t5 = new Tema("Tema Don Quijote");
		Tema t6 = new Tema("Tema Poesias castellanas");

		temaRepository.save(t1);
		temaRepository.save(t2);
		temaRepository.save(t3);
		temaRepository.save(t4);
		temaRepository.save(t5);
		temaRepository.save(t6);

		o1.setTema(t1);
		obraRepository.save(o1);
		o2.setTema(t2);
		obraRepository.save(o2);
		o3.setTema(t3);
		obraRepository.save(o3);
		o4.setTema(t4);
		obraRepository.save(o4);
		o5.setTema(t5);
		obraRepository.save(o5);
		o6.setTema(t6);
		obraRepository.save(o6);

		c1.setObra(o1);
		c2.setObra(o2);
		c3.setObra(o3);
		c4.setObra(o4);
		c5.setObra(o5);
		c6.setObra(o6);

		citaRepository.save(c1);
		citaRepository.save(c2);
		citaRepository.save(c3);
		citaRepository.save(c4);
		citaRepository.save(c5);
		citaRepository.save(c6);

		List<Autor> autores1a = new ArrayList<>();
		autores1a.add(a1);
		o1.setAutores(autores1a);

		List<Autor> autores1b = new ArrayList<>();
		autores1b.add(a1);
		o2.setAutores(autores1b);

		List<Autor> autores1c = new ArrayList<>();
		autores1b.add(a1);
		o3.setAutores(autores1c);

		List<Autor> autores2 = new ArrayList<>();
		autores2.add(a2);
		o4.setAutores(autores2);

		List<Autor> autores3 = new ArrayList<>();
		autores3.add(a3);
		o5.setAutores(autores3);

		List<Autor> autores4 = new ArrayList<>();
		autores4.add(a4);
		o6.setAutores(autores4);

		autorRepository.save(a1);
		autorRepository.save(a2);
		autorRepository.save(a3);
		autorRepository.save(a4);

		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}

}
