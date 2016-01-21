package com.itcherry;
import javax.crypto.spec.*;
import java.security.*;
import java.util.Arrays;

import javax.crypto.*;
  
public class Main1
{
   public static void main(String []args) throws Exception {
      String toEncrypt = "The shorter you live, the longer you're dead dsgdfg sdfg sdfg sdfg sdfg !";
      System.out.print(toEncrypt + "\n Bytes : ");
      System.out.println(Arrays.toString(toEncrypt.getBytes()));
      
      System.out.println("Encrypting...");
      byte[] encrypted = encrypt(toEncrypt, "password");
      for(byte i : encrypted){
    	  System.out.print((char)i);
      }
  
      System.out.println("\nDecrypting...");
      String decrypted = decrypt(encrypted, "password");
      System.out.println(Arrays.toString(decrypted.getBytes()));
     
      System.out.println("Decrypted text: " + decrypted);
   }
  
   public static byte[] encrypt(String toEncrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance("RC2");
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // create an instance of cipher
      Cipher cipher = Cipher.getInstance("RC2");
  
      // initialize the cipher with the key
      cipher.init(Cipher.ENCRYPT_MODE, sk);
  
      // enctypt!
      byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
  
      return encrypted;
   }
  
   public static String decrypt(byte[] toDecrypt, String key) throws Exception {
      // create a binary key from the argument key (seed)
      SecureRandom sr = new SecureRandom(key.getBytes());
      KeyGenerator kg = KeyGenerator.getInstance("RC2");
      kg.init(sr);
      SecretKey sk = kg.generateKey();
  
      // do the decryption with that key
      Cipher cipher = Cipher.getInstance("RC2");
      cipher.init(Cipher.DECRYPT_MODE, sk);
      byte[] decrypted = cipher.doFinal(toDecrypt);
  
      return new String(decrypted);
   }
}