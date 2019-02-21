package es.daw.bibliografia.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Long> {
	List<Obra> findByAutores(Autor autor);
	List<Obra> findByCitas(Cita cita);
	Optional<Obra> findOneByTitle(String title);
}
