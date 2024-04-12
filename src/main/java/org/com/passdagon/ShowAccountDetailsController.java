package org.com.passdagon;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import org.com.passdagon.model.Account;
import org.com.passdagon.model.User;

import java.io.InterruptedIOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowAccountDetailsController implements Initializable {

  @FXML
  private TextField accountNameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private ToggleButton showPasswordToggleButton;

  @FXML
  private TextField usernameField;
  @FXML
  private Label shownPassword;
  int n = 0;

  @FXML
  void passwordFieldKeyTyped(KeyEvent event) {
    shownPassword.textProperty().bind(Bindings.concat(passwordField.getText()));
  }

  @FXML
  void togglePasswordVisibility(ActionEvent event) {
    System.out.println("clicked show");

    if(showPasswordToggleButton.isSelected()) {
      shownPassword.setVisible(true);
      shownPassword.textProperty().bind(Bindings.concat(passwordField.getText()));
      showPasswordToggleButton.setText("Hide");
    } else {
      shownPassword.setVisible(false);
      //shownPassword.textProperty().bind(Bindings.concat(passwordField.getText()));
      showPasswordToggleButton.setText("Show");
    }

  }

  public void setAll() {
    System.out.println("in setall");
    Account account = User.getInstance().getNewAccount();
    accountNameField.setText(account.getAccountName().toString());
    usernameField.setText(account.getUsername());
    passwordField.setText(account.getPassword());
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    shownPassword.setVisible(false);
  }
}