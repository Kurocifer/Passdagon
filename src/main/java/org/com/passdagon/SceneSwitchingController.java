package org.com.passdagon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitchingController {

  private Stage stage;
  private Scene scene;
  private Parent root;

  public void switchToMainWindow(ActionEvent event) throws IOException {
    System.out.println("in here yo");
    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-account-view.fxml")));
    scene = new Scene(root);

    stage = new Stage();
    stage.setScene(scene);
    stage.initStyle(StageStyle.UTILITY);
    stage.show();

  }

}
