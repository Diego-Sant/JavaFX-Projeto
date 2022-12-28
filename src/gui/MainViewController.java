package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	// MenuItem são as classes no Menu dentro do MenuBar
	
	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		LoadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	// Resumidamente isso faz com que as views se conectem
	// Ao clicar em About no programa, ele acessa o conteúdo do About.fxml
	private synchronized void LoadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			// Acessar o Scene do Main.java
			Scene mainScene = Main.getMainScene();
			// Acessar o VBox que está dentro do Content que está dentro do ScrollPane
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
		
			// Pegar o primeiro(0) filho(children) do VBox(MainMenu)
			Node mainMenu = mainVBox.getChildren().get(0);
			// Limpar todos os filhos do MainVBox
			mainVBox.getChildren().clear();
			
			// Adicionar mainMenu
			mainVBox.getChildren().add(mainMenu);
			// Adicionar coleção(AddAll) - Os filhos do newVBox
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}