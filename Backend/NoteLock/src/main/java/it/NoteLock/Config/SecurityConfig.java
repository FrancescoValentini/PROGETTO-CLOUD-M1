package it.NoteLock.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import it.NoteLock.Filters.JWTFilter;
/**
 * Springboot Security Configuration
 * 
 * @author Francesco Valentini
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	JWTFilter jwtFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(c -> c.disable()); // disable csrf protection
		http.formLogin(c -> c.disable()); // disable default login form
		
		// disable springboot sessions
		http.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER)
		); 
		
	    http.headers(headers -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                    ))
        );
	    
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.authorizeHttpRequests(req -> req
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/api/users/**").hasAnyAuthority("Admin")
				.requestMatchers("/api/folders/**").hasAnyAuthority("Admin","Utente")
				.requestMatchers("/api/notes/**").hasAnyAuthority("Admin","Utente")
				.anyRequest().authenticated()
				);
		
		return http.build();
	}
}
