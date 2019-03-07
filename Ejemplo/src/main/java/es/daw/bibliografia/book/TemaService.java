package es.daw.bibliografia.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemaService {

	@Autowired
	private TemaRepository repository;

	public Optional<Tema> findOne(long id) {
		return repository.findById(id);
	}

	public List<Tema> findAll() {
		return repository.findAll();
	}

	public Page<Tema> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<Tema> findOneByContenido(String contenido) {
		return repository.findOneByContenido(contenido);
	}

	public void save(Tema tema) {
		repository.save(tema);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

	public Tema findByObra(Obra obra) {
		return repository.findByObras(obra);
	}
	
	public List <Obra> findObrasByTema(Tema tema){
		List<Obra> obras = tema.getObras();
		return obras;	
		
	}
	
	public List<Cita> findCitasByTema(Tema tema){
		List<Obra> obras = tema.getObras();
		List<Cita> citas = new ArrayList<Cita>();
		for (int i = 0; i < obras.size(); i++) {
			citas = Stream.concat(citas.stream(), obras.get(i).getCitas().stream()).collect(Collectors.toList());
		}
		return citas;
	}
	
	public List<Autor> findAutoresByTema(Tema tema){
		List<Obra> obras = tema.getObras();
		List<Autor> autores = new ArrayList<Autor>();
		for (int i = 0; i < obras.size(); i++) {
			autores = Stream.concat(autores.stream(), obras.get(i).getAutores().stream())
					.collect(Collectors.toList());
		}
		return autores;
	}

	public void deleteByContenido(String contenido) {
		Tema tema= repository.findOneByContenido(contenido).get();
		repository.delete(tema);
	}
	
	public List<Tema> findTemasByObras(List<Obra> obras){
		List<Tema> temas = new ArrayList<>();
		Tema tema;
		for (int i = 0; i < obras.size(); i++) {
			tema = repository.findByObras(obras.get(i));
			if (tema != null)
				temas.add(tema);
		}
		return temas;
	}
	
}
