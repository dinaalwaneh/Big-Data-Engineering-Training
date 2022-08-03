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
	
	// configure AuthenticationManager so that it knows from where to load
	// user for matching credentials
	// Use BCryptPasswordEncoder
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
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
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().antMatchers("/home").permitAll()
			.antMatchers("/login/**").permitAll()
			.antMatchers("/authenticate/**").permitAll()
			.antMatchers("/register/**").permitAll()
			.antMatchers("/user/{username}/**").permitAll()
			.antMatchers("/dashboard").permitAll()
			.antMatchers("/customer").permitAll()
			.antMatchers("/product").permitAll()
			.antMatchers("/employee").permitAll()
			.antMatchers("/users/**").hasAuthority("SUPERUSER")
			.antMatchers("/user/**").hasAuthority("SUPERUSER")
			.antMatchers("/role/**").permitAll()
			.antMatchers("/userr/{id}/**").permitAll()
			.antMatchers("/role/**").permitAll()
			.antMatchers("/items/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER")
			.antMatchers("/item/**").hasAuthority("SUPERUSER")
			.antMatchers("/item/{id}/**").hasAuthority("SUPERUSER")
			.antMatchers("/invoiceitems/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/invoiceitem/{id}/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/paginationAndSort/{offset}/{pageSize}/{field}/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/invoice/{id}/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/customers/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER")
			.antMatchers("/customer/{id}/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER")
			.antMatchers("/invoice/{id}/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/employee/**").hasAuthority("SUPERUSER")
			.antMatchers("/resources/static/**").permitAll()
			.antMatchers("/invoicehistory/{invoiceId}/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/invoicehistory/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/invoiceHistory/**").permitAll()
			.antMatchers("/allinvoiceshistory/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/upload/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER")
			.antMatchers("/invoices/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/sUDashboard/**").permitAll()
			.antMatchers("/sUInvoicesHistory/**").permitAll()
			.antMatchers("/InvicesPaginationAndSort/{userName}/{offset}/{pageSize}/{field}/**").hasAnyAuthority("SUPERUSER","SUPPORTUSER","AUDITORUSER")
			.antMatchers("/invoicesByUserName/{userName}/**").hasAnyAuthority("SUPPORTUSER")
			.antMatchers("/AUDashboard/**").permitAll() 
			.antMatchers("/AUHistory/**").permitAll()
			
			.anyRequest().authenticated();
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}