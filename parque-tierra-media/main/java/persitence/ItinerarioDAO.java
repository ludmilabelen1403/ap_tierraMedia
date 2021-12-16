package persitence;

import java.util.ArrayList;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;

public interface ItinerarioDAO extends GenericDAO<Object> {
	public int insertAtraccion(Atraccion atraccion, Usuario usuario);
	public int insertPromocion(Promocion promocion, Usuario usuario);
	public ArrayList<Producto> buscarItinerarioPorUsuario(String nombre);
	
}