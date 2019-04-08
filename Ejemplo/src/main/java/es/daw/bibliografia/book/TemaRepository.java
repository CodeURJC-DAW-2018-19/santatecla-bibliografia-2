package es.daw.bibliografia.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	List<Tema> findByObras(Obra obra);

	Optional<Tema> findOneByContenido(String contenido);
}
