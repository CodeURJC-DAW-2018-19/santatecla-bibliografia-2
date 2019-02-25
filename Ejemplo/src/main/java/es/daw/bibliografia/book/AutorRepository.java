package es.daw.bibliografia.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	Optional<Autor> findOneByNombre(String nombre);
}
