package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

import java.util.List;

//import com.security.model.DummyUser;
import com.security.model.User;
import com.security.service.InMemoryUserDetailsService;

import org.springframework.security.provisioning.JdbcUserDetailsManager;

import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;

import com.security.config.PlainTextPasswordEncoder;

@Configuration
public class UserManagementConfig {

    
    @Bean
    public UserDetailsService
                 userDetailsService() {
        
       var cs = new 
           DefaultSpringSecurityContextSource("ldap://127.0.0.1:33389/dc=springframework,dc=org");
      var manager = new LdapUserDetailsManager(cs);
      cs.afterPropertiesSet();
      manager.setUsernameMapper(
    new
DefaultLdapUsernameToDnMapper("ou=groups", "uid") );
    
      manager.setGroupSearchBase("ou=groups");

      return manager;
    }
    /* DB Implementation 
    @Bean
    public UserDetailsService 
         userDetailsService(DataSource dts){

     String usersByUsernameQuery =
         "SELECT user, password, enabled "+
          "FROM login_users WHERE user = ? ";

     String authsByUserQuery = 
         "SELECT username, authority FROM "+
          "authorities WHERE username = ?";

      var userDetailsManager = 
          new JdbcUserDetailsManager(dts);

     userDetailsManager
     .setUsersByUsernameQuery(usersByUsernameQuery);

     userDetailsManager
   .setAuthoritiesByUsernameQuery(authsByUserQuery);
      
      return userDetailsManager; */
     //return new JdbcUserDetailsManager(dts);


       // var userDetailsService = 
         //    new InMemoryUserDetailsManager();

       /* var user = User.withUsername("john")
           .password("123")
           .authorities("read")
           .build(); */
       //DummyUser user = new DummyUser();
       /* UserDetails u = 
           new User("bill", "123", "read");

       List<UserDetails> users = 
                            List.of(u); */

       //userDetailsService.createUser(user); 
       //return userDetailsService;
       //return new InMemoryUserDetailsService(users);

    //}

    @Bean
    public PasswordEncoder passwordEncoder(){
        
        PasswordEncoder p = 
             new StandardPasswordEncoder();
        System.out.println("ENCRYPT STANDARD: ");
        System.out.println(p.encode("123"));
        return p; 
        //String result = encoder.encode("myPassword");

        //return new Sha512PasswordEncoder();
        //return new PlainTextPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

}
