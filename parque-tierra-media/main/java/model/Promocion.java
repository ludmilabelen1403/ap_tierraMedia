package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Promocion extends Producto{
	ArrayList<Atraccion> atracciones;
	
	public Promocion(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones) {
		super(nombre, costo, tiempo, tipoDeAtraccion, cupo);
		this.atracciones = atracciones;
	}
	
	protected Promocion(String nombre, ArrayList<Atraccion> atracciones) {
		super(nombre);
		this.atracciones = atracciones;
	}
	
	protected Promocion() {
		
	}
	
	protected abstract double getCostoPromocion();

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}
	
	protected String getNombresAtracciones() {
		String[] nombres = new String[atracciones.size()];
		for (int i = 0; i < this.atracciones.size(); i++){
			nombres[i] = atracciones.get(i).getNombre();
		}
		return Arrays.deepToString(nombres);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atracciones);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atracciones, other.atracciones);
	}

	public boolean esPromo() {
		return true;
	}
	
	public abstract void restarCupo() ;
}
