package org.com.passdagon;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;
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

  @FXML
  private TextArea dateModifiedTextArea;

  @FXML
  private ToggleButton setEditButton;

  @FXML
  private Button saveButton;

  private boolean edit = false;
  private URL initializableUrl;
  private ResourceBundle initializableResourceBundle;

  @FXML
  void editable(ActionEvent event) {
    if(setEditButton.isSelected()) {
      edit = true;
      setEditButton.setText("Cancel");
      saveButton.setVisible(true);
      makeFieldsEditable(edit);
    } else {
      edit = false;
      setEditButton.setText("Edit");
      initialize(initializableUrl, initializableResourceBundle);
    }
  }

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

//  public void setAll() {
//    System.out.println("in setall");
////    Account account = User.getInstance().getNewAccount();
////    accountNameField.setText(account.getAccountName().toString());
////    usernameField.setText(account.getUsername());
////    passwordField.setText(account.getPassword());
////    dateModifiedTextArea.setText("Date modified: " + account.getDateModified());
//  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // setting this two variables for later use
    initializableUrl = url;
    initializableResourceBundle = resourceBundle;


    makeFieldsEditable(false);
    usernameField.setEditable(false);
    dateModifiedTextArea.setEditable(false);
//    System.out.println("in initailisavle - URL, resourceBundle: " + url +  resourceBundle);


    Account account = User.getInstance().getNewAccount();
    accountNameField.setText(account.getAccountName().toString());
    usernameField.setText(account.getUsername());
    passwordField.setText(account.getPassword());
    dateModifiedTextArea.setText("Date modified: " + account.getDateModified());
    shownPassword.setVisible(false);

    saveButton.setVisible(false);
  }

  private void makeFieldsEditable(boolean b) {
    passwordField.setEditable(b);
    accountNameField.setEditable(b);
    usernameField.setEditable(b);
  }
}