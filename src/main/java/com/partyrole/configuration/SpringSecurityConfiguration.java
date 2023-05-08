package com.partyrole.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager createUserDetails() {

		UserDetails userDetails1 = createNewUser("Admin", "admin");
		UserDetails userDetails2 = createNewUser("Nilam", "pass");

		return new InMemoryUserDetailsManager(userDetails1,userDetails2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> PasswordEncoder().encode(input);
		UserDetails userDetails = User.builder()
									  .passwordEncoder(passwordEncoder)
									  .username(username)
									  .password(password)
									  .roles("USER","ADMIN")
									  .build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	/*
	 * All URLs are protected Login form is shown for all unauthorized requests CSRF
	 * enabled (Cross sight request forgery) Frames enabled X-Frame-options enabled-
	 * frames can not be used. H2-console uses frames – Disable X frame options
	 * header
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {


		  httpSecurity.authorizeHttpRequests(auth ->
		  		auth.anyRequest().authenticated());
		  httpSecurity.formLogin(withDefaults());
		  httpSecurity.csrf().disable();
		  httpSecurity.headers().frameOptions().disable();
		  
		  return httpSecurity.build();
		  
		  

		/*
		 * httpSecurity.csrf().disable()
		 * .authorizeHttpRequests().requestMatchers("/admin/**").hasAnyRole("ADMIN")
		 * .anyRequest().hasAnyRole("USER").and() .formLogin() .loginPage("/login")
		 * .defaultSuccessUrl("/dashboard") .permitAll();
		 *
		 * return httpSecurity.build();
		 */

	}

	@Bean
	public ObjectMapper objectMapper() {
	    return new ObjectMapper()
	            .setDefaultPropertyInclusion(Include.NON_NULL)
	            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
	            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
	            .findAndRegisterModules();
	}
}
