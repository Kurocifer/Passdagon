package org.com.passdagon.persistence;

import org.com.passdagon.MainController;
import org.com.passdagon.model.Account;
import org.com.passdagon.model.User;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Objects;


public class Persistence {

  private static String getAndCreateFilePath() {
    Path homDir = Paths.get(System.getProperty("user.home"));
    Path filePath = homDir.resolve(".passdagon").resolve("passdagon.vx");

    if(!fileExist()) {
      try {
        Files.createDirectories(filePath.getParent());
        Files.createFile(filePath);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return filePath.toString();
  }

  public static void save() {
    try(ObjectOutputStream os =
            new ObjectOutputStream(new FileOutputStream(getAndCreateFilePath()))) {

      SaveUser saveUser = new SaveUser();
      ArrayList<Account> accounts = new ArrayList<>(User.getInstance().getAccounts());
      System.out.println("Accounts to be saved: " + accounts);

      saveUser.setPassword(User.getInstance().getPassword());
      saveUser.setAccounts(accounts);

      System.out.println("User details to be saved: " + saveUser);
      os.writeObject(saveUser);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void load() {

    if(fileExist()) {
      System.out.println("File exist");
      try(ObjectInputStream is =
              new ObjectInputStream(new FileInputStream(getAndCreateFilePath()))) {
        System.out.println("in try catch");
        SaveUser loadedUser = (SaveUser) is.readObject();

        System.out.println("User loaded: " + loadedUser);
        loadedUser.setStringPropertiesAfterReload();

        MainController.accounts.addAll(loadedUser.getAccounts());
        System.out.println("main controller accounts: " + MainController.accounts);
        User.getInstance().setPassword(loadedUser.getPassword());
        User.getInstance().setAccounts(MainController.accounts);

      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  private static boolean fileExist() {
    Path homDir = Paths.get(System.getProperty("user.home"));
    Path filePath = homDir.resolve(".passdagon").resolve("passdagon.vx");

    return Files.exists(filePath);
  }
}

class SaveUser implements Serializable {
  private String password = null;
  private ArrayList<Account> accounts;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ArrayList<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(ArrayList<Account> accounts) {
    this.accounts = accounts;
  }

  public void setStringPropertiesAfterReload() {
    System.out.println("Setting String Properties");
    System.out.println(accounts);
    for (Account account : accounts) {
      account.setAllStringPropertiesAfterReload();
      System.out.println("account looping: " + account);
    }
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SaveUser saveUser = (SaveUser) o;
    return Objects.equals(password, saveUser.password) && Objects.equals(accounts, saveUser.accounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, accounts);
  }

  @Override
  public String toString() {
    return "SaveUser{" +
            "password='" + password + '\'' +
            ", accounts=" + accounts +
            '}';
  }
}
