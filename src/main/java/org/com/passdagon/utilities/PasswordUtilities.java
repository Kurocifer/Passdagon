package org.com.passdagon.utilities;

import org.com.passdagon.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class PasswordUtilities {

  public static String password = null;

  public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hash = factory.generateSecret(spec).getEncoded();

    // Combine salt and hash for storage (Base64 encoding for example)
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String encodedHash = Base64.getEncoder().encodeToString(hash);
    return encodedSalt + ":" + encodedHash;
  }

  public static boolean validatePassword(String loginPassword) throws NoSuchAlgorithmException,
          InvalidKeySpecException {

    if(loginPassword == null) {
      return false;
    }
    // Extract salt and hash from storedHash (split using ":")
    String[] parts = User.getInstance().getPassword().split(":");
    byte[] salt = Base64.getDecoder().decode(parts[0]);
    byte[] storedHashBytes = Base64.getDecoder().decode(parts[1]);

    // Perform hashing on entered password using extracted salt
    KeySpec spec = new PBEKeySpec(loginPassword.toCharArray(), salt, 65536, 128);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] generatedHash = factory.generateSecret(spec).getEncoded();

    // Compare generated hash with stored hash
    password = hashPassword(loginPassword);
    return Arrays.equals(generatedHash, storedHashBytes);
  }
}

//  public static boolean validatePassword(String loginPassword) {
//    if(User.getInstance().getPassword().equals(loginPassword)) {
//      password = loginPassword;
//      return true;
//    }
//    return false;
//  }
//}
