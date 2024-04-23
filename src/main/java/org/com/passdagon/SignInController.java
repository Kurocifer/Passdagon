package org.com.passdagon;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.com.passdagon.exceptions.PasswordMismatchException;
import org.com.passdagon.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

  @FXML
  private Button cancelButton;

  @FXML
  private ToggleButton confirmPasswordToggleButton;

  @FXML
  private PasswordField confirmedPasswordField;

  @FXML
  private PasswordField mainPasswordField;

  @FXML
  private ToggleButton mainPasswordToggleButton;

  @FXML
  private Button saveButton;

  @FXML
  private Label shownConfirmedPassword;

  @FXML
  private Label shownPassword;

  @FXML
  private TextArea textArea;
  @FXML
  private AnchorPane signInAnchorPane;

  @FXML
  void cancel(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText("Closing window");
    alert.setContentText("Any operation done during this session will be lost");
    if(alert.showAndWait().get() == ButtonType.OK)
      ((Stage) signInAnchorPane.getScene().getWindow()).close();
  }

  @FXML
  void confirmPasswordFieldKeyTyped(KeyEvent event) {
    shownConfirmedPassword.textProperty().bind(Bindings.concat(confirmedPasswordField.getText()));

  }

  @FXML
  void mainPasswordFieldKeyTyped(KeyEvent event) {
    shownPassword.textProperty().bind(Bindings.concat(mainPasswordField.getText()));
  }

  @FXML
  void save(ActionEvent event) throws PasswordMismatchException {
    if(mainPasswordField.getText().equals(confirmedPasswordField.getText())) {
      String password = mainPasswordField.getText();
      User.getInstance().setPassword(password);
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Wrong password");
      alert.setContentText("The two passwords provided must be the same");
      alert.showAndWait();
      throw new PasswordMismatchException();
    }
  }

  @FXML
  void toggleConfirmPasswordVisibility(ActionEvent event) {
    if(confirmPasswordToggleButton.isSelected()) {
      shownConfirmedPassword.setVisible(true);
      confirmPasswordToggleButton.setText("Hide");
      shownConfirmedPassword.textProperty().bind(Bindings.concat(confirmedPasswordField.getText()));
    } else {
      shownConfirmedPassword.setVisible(false);
      confirmPasswordToggleButton.setText("Hide");
    }
  }

  @FXML
  void toggleMainPasswordVisibility(ActionEvent event) {
    if(mainPasswordToggleButton.isSelected()) {
      shownPassword.setVisible(true);
      mainPasswordToggleButton.setText("Hide");
      shownPassword.textProperty().bind(Bindings.concat(mainPasswordField.getText()));
    } else {
      shownPassword.setVisible(false);
      mainPasswordToggleButton.setText("Show");
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    textArea.setText(" Could not find data file on your computer.\n" +
            "It may have been moved to a different location in this case locate the file and move it \n" +
            "to your home dir in a folder named .passdagon. file name should be passdagon.vx\n" +
            "\nThis might also be your first time opening this software then you'll have to create a password.\n" +
            "\nThis might be the only password you'll have to remember :)"
    );

    textArea.setEditable(false);

    shownConfirmedPassword.setVisible(false);
    shownPassword.setVisible(false);
  }
}
