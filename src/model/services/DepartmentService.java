package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	// Buscar departamentos pelo banco de dados
	public List<Department> findAll() {
		return dao.findAll();
	}
	
	// Diferenciar se irá adicionar um departamento novo ou atualizar informações
	public void saveOrUpdate(Department obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	// Remover departamento
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}
	
}
