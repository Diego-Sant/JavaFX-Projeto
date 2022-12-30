package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(ActionEvent event) {
		// getSource é uma superclasse de Node, enquanto getWindow é uma superclasse de Stage
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}
	
	// Passar atributo para Integer
	public static Integer tryParseToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
}
