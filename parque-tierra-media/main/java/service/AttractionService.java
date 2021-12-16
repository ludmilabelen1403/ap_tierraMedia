package service;

import java.util.List;

import model.Atraccion;
import model.tipo;
import persitence.AtraccionDAO;
import persitence.DAOFactory;

public class AttractionService {

	public  List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}
	
	

 Atraccion createAtracciones(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo) {

		Atraccion atrcc = new Atraccion(nombre, costo, tiempo,tipoDeAtraccion, cupo);

		if (atrcc.isValid()) {
			AtraccionDAO atraccion = DAOFactory.getAtraccionDAO();
			atraccion.insert(atrcc);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atrcc;
	}

	public Atraccion update(int id, String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo) {

		AtraccionDAO atraccion = DAOFactory.getAtraccionDAO();
		Atraccion atrcc = atraccion.buscarAtraccionPorId(id);

		atrcc.setNombre(nombre);;
		atrcc.setCosto(costo);;
		atrcc.setTiempo(tiempo);;
		atrcc.setCupo(cupo);;

		if (atrcc.isValid()) {
			atraccion.update(atrcc);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return atrcc;
	}

	public void delete(Integer id) {
		Atraccion atraccion = new Atraccion (id, null, null, null, null, null);

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.delete(atraccion);
	}

	public  Atraccion find(Integer id) {
		return DAOFactory.getAtraccionDAO().buscarAtraccionPorId(id);
	}
	
	

}
