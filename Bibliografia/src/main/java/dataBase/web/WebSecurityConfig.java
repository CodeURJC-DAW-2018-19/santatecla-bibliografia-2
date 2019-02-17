package dataBase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import dataBase.user.UserRepositoryAuthProvider;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthProvider userRepoAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	//http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic().disable();
    	// Public pages
    	 http.authorizeRequests().antMatchers("/").permitAll();
    	 http.authorizeRequests().antMatchers("/login").permitAll();
    	 http.authorizeRequests().antMatchers("/loginerror").permitAll();
    	 http.authorizeRequests().antMatchers("/logout").permitAll();
    	 // Private pages (all other pages)
    	 http.authorizeRequests().anyRequest().authenticated();
    	 // Login form
    	 http.formLogin().loginPage("/login");
    	 http.formLogin().usernameParameter("username");
    	 http.formLogin().passwordParameter("password");
    	 http.formLogin().defaultSuccessUrl("/home");
    	 http.formLogin().failureUrl("/loginerror");
    	 // Logout
    	 http.logout().logoutUrl("/logout");
    	 http.logout().logoutSuccessUrl("/");

    	 // Disable CSRF at the moment
    	 http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(userRepoAuthProvider);
    }
}
