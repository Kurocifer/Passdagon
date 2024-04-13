package org.com.passdagon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Account {

  private URL accountName;
  private String username;
  private String password;
  private LocalDate dateModified;

  private StringProperty accountNameProperty;
  private StringProperty usernameProperty;
  private StringProperty dateModifiedProperty;


  public Account(URL accountName, String username, String password, LocalDate dateModified) {
    this.accountName = accountName;
    this.username = username;
    this.password = password;
    this.dateModified = dateModified;

    accountNameProperty = new SimpleStringProperty(accountName.toString());
    usernameProperty = new SimpleStringProperty(username);
    dateModifiedProperty = new SimpleStringProperty(dateModified.toString());

  }

  public URL getAccountName() {
    return accountName;
  }

  public void setAccountName(URL accountName) {
    this.accountName = accountName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
public StringProperty usernamePropertyProperty() {
    return usernameProperty;
  }
  public LocalDate getDateModified() {
    return dateModified;
  }

  public void setDateModified(LocalDate dateModified) {
    this.dateModified = dateModified;
  }


  public StringProperty accountNameProperty() {
    return accountNameProperty;
  }

  public StringProperty usernameProperty() {
    return usernameProperty;
  }

  public StringProperty dateModifiedProperty() {
    return dateModifiedProperty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return Objects.equals(accountName, account.accountName) && Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(dateModified, account.dateModified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountName, username, password, dateModified);
  }

  @Override
  public String toString() {
    return "Account{" +
            "accountName=" + accountName +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", dateModified=" + dateModified +
            '}';
  }
}
