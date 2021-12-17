package service;

import java.util.ArrayList;

import model.Producto;
import model.Usuario;
import model.nullobjects.NullUser;
import persitence.DAOFactory;
import persitence.ItinerarioDAO;
import persitence.UserDAO;

public class ItinerarioService {

	
	public ArrayList<Producto> comprado (String username) {
	
		ItinerarioDAO itDao  =  DAOFactory.getItinerarios();
		ArrayList<Producto> itinerario = itDao.buscarItinerarioPorUsuario(username);
		
	return itinerario;
	}
		
	
	
}
