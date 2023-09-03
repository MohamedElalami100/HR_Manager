package com.MercureIT.HR_Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http
        .csrf(csrf -> csrf.disable())
    	.authorizeHttpRequests(authz -> authz
    			  .requestMatchers("/users/addnew").permitAll()
    			  .requestMatchers("/users/list").permitAll()
    			  .requestMatchers("/login").permitAll()
    			  .requestMatchers("/register").permitAll()    			  
    			  .requestMatchers("/resources/**").permitAll()
    			  .requestMatchers("/assets/**").permitAll()
    			  .requestMatchers("/css/**").permitAll()
    			  .requestMatchers("/fonts/**").permitAll()
    			  .requestMatchers("/img/**").permitAll()
    			  .requestMatchers("/js/**").permitAll()
    			  .requestMatchers("/user").hasAuthority("Admin")
    			  .requestMatchers("/user/**").hasAuthority("Admin")
    			  .anyRequest().authenticated()
    	)
        .formLogin(formLogin -> 
        formLogin
            .loginPage("/login")
            .failureUrl("/login?error=true") 
            .permitAll()
	    )
	    .logout(logout -> 
	        logout
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login")
	            .permitAll()
	    )
	    .exceptionHandling(exceptionHandling ->
        	exceptionHandling
            	.accessDeniedPage("/accessDenied")
	    );
    	
        return http.build();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();		
		provider.setUserDetailsService(userDetailsService);	
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		return provider;
	}
	
}
