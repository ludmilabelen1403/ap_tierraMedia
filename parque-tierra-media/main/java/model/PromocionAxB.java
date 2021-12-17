package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PromocionAxB extends Promocion{
	private Atraccion atraccionGratis;
	
	public PromocionAxB(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo, ArrayList<Atraccion> atracciones, Atraccion atraccionGratis) {
		super(nombre, costo, tiempo, tipoDeAtraccion, cupo, atracciones);
		this.atraccionGratis = atraccionGratis;
	}
	
	public PromocionAxB(String nombre, ArrayList<Atraccion> atracciones, Atraccion atraccionGratis) {
		super(nombre, atracciones);
		this.atraccionGratis = atraccionGratis;
	}
	
	public PromocionAxB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.getNombre() + ": " + super.getNombresAtracciones() + " con " + this.atraccionGratis.getNombre() + " gratis a " + this.getCostoPromocion() + " monedas";
	}
	
	public Atraccion getAtraccionGratis() {
		return this.atraccionGratis;
	}
	
	public double getCostoPromocion() {
		double costoPromocion = 0;
		for (int i = 0; i < atracciones.size(); i++){
			costoPromocion += atracciones.get(i).getCosto();
		}
		return costoPromocion;
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
		tiempo += this.atraccionGratis.getTiempo();
		return tiempo;
	}
	
	@Override
	public int getCupo() {
		Integer[] cupos = new Integer[atracciones.size()+1];
		for (int i = 0; i < super.atracciones.size(); i++) {
			cupos[i] = atracciones.get(i).getCupo();
		}
		cupos[cupos.length-1] = this.atraccionGratis.getCupo();
		Arrays.sort(cupos);
		return cupos[0];
	}

	public void restarCupo() {
		for (int i = 0; i < atracciones.size(); i++) {
			atracciones.get(i).restarCupo();
		}
		this.atraccionGratis.restarCupo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atraccionGratis);
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
		PromocionAxB other = (PromocionAxB) obj;
		return Objects.equals(atraccionGratis, other.atraccionGratis);
	}
}
