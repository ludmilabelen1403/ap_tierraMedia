package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;


import jdbc.ConnectionProvider;
import model.Atraccion;
import model.PromocionAxB;
import persitence.DAOFactory;
import persitence.MissingDataException;
import persitence.PromocionAxBDAO;


public class PromocionAxBDAOImpl implements PromocionAxBDAO {
	public int insert(PromocionAxB promocion) {
		PromocionDAOImpl promo = DAOFactory.getPromociones();
		try {
			
			String sql = "INSERT INTO Promociones (Nombre, Atraccion_gratis_id) VALUES (?, ?)";
			
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			
			AtraccionDAOImpl atrcc = new AtraccionDAOImpl();
			
			statement.setInt(2, atrcc.buscarAtraccionPorNombre(promocion.getAtraccionGratis().getNombre()).getId());
			int rows = statement.executeUpdate();
			
			int id_promocion = promo.buscarIdPorNombre(promocion.getNombre());
			
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

	public ArrayList<PromocionAxB> findAll() {
		ArrayList<PromocionAxB> promociones= new ArrayList<PromocionAxB>();
		return promociones;
	}
	
	public ArrayList<PromocionAxB> getPromocionesAxB() {
		try {
			String sql = "SELECT * FROM Promociones WHERE Atraccion_gratis_id is not null";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			ArrayList<PromocionAxB> promocionesAxB= new ArrayList<PromocionAxB>();
			while (resultados.next()) {
				promocionesAxB.add(toPromocion(resultados));
			}
	
			return promocionesAxB;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	public PromocionAxB toPromocion(ResultSet resultados) throws SQLException {
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
		
		Atraccion atraccionGr = atrcc.buscarAtraccionPorId(resultados.getInt(4));
		
		PromocionAxB promo = new PromocionAxB(resultados.getString(2), atracciones, atraccionGr); 
		
		return new PromocionAxB(promo.getNombre(), promo.getCostoPromocion(), promo.getTiempo(), atracciones.get(0).getTipo(), promo.getCupo(), atracciones, atraccionGr);
		// String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, Atraccion atraccionGratis
		
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	public int update(PromocionAxB t) {
		String sql = "UPDATE Promociones SET Atraccion_gratis_id = ? WHERE NOMBRE = ?";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			AtraccionDAOImpl atrcc = new AtraccionDAOImpl();
			statement.setInt(1, atrcc.buscarAtraccionPorNombre(t.getAtraccionGratis().getNombre()).getId());
			statement.setString(2, t.getNombre());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(PromocionAxB t) {
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
	
	public PromocionAxB buscarPromocionAxBPorNombre(String nombre) {
		try {
			String sql = "SELECT * FROM Promociones WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();
			
			PromocionAxB promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}
			
			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}