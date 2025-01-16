package it.NoteLock.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
/**
 * Springboot Security Configuration
 * 
 * @author Francesco Valentini
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
	    
		// http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		// Allow all requests without authentication (TEST only)
		http.authorizeHttpRequests(req -> req
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/**").permitAll()
				.anyRequest().authenticated()
				);
		
		return http.build();
	}
}
