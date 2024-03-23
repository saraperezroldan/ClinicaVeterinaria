package com.clinica.clinicaVeterinaria.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

	@Value("${app.security.enable}")
	private boolean isAuthenticated;
	
	@Autowired
	private CustomAuthenticationSuccessHandler loginSuccessHandler;
	
	@Bean
	SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
		
		// activate cors and disable csrf
		http.cors().and().csrf().disable();
		
		if(isAuthenticated) {
			// all request authenticated
			http.authorizeRequests((requests) -> requests.anyRequest().authenticated());			

			// OKTA configuration
			// enables OAuth redirect login
			http.oauth2Login()
				.successHandler(loginSuccessHandler);
			
		    // enables OAuth Client configuration
		    http.oauth2Client();
		    // enables REST API support for Opaque or JWT Tokens
		    http.oauth2ResourceServer().opaqueToken();
		}
		
		return http.build();
	}
}
