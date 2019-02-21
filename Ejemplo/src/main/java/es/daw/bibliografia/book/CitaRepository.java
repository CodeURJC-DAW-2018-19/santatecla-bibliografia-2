package es.daw.bibliografia.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
	Optional<Cita> findOneByContenido(String contenido);
}
