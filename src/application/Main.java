package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// Carregar o diretório do MainView
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			// Arrumar o problema ao cortar pela metade alguma classe
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			// Instacia o ScrollPane ao AnchorPane
			Scene mainScene = new Scene(scrollPane);
			// Mostrar a cena/tela e o título
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX application");
			// Mostrar tudo
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
