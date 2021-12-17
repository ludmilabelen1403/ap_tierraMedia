package persitence;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PromocionAbsoluta;
import model.PromocionAxB;



public interface PromocionAxBDAO extends GenericDAO<PromocionAxB>{
	public  ArrayList<PromocionAxB> getPromocionesAxB();
	public PromocionAxB toPromocion(ResultSet resultados)  throws SQLException;
}
