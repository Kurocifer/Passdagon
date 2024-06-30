package org.com.passdagon;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;
import org.com.passdagon.exceptions.AccountNotPresentException;
import org.com.passdagon.model.Account;
import org.com.passdagon.model.User;
import org.com.passdagon.utilities.GeneralUtilities;

import java.io.InterruptedIOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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
  private TextField descriptionField;

  @FXML
  private Label shownPassword;

  @FXML
  private TextArea dateModifiedTextArea;

  @FXML
  private ToggleButton setEditButton;

  @FXML
  private Button saveButton;

  private Account account;

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

//    System.out.println("in initailisavle - URL, resourceBundle: " + url +  resourceBundle);


    account = User.getInstance().getNewAccount();
    accountNameField.setText(account.getAccountName().toString());
    usernameField.setText(account.getUsername());
    passwordField.setText(account.getPassword());
    descriptionField.setText(account.getDescription());
    dateModifiedTextArea.setText("Date modified: " + account.getDateModified());
    shownPassword.setVisible(false);

    saveButton.setVisible(false);
  }

  private void makeFieldsEditable(boolean b) {
    passwordField.setEditable(b);
    accountNameField.setEditable(b);
    usernameField.setEditable(b);
    dateModifiedTextArea.setEditable(b);
    descriptionField.setEditable(b);
  }

  @FXML
  void edit(ActionEvent event) throws MalformedURLException, URISyntaxException, AccountNotPresentException {
    System.out.println("Edit button clicked");
    List<Account> accounts = User.getInstance().getAccounts();
    System.out.println("Accounts: " + MainController.accounts);
    System.out.println("account: " + account);

    URL editedAccountName = GeneralUtilities.stringToURL(accountNameField.getText());
    String editedDescription = descriptionField.getText();
    String EditedUsername = usernameField.getText();
    String editedPassword = passwordField.getText();

    Account editedAccount = new Account(
            editedAccountName, EditedUsername, EditedUsername, editedDescription, LocalDate.now());


    int indexOfAccountToEdit = MainController.accounts.indexOf(account);
    System.out.println(indexOfAccountToEdit);

    if (indexOfAccountToEdit != -1) {
//      account = accounts.get(indexOfAccountToEdit);

//      account.setAccountName(GeneralUtilities.stringToURL(accountNameField.getText()));
//      account.setUsername(usernameField.getText());
//      account.setPassword(passwordField.getText());
//      account.setDateModified(LocalDate.now());

      System.out.println("edited account: " + account);

//      System.out.println("User instance: " + User.getInstance().getAccounts());
//      User.getInstance().deleteAccount(indexOfAccountToEdit);

      User.getInstance().deleteAccount(indexOfAccountToEdit);
      User.getInstance().setNewAccount(editedAccount);
      User.getInstance().addAccount(editedAccount);

      MainController.accounts.remove(indexOfAccountToEdit);
      MainController.accounts.add(editedAccount);
      System.out.println("new account list: " + accounts);

      System.out.println("Edited");

      saveButton.setVisible(false);
    }
    else {
      System.out.println("Not found");
      throw new AccountNotPresentException();
    }
  }
}