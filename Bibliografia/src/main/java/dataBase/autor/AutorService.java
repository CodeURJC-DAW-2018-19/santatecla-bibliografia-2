package dataBase.autor;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AutorService {

	@Autowired
	private AutorRepository repository;

	public Optional<Autor> findOne(long id) {
		return repository.findById(id);
	}

	public List<Autor> findAll() {
		return repository.findAll();
	}

	public void save(Autor autor) {
		repository.save(autor);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}
