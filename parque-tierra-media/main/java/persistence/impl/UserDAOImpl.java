package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import jdbc.ConnectionProvider;
import model.Usuario;
import model.tipo;
import persitence.MissingDataException;
import persitence.UserDAO;

public class UserDAOImpl implements UserDAO {

	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO Usuarios (Nombre, Presupuesto, Tiempo_disponible, Preferencia_id) VALUES (?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setDouble(2, usuario.getPresupuesto());
			statement.setDouble(3, usuario.getTiempoDisponible());
			statement.setInt(4, usuario.getPreferenciaId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM Usuarios WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	public int update(Usuario usuario) {

		try {
			String sql = "UPDATE Usuarios SET Presupuesto = ?, Tiempo_disponible = ? WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, usuario.getPresupuesto());
			statement.setDouble(2, usuario.getTiempoDisponible());
			statement.setString(3, usuario.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	

	public java.util.List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM Usuarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUsuario(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public ArrayList<Usuario> getUsuaries() {

		try {
			String sql = "SELECT * FROM Usuarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

			while (resultados.next()) {
				int id = resultados.getInt(1);
				String nombreDeUsuario = resultados.getString(2);
				Integer tipo_preferencia_id = resultados.getInt(5);
				tipo tipo_preferencia = tipo.INDETERMINADO;
				double presupuesto = resultados.getDouble(3);
				double tiempo_disponible = resultados.getDouble(4);

				if (tipo_preferencia_id.equals(1)) {
					tipo_preferencia = tipo.AVENTURA;
				} else if (tipo_preferencia_id.equals(2)) {
					tipo_preferencia = tipo.DEGUSTACION;
				} else if (tipo_preferencia_id.equals(3)) {
					tipo_preferencia = tipo.PAISAJE;
				}

				Usuario UsuarioNuevo = new Usuario(id, nombreDeUsuario, tipo_preferencia, presupuesto, tiempo_disponible);
				usuarios.add(UsuarioNuevo);

			}

			return usuarios;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}
	
	public Usuario findByUsername(String usuario) {
		try {
			String sql = "SELECT * FROM Usuarios WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario);
			ResultSet resultados = statement.executeQuery();

			Usuario usuarioN = null;

			if (resultados.next()) {
				usuarioN = toUsuario(resultados);
			}

			return usuarioN;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	private Usuario toUsuario(ResultSet resultados) throws SQLException {
		tipo tipo_preferencia = tipo.INDETERMINADO;
		int tipo_preferencia_id = resultados.getInt(5);
		if (tipo_preferencia_id == 1) {
			tipo_preferencia = tipo.AVENTURA;
		} else if (tipo_preferencia_id == 2) {
			tipo_preferencia = tipo.DEGUSTACION;
		} else if (tipo_preferencia_id == 3) {
			tipo_preferencia = tipo.PAISAJE;
		}
		return new Usuario(resultados.getInt(1), resultados.getString(2), tipo_preferencia, resultados.getDouble(3),
				resultados.getDouble(4));
	}

	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int buscarIdUsuario(Usuario usuario) {
		try {
			String sql= "SELECT id FROM usuarios WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, usuario.getNombre());
			
			ResultSet resultados = statement.executeQuery();
			
			int idUsuario = resultados.getInt(1);
			
			return idUsuario;
		}catch(Exception e) {
				throw new MissingDataException(e);
		}
			
	}
	
	public int updateDineroTiempo(Usuario usuario, double tiempo, double monto) {

		try {
			String sql = "UPDATE Usuarios SET Presupuesto = ?, Tiempo_disponible = ? WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, monto);
			statement.setDouble(2, tiempo);
			statement.setString(3, usuario.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
