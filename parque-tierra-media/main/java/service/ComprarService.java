package service;

import java.util.HashMap;
import java.util.Map;

import model.Atraccion;
import model.Usuario;
import persitence.AtraccionDAO;
import persitence.DAOFactory;
import persitence.UserDAO;

public class ComprarService {

	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer usuario_id, Integer atraccion_id) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario usuario = userDAO.find(usuario_id);
		Atraccion atraccion = atraccionDAO.find(atraccion_id);

		if (!atraccion.tieneCupo()) {
			errors.put("atraccion", "No hay cupo disponible");
		}
		if (!usuario.puedeComprar(atraccion)) {
			errors.put("usuario", "No tienes dinero suficiente");
		}
		if (!usuario.tieneTiempo(atraccion)) {
			errors.put("usuario", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			usuario.comprar(atraccion);;
			atraccion.restarCupo();;

			// no grabamos para no afectar la base de pruebas
			atraccionDAO.update(atraccion);
			userDAO.update(usuario);
		}

		return errors;

	}
	
}
