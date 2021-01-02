package com.ignacio.springboot.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import com.ignacio.springboot.app.models.Person;
import com.ignacio.springboot.app.repository.PersonRepository;

@SpringBootApplication
public class ProyectoCrudProductosApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProyectoCrudProductosApplication.class, args);
	}

}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  PersonRepository personRepository;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(inputName-> {
      Person person = personRepository.findByEmail(inputName);
        if (person != null) { // si existe se instancia la clase User de Spring
          return new User(person.getEmail(), person.getPassword(),
                  AuthorityUtils.createAuthorityList("USER")); // Autentica al usuario como USER
        } else {
          throw new UsernameNotFoundException("Unknown user: " + inputName);
        }
    });
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
               .antMatchers("/admin/**").hasAuthority("ADMIN")
               .antMatchers("/nuevo-producto.html").hasAuthority("USER")
               .and()
            .formLogin();
        
        http.formLogin()
        .usernameParameter("email")
        .passwordParameter("password")
        .loginPage("/api/login");  
     http.logout().logoutUrl("/api/logout");
     
  // turn off checking for CSRF tokens
     http.csrf().disable();

     // if user is not authenticated, just send an authentication failure response
     http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

     // if login is successful, just clear the flags asking for authentication
     http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

     // if login fails, just send an authentication failure response
     http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

     // if logout is successful, just send a success response
     http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
   }

   private void clearAuthenticationAttributes(HttpServletRequest request) {
     HttpSession session = request.getSession(false);
     if (session != null) {
       session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
     }
   }
     

/*private boolean isGuest(Authentication authentication) {
	  return authentication == null || authentication instanceof AnonymousAuthenticationToken;
	}*/
}