package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(getAuthProvider());
		
		/*auth.inMemoryAuthentication()
		.withUser("harry").
		password(getPassEncoder().encode("potter"))
		.authorities("MANAGER")
		.and()
		.withUser("ronald").password(getPassEncoder().encode("weasley"))
		.authorities("ACCOUNTANT");
		*/
	}
	
	
	private AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(myUserDetailsService);
		auth.setPasswordEncoder(getPassEncoder());
		return auth;
	}


	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/hello").hasAnyAuthority("MANAGER")
		.antMatchers(HttpMethod.POST,"/user").permitAll()
		.antMatchers(HttpMethod.GET,"/department").hasAnyAuthority("ACCOUNTANT")
		.antMatchers(HttpMethod.POST,"/employee/{did}").authenticated()
		.antMatchers(HttpMethod.GET,"/employee").authenticated()
		.antMatchers(HttpMethod.GET,"/employee/{id}").authenticated()
		.antMatchers(HttpMethod.PUT,"/employee/{id}").authenticated()
		.antMatchers(HttpMethod.DELETE,"/employee/{id}").authenticated()
		.antMatchers(HttpMethod.GET,"/employee/city").permitAll()
		.antMatchers(HttpMethod.GET,"/employee/salary").authenticated()
		.antMatchers(HttpMethod.GET,"/employee/age").authenticated()
		.antMatchers(HttpMethod.GET,"/employee/department/{did}").authenticated()

		.anyRequest().permitAll()
		.and()
		.httpBasic()
		.and().csrf().disable();
	}
	@Bean
	public PasswordEncoder getPassEncoder() {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder;
	}

}
