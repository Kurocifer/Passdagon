package org.com.passdagon;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.com.passdagon.model.User;
import org.com.passdagon.utilities.LoginUtilities;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class PassDagon extends Application {

  @Override
  public void start(Stage stage) throws IOException {

    Path homDir = Paths.get(System.getProperty("user.home"));

    //SceneSwitchingController sceneSwitchingController = new SceneSwitchingController();

    //File file = new File(homDir, ".passdagon/passdagon.vx");
    Path filePath = homDir.resolve(".passdagon").resolve("passdagon.vx");

    boolean exists = Files.exists(filePath);
    if(exists) {
      loadMainWindow(stage);
    } else {
      System.out.println("Could not find data file on your computer.!");
      System.out.println("It may have been moved to a different location in this case locate the file and move it " +
              "to the your home dir in a folder named .passdagon. file name should be passdagon.vx\n" +
              " This might also be your first time opening this software then you'll have to create a password.\n" +
              " This might be the only password you'll have to remember :)");

      FXMLLoader fxmlLoader = new FXMLLoader(PassDagon.class.getResource("sign-in-view.fxml"));
      Scene scene = new Scene(fxmlLoader.load());
      stage.setScene(scene);
      stage.show();

      stage.setOnCloseRequest(event ->  {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("You're about to quit");
        alert.setContentText("Any data saved will be used for authentication to your data");
        if(alert.showAndWait().get() == ButtonType.OK && !(User.getInstance().getPassword().isEmpty())) {
          try {
            event.consume();
            stage.close();
            loadMainWindow(new Stage());
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        } else {
          event.consume();
        }
      });

    }
  }

  public static void main(String[] args) throws IOException {
//    Path homDir = Paths.get(System.getProperty("user.home"));
//
//    SceneSwitchingController sceneSwitchingController = new SceneSwitchingController();
//
//    //File file = new File(homDir, ".passdagon/passdagon.vx");
//    Path filePath = homDir.resolve(".passdagon").resolve("passdagon.vx");
//
//    boolean exists = Files.exists(filePath);

      launch();
//     else {
//      System.out.println("Could not find data file on your computer.!");
//      System.out.println("It may have been moved to a different location in this case locate the file and move it " +
//              "to the your home dir in a folder named .passdagon. file name should be passdagon.vx\n" +
//              " This might also be your first time opening this software then you'll have to create a password.\n" +
//              " This might be the only password you'll have to remember :)");
//
//      sceneSwitchingController.switchToSignInWindow(new ActionEvent());
//    }
  }

  public void loadMainWindow(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(PassDagon.class.getResource("main-scene.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.show();
  }
}