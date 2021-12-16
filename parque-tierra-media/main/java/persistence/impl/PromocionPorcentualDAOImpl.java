package persistence.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.ConnectionProvider;

import model.PromocionPorcentual;
import persitence.DAOFactory;
import persitence.MissingDataException;
import persitence.PromocionPorcentualDAO;
import model.Atraccion;

public class PromocionPorcentualDAOImpl implements PromocionPorcentualDAO {
	public int insert(PromocionPorcentual promocion) {
		PromocionDAOImpl promo = DAOFactory.getPromociones();
		try {
			String sql = "INSERT INTO Promociones (Nombre, Descuento) VALUES (?, ?)";
			
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setDouble(2, promocion.getDescuento());
			int rows = statement.executeUpdate();
			
			int id_promocion = promo.buscarIdPorNombre(promocion.getNombre());
			
			AtraccionDAOImpl atrcc = new AtraccionDAOImpl();
			String sql3 = "";
			for (Atraccion atraccion : promocion.getAtracciones()) {
				atraccion = atrcc.buscarAtraccionPorNombre(atraccion.getNombre());
				sql3 = "INSERT INTO Atracciones_Promociones (Promocion_id, Atraccion_id) VALUES (?, ?)";
				statement = conn.prepareStatement(sql3);
				statement.setInt(1, id_promocion);
				statement.setInt(2, atraccion.getId());
				statement.executeUpdate();
			}
			
			
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public ArrayList<PromocionPorcentual> findAll() {
		ArrayList<PromocionPorcentual> promociones= new ArrayList<PromocionPorcentual>();
		return promociones;
	}

	public ArrayList<PromocionPorcentual> getPromocionesPorcentuales() {
		try {
			String sql = "SELECT * FROM Promociones WHERE Descuento is not null";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			ArrayList<PromocionPorcentual> promocionesPorcentuales= new ArrayList<PromocionPorcentual>();
			while (resultados.next()) {
				promocionesPorcentuales.add(toPromocion(resultados));
			}
	
			return promocionesPorcentuales;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public PromocionPorcentual toPromocion(ResultSet resultados) throws SQLException {
		String sql = "SELECT * FROM Atracciones_Promociones WHERE Promocion_id = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, resultados.getInt(1));
		ResultSet res = statement.executeQuery();
		
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		AtraccionDAOImpl atrcc = new AtraccionDAOImpl();
		while (res.next()) {
			atracciones.add(atrcc.buscarAtraccionPorId(res.getInt(3)));
		}
		
		PromocionPorcentual promo = new PromocionPorcentual(resultados.getString(2), atracciones, resultados.getDouble(3));
		
		return new PromocionPorcentual(promo.getNombre(), promo.getCostoPromocion(), promo.getTiempo(), atracciones.get(0).getTipo(), promo.getCupo(), atracciones, resultados.getDouble(3));
		// String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, double descuento
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(PromocionPorcentual t) {
		String sql = "UPDATE Promociones SET Descuento = ? WHERE NOMBRE = ?";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, t.getDescuento());
			statement.setString(2, t.getNombre());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(PromocionPorcentual t) {
		try {
			String sql = "DELETE FROM Promociones WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public PromocionPorcentual buscarPromocionPorcentualPorNombre(String nombre) {
		try {
			String sql = "SELECT * FROM Promociones WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();
			
			PromocionPorcentual promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}
			
			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}