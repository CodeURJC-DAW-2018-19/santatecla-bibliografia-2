package es.daw.bibliografia.book;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ObraService {

	@Autowired
	private ObraRepository repository;

	public Optional<Obra> findOne(long id) {
		return repository.findById(id);
	}

	public List<Obra> findAll() {
		return repository.findAll();
	}

	public Page<Obra> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Optional<Obra> findOneByTitle(String title) {
		return repository.findOneByTitle(title);
	}

	public List<Obra> findByAuthor(Autor author) {
		return repository.findByAutores(author);
	}

	public List<Obra> findByCita(Cita cita) {
		return repository.findByCitas(cita);
	}

	public void save(Obra obra) {
		repository.save(obra);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
