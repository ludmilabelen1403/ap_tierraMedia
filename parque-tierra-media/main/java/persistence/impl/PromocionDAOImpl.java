package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.ConnectionProvider;
import model.Promocion;
import persitence.DAOFactory;
import persitence.MissingDataException;
import persitence.PromocionAbsolutaDAO;
import persitence.PromocionAxBDAO;
import persitence.PromocionPorcentualDAO;

public class PromocionDAOImpl {
	PromocionAbsolutaDAO nuevaAbs = DAOFactory.getPromocionesAbsolutas();
	PromocionAxBDAO nuevaAxB = DAOFactory.getPromocionesAxB();
	PromocionPorcentualDAO nuevaPorcentual = DAOFactory.getPromocionesPorcentuales();
	
	public int buscarIdPorNombre(String nombre) {
		try {
			String sql = "SELECT ID FROM Promociones WHERE Nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			int id = resultados.getInt(1);

			return id;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@SuppressWarnings("unused")
	public Promocion buscarPromocionPorId(int id) {
		try {
			String sql = "SELECT * FROM Promociones WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			// nombre, descuento, atraccion_gratis, monto_final
			String nombre = resultados.getString(2);
			Double descuento = resultados.getDouble(3);
			Integer atraccion_gratis = resultados.getInt(4);
			Double monto_final = resultados.getDouble(5);
						
			if (descuento != null) {
				return (nuevaPorcentual).toPromocion(resultados);
			} else if (atraccion_gratis != null) {
				return nuevaAxB.toPromocion(resultados);
			} else if (monto_final != null) {
				return nuevaAbs.toPromocion(resultados);
			}
			return null;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
