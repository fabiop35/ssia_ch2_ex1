package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;

import com.security.handlers.CustomAuthenticationSuccessHandler;
import com.security.handlers.CustomAuthenticationFailureHandler;


@EnableAsync
@Configuration
public class WebAuthorizationConfig
    extends WebSecurityConfigurerAdapter {

 @Autowired
 private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

 @Autowired
 private CustomAuthenticationFailureHandler authenticationFailureHandler;
    
 @Bean
 public InitializingBean 
           initializingBean(){
      
      return () -> SecurityContextHolder
          .setStrategyName(
            SecurityContextHolder.
             MODE_INHERITABLETHREADLOCAL);
    }

 @Autowired
 private AuthenticationProvider authenticationProvider;


 /* @Override
  protected void configure(
    AuthenticationManagerBuilder auth){
      
 System.out.println("》》CONFIGURE 《《");  
    auth.authenticationProvider(
                authenticationProvider);
    }*/
    
    /*@Override
    protected void configure(
              HttpSecurity http) 
                   throws Exception{
        
      http.httpBasic( c -> {
        
        c.realmName("OTHER");
        c.authenticationEntryPoint(
         new CustomEntryPoint () );
      });
      http.authorizeRequests().
          anyRequest().authenticated();
    }*/

    @Override
    protected void configure(
                 HttpSecurity http)
                     throws Exception {

      //http.httpBasic();
      http.formLogin()
        //.defaultSuccessUrl("/home", true);
     .successHandler(authenticationSuccessHandler)
     .failureHandler(authenticationFailureHandler)
     .and()
      .httpBasic();

      http.authorizeRequests()
        .anyRequest().authenticated();
    }

}
