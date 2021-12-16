package model;


public class Atraccion extends Producto{

	public Atraccion(int id, String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo) {
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
}
