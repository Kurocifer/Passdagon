package org.com.passdagon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.com.passdagon.exceptions.AccountNotPresentException;
import org.com.passdagon.model.Account;
import org.com.passdagon.model.User;
import org.com.passdagon.utilities.GeneralUtilities;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

public class MainController {
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

    //List<Account> fullaccounts = User.getInstance().getAccounts();
//    accounts = User.getInstance().getAccounts();
//
//    Account newAccount = User.getInstance().getNewAccount();

//    System.out.println(newAccount);
//   // accounts.add(newAccount);
//    System.out.println(accounts);
    table();

  }

  private void table() {

//    accounts = FXCollections.observableArrayList();
    //System.out.println("in refresh: " + accounts);

    //List<Account> fullaccounts = User.getInstance().getAccounts();
//    accounts = null;
//    accounts = User.getInstance().getAccounts();

//    Account newAccount = User.getInstance().getNewAccount();
//    System.out.println("new account in table: " + newAccount);

//    System.out.println(newAccount);
    //accounts.add(newAccount);
    System.out.println("in table" + accounts);
    //Collections.reverse(accounts);
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
            loadDetailsViewWindow();
          } catch (URISyntaxException | IOException | AccountNotPresentException ex) {
            //System.out.println("Invalid URL: Account must be a valid URL");
            ex.printStackTrace();
          }
          System.out.println("id: " + id);
        }
      });

      return myRow;
    });
  }

  private void loadDetailsViewWindow() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("account-details-view.fxml"));
    Parent root = loader.load();

    //ShowAccountDetailsController showAccountDetailsController = loader.getController();
    //showAccountDetailsController.setAll();

    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

//  private Account filterAccountByAccountNameAndUsername(
//          URL accountName, String username,
//          ObservableList<Account> accounts) throws AccountNotPresentException {
//
//    Optional<Account> filteredAccount = accounts.stream()
//            .filter(account -> account.getAccountName().equals(accountName)
//            && account.getUsername().equals(username))
//            //.filter(account -> account.getUsername().equals(username))
//            .findFirst();
//
//    if(filteredAccount.isPresent()) {
//      return filteredAccount.get();
//    } else {
//      throw new AccountNotPresentException();
//    }
//  }
}