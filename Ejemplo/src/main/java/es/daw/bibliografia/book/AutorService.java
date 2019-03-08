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
public class AutorService {

	@Autowired
	private AutorRepository repository;

	public Optional<Autor> findOne(long id) {
		return repository.findById(id);
	}

	public Optional<Autor> findOneByNombre(String nombre) {
		return repository.findOneByNombre(nombre);
	}

	public List<Autor> findAll() {
		return repository.findAll();
	}

	public Page<Autor> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public void save(Autor autor) {
		repository.save(autor);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public List<Autor> findAutoresByObra(Obra obra){
		return obra.getAutores();
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
	
	public Optional<Autor> deleteByNombre(String nombre) {
		Optional<Autor> autor = repository.findOneByNombre(nombre);
		repository.delete(autor.get());
		
		return autor;
	}
}