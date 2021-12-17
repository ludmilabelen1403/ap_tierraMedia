package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PromocionAbsoluta extends Promocion{
	private double monto;
	
	public PromocionAbsoluta(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, double monto) {
		super(nombre, costo, tiempo, tipoDeAtraccion, cupo, atracciones);
		this.monto = monto;
	}
	
	public PromocionAbsoluta(String nombre, ArrayList<Atraccion> atracciones, double monto) {
		super(nombre, atracciones);
		this.monto = monto;
	}
	
	@Override
	public String toString() {
		return super.getNombre() + ": " + super.getNombresAtracciones() + " a " + this.getCosto() + " monedas totales";
	}
	
	@Override
	public double getCosto() {
		return monto;
	}
	
	@Override
	public double getTiempo() {
		double tiempo = 0;
		for (int i = 0; i < super.atracciones.size(); i++) {
			tiempo+= atracciones.get(i).getTiempo();
		}
		return tiempo;
	}
	
	@Override
	public int getCupo() {
		Integer[] cupos = new Integer[atracciones.size()];
		for (int i = 0; i < super.atracciones.size(); i++) {
			cupos[i] = atracciones.get(i).getCupo();
		}
		Arrays.sort(cupos);
		return cupos[0];
	}
	
	public void restarCupo() {
		for (int i = 0; i < super.atracciones.size(); i++) {
			super.atracciones.get(i).restarCupo();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(monto);
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
		PromocionAbsoluta other = (PromocionAbsoluta) obj;
		return Double.doubleToLongBits(monto) == Double.doubleToLongBits(other.monto);
	}

	@Override
	protected double getCostoPromocion() {
		return this.monto;
	}
}
