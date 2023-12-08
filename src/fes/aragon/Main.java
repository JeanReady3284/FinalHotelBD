package fes.aragon;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/Inicio.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/fes/aragon/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
		//	primaryStage.setX(Screen.getPrimary().getVisualBounds().getMaxX());
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void setRoot(String fxml) {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
