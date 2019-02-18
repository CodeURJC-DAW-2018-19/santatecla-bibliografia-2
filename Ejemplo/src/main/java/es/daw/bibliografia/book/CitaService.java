package es.daw.bibliografia.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService {

	@Autowired
	private CitaRepository repository;

	public Optional<Cita> findOne(long id) {
		return repository.findById(id);
	}

	public List<Cita> findAll() {
		return repository.findAll();
	}

	public void save(Cita cita) {
		repository.save(cita);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}
