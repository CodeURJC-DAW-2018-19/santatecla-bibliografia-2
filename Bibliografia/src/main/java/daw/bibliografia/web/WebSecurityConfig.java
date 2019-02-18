package daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import daw.bibliografia.user.UserRepositoryAuthProvider;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthProvider userRepoAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
    	// Public pages
    	 http.authorizeRequests().antMatchers("/").permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(userRepoAuthProvider);
    }
}
