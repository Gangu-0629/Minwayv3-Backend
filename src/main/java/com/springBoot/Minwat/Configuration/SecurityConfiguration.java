package com.springBoot.Minwat.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.springBoot.Minwat.JWTokens.JwtAuthenticator;
import com.springBoot.Minwat.JWTokens.JwtExceptionHandler;
import com.springBoot.Minwat.Services.Userservice;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private Userservice userservice;
	
	@Autowired
	private JwtExceptionHandler point;
	
	@Autowired
	private JwtAuthenticator filter;
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationprovider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userservice);
		provider.setPasswordEncoder(passwordencoder());
		return provider;
	}
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity) throws Exception{
		httpsecurity.csrf((c)->c.disable()).authorizeHttpRequests((auth)->auth.requestMatchers("/auth/**").permitAll().requestMatchers("/ws/**").permitAll().requestMatchers("/app/**").permitAll().requestMatchers("/info/**").authenticated())
		.exceptionHandling((ex)->ex.authenticationEntryPoint(point))
		 .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		
		 httpsecurity.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
		 ;
		return httpsecurity.build();
	}

	@Bean
	public AuthenticationManager Authenticationmanager(AuthenticationConfiguration builder) throws Exception{
		return builder.getAuthenticationManager();
	}
	

}
