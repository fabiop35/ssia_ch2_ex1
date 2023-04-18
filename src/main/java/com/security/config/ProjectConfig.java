package com.security.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;



@Configuration
public class ProjectConfig 
            extends WebSecurityConfigurerAdapter {

   /* @Override
    @Bean
    public UserDetailsService userDetailsService(){

        var userDetailsService = 
            new InMemoryUserDetailsManager();

        var user = 
            User.withUsername("john")
             .password("12345")
             .authorities("read")
             .build();

        userDetailsService.createUser(user);

        return userDetailsService;
    } */

   /* @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();

    } */

   @Override
    protected void configure(HttpSecurity http)
        throws Exception {

        http.httpBasic();
        http.authorizeRequests()
         .anyRequest()
         .authenticated();

      //get resources without authentication 
       /* http.authorizeRequests()
            .anyRequest()
            .permitAll();*/
    } 
    
    /* Define UserDetailsService and PassEncoder*/
    public void configure(
        AuthenticationManagerBuilder auth) 
                throws Exception {
    
        
        auth.inMemoryAuthentication()
            .withUser("john")
            .password("12445")
            .authorities("read")
            .and()
            .passwordEncoder(
              NoOpPasswordEncoder.getInstance() );


        /* var userDetailsService = 
            new InMemoryUserDetailsManager();

        var user = User.withUsername("john")
            .password("12345")
            .authorities("read")
            .build();

        userDetailsService.createUser(user);

        auth.userDetailsService(userDetailsService)
         .passwordEncoder(
             NoOpPasswordEncoder.getInstance());*/

    }
}
