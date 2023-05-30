package com.security.handlers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Component
public class 
    CustomAuthenticationSuccessHandler 
     implements 
      AuthenticationSuccessHandler {

 @Override
 public void onAuthenticationSuccess(
         HttpServletRequest request, 
          HttpServletResponse response,
           Authentication authentication)
                      throws IOException{
    
    var authorities = 
          authentication.getAuthorities();

    Optional<? extends GrantedAuthority> auth =
     authorities.stream().
     filter( a -> 
      a.getAuthority().equals("read") ).findFirst();

    if(auth.isPresent()){
        response.sendRedirect("/home");
    }
    else {
      response.sendRedirect("/error");
    }

         
 }

}
