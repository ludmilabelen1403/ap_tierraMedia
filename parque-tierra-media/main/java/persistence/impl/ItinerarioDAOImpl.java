package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;
import persitence.DAOFactory;
import persitence.ItinerarioDAO;
import persitence.MissingDataException;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	UserDAOImpl userImpl = new UserDAOImpl();
	AtraccionDAOImpl atrImpl = new AtraccionDAOImpl();
	PromocionDAOImpl promoImpl = new PromocionDAOImpl();

	public int insertAtraccion(Atraccion atraccion, Usuario usuario) {

		try {
			String sql = "INSERT INTO ITINERARIO (atraccion_id, usuario_id) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atrImpl.buscarIdAtraccion(atraccion));
			statement.setInt(2, userImpl.buscarIdUsuario(usuario));
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insertPromocion(Promocion promocion, Usuario usuario) {
		PromocionDAOImpl promo = DAOFactory.getPromociones();
		try {
			String sql = "INSERT INTO ITINERARIO (promocion_id, usuario_id) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promo.buscarIdPorNombre(promocion.getNombre()));
			statement.setInt(2, userImpl.buscarIdUsuario(usuario));
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@SuppressWarnings("unused")
	public ArrayList<Producto> buscarItinerarioPorUsuario(String nombre) {
		try {
			String sql = "SELECT * FROM Itinerario WHERE usuario_id = (SELECT id FROM Usuarios WHERE nombre = ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();
			
			ArrayList<Producto> itinerario = new ArrayList<Producto>();

			while (resultados.next()) {
				Integer usuario_id = resultados.getInt(2);
				Integer atraccion_id = resultados.getInt(3);
				Integer promocion_id = resultados.getInt(4);

				
				if (atraccion_id != 0) {
					itinerario.add(atrImpl.buscarAtraccionPorId(atraccion_id));
				}
				else {
					itinerario.add(promoImpl.buscarPromocionPorId(promocion_id));
				}
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Object t) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}