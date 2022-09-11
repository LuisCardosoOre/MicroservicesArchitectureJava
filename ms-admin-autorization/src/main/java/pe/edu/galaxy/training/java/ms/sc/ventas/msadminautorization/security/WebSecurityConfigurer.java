package pe.edu.galaxy.training.java.ms.sc.ventas.msadminautorization.security;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*
	 * @Override
	 * 
	 * @Bean public UserDetailsService userDetailsServiceBean() throws Exception {
	 * return super.userDetailsServiceBean(); }
	 */
	private UserDetailsService userDetailsService;
	

	public WebSecurityConfigurer(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/* 
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
	/**/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* */
		auth.inMemoryAuthentication().withUser("user").password("{noop}123").roles("USER").and().withUser("lcardoso")
				.password("{noop}abc").roles("USER", "ADMIN");
		
		System.out.println("configure...");
		//auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
/*
	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER);
		return bean;
	}
*/
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.antMatcher("/**").authorizeRequests() .antMatchers("/oauth/authorize**",
	 * "/login**", "/error**", "/oauth/token**").permitAll().and()
	 * .authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll
	 * (); }
	 */
}
