package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {

	private Department entity;
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button bttnSave;
	
	@FXML
	private Button bttnCancel;
	
	@FXML
	public void onBttnSaveAction() {
		System.out.println("onBttnSaveAction");
	}
	
	@FXML
	public void onBttnCancelAction() {
		System.out.println("onBttnCancelAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
		Constraints.setTextNotNumbersAllowed(txtName);
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		// String.valueOf por que o Id não era String
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
