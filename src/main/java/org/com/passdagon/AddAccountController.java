package org.com.passdagon;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.com.passdagon.model.Account;
import org.com.passdagon.model.User;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AddAccountController implements Initializable {
  @FXML
  private TextField accountNameField;

  @FXML
  private AnchorPane addAccountScene;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button saveButton;

  @FXML
  private TextField usernameField;

  @FXML
  private Label shownPassword;

  @FXML
  private ToggleButton showPasswordToggleButton;


  @FXML
  void passwordFieldKeyTyped(KeyEvent event) {
    shownPassword.textProperty().bind(Bindings.concat(passwordField.getText()));
  }

  @FXML
  void togglePasswordVisibility(ActionEvent event) {
    if(showPasswordToggleButton.isSelected()) {
      shownPassword.setVisible(true);
      shownPassword.textProperty().bind(Bindings.concat(passwordField.getText()));
      showPasswordToggleButton.setText("HIde");
    } else {
      shownPassword.setVisible(false);
      showPasswordToggleButton.setText("Show");
    }
  }

  private Stage stage;

  private Account account;

//  @FXML
//  void saveAccount(ActionEvent event) {
//    account = new Account();
//
//    String stringUrl = accountNameField.getText();
//
//    try {
//      if(!stringUrl.contains("https://"))
//        stringUrl = "https://" + stringUrl;
//
//      URI uri = new URI(stringUrl);
//      URL url = uri.toURL();
//      account.setAccountName(url);
//    } catch (URISyntaxException | MalformedURLException ex) {
//      System.out.println("Invalid URL: Account Name must be a valid URL");
//      ex.printStackTrace();
//    }
//
//    account.setUsername(usernameField.getText());
//    account.setAccountPassword(passwordField.getText());
//    account.setDateModified(new Date());
//
//    User.getInstance().setNewAccount(account);
//
//    stage = (Stage) addAccountScene.getScene().getWindow();
//    System.out.println("close");
//    stage.close();
//
//    System.out.println(account);
//  }

//  public void middleMan() {
////    System.out.println("in middleman: " + account);
//    User.getInstance().addAccount(account);
//  }

  @FXML
  void saveAccount(ActionEvent event) {

    String password = passwordField.getText();
    String username = usernameField.getText();
    URL urlAccountName;
    String stringAccountName = accountNameField.getText();

    try {
      if(!stringAccountName.contains("https://"))
        stringAccountName = "https://" + stringAccountName;

      URI uri = new URI(stringAccountName);
      urlAccountName = uri.toURL();

      account = new Account(urlAccountName, username, password, LocalDate.now());

      User.getInstance().addAccount(account);
    } catch (URISyntaxException | MalformedURLException ex) {
      System.out.println("Invalid URL: Account Name must be a valid URL");
      ex.printStackTrace();
    }

    stage = (Stage) addAccountScene.getScene().getWindow();
    System.out.println("close");
    stage.close();

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    shownPassword.setVisible(false);
  }
}
