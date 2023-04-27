package com.security.util;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.encrypt.Encryptors;

public class ExEncrypt {

  public String encryptText(String strToEncrypt){
  
   System.out.println("##### Encrypt.TextEncrypt ######");
   String salt = KeyGenerators.string().
                                generateKey();
   String pass =  "secret";
   String valueToEncrypt = "HELLO";
   TextEncryptor e = Encryptors.text(pass,salt);
   String encrypted = e.encrypt(valueToEncrypt);
   System.out.println(">>> Encrypted: "+ encrypted);
   String decrypted = e.decrypt(encrypted);
   System.out.println(">>> Decrypted: "+decrypted);
   return encrypted;

  }

  public String queryableEncrypt(String msg){

    System.out.println("##### Encrypt.TextEncryp.QUERYABLE ######");
    String salt = KeyGenerators.string().generateKey();
    String pass = "secret";
    String valToEncrypt = "HELLO";
    TextEncryptor e = Encryptors.queryableText(pass, salt);
    String encrypted1 = e.encrypt(valToEncrypt);
    System.out.println("》Enc1: "+encrypted1);
    String encrypted2 = e.encrypt(valToEncrypt);
    System.out.println("》Enc2: "+encrypted2);

    System.out.println("Dec 1: "+e.decrypt(encrypted1));

    return encrypted1;

  }

  public static void main(String[] args){

    ExEncrypt ex = new ExEncrypt();
    ex.encryptText("Hello");
    ex.queryableEncrypt("Hello");
  }

}
