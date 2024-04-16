package org.com.passdagon.utilities;

import org.com.passdagon.model.User;

public class LoginUtilities {

  public static String password = null;
  public static boolean validatePassword(String loginPassword) {
    if(User.getInstance().getPassword().equals(loginPassword)) {
      password = loginPassword;
      return true;
    }
    return false;
  }
}
