module org.com.passdagon {
  requires javafx.controls;
  requires javafx.fxml;


  opens org.com.passdagon to javafx.fxml;
  exports org.com.passdagon;
}