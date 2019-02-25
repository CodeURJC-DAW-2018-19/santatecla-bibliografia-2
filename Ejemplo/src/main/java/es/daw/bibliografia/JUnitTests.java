package es.daw.bibliografia;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorRepository;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.BookRepository;
import es.daw.bibliografia.book.BookService;
import es.daw.bibliografia.book.CitaRepository;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraRepository;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaRepository;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JUnitTests {
	
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
	
	@Autowired
	private BookService bookService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private ObraService obraService;
	@Autowired
	private TemaService temaService;
	

	
    @Test
    public void getsDataAuthorExample() throws Exception {	
    	assertEquals(autorService.findOneByNombre("William Shakespeare").get().getNombre(), 
    			"William Shakespeare");
    	assertEquals(autorService.findOneByNombre("William Shakespeare").get().getFecha_def(), 
    			"23 de abril 1616");
    	assertEquals(autorService.findOneByNombre("William Shakespeare").get().getFecha_nac(), 
    			"26 de abril de 1564");
    	assertEquals(autorService.findOneByNombre("William Shakespeare").get().getUrl_foto(), 
    			"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/255px-Shakespeare.jpg");
    	assertEquals(autorService.findOneByNombre("William Shakespeare").get().getUrl_mapa(), 
    			"https://media-cdn.tripadvisor.com/media/photo-s/0d/f4/e1/3e/england-in-one-day-stonehenge.jpg");
    }
    
    @Test
    public void getsDataThemeExample() throws Exception {
    	assertEquals(temaService.findOneByContenido("Tragedia").get().getContenido(), "Tragedia");
    }
    
    @Test
    public void getsDataWorkExample() throws Exception {
    	assertEquals(obraService.findOneByTitle("Hamlet").get().getTitle(), "Hamlet");
    	assertEquals(obraService.findOneByTitle("Hamlet").get().getURL(), "https://www.catedra.com/jpg_g/catedra/CA00014412.jpg");
    	assertEquals(obraService.findOneByTitle("Hamlet").get().getUrl_editorial(), "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg");
    	assertEquals(obraService.findOneByTitle("Hamlet").get().getDate(), "1605");
    	assertEquals(obraService.findOneByTitle("Hamlet").get().getEditorial(), "Ed. Oceano");

    }

}