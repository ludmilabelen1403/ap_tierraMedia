package persitence;

import persistence.impl.AtraccionDAOImpl;
import persistence.impl.UserDAOImpl;

public class DAOFactory {


	public static UserDAOImpl getUserDAO() {
		return new UserDAOImpl();
	}

	public static AtraccionDAOImpl getAtraccionDAO() {
		return new AtraccionDAOImpl();
		
	}
//	
//	public static PromocionPorcentualDAOImpl getPromocionesPorcentuales() {
//		return new PromocionPorcentualDAOImpl();
//	}
//	
//	public static PromocionAxBDAOImpl getPromocionesAxB() {
//		return new PromocionAxBDAOImpl();
//	}
//	
//	public static PromocionAbsolutaDAOImpl getPromocionesAbsolutas() {
//		return new PromocionAbsolutaDAOImpl();
//	}
//
//	public static ItinerarioDAOImpl getItinerarios() {
//		return new ItinerarioDAOImpl();
//	}
//	public static PromocionDAOImpl getPromociones() {
//		return new PromocionDAOImpl();
//	}

}
