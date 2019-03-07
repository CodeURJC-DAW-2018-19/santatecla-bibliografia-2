package es.daw.bibliografia;

import org.springframework.beans.factory.annotation.Autowired;

import es.daw.bibliografia.web.AutorWebController;
import es.daw.bibliografia.web.BookWebController;
import es.daw.bibliografia.web.ObraController;
import es.daw.bibliografia.web.TemaWebController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

	@Autowired
	private BookWebController bookWebController;

	@Autowired
	private AutorWebController autorWebController;

	@Autowired
	private ObraController obraController;

	@Autowired
	private TemaWebController temaController;

	@Test
	public void contexLoads() throws Exception {
		assertThat(bookWebController).isNotNull();
		assertThat(autorWebController).isNotNull();
		assertThat(obraController).isNotNull();
		assertThat(temaController).isNotNull();
	}

}
