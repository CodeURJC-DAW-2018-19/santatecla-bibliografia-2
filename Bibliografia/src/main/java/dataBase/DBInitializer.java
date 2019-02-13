package dataBase;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dataBase.user.User;
import dataBase.user.UserRepository;

@Component
public class DBInitializer {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		userRepository.save(new User("alumno", "alumno", "ROLE_USER"));
		userRepository.save(new User("profe", "profe", "ROLE_USER", "ROLE_ADMIN"));	
	}


}