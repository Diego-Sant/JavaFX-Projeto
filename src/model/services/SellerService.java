package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {

	private SellerDao dao = DaoFactory.createSellerDao();
	
	// Buscar vendedores pelo banco de dados
	public List<Seller> findAll() {
		return dao.findAll();
	}
	
	// Diferenciar se irá adicionar um usuário novo ou atualizar informações
	public void saveOrUpdate(Seller obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	// Remover vendedores
	public void remove(Seller obj) {
		dao.deleteById(obj.getId());
	}
	
}
