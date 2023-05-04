package com.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider
          implements AuthenticationProvider {
   
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(
           Authentication authentication) {

  String username = authentication.getName();
  String passwd = authentication.getCredentials().toString();

  UserDetails usr = userDetailsService.loadUserByUsername(username);

   if (passwordEncoder.matches( passwd, 
                       usr.getPassword() ) ){
       System.out.println("IF");
    return new 
        UsernamePasswordAuthenticationToken(
                username, passwd,
                usr.getAuthorities() );
   }
   else {
       System.out.println("ELSE");
    throw new BadCredentialsException("Bce Something went wrong!");

   }
  }


  @Override
  public boolean supports(
            Class<?> authenticationType) {
     
   return authenticationType.equals(
    UsernamePasswordAuthenticationToken.class);
    }


}
