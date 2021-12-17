package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PromocionPorcentual extends Promocion{
	private double descuento;
	
	public PromocionPorcentual(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, double descuento) {
		super(nombre, costo, tiempo, tipoDeAtraccion, cupo, atracciones);
		this.descuento = descuento;
	}
	
	public PromocionPorcentual(String nombre, ArrayList<Atraccion> atracciones, double descuento) {
		super(nombre, atracciones);
		this.descuento = descuento;
	}
	
	public double getDescuento() {
		return this.descuento;
	}
	
	@Override
	public String toString() {
		return super.getNombre() + " : " + super.getNombresAtracciones() + " con un descuento del " + this.descuento * 100 + " %"+" por lo que el costo total es de "+ this.getCostoPromocion() + " monedas";
	}
	
	@Override
	public double getCosto() {
		return this.costo;
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
		Integer[] cupos = new Integer[super.atracciones.size()];
		for (int i = 0; i < super.atracciones.size(); i++) {
			cupos[i] = super.atracciones.get(i).getCupo();
		}
		Arrays.sort(cupos);
		return cupos[0];
	}

	public void restarCupo() {
		for (int i = 0; i < atracciones.size(); i++) {
			atracciones.get(i).restarCupo();
		}
	}

	@Override
	public double getCostoPromocion() {
		double costoPromocion = 0;
		for (int i = 0; i < atracciones.size(); i++){
			costoPromocion += atracciones.get(i).getCosto();
		}
		return Math.round(costoPromocion * (1 - this.descuento)*1000d)/1000d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descuento);
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
		PromocionPorcentual other = (PromocionPorcentual) obj;
		return Double.doubleToLongBits(descuento) == Double.doubleToLongBits(other.descuento);
	}
}