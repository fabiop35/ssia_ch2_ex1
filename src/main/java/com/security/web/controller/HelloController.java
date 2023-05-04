package com.security.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Async;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello(Authentication a){
   
   /*SecurityContext context = 
          SecurityContextHolder.getContext();
   Authentication a = context.getAuthentication(); */
      return "Hello, "+a.getName()+"!!!";
  }    

  @Async
  @GetMapping("/bye")
  public String goodbye(){
    SecurityContext context = 
        SecurityContextHolder.getContext();
    String username = 
        context.getAuthentication().getName();
    System.out.println(">> bye: "+username);

    return "bye, "+ username +"!";

  }
  @GetMapping("/ciao")
  public String ciao() throws Exception{
    
    Callable<String> task = () -> {
     SecurityContext context =
      SecurityContextHolder.getContext();
      return context.getAuthentication().getName();
    };
    ExecutorService e =
        Executors.newCachedThreadPool();
    try {
     return "Ciao, "+e.submit(task).
         get() +"!";
    }finally{
      e.shutdown();
    }

  }

  @GetMapping("/hola")
  public String hola() 
           throws Exception{
   
   Callable<String> task = () -> {
    SecurityContext context = 
      SecurityContextHolder.getContext();
      return context.getAuthentication().getName();
   };
   ExecutorService e = Executors.
       newCachedThreadPool();
   e = new DelegatingSecurityContextExecutorService(e);
   try{
    return "Hola, " + 
        e.submit(task).get() + "!!";
   }finally {
    e.shutdown();
   }
  }


}
