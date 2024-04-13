package org.com.passdagon.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.com.passdagon.exceptions.AccountNotPresentException;
import org.com.passdagon.model.Account;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

public class GeneralUtilities {

  private static ObservableList<Account> accounts = FXCollections.observableArrayList();


  public static Account filterAccountByAccountNameAndUsername(
          URL accountName, String username, ObservableList<Account> accounts) throws AccountNotPresentException {

    Optional<Account> filteredAccount = accounts.stream()
            .filter(account -> account.getAccountName().equals(accountName)
                    && account.getUsername().equals(username))
            //.filter(account -> account.getUsername().equals(username))
            .findFirst();

    if(filteredAccount.isPresent()) {
      return filteredAccount.get();
    } else {
      throw new AccountNotPresentException();
    }
  }

  public static URL stringToURL(String s) throws URISyntaxException, MalformedURLException {
    URI uri = new URI(s);
    return uri.toURL();
  }
}
