package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception{
		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("LoginBH.fxml"));
        Parent root = mainLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("imgs/logo_blue_hotel.png"));
        stage.setTitle("Blue Hotel");
//        stage.setMinWidth(1200);
//        stage.setMinHeight(700);
        stage.show();
	}
}
