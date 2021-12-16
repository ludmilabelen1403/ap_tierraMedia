package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.tipo;
import persitence.AtraccionDAO;
import persitence.MissingDataException;
import verificador.verificadorAtraccion;

public class AtraccionDAOImpl implements AtraccionDAO {

	public int insert(Atraccion atraccion) {

		try {
			String sql = "INSERT INTO Atracciones (Nombre, Costo, Tiempo, Cupo, tipo_atraccion_id) VALUES (?, ?, ?, ?, ?)";

			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setDouble(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupo());
			statement.setInt(5, atraccion.getTipoAtraccionId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public ArrayList<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM Atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public ArrayList<Atraccion> getAtracciones() {
		try {
		String sql = "SELECT * FROM Atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		while (resultados.next()) {
			int id = resultados.getInt(1);
			String nombreDeAtraccion = resultados.getString(2);
			double costoDeAtraccion = resultados.getDouble(3);
			double tiempo = resultados.getDouble(4);
			int cupo = resultados.getInt(5);
			Integer id_tipo_atraccion = resultados.getInt(6);
			tipo tipo_atraccion = tipo.INDETERMINADO;

			if (id_tipo_atraccion.equals(1)) {
				tipo_atraccion = tipo.AVENTURA;
			} else if (id_tipo_atraccion.equals(2)) {
				tipo_atraccion = tipo.DEGUSTACION;
			} else if (id_tipo_atraccion.equals(3)) {
				tipo_atraccion = tipo.PAISAJE;
			}
			
			Atraccion a = new Atraccion(id, nombreDeAtraccion, costoDeAtraccion, tiempo, tipo_atraccion, cupo);
			atracciones.add(a);
		}

		return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	public Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		tipo tipo_preferencia = tipo.INDETERMINADO;
		int tipo_preferencia_id = resultados.getInt(6);
		if (tipo_preferencia_id == 1) {
			tipo_preferencia = tipo.AVENTURA;
		} else if (tipo_preferencia_id == 2) {
			tipo_preferencia = tipo.DEGUSTACION;
		} else if (tipo_preferencia_id == 3) {
			tipo_preferencia = tipo.PAISAJE;
		}
		return new Atraccion(resultados.getInt(1), resultados.getString(2), resultados.getDouble(3), resultados.getDouble(4), tipo_preferencia, resultados.getInt(5));
		// String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Atraccion t) {
		String sql = "UPDATE Atracciones SET Costo = ?, Tiempo = ?, Cupo = ? WHERE Nombre = ?";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, t.getCosto());
			statement.setDouble(2, t.getTiempo());
			statement.setDouble(3, t.getCupo());
			statement.setString(4, t.getNombre());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}

	}

	public int delete(Atraccion t) {
		try {
			String sql = "DELETE FROM Atracciones WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, t.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Atraccion buscarAtraccionPorNombre(String nombre) {
		try {
			String sql = "SELECT * FROM Atracciones WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccionN = null;

			if (resultados.next()) {
				atraccionN = toAtraccion(resultados);
			}

			return atraccionN;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public void restarCupo(int id) throws SQLException {
		 
		verificadorAtraccion v = new verificadorAtraccion();
		while(v.hayCupo(id)) {
		
		String sql = "UPDATE Atracciones\r\n"
				+ "SET Cupo = Cupo-1\r\n"
				+ "WHERE Atracciones.ID = ?";

			Connection conn;
			try {
				conn = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1,id);
				preparedStatement.executeUpdate();
			} 
			catch (SQLException e){
				e.printStackTrace();
		    }
     	}
	}
	

	public int buscarIdAtraccion(Atraccion atraccion) {
		try {
			String sql= "SELECT ID FROM Atracciones WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, atraccion.getNombre());
			
			ResultSet resultados = statement.executeQuery();
			
			int idAtraccion = resultados.getInt(1);
			
			return idAtraccion;
		}
		catch(Exception e) {
				throw new MissingDataException(e);
		}	
	}
	
	public Atraccion buscarAtraccionPorId(int id) {
		try {
			String sql= "SELECT * FROM Atracciones WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccionN = null;

			if (resultados.next()) {
				atraccionN = toAtraccion(resultados);
			}
			return atraccionN;
		}
		catch(Exception e) {
				throw new MissingDataException(e);
		}
	}
	
	public int updateCupo(Atraccion t, int cupo) {
		String sql = "UPDATE Atracciones SET Cupo = ? WHERE Nombre = ?";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, cupo);
			statement.setString(2, t.getNombre());
			int rows = statement.executeUpdate();
			
			return rows;
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}

	}
}