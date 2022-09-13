package com.org.cricketleagueapp.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class CricketLeagueSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
    public void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth.inMemoryAuthentication()
        .withUser("organiser").password(passwordEncoder().encode("org1234")).roles("ORGANIZER")
        .and()
        .withUser("owner").password(passwordEncoder().encode("own1234")).roles("OWNER")
        .and()
        .withUser("player").password(passwordEncoder().encode("player1234")).roles("PLAYER")
        .and()
        .withUser("audience").password(passwordEncoder().encode("aud1234")).roles("AUDIENCE");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
	        .authorizeRequests()
	        .antMatchers("/ground/**").permitAll()
	        .and()
	        .authorizeRequests()
	        .antMatchers("/organiser/**", "/tournament/**").hasRole("ORGANIZER")
	        .antMatchers("/own/**", "/team/**").hasRole("OWNER")
	        .antMatchers("/player/**").hasRole("PLAYER")
	        .antMatchers("/audience/**").hasRole("AUDIENCE")
	        .anyRequest().authenticated()
	        .and()
	        .httpBasic();
		 http.cors().and().csrf().disable();

    }
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
