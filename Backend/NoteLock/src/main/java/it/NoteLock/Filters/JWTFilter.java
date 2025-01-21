package it.NoteLock.Filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import it.NoteLock.Exceptions.GlobalExceptionHandler;
import it.NoteLock.Models.UserAccount;
import it.NoteLock.Repositories.UserRepository;
import it.NoteLock.Utils.JWTTools;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
    private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

	@Autowired
	JWTTools jwtTools;
	
	@Autowired
	UserRepository repo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		
        if (token == null || !token.startsWith("Bearer ")) { // token non presente
            filterChain.doFilter(request, response); 
            return;
        }else { // token presente, lo verifica
        	String userID = jwtTools.verifyToken(token.substring(7)).get("sub").toString();
        	Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
        	if(userID != null && authContext == null) {
            	UserAccount u = repo.findById(userID).get();

    			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
    					u,
    					null,
    					u.getAuthorities()
    					);
    			SecurityContextHolder.getContext().setAuthentication(auth);
    			
        	}
        }
		
		filterChain.doFilter(request, response);
	}
	
	
	@Bean
	public FilterRegistrationBean registration(JWTFilter filter) {
	    FilterRegistrationBean<JWTFilter> registrationBean = new FilterRegistrationBean<>(filter);
	    registrationBean.setEnabled(false); // Disabilita la registrazione globale
	    return registrationBean;
	}

}
