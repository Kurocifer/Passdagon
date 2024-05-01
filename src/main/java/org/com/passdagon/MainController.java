package org.com.passdagon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.com.passdagon.exceptions.AccountNotPresentException;
import org.com.passdagon.exceptions.PasswordMismatchException;
import org.com.passdagon.model.Account;
import org.com.passdagon.model.User;
import org.com.passdagon.utilities.GeneralUtilities;
import org.com.passdagon.utilities.PasswordUtilities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {
  @FXML
  private TableColumn<Account, String> accountColumn;

  @FXML
  private Button addButton;

  @FXML
  private Button refreshButton;

  @FXML
  private TableColumn<Account, String> dateModifiedColumn;

  @FXML
  private AnchorPane mainWindow;

  @FXML
  private TableView<Account> tableView;

  @FXML
  private TableColumn<Account, String> usernameColumn;

  SceneSwitchingController sceneSwitchingController = new SceneSwitchingController();
  public static ObservableList<Account> accounts = FXCollections.observableArrayList();
  private int myIndex;
  private String  id;

  @FXML
  void addAccount(ActionEvent event) throws IOException {
    sceneSwitchingController.switchToMainWindow(event);
    table();
  }

  @FXML
  public  void refresh(ActionEvent event) {
    System.out.println("in refresh: " + accounts);

    table();

  }

  private void table() {

    System.out.println("in table" + accounts);
    tableView.setItems(accounts);
    accountColumn.setCellValueFactory(f -> f.getValue().accountNameProperty());
    usernameColumn.setCellValueFactory(f -> f.getValue().usernameProperty());
    dateModifiedColumn.setCellValueFactory(f -> f.getValue().dateModifiedProperty());

    tableView.refresh();

    tableView.setRowFactory( tv -> {
      TableRow<Account> myRow = new TableRow<>();
      myRow.setOnMouseClicked (event -> {
        if (event.getClickCount() == 2 && (!myRow.isEmpty())) {
          myIndex = tableView.getSelectionModel().getSelectedIndex();



          id = String.valueOf(tableView.getItems().get(myIndex).getUsername());
          String ac = String.valueOf(tableView.getItems().get(myIndex).getAccountName());
          String  an = String.valueOf(tableView.getItems().get(myIndex).getDateModified());

          try {

            URL url = GeneralUtilities.stringToURL(ac);

            System.out.println("url: " + url);
            System.out.println("here");

            String pass = GeneralUtilities.filterAccountByAccountNameAndUsername(url, id, accounts)
                            .getPassword();
            Account account = new Account(url, id, pass, LocalDate.parse(an));

            System.out.println("down");
            User.getInstance().setNewAccount(account);

            loadRequestPasswordWindow();
          } catch (URISyntaxException | IOException | AccountNotPresentException | PasswordMismatchException ex) {
            ex.printStackTrace();
          }
          System.out.println("id: " + id);
        }
      });

      return myRow;
    });
  }

  private void loadDetailsViewWindow() throws IOException, PasswordMismatchException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("account-details-view.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

private void loadRequestPasswordWindow() throws IOException, PasswordMismatchException {
  FXMLLoader loader = new FXMLLoader(getClass().getResource("request-password-view.fxml"));
  Parent root = loader.load();

  Scene scene = new Scene(root);
  Stage stage = new Stage();
  stage.setScene(scene);
  stage.show();


  stage.setOnCloseRequest(event -> {
    System.out.println("returned");
    System.out.println("hashed password: " + PasswordUtilities.password);
    try {
      if (PasswordUtilities.validatePassword(PasswordUtilities.password)) {
        System.out.println("in if");
        PasswordUtilities.password = null;
        try {
          loadDetailsViewWindow();
        } catch (IOException | PasswordMismatchException e) {
          throw new RuntimeException(e);
        }
      } else {
        PasswordUtilities.password = null;
      }
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  });
}

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    table();
  }
}