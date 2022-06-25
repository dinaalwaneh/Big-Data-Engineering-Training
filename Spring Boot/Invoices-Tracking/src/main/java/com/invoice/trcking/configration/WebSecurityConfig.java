package com.invoice.trcking.configration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	
@Bean
	
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder( passwordEncoder());
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	            .ignoring()
	            .antMatchers("/css/**","/images/**","/js/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			
			.authorizeRequests().antMatchers("/home").permitAll()
			.antMatchers("/login/**").permitAll()
			.antMatchers("/authenticate/**").permitAll()
			.antMatchers("/register/**").permitAll()
			.antMatchers("/register/**").permitAll()
			.antMatchers("/user/{username}/**").permitAll()
			.antMatchers("/profile").permitAll()
			.antMatchers("/dashboard").permitAll()
			.antMatchers("/dashboardd").permitAll()
			.antMatchers("/customer").permitAll()
			.antMatchers("/product").permitAll()
			.antMatchers("/employee").permitAll()
			.antMatchers("/get/users/**").permitAll()
			.antMatchers("/add/user/**").permitAll()
			.antMatchers("/add/role/**").permitAll()
			.antMatchers("/get/user/{id}/**").permitAll()
			.antMatchers("/add/role/**").permitAll()
			.antMatchers("/get/items/**").permitAll()
			.antMatchers("/add/item/**").permitAll()
			.antMatchers("/put/item/{id}/**").permitAll()
			
			.antMatchers("/get/customers/**").permitAll()
			.antMatchers("/update/customer/{id}/**").permitAll()
			.antMatchers("/add/employee/**").permitAll()
			.antMatchers("/resources/static/**").permitAll()
			.anyRequest().authenticated();
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}