package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	// Feito isso para o Scene ser acessado fora do Main.java
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Carregar o diretório do MainView
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			// Arrumar o problema ao cortar pela metade alguma classe
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			// Feito isso para o Scene ser acessado fora do Main.java
			mainScene = new Scene(scrollPane);
			// Mostrar a cena/tela e o título
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX application");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Feito isso para o Scene ser acessado fora do Main.java
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
