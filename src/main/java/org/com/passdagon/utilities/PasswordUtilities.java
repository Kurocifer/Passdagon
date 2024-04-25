package org.com.passdagon.utilities;

import org.com.passdagon.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordUtilities {

  public static String password = null;
  public static boolean validatePassword(String loginPassword) {
    if(User.getInstance().getPassword().equals(loginPassword)) {
      password = loginPassword;
      return true;
    }
    return false;
  }

  public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

    byte[] hash = factory.generateSecret(spec).getEncoded();

    return Arrays.toString(hash);
  }
}
