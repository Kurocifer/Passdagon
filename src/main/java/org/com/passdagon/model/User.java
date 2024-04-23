package org.com.passdagon.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

  private static User userInstance;
  private String name;
  private String password = null;
  private ObservableList<Account> accounts = FXCollections.observableArrayList();
  private Account account;

  private User() {}
  public static User getInstance() {
    if(userInstance == null)
      userInstance = new User();

    return userInstance;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ObservableList<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(ObservableList<Account> accounts) {
    this.accounts = accounts;
  }

  public void addAccount(Account account) {
    accounts.add(account);
  }

  public void setNewAccount(Account newAccount)  {
    this.account = newAccount;
  }

  public void deleteAccount(int accountIndex) {
    accounts.remove(accountIndex);
  }


  public Account getNewAccount() {
    return account;
  }

  public void setStringPropertiesAfterReload() {
    for (Account account : accounts) {
      account.setAllStringPropertiesAfterReload();
    }
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", accounts=" + accounts +
            '}';
  }
}
