package com.security.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha512PasswordEncoder
                implements PasswordEncoder{
    
    @Override
    public String encode(CharSequence rawPassword){
      System.out.println(">>>SHA PLAIN: "+rawPassword);  
      return hashWithSHA512(rawPassword.toString());
    }
    
    @Override
    public boolean matches(CharSequence rawPassword,
            String encodePassword){
        String hashedPassword = 
                    encode(rawPassword);
    return encodePassword.equals(hashedPassword);

    }
    

    public String hashWithSHA512(String input){

      StringBuilder result = new StringBuilder();
    try{
        MessageDigest md =
            MessageDigest.getInstance("SHA-512");
     byte[] digested = md.digest(input.getBytes());
     for (int i=0; i<digested.length; i++){
        result.append(Integer.toHexString(0xFF & digested[i]));
      }
    }catch(NoSuchAlgorithmException e){
      throw new RuntimeException("Bad algorithm");
    }  
    System.out.println(">>>SHA Encode: "+result.toString());
    return result.toString();

   }
}
