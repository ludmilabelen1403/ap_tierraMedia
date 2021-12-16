package service;

import java.util.List;

import model.Atraccion;
import persitence.DAOFactory;

public class AtraccionService {

	public List<Atraccion>list(){
		return DAOFactory.getAtraccionDAO().findAll();
	}
	
	
	
}
