package org.com.passdagon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainController {
  @FXML
  private TableColumn<?, ?> accountColumn;

  @FXML
  private Button addButton;

  @FXML
  private TableColumn<?, ?> dateModifiedColumn;

  @FXML
  private AnchorPane mainWindow;

  @FXML
  private TableView<?> tableView;

  @FXML
  private TableColumn<?, ?> usernameColumn;

  SceneSwitchingController sceneSwitchingController = new SceneSwitchingController();

  @FXML
  void addAccount(ActionEvent event) throws IOException {
    sceneSwitchingController.switchToMainWindow(event);
  }


}