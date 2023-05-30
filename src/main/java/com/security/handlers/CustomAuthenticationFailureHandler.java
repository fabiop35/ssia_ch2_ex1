package com.security.handlers;


import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Component
public class 
   CustomAuthenticationFailureHandler 
       implements 
         AuthenticationFailureHandler {


 @Override
 public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException aue){
 
    response.setHeader("failed", 
            LocalDateTime.now()
             .toString() );

 }


}
