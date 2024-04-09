package org.com.passdagon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class AddAccountController {
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

  Stage stage;

  @FXML
  void saveAccount(ActionEvent event) {
    stage = (Stage) addAccountScene.getScene().getWindow();
    System.out.println("close");
    stage.close();
  }

}
