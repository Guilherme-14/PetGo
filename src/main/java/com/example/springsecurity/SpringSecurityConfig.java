package com.example.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http
		.csrf().disable()
		.authorizeHttpRequests((requests)-> requests
				.requestMatchers(
						"/v3/api-docs/**",
	                    "/swagger-ui/**",
	                    "/swagger-ui.html/**")
				.permitAll()
				.requestMatchers(
						HttpMethod.POST,"/consulta/", "/especialidade/", "/veterinario/", "/proprietario/", "/pet/")
				.permitAll()
				.requestMatchers(
						HttpMethod.GET,"/consulta/", "/especialidade/", "/veterinario/", "/proprietario/", "/pet/")
				.permitAll()
				.requestMatchers(
						HttpMethod.DELETE,"/consulta/{id}", "/especialidade/{id}","/veterinario/{id}","/proprietario/{id}","/pet/{id}")
				.permitAll()
				.requestMatchers(
						HttpMethod.PUT,"/consulta/**","/especialidade/**","/veterinario/**","/proprietario/**","/pet/**")
				.permitAll()				
				.anyRequest()
				.authenticated()	
				)
		.httpBasic();
		return http.build();
	}
	@Bean
	public InMemoryUserDetailsManager userDetailsService(){
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("CodeCrusaders")
				.password("P@ssw0rd")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);

	}
}
