package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.PromocionAbsoluta;
import persitence.DAOFactory;
import persitence.MissingDataException;
import persitence.PromocionAbsolutaDAO;
public class PromocionAbsolutaDAOImpl implements PromocionAbsolutaDAO{
	
	
	
	public int insert(PromocionAbsoluta promocion) {
		PromocionDAOImpl promo = DAOFactory.getPromociones();
		try {
			String sql = "INSERT INTO Promociones (Nombre, Monto_final) VALUES (?, ?)";
			
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setDouble(2, promocion.getCosto());
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

	public ArrayList<PromocionAbsoluta> getPromocionesAbsolutas() {
		try {
		String sql = "SELECT * FROM Promociones WHERE Monto_final is not null";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();
		
		ArrayList<PromocionAbsoluta> promocionesAbs= new ArrayList<PromocionAbsoluta>();
		while (resultados.next()) {
			promocionesAbs.add(toPromocion(resultados));
		}

		return promocionesAbs;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public PromocionAbsoluta toPromocion(ResultSet resultados) throws SQLException {
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
		
		PromocionAbsoluta promo = new PromocionAbsoluta(resultados.getString(2), atracciones, resultados.getDouble(5)); 
		
		return new PromocionAbsoluta(promo.getNombre(), promo.getCosto(), promo.getTiempo(), atracciones.get(0).getTipo(), promo.getCupo(), atracciones, resultados.getDouble(5));
		// String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, double monto
	}
	
	public LinkedList<PromocionAbsoluta> findAll(){
		LinkedList<PromocionAbsoluta> promociones = new LinkedList<PromocionAbsoluta>();
		return promociones;
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(PromocionAbsoluta t) {
		String sql = "UPDATE Promociones SET Monto_final = ? WHERE NOMBRE = ?";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, t.getCosto());
			statement.setString(2, t.getNombre());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}
	}

	public int delete(PromocionAbsoluta t) {
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
	
	public PromocionAbsoluta buscarPromocionAbsolutaPorNombre(String nombre) {
		try {
			String sql = "SELECT * FROM Promociones WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			PromocionAbsoluta promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}