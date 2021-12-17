package persitence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PromocionAbsoluta;
import model.PromocionPorcentual;


public interface PromocionPorcentualDAO extends GenericDAO<PromocionPorcentual> {
	public  ArrayList<PromocionPorcentual> getPromocionesPorcentuales ();
	public PromocionPorcentual toPromocion(ResultSet resultados)  throws SQLException;
}