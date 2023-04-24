package com.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainTextPasswordEncoder
                implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword){
        System.out.println("-----< rawPassword: "
                +rawPassword.toString());
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword,
                            String encodePassword){
        System.out.println("----< encodePassword >----"+encodePassword);
        return rawPassword.equals(encodePassword);

     }
}
