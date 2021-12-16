package verificador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionProvider;

public class verificadorAtraccion {
 
	
	public boolean hayCupo(int id) throws SQLException  {
		
		
			String sql ="SELECT cupo\r\n"
					+ "FROM Atraccion\r\n"
					+ "where id = ?";
					
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();
		
		int cupo=0;
		if (resultados.next()) {
	     cupo = resultados.getInt(1);
		
		}
		
		return cupo>=1;
	}

}