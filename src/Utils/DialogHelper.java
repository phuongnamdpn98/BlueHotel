package Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogHelper {
    public static void warningAlert(String note, String hint) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("CẢNH BÁO");
        alert.setHeaderText(note);
        alert.setContentText(hint);
        alert.showAndWait();
    };

    public static void errorAlert(String note, String hint) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("LỖI");
        alert.setHeaderText(note);
        alert.setContentText(hint);
        alert.showAndWait();
    };

    public static void confrimAlert(String note, String hint) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("XÁC NHẬN");
        alert.setHeaderText(note);
        alert.setContentText(hint);
        alert.showAndWait();
    };

    public static void informAlert(String note, String hint) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("THÔNG BÁO");
        alert.setHeaderText(note);
        alert.setContentText(hint);
        alert.showAndWait();
    };

    public static void main(String[] args) {

    }
}
