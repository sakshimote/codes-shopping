package com.lms.api;

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

import com.lms.api.service.MyUserDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
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
		// TODO Auto-generated method stub
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(myUserDetailsService);
		auth.setPasswordEncoder(getPassEncoder());
		return auth;
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/user/register").permitAll()
		.antMatchers(HttpMethod.GET,"/user/login").authenticated()
		.antMatchers(HttpMethod.PUT,"/user/update/**").authenticated()
		.antMatchers(HttpMethod.POST,"/learningTrack/insert").hasAnyAuthority("admin")
		.antMatchers(HttpMethod.POST,"/course/insert/{ltid}").hasAnyAuthority("admin")
		.antMatchers(HttpMethod.POST,"/course/author/**/**").authenticated()
		.antMatchers(HttpMethod.POST,"/author/insert").authenticated()
		.antMatchers(HttpMethod.GET,"/learning-track").permitAll()
		.antMatchers(HttpMethod.POST,"/enroll/user/learning-track/{uid}/{tid}").authenticated()	
		.antMatchers(HttpMethod.GET,"/user/learning-track").authenticated()
		.antMatchers(HttpMethod.POST,"/review/{cid}").authenticated()
		.antMatchers(HttpMethod.GET,"/reviews").permitAll()
		.antMatchers(HttpMethod.GET,"/review/{cid}").permitAll()
		.antMatchers(HttpMethod.GET,"/review/sort-rating/{cid}").permitAll()
		.antMatchers(HttpMethod.PUT,"/review/{rid}").authenticated()
		.antMatchers(HttpMethod.GET,"/review/statsByCid/{cid}").authenticated()
		.antMatchers(HttpMethod.POST,"/video/insert/{mid}").authenticated()
		.antMatchers(HttpMethod.POST,"/module/insert/{cid}").authenticated()
		.antMatchers(HttpMethod.GET,"/course/video/stats/{cid}").permitAll()
		.antMatchers(HttpMethod.POST,"/topic/insert").authenticated()
		.antMatchers(HttpMethod.POST,"/question/insert").authenticated()
		.antMatchers(HttpMethod.POST,"/answer/insert").authenticated()
		.antMatchers(HttpMethod.POST,"/topic/question/{tid}/{qid}").authenticated()
		.antMatchers(HttpMethod.POST,"/question/answer/{qid}/{aid}").authenticated()
		.antMatchers(HttpMethod.GET,"/topic-records").authenticated()
		.antMatchers(HttpMethod.GET,"/question/{tid}").authenticated()
		.antMatchers(HttpMethod.GET,"/answers/{qid}").authenticated()
		.antMatchers(HttpMethod.PUT,"/question/likes/{qid}").authenticated()
		.antMatchers(HttpMethod.PUT,"/answer/likes/{aid}").authenticated()
		.antMatchers(HttpMethod.PUT,"answer/update/{aid}").authenticated()
		.antMatchers(HttpMethod.PUT,"question/update/{qid}").authenticated()
		.antMatchers(HttpMethod.GET,"/topic-stats").authenticated()
		
		
		

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

