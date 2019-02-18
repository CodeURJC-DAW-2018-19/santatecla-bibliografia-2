package daw.bibliografia.obra;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Long> {
	List<Obra> findByTitle (String title);
	
}
