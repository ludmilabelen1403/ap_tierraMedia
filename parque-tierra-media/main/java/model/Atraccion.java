package model;

public class Atraccion extends Producto{

	public Atraccion(int id, String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo) {
		super(id, nombre, costo, tiempo, tipoDeAtraccion, cupo);
	}
	
	public Atraccion(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo) {
		super(nombre, costo, tiempo, tipoDeAtraccion, cupo);
	}

	public Atraccion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "la atracción "+super.nombre + " tiene un costo de " + super.costo + " monedas, su duración es de " + super.tiempo + " horas, y contiene" + super.cupo + " lugares disponibles, es de tipo: " + super.tipoDeAtraccion+ ")";
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

