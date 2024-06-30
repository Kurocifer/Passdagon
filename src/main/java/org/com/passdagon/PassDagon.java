package org.com.passdagon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.com.passdagon.model.User;
import org.com.passdagon.persistence.Persistence;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PassDagon extends Application {

  @Override
  public void start(Stage stage) throws IOException {

    Path homDir = Paths.get(System.getProperty("user.home"));

    Path filePath = homDir.resolve(".passdagon").resolve("passdagon.vx");

    boolean exists = Files.exists(filePath);
    if(exists) {
      loadMainWindow(stage);
    } else {
      System.out.println("Could not find data file on your computer.!");
      System.out.println("It may have been moved to a different location in this case locate the file and move it\n" +
              "to the your home dir in a folder named '.passdagon'. file name should be passdagon.vx\n" +
              "\nThis might also be your first time opening this software then you'll have to create a password.\n" +
              "This might be the only password you'll have to remember :)");

      FXMLLoader fxmlLoader = new FXMLLoader(PassDagon.class.getResource("sign-in-view.fxml"));
      Scene scene = new Scene(fxmlLoader.load());
      stage.setScene(scene);
      stage.show();

      stage.setOnCloseRequest(event ->  {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("You're about to quit");
        alert.setContentText("Any data saved will be used for authentication to your data");
        if(alert.showAndWait().get() == ButtonType.OK && User.getInstance().getPassword() != null) {
          try {
            event.consume();
            stage.close();
            loadMainWindow(new Stage());
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        } else {
          event.consume();
          stage.close();
        }
      });

    }
  }

  public static void main(String[] args) throws IOException {
      launch();
  }

  public void loadMainWindow(Stage stage) throws IOException {
    System.out.println("Calling load method");
    Persistence.load();
    FXMLLoader fxmlLoader = new FXMLLoader(PassDagon.class.getResource("main-scene.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.show();

    stage.setOnCloseRequest(event -> {
      Persistence.save();
    });
  }
}