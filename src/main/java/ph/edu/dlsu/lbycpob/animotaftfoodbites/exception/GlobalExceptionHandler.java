package ph.edu.dlsu.lbycpob.animotaftfoodbites.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class GlobalExceptionHandler {

    public static void handleException(Exception ex) {
        Alert alert = new Alert(
                Alert.AlertType.ERROR,
                ex.getMessage(),
                ButtonType.OK
        );
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.showAndWait();
    }

}
