package org.com.passdagon.model;

import java.net.URL;
import java.util.Date;

public class Account {

  private URL accountName;
  private String username;
  private String accountPassword;
  private Date dateModified;

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

  public String getAccountPassword() {
    return accountPassword;
  }

  public void setAccountPassword(String accountPassword) {
    this.accountPassword = accountPassword;
  }

  public Date getDateModified() {
    return dateModified;
  }

  public void setDateModified(Date dateModified) {
    this.dateModified = dateModified;
  }

  @Override
  public String toString() {
    return "Account{" +
            "accountName=" + accountName +
            ", username='" + username + '\'' +
            ", password='" + accountPassword + '\'' +
            ", dateModified=" + dateModified +
            '}';
  }
}
