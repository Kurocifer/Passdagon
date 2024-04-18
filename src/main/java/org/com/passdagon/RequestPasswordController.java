package org.com.passdagon;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.com.passdagon.model.User;
import org.com.passdagon.utilities.LoginUtilities;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestPasswordController implements Initializable {
  @FXML
  public PasswordField passwordField;
  @FXML
  private Label shownPassword;

  @FXML
  private ToggleButton togglePasswordVisibilityButton;
  @FXML
  private AnchorPane requestPasswordAnchorPane;

  @FXML
  void passwordFieldKeyTyped(KeyEvent event) {
    shownPassword.textProperty().bind(Bindings.concat(passwordField.getText()));
  }

  @FXML
  void togglePasswordVisibility(ActionEvent event) {
    if(togglePasswordVisibilityButton.isSelected()) {
      shownPassword.setVisible(true);
      togglePasswordVisibilityButton.setText("Hide");
      shownPassword.textProperty().bind(Bindings.concat(passwordField.getText()));
    } else {
      shownPassword.setVisible(false);
      togglePasswordVisibilityButton.setText("show");
    }
  }

  @FXML
  void checkPassword(ActionEvent event) {

      if (LoginUtilities.validatePassword(passwordField.getText())) {
        System.out.println("true");
        LoginUtilities.password = passwordField.getText();
//        ((Stage) requestPasswordAnchorPane.getScene().getWindow()).close();
      } else {
        LoginUtilities.password = null;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong password");
        alert.setContentText("Failed to authenticate try again.");
        alert.showAndWait();
      }
  }


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    shownPassword.setVisible(false);
  }
}
