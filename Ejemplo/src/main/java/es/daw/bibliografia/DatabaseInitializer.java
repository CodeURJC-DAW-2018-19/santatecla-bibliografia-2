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

	private BookRepository bookRepository;
	private UserRepository userRepository;
	private AutorRepository autorRepository;
	private CitaRepository citaRepository;
	private ObraRepository obraRepository;
	private TemaRepository temaRepository;

	@Autowired
	public DatabaseInitializer(BookRepository bookRepository, UserRepository userRepository,
			AutorRepository autorRepository, CitaRepository citaRepository, ObraRepository obraRepository,
			TemaRepository temaRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.autorRepository = autorRepository;
		this.citaRepository = citaRepository;
		this.obraRepository = obraRepository;
		this.temaRepository = temaRepository;
	}

	@PostConstruct
	public void init() {

		// Sample Autor-Cita
		Autor a1 = new Autor("William Shakespeare", 
				"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/255px-Shakespeare.jpg", 
				"https://media-cdn.tripadvisor.com/media/photo-s/0d/f4/e1/3e/england-in-one-day-stonehenge.jpg", 
				"26 de abril de 1564", "23 de abril 1616", "Stratford-upon-Avon, Inglaterra");
		Autor a2 = new Autor("Jorge Mario Pedro Vargas Llosa", 
				"https://www.nobelprize.org/images/vargas_llosa-15156-content-portrait-tablet.jpg", 
				"https://static1.squarespace.com/static/5388e453e4b0813d343199fc/t/5849dd77bebafb2ceef94f22/1481235854682/arequipa+peru+tour", 
				"28 de marzo de 1936", "~", "Arequipa, Peru");
		Autor a3 = new Autor("Garcilaso de la Vega", 
				"https://historia-biografia.com/wp-content/uploads/2017/10/Sin-t%C3%ADtulo2-1.jpg", 
				"https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Toledo_%2837737041515%29.jpg/266px-Toledo_%2837737041515%29.jpg", 
				"Sobre 1498 ", "14 de octubre de 1536", "Toledo");
		Autor a4 = new Autor("Miguel de Cervantes Saavedra", 
				"https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Cervantes_J%C3%A1uregui.jpg/330px-Cervantes_J%C3%A1uregui.jpg", 
				"http://www.turismoalcala.es/wp-content/uploads/2013/08/visitasguiadas-responsive.jpg", 
				"​29 de septiembre de 1547", "22 de abril de 1616", "Alcalá de Henares");
		
		autorRepository.save(a1);
		autorRepository.save(a2);
		autorRepository.save(a3);
		autorRepository.save(a4);

		Cita c1 = new Cita("Cita Hamlet");
		Cita c2 = new Cita("Cita Romeo y Julieta");
		Cita c3 = new Cita("Cita Enrique 4");
		Cita c4 = new Cita("Cita La ciudad y los perros");
		Cita c5 = new Cita("Cita Don Quijote");
		Cita c6 = new Cita("Cita Poesias castellanas");
		
		citaRepository.save(c1);
		citaRepository.save(c2);
		citaRepository.save(c3);
		citaRepository.save(c4);
		citaRepository.save(c5);
		citaRepository.save(c6);
		
		Obra o1 = new Obra("Hamlet", "url foto", "fecha", "editorial", "url editorial");
		Obra o2 = new Obra("Romeo y Julieta", "url foto", "fecha", "editorial", "url editorial");
		Obra o3 = new Obra("Enrique 4", "url foto", "fecha", "editorial", "url editorial");
		Obra o4 = new Obra("La ciudad y los perros", "url foto", "fecha", "editorial", "url editorial");
		Obra o5 = new Obra("Don Quijote", "url foto", "fecha", "editorial", "url editorial");
		Obra o6 = new Obra("Poesias castellanas", "url foto", "fecha", "editorial", "url editorial");

		List<Cita> citas1 = new ArrayList<>();
		citas1.add(c1);
		o1.setCitas(citas1);
		
		List<Cita> citas2 = new ArrayList<>();
		citas2.add(c2);
		o2.setCitas(citas2);
		
		List<Cita> citas3 = new ArrayList<>();
		citas3.add(c3);
		o3.setCitas(citas3);
		
		List<Cita> citas4 = new ArrayList<>();
		citas4.add(c4);
		o4.setCitas(citas4);
		
		List<Cita> citas5 = new ArrayList<>();
		citas5.add(c5);
		o5.setCitas(citas5);
		
		List<Cita> citas6 = new ArrayList<>();
		citas6.add(c6);
		o6.setCitas(citas6);
	
		
		List<Autor> autores1 = new ArrayList<>();
		autores1.add(a1);
		o1.setAutores(autores1);
		o2.setAutores(autores1);
		o3.setAutores(autores1);
		
		List<Autor> autores2 = new ArrayList<>();
		autores2.add(a2);
		o4.setAutores(autores2);
				
		List<Autor> autores3 = new ArrayList<>();
		autores3.add(a3);
		o5.setAutores(autores3);
		
		List<Autor> autores4 = new ArrayList<>();
		autores4.add(a4);
		o6.setAutores(autores4);

		obraRepository.save(o1);
		obraRepository.save(o2);
		obraRepository.save(o3);
		obraRepository.save(o4);
		obraRepository.save(o5);
		obraRepository.save(o6);
		
		
		Tema t1 = new Tema("Tema Hamlet");
		Tema t2 = new Tema("Tema Romeo y Julieta");
		Tema t3 = new Tema("Tema Enrique 4");
		Tema t4 = new Tema("Tema La ciudad y los perros");
		Tema t5 = new Tema("Tema Don Quijote");
		Tema t6 = new Tema("Tema Poesias castellanas");
		
		
		List<Obra> obras1 = new ArrayList<>();
		obras1.add(o1);
		t1.setObra(obras1);
		
		List<Obra> obras2 = new ArrayList<>();
		obras2.add(o2);
		t2.setObra(obras2);
		
		List<Obra> obras3 = new ArrayList<>();
		obras3.add(o3);
		t3.setObra(obras3);
		
		List<Obra> obras4 = new ArrayList<>();
		obras4.add(o4);
		t4.setObra(obras4);
		
		List<Obra> obras5 = new ArrayList<>();
		obras5.add(o5);
		t5.setObra(obras5);
		
		List<Obra> obras6 = new ArrayList<>();
		obras6.add(o6);
		t6.setObra(obras6);
		
		temaRepository.save(t1);
		temaRepository.save(t2);
		temaRepository.save(t3);
		temaRepository.save(t4);
		temaRepository.save(t5);
		temaRepository.save(t6);



		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}

}
