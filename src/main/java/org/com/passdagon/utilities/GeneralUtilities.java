package org.com.passdagon.utilities;

import javafx.collections.ObservableList;
import org.com.passdagon.exceptions.AccountNotPresentException;
import org.com.passdagon.model.Account;

import java.net.URL;
import java.util.Optional;

public class GeneralUtilities {

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
}
