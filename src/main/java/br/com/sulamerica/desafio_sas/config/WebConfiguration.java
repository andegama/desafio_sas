package br.com.sulamerica.desafio_sas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import br.com.sulamerica.desafio_sas.component.JwtTokenProvider;

@Configuration
public class WebConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
    private JwtTokenProvider jwtTokenProvider;

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers("/h2_console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/cargo/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cargo/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/cargo/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/cargo/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    }

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
