package org.sid.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	

   /* 
     //Partie pour stackage des utilisateurs en memoire
     @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    request.getSession().setAttribute("username", authentication.getName());
                    response.sendRedirect("/main");
                }).permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER"))
                .withUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN"));
    }
*/
	
	
	
	
	@Autowired
	private DataSource dataSource;
	
	
	@Bean 
	public PasswordEncoder passwordEncoder() 
	{ 
		return new BCryptPasswordEncoder(); 
	}
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

 
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	
	 
	auth.jdbcAuthentication()
	    .dataSource(dataSource)
	    .usersByUsernameQuery("select username as principal,password as credentiels,actif from joueur where username=?")
	    .authoritiesByUsernameQuery("select username as principal,role as role from relation where username=?")
	    .rolePrefix("ROLE_")
	    .passwordEncoder(passwordEncoder());
	
	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    
	  
}
	
	
   @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/error").permitAll()
            .antMatchers("/resources/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .successHandler((request, response, authentication) -> {
                request.getSession().setAttribute("username", authentication.getName());
                response.sendRedirect("/joueurs?choixJoueurs");
            }).permitAll();
}



}
