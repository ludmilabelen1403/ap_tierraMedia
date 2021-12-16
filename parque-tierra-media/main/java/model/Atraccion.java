package model;

import java.util.HashMap;
import java.util.Map;

public class Atraccion extends Producto{

	private Map<String, String> errors;
	

	public Atraccion(int id, String nombre, Double costo, Double tiempo, tipo tipoDeAtraccion, Integer cupo) {
		super(id, nombre, costo, tiempo, tipoDeAtraccion, cupo);
	}
	
	public Atraccion(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo) {
		super(nombre, costo, tiempo, tipoDeAtraccion, cupo);
	}

	

	@Override
	public String toString() {
		return super.nombre + "(" + super.costo + " monedas, " + super.tiempo + " horas, " + super.cupo + " lugares, tipo: " + super.tipoDeAtraccion+ ")";
	}
	
	public int getTipoAtraccionId() {
		if (super.tipoDeAtraccion.equals(tipo.AVENTURA)) {
			return 1;
		}
		else if (super.tipoDeAtraccion.equals(tipo.DEGUSTACION)) {
			return 2;
		}
		else if (super.tipoDeAtraccion.equals(tipo.PAISAJE)) {
			return 3;
		}
		return 0;
	}
	
	public boolean esPromo() {
		return false;
	}
	
	public void restarCupo() {
		super.cupo-=1;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (tiempo <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (tiempo > 60) {
			errors.put("duration", "Excede el tiempo máximo");
		}
		if (cupo <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	
}
