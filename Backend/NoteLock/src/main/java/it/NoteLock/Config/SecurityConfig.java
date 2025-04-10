package it.NoteLock.Config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
	
    @Value("${spring.application.feurl}")
    private String feUrl;

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
	    
	    http.cors(Customizer.withDefaults());
	    
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
	

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowedOrigins(List.of(feUrl)); 
	    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    config.setAllowedHeaders(List.of("*")); 
	    config.setAllowCredentials(true); 
	
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    return source;
	}
}
