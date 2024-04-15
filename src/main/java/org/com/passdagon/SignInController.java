package org.com.passdagon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

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
  void cancel(ActionEvent event) {

  }

  @FXML
  void confirmPasswordFieldKeyTyped(KeyEvent event) {

  }

  @FXML
  void mainPasswordFieldKeyTyped(KeyEvent event) {

  }

  @FXML
  void save(ActionEvent event) {

  }

  @FXML
  void toggleConfirmPasswordVisibility(ActionEvent event) {

  }

  @FXML
  void toggleMainPasswordVisibility(ActionEvent event) {

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    textArea.setText(" Could not find data file on your computer." +
            "It may have been moved to a different location in this case locate the file and move it " +
            "to the your home dir in a folder named .passdagon. file name should be passdagon.vx\n" +
            " This might also be your first time opening this software then you'll have to create a password.\n" +
            " This might be the only password you'll have to remember :)"
    );

    textArea.setEditable(false);

    shownConfirmedPassword.setVisible(false);
    shownPassword.setVisible(false);
  }
}
