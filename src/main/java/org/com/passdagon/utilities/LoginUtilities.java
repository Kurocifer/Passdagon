package org.com.passdagon.utilities;

import org.com.passdagon.model.User;

public class LoginUtilities {

  public static boolean validatePassword(String password) {
    return User.getInstance().getPassword().equals(password);
  }
}
