package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.exceptions.ValidationException;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	private DepartmentService service;
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	private Department entity;
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	// Outros objetos poderão se inscrever nessa lista para receberem um evento
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	// Desde que os objetos implementem o DataChangeListener poderão se inscrever para receber o evento
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
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
	public void onBttnSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			// Fechar a janela ao clicar em Save
			Utils.currentStage(event).close();
		}
		catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}
	}
	
	// Metódo para atualizar a tela em tempo real ao em vez de precisar atualizar manualmente
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Department getFormData() {
		Department obj = new Department();
		
		ValidationException exception = new ValidationException("Validation error");
		
		// tryParseToInt usado pois o getText era String e o setId era Integer, então precisou ser convertido para Integer
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		
		// Configuração para dar excessão caso o campo name esteja vazio
		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addError("name", "Field can't be empty");
		}
		// Tanto o setName quanto o getText eram Strings então não foi preciso o tryParseToInt
		obj.setName(txtName.getText());
		
		// Só irá retornar obj caso tiver 0 errors
		if (exception.getErrors().size() > 0) {
			throw exception;
		}
		return obj;
	}

	@FXML
	public void onBttnCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
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
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		// Pegando error correspondente ao campo "name" e usando em labelErrorName
		if (fields.contains("name")) {
			labelErrorName.setText(errors.get("name"));
		}
	}

}
