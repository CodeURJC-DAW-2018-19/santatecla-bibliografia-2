package es.daw.bibliografia;

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

	@PostConstruct
	public void init() {

		// Sample books

		bookRepository.save(new Book("SUEÑOS DE ACERO Y NEON",
				"Los personajes que protagonizan este relato sobreviven en una sociedad en decadencia a la que, no obstante, lograrán devolver la posibilidad de un futuro. Año 2484. En un mundo dominado por las grandes corporaciones, solo un hombre, Jordi Thompson, detective privado deslenguado y vividor, pero de gran talento y sentido d..."));
		bookRepository.save(new Book("LA VIDA SECRETA DE LA MENTE",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos."));
		bookRepository.save(new Book("CASI SIN QUERER",
				"El amor algunas veces es tan complicado como impredecible. Pero al final lo que más valoramos son los detalles más simples, los más bonitos, los que llegan sin avisar. Y a la hora de escribir sobre sentimientos, no hay nada más limpio que hacerlo desde el corazón. Y eso hace Defreds en este libro."));
		bookRepository.save(new Book("TERMINAMOS Y OTROS POEMAS SIN TERMINAR",
				"Recopilación de nuevos poemas, textos en prosa y pensamientos del autor. Un sabio dijo una vez: «Pocas cosas hipnotizan tanto en este mundo como una llama y como la luna, será porque no podemos cogerlas o porque nos iluminan en la penumbra». Realmente no sé si alguien dijo esta cita o me la acabo de inventar pero deberían de haberla escrito porque el poder hipnótico que ejercen esa mujer de rojo y esa dama blanca sobre el ser humano es digna de estudio."));
		bookRepository.save(new Book("LA LEGIÓN PERDIDA",
				"En el año 53 a. C. el cónsul Craso cruzó el Éufrates para conquistar Oriente, pero su ejército fue destrozado en Carrhae. Una legión entera cayó prisionera de los partos. Nadie sabe a ciencia cierta qué pasó con aquella legión perdida.150 años después, Trajano está a punto de volver a cruzar el Éufrates. ..."));

		//Sample Autor-Cita
		
//		Autor a = new Autor("Pepe");
//		autorRepository.save(a);
//		
//		Cita c1 = new Cita("Hola people");
//		Cita c2 = new Cita("Hola gente");
//		
//		c1.setAutor(a);
//		c2.setAutor(a);
//		
//		citaRepository.save(c1);
//		citaRepository.save(c2);
		
		Obra o = new Obra("titulo","url foto","fecha","editorial","url editorial");
		obraRepository.save(o);
		// Sample users

//		userRepository.save(new User("user", "pass", "ROLE_USER"));
//		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}

}
