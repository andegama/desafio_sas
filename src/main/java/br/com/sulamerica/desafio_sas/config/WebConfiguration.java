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

@SuppressWarnings("deprecation")
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
                .antMatchers(HttpMethod.GET, "/cargo/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.DELETE, "/cargo/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.PUT, "/cargo/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.POST, "/cargo/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.GET, "/usuario/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.DELETE, "/usuario/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.PUT, "/usuario/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.POST, "/usuario/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.GET, "/perfil/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.DELETE, "/perfil/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.PUT, "/perfil/**").hasAuthority("Administrador")
                .antMatchers(HttpMethod.POST, "/perfil/**").hasAuthority("Administrador")
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    }

	//TODO - Implementar Encoder
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
