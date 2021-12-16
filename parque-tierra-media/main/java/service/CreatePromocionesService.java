package service;

import java.util.ArrayList;

import model.Atraccion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.tipo;
import persitence.DAOFactory;
import persitence.PromocionAbsolutaDAO;
import persitence.PromocionAxBDAO;
import persitence.PromocionPorcentualDAO;

public class CreatePromocionesService {

		
	public static void createPromoPorcentual(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, double descuento) {
		PromocionPorcentual promocionP = new PromocionPorcentual(nombre, costo, tiempo, tipoDeAtraccion, cupo, atracciones, descuento);
		PromocionPorcentualDAO pP = DAOFactory.getPromocionesPorcentuales();
		  pP.insert(promocionP);
		
		
	}
	public static void createPromoAxB(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, Atraccion atraccionGratis) {
		PromocionAxB promocionAxB = new PromocionAxB(nombre, costo, tiempo, tipoDeAtraccion, cupo, atracciones, atraccionGratis);
		PromocionAxBDAO pAxB = DAOFactory.getPromocionesAxB();
		pAxB.insert(promocionAxB);
		
		
		
	}
	public static void createPromoAbsoluta(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, double monto) {
		
		PromocionAbsoluta promocionAbsoluta = new PromocionAbsoluta(nombre, costo, tiempo, tipoDeAtraccion, cupo, atracciones, monto);
		PromocionAbsolutaDAO pAbs = DAOFactory.getPromocionesAbsolutas();
		pAbs.insert(promocionAbsoluta);
		
		
		
	}
	
}
