package com.eshop.project.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.eshop.project.api.services.UserService;

@Configuration
@EnableWebSecurity
public class ProjectSecurity extends WebSecurityConfigurerAdapter {
	private final UserService userDetailsService;

	public ProjectSecurity(UserService userDetails) {
		this.userDetailsService = userDetails;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().mvcMatchers("/admin/**").hasAuthority("ADMIN")
				.mvcMatchers("/", "/login", "/signup", "/products/**", "/cart/**", "/category/**", "/search",
						"/product-images/**", "/api/check_email")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.usernameParameter("email").permitAll().and().logout().logoutSuccessUrl("/");

//		http.authorizeRequests().mvcMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated().and()
//				.formLogin().and().httpBasic();
//
//		http.csrf(csrfCustomizer -> {
//			csrfCustomizer.ignoringAntMatchers("/admin/**");
//		});
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/img/**", "/css/**", "/js/**");
	}

}
