package org.com.passdagon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import org.com.passdagon.model.Account;
import org.com.passdagon.model.User;

public class ShowAccountDetailsController {

  @FXML
  private TextField accountNameField;

  @FXML
  private TextField passwordField;

  @FXML
  private ToggleButton showPasswordToggleButton;

  @FXML
  private TextField usernameField;

  @FXML
  void togglePasswordVisibility(ActionEvent event) {
  }

  public void setAll() {
    System.out.println("in setall");
    Account account = User.getInstance().getNewAccount();
    accountNameField.setText(account.getAccountName().toString());
    usernameField.setText(account.getUsername());
    passwordField.setText(account.getPassword());
  }

}