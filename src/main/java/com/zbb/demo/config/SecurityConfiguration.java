package com.zbb.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.zbb.demo.business.IUserService;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
   @Autowired
   private IUserService userService;
 
    @Autowired
    private DataSource dataSource;
 
   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	
    	 auth.inMemoryAuthentication()                                                           //default user and pass    
         .withUser("Zehra").password(passwordEncoder().encode("arhez")).roles("USER")      
         .and()
         .withUser("Admin").password(passwordEncoder().encode("nimda")).roles("ADMIN");    
 
        auth.userDetailsService(userService)
         .passwordEncoder(passwordEncoder());
        
    }
   
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http         
         .headers()
          .frameOptions().sameOrigin()
          .and()
            .authorizeRequests()
             .antMatchers("/resources/**", "/webjars/**","/registration","/").permitAll()
              
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/h2-console/**").hasRole("ADMIN")
                
                .anyRequest().authenticated()
                .and()
                
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/login?logout")
             .deleteCookies("my-remember-me-cookie")
                .permitAll()
                .and()
             .rememberMe()
              //.key("my-secure-key")
              .rememberMeCookieName("my-remember-me-cookie")
              .tokenRepository(persistentTokenRepository())
              .tokenValiditySeconds(24 * 60 * 60)
              .and()
            .exceptionHandling()
            .and().csrf().ignoringAntMatchers("/h2-console/**")
            .and().headers().frameOptions().sameOrigin()
              ;
  
    }
    
    PersistentTokenRepository persistentTokenRepository(){
     JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
     tokenRepositoryImpl.setDataSource(dataSource);
     return tokenRepositoryImpl;
    }
}