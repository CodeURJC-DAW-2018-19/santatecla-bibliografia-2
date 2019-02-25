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
		Autor a5 = new Autor("Pedro Calderón de la Barca", 
				"https://es.wikipedia.org/wiki/Pedro_Calder%C3%B3n_de_la_Barca#/media/File:Retrato_de_Pedro_Calder%C3%B3n_de_la_Barca.jpg", 
				"https://es.wikipedia.org/wiki/Corona_de_Castilla#/media/File:Corona_de_Castilla_1400_es.svg", 
				"​17 enero 1600", "25 mayo 1681", "Madrid");
		Autor a6 = new Autor("Rubén Darío","https://es.wikipedia.org/wiki/Rub%C3%A9n_Dar%C3%ADo#Figuras_ret%C3%B3ricas", 
				"https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/NIC_orthographic.svg/250px-NIC_orthographic.svg.png",
				"18 de enero de 1867", "6 de febrero de 1916",  "León, Nicaragua");
		Autor a7 = new Autor("Horacio", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Quintus_Horatius_Flaccus.jpg/180px-Quintus_Horatius_Flaccus.jpg",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/Civilizacion_etrusca.png/220px-Civilizacion_etrusca.png", "8 de diciembre de 65 a. C.","27 de noviembre de 8 a. C.", "Roma");
		Autor a8 = new Autor("Fray Luis de León","https://canalhistoria.es/wp-content/uploads/2016/04/frayluisdeleon-1.jpg",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Espa%C3%B1aLoc.svg/266px-Espa%C3%B1aLoc.svg.png", "1527", "1591", "Belmonte");
		Autor a9 = new Autor("Luis de Góngora", "http://www.los-poetas.com/h/gongo.jpg",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Espa%C3%B1aLoc.svg/266px-Espa%C3%B1aLoc.svg.png", "11 de julio de 1561", "23 de mayo de 1627", "Córdoba");
		Autor a10 = new Autor("Duque de Rivas", "https://www.ecured.cu/images/6/64/Duque_de_rivas.jpg",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Espa%C3%B1aLoc.svg/266px-Espa%C3%B1aLoc.svg.png", "10 de marzo de 1791", "22 de junio de 1865", "Córdoba");
		Autor a11 = new Autor("Duque de Rivas 2", "https://www.ecured.cu/images/6/64/Duque_de_rivas.jpg",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Espa%C3%B1aLoc.svg/266px-Espa%C3%B1aLoc.svg.png", "10 de marzo de 1791", "22 de junio de 1865", "Córdoba");
		
												
		autorRepository.save(a1);
		autorRepository.save(a2);
		autorRepository.save(a3);
		autorRepository.save(a4);
		autorRepository.save(a5);
		autorRepository.save(a6);
		autorRepository.save(a7);
		autorRepository.save(a8);
		autorRepository.save(a9);
		autorRepository.save(a10);
		autorRepository.save(a11);

		Cita c1 = new Cita("Ser o no ser, esa es la cuestión. ¿Cuál es más digna acción del ánimo, sufrir los tiros penetrantes de la fortuna injusta, u oponer los brazos a este torrente de calamidades, y darlas fin con atrevida resistencia? Morir es dormir. ¿No más? ");
		Cita c2 = new Cita("El amor corre al amor como el colegial huye del libro y como el que va a clase se aparta de el con cara triste.");
		Cita c3 = new Cita("Señor: líbrame de mis amigos, que de mis enemigos yo me cuidaré.");
		Cita c4 = new Cita("O comes o te comen, no hay más remedio.");
		Cita c5 = new Cita("Cada uno es tal como Dios le hizo, y aún peor muchas veces.");
		Cita c6 = new Cita("coged de vuestra alegre primavera \r\n" + 
				"el dulce fruto antes que el tiempo airado \r\n" + 
				"cubra de nieve la hermosa cumbre.");
		Cita c7 = new Cita("¿Qué es la vida? Un frenesí.\r\n" + "¿Qué es la vida? Una ilusión,\r\n" + 
							"una sombra, una ficción,\r\n" + "y el mayor bien es pequeño; \r\n" + 
							"que toda la vida es sueño,\r\n" + "y los sueños, sueños son.");
		Cita c8 = new Cita("El odio se gana tanto con las buenas acciones como con las malas.");
		Cita c9 = new Cita("No hay dolor más grande que el dolor de ser vivo\r\n" + 
				"ni mayor pesadumbre que la vida consciente");
		Cita c10 = new Cita("Quien estima la dorada medianía, en su seguridad prescinde de la sordidez de una casa vieja, prescinde en su sobriedad del palacio que atrae la envidia.");
		Cita c11 = new Cita("Cuántas veces el excelso pino se ve sacudido por los vientos, y las torres más altas se vienen abajo con caída más dura, como los rayos hieren las cimas de las montañas.");
		Cita c12 = new Cita("Sierra que vas al cielo\r\n" + 
				"altísima, y que gozas del sosiego\r\n" + 
				"que no conoce el suelo,\r\n" + 
				"adonde el vulgo ciego\r\n" + 
				"ama el morir, ardiendo en vivo fuego:");
		Cita c13 = new Cita("La fugitiva ninfa, en tanto, donde\r\n" + 
				"hurta un laurel su tronco al sol ardiente,\r\n" + 
				"tanto jazmines cuanta hierba esconde\r\n" + 
				"la nieve de sus miembros, da una fuente.");
		Cita c14 = new Cita("Busca, imbécil, al padre Rafael... Yo soy un enviado del infierno, soy el demonio exterminador... Huid, miserables.");
		
		
		citaRepository.save(c1);
		citaRepository.save(c2);
		citaRepository.save(c3);
		citaRepository.save(c4);
		citaRepository.save(c5);
		citaRepository.save(c6);
		citaRepository.save(c7);
		citaRepository.save(c8);
		citaRepository.save(c9);
		citaRepository.save(c10);
		citaRepository.save(c11);
		citaRepository.save(c12);
		citaRepository.save(c13);
		citaRepository.save(c14);
		
		
		Obra o1 = new Obra("Hamlet", "https://www.catedra.com/jpg_g/catedra/CA00014412.jpg",
				"1605", "Ed. Oceano", "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg");
		Obra o2 = new Obra("Romeo y Julieta", "https://www.alianzaeditorial.es//jpg_g/alianza/LB00251101.jpg", "1597", "Alianza Editorial", "https://www.vectorlogo.es/wp-content/uploads/2016/10/logo-vector-alianza-editorial.jpg");
		Obra o3 = new Obra("Enrique IV", "https://static0planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/120/m_libros/enrique-iv_9788467041675.jpg", "1597", "Austral", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROjwjXurrLtNC6e2Zhopj_yZjvpcQKzKynXjbGCrNHX0yZtD4D");
		Obra o4 = new Obra("La ciudad y los perros", "https://cdn.gandhi.com.mx/media/catalog/product/cache/1/image/370x/9df78eab33525d08d6e5fb8d27136e95/i/m/image_1165_1_96146.jpg", "1963","Seix Barral", "https://www.planetadelibros.com/img/sellos_og/logo_SEIXBARRAL.jpg");
		Obra o5 = new Obra("Don Quijote", "https://imagessl9.casadellibro.com/a/l/t0/49/9788437622149.jpg", "1605", "Cátedra", "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg");
		Obra o6 = new Obra("Soneto XXIII", "https://www.catedra.com/jpg_p/catedra/CA00004424.jpg" , "1491", "Cátedra", "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg");
		Obra o7 = new Obra("La vida es sueño", "https://www.catedra.com/jpg_g/catedra/CA00047330.jpg",
				"1635 ", "Cátedra", "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg");
		Obra o8 = new Obra("El principe", "https://www.alianzaeditorial.es//jpg_g/alianza/LB00236201.jpg",
							"1532", "Alianza Editorial", "https://www.vectorlogo.es/wp-content/uploads/2016/10/logo-vector-alianza-editorial.jpg");
		Obra o9 = new Obra("Cantos de vida y esperanza", "https://www.alianzaeditorial.es//jpg_g/alianza/LB00098201.jpg", "1905", "Alianza Editorial", "https://www.vectorlogo.es/wp-content/uploads/2016/10/logo-vector-alianza-editorial.jpg");
		Obra o10 = new Obra("Odas", "https://www.alianzaeditorial.es//jpg_g/alianza/LB00127301.jpg", "23 a.C", "Alianza Editorial", "https://www.vectorlogo.es/wp-content/uploads/2016/10/logo-vector-alianza-editorial.jpg");
		Obra o11 = new Obra("Oda a la vida Retirada","1574", "https://http2.mlstatic.com/oda-a-la-vida-retirada-y-otros-poemas-fray-luis-de-leon-D_NQ_NP_682178-MLM26410469570_112017-F.jpg,", "Planeta", "https://pbs.twimg.com/profile_images/926033131223871488/7t1-cwuY_400x400.jpg");
		Obra o12 = new Obra("Fábula de Polifemo y Galatea", "https://www.catedra.com/jpg_g/catedra/CA00220601.jpg","1612", "Cátedra", "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg");
		Obra o13 = new Obra("Don Álvaro o la fuerza del sino", "https://images-na.ssl-images-amazon.com/images/I/41bRQ26T6fL._SX304_BO1,204,203,200_.jpg", "22 de marzo de 1835", "Cátedra", "https://pbs.twimg.com/profile_images/3243051312/6a054036cdb81091d4e57f68fe756462_400x400.jpeg");
		

		
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
		
		List<Cita> citas7 = new ArrayList<>();
		citas7.add(c7);
		o7.setCitas(citas7);
		
		List<Cita> citas8 = new ArrayList<>();
		citas8.add(c8);
		o8.setCitas(citas8);
		
		List<Cita> citas9 = new ArrayList<>();
		citas9.add(c9);
		o9.setCitas(citas9);
		
		List<Cita> citas10 = new ArrayList<>();
		citas10.add(c10);
		citas10.add(c11);
		o10.setCitas(citas10);
		
		List<Cita> citas11 = new ArrayList<>();
		citas11.add(c12);
		o11.setCitas(citas11);

		List<Cita> citas12 = new ArrayList<>();
		citas12.add(c13);
		o12.setCitas(citas12);
		
		List<Cita> citas13 = new ArrayList<>();
		citas13.add(c14);
		o13.setCitas(citas13);

		
		
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

		List<Autor> autores5 = new ArrayList<>();
		autores5.add(a5);
		o7.setAutores(autores5);

		List<Autor> autores6 = new ArrayList<>();
		autores6.add(a5);
		o8.setAutores(autores6);
		
		List<Autor> autores7 = new ArrayList<>();
		autores7.add(a6);
		o9.setAutores(autores7);
		
		List<Autor> autores8 = new ArrayList<>();
		autores8.add(a7);
		o10.setAutores(autores8);

		List<Autor> autores9 = new ArrayList<>();
		autores9.add(a8);
		o11.setAutores(autores9);
		
		List<Autor> autores10 = new ArrayList<>();
		autores10.add(a9);
		o12.setAutores(autores10);
		
		List<Autor> autores11 = new ArrayList<>();
		autores11.add(a10);
		o13.setAutores(autores11);

		
		obraRepository.save(o1);
		obraRepository.save(o2);
		obraRepository.save(o3);
		obraRepository.save(o4);
		obraRepository.save(o5);
		obraRepository.save(o6);
		obraRepository.save(o7);
		obraRepository.save(o8);
		obraRepository.save(o9);
		obraRepository.save(o10);
		obraRepository.save(o11);
		obraRepository.save(o12);
		obraRepository.save(o13);
		
		
		Tema t1 = new Tema("Tragedia");
		Tema t2 = new Tema("El amor");
		Tema t3 = new Tema("Tragicomedia");
		Tema t4 = new Tema("Novela social");
		Tema t5 = new Tema("Comedia");
		Tema t6 = new Tema("Carpe Diem");
		Tema t7 = new Tema("La vida como un sueño");
		Tema t8 = new Tema("El fin justifica los medios");
		Tema t9 = new Tema("El sentido de la vida");
		Tema t10 = new Tema("Aurea Mediocritas");
		Tema t11 = new Tema("Locus amoenus");
		Tema t12 = new Tema("El destino");
		
		
		
		List<Obra> obrasTragedia = new ArrayList<>();
		obrasTragedia.add(o1);
		t1.setObra(obrasTragedia);
		
		List<Obra> obrasAmor = new ArrayList<>();
		obrasAmor.add(o2);
		t2.setObra(obrasAmor);
		
		List<Obra> obrasTragicomedia = new ArrayList<>();
		obrasTragicomedia.add(o3);
		t3.setObra(obrasTragicomedia);
		
		List<Obra> obrasNovSocial = new ArrayList<>();
		obrasNovSocial.add(o4);
		t4.setObra(obrasNovSocial);
		
		List<Obra> obrasComedia = new ArrayList<>();
		obrasComedia.add(o5);
		t5.setObra(obrasComedia);
		
		List<Obra> obrasCarpeDiem = new ArrayList<>();
		obrasCarpeDiem.add(o6);
		t6.setObra(obrasCarpeDiem);
		
		List<Obra> obrasVidaSueno = new ArrayList<>();
		obrasVidaSueno.add(o7);
		t7.setObra(obrasVidaSueno);
		
		List<Obra> obrasFinMedios = new ArrayList<>();
		obrasFinMedios.add(o8);
		t8.setObra(obrasFinMedios);
		
		List<Obra> obrasSentidoVida = new ArrayList<>();
		obrasSentidoVida.add(o9);
		t9.setObra(obrasSentidoVida);
		
		List<Obra> obrasAureaMediocritas = new ArrayList<>();
		obrasAureaMediocritas.add(o10);
		t10.setObra(obrasAureaMediocritas);
		
		List<Obra> obrasLocusAmoenus = new ArrayList<>();
		obrasLocusAmoenus.add(o11);
		obrasLocusAmoenus.add(o12);
		t11.setObra(obrasLocusAmoenus);

		List<Obra> obrasDestino = new ArrayList<>();
		obrasDestino.add(o13);
		t12.setObra( obrasDestino);
		
	
		temaRepository.save(t1);
		temaRepository.save(t2);
		temaRepository.save(t3);
		temaRepository.save(t4);
		temaRepository.save(t5);
		temaRepository.save(t6);
		temaRepository.save(t7);
		temaRepository.save(t8);
		temaRepository.save(t9);
		temaRepository.save(t10);
		temaRepository.save(t11);
		temaRepository.save(t12);


		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}

}
