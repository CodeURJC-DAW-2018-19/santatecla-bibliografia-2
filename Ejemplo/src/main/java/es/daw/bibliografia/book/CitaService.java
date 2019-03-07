package es.daw.bibliografia.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public Optional<Cita> findOneByContenido(String contenido) {
		return repository.findOneByContenido(contenido);
	}

	public void save(Cita cita) {
		repository.save(cita);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public List<Cita> findCitasByObras(List<Obra> obras){
		List<Cita> citas = new ArrayList<>();
		for (int i = 0; i < obras.size(); i++) {
			citas = Stream.concat(citas.stream(), obras.get(i).getCitas().stream()).collect(Collectors.toList());
		}
		return citas;
	}
}
