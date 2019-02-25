package es.daw.bibliografia.book;

import java.util.List;
import java.util.Optional;

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
}