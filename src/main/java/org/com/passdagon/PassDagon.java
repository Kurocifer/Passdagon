package org.com.passdagon;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class PassDagon extends Application {

  @Override
  public void start(Stage stage) throws IOException {

    Path homDir = Paths.get(System.getProperty("user.home"));

    SceneSwitchingController sceneSwitchingController = new SceneSwitchingController();

    //File file = new File(homDir, ".passdagon/passdagon.vx");
    Path filePath = homDir.resolve(".passdagon").resolve("passdagon.vx");

    boolean exists = Files.exists(filePath);
    if(exists) {
      FXMLLoader fxmlLoader = new FXMLLoader(PassDagon.class.getResource("main-scene.fxml"));
      Scene scene = new Scene(fxmlLoader.load());
      stage.setScene(scene);
      stage.show();
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
}