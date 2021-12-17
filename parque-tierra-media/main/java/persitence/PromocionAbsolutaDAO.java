package persitence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PromocionAbsoluta;

public interface PromocionAbsolutaDAO extends GenericDAO <PromocionAbsoluta> {
	public  ArrayList<PromocionAbsoluta> getPromocionesAbsolutas();
	public PromocionAbsoluta toPromocion(ResultSet resultados)  throws SQLException;
}