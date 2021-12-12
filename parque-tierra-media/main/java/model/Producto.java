package model;

import java.util.Objects;

public abstract class Producto {
	protected int id;
	protected String nombre;
	protected double costo;
	protected double tiempo;
	tipo tipoDeAtraccion;
	protected int cupo;
	
	public Producto(int id, String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.cupo = cupo;
	}
	
	public Producto(String nombre, double costo, double tiempo, tipo tipoDeAtraccion, int cupo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.cupo = cupo;
	}
	
	public Producto() {
	}
	
	public Producto(String nombre) {
		this.nombre = nombre;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

    public double getCosto() {
    	return this.costo;
    }
    
    public double getTiempo() {
    	return this.tiempo;
    }
    
    public tipo getTipo() {
    	return this.tipoDeAtraccion;
    }

	public int getCupo() {
		return cupo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempo, tipoDeAtraccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo)
				&& tipoDeAtraccion == other.tipoDeAtraccion;
	}

	public abstract boolean esPromo();
	
	public boolean tieneCupo() {
		return this.cupo >= 1;
	}
	
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	
	public abstract void restarCupo() ;
}
