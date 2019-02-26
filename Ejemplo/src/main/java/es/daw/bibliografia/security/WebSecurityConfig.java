package es.daw.bibliografia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/table").permitAll();

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();

		// Private pages (all other pages)
		http.authorizeRequests().antMatchers("/obra/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/tema/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/autor/**").hasAnyRole("ADMIN");

		http.authorizeRequests().antMatchers("/obrashow/**").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers("/temashow/**").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests().antMatchers("/autorshow/**").hasAnyRole("USER", "ADMIN");

		http.authorizeRequests().antMatchers("/delete/").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/delete/").hasAnyRole("USER");

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/loginerror");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(userRepoAuthProvider);
	}
}
