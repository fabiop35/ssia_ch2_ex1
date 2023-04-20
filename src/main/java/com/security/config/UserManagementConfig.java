package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

//import com.security.model.DummyUser;
import com.security.model.User;
import com.security.service.InMemoryUserDetailsService;

@Configuration
public class UserManagementConfig {


    @Bean
    public UserDetailsService userDetailsService(){

       // var userDetailsService = 
         //    new InMemoryUserDetailsManager();

       /* var user = User.withUsername("john")
           .password("123")
           .authorities("read")
           .build(); */
       //DummyUser user = new DummyUser();
       UserDetails u = 
           new User("bill", "123", "read");

       List<UserDetails> users = 
                            List.of(u);

       //userDetailsService.createUser(user); 
       //return userDetailsService;
       return new InMemoryUserDetailsService(users);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
