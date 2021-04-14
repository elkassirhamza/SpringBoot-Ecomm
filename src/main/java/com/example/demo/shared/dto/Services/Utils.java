package com.example.demo.shared.dto.Services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;



@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
	
	public String generateUserId(int length)
    {

        // create StringBuffer size of AlphaNumericString
        StringBuilder returnValue = new StringBuilder(length);
  
        for (int i = 0; i < length ; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
           returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
  
        return new String(returnValue);
    }

}
