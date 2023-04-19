package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
  
import com.security.model.DummyUser;

@Configuration
public class UserManagementConfig {


    @Bean
    public UserDetailsService userDetailsService(){

        var userDetailsService = 
             new InMemoryUserDetailsManager();

       /* var user = User.withUsername("john")
           .password("123")
           .authorities("read")
           .build(); */
       DummyUser user = new DummyUser();

       userDetailsService.createUser(user); 

       return userDetailsService;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
