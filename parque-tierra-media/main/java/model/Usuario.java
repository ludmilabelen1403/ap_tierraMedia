package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import utils.Crypt;

public class Usuario {
	private int id;
	private Boolean admin;
	private String nombre, contraseña;
	private double presupuesto;
	private double tiempoDisponible;
	private tipo preferencia;
	protected ArrayList<Producto> itinerario;
	
	public Usuario(int id, String nombre, tipo preferencia, double presupuesto, double tiempoDisponible) {
		this.id = id;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = preferencia;
	}
	
	public Usuario(String nombre, tipo preferencia, double presupuesto, double tiempoDisponible) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = preferencia;
	}
	
	public Usuario(Integer id, String username, String password, Integer coins, Double time, Boolean admin) {
		super();
		this.id = id;
		this.nombre = username;
		this.contraseña = password;
		this.presupuesto = coins;
		this.tiempoDisponible = time;
		this.admin = admin;
	}
	
	public Usuario( String username, String password, double coins, Double time, Boolean admin,tipo preferencia) {
		super();
		
		this.nombre = username;
		this.contraseña = password;
		this.presupuesto = coins;
		this.tiempoDisponible = time;
		this.admin = admin;
		this.preferencia = preferencia;
		this.contraseña=password;
		setPassword(password);
	}
	
//	public Usuario(String nombre, int preferencia, double presupuesto, double tiempoDisponible) {
//		this.nombre = nombre;
//		this.presupuesto = presupuesto;
//		this.tiempoDisponible = tiempoDisponible;
//		this.preferencia_id = preferencia;
//	}
	
//	public String toString() {
//		return this.nombre + " prefiere las atracciones del tipo " + this.preferencia + ", tiene " + this.presupuesto + " monedas disponibles y cuenta con " + this.tiempoDisponible + " horas disponibles";
//	}
	
	
	
	public int getPreferenciaId() {
		if (this.preferencia.equals(tipo.AVENTURA)) {
			return 1;
		}
		else if (this.preferencia.equals(tipo.DEGUSTACION)) {
			return 2;
		}
		else if (this.preferencia.equals(tipo.PAISAJE)) {
			return 3;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", admin=" + admin + ", nombre=" + nombre + ", contraseña=" + contraseña
				+ ", presupuesto=" + presupuesto + ", tiempoDisponible=" + tiempoDisponible + ", preferencia="
				+ preferencia + ", itinerario=" + itinerario + "]";
	}

	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public double getPresupuesto() {
		return this.presupuesto;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public tipo getPreferencia() {
		return this.preferencia;
	}
	
	public static ArrayList<Producto> listaDePreferencias(ArrayList<Producto> productos, tipo preferencia) {
		Collections.sort(productos, new Ofertable(preferencia));
		return productos;
	}
	
	public void restarTiempo(double tiempoDelProducto) {
		this.tiempoDisponible-=tiempoDelProducto;
	}
	
	public void restarPresupuesto(double costoDelProducto) {
		 this.presupuesto-=costoDelProducto;
	}
	
	public double gastoTotal() {
		double GastoTotalDelUsuario = 0;
		for (int i = 0; i < this.getItinerario().size(); i++) {
			double gasto = 0;
			gasto += this.getItinerario().get(i).getCosto();
			GastoTotalDelUsuario += gasto;
			gasto = 0;
		}
		return GastoTotalDelUsuario;
	}
	
	public double gastoTotalTiempo() {
		double TiempoGastadoDelUsuario = 0;
		for (int i = 0; i < this.getItinerario().size(); i++) {
			double gasto = 0;
			gasto += this.getItinerario().get(i).getTiempo();
			TiempoGastadoDelUsuario += gasto;
			gasto = 0;
		}
		return TiempoGastadoDelUsuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itinerario, nombre, preferencia, presupuesto, tiempoDisponible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(itinerario, other.itinerario) && Objects.equals(nombre, other.nombre)
				&& preferencia == other.preferencia
				&& Double.doubleToLongBits(presupuesto) == Double.doubleToLongBits(other.presupuesto)
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible);
	}
	
	public void crearItinerario() {
		this.itinerario = new ArrayList<Producto>();
	}
	
	public void agregarProducto(Producto producto) {
		itinerario.add(producto);
		restarPresupuesto(producto.getCosto());
		restarTiempo(producto.getTiempo());
	}
	
	public ArrayList<Producto> getItinerario() {
		return itinerario;
	}

	public void setItinerario(ArrayList<Producto> itinerario) {
		this.itinerario = itinerario;
	}
	public boolean isNull() {
		return false;
	}
	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.contraseña);
	}
	
	public Boolean getAdmin() {
		return admin;
	}
	public Boolean isAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	public void setPassword(String password) {
		this.contraseña = Crypt.hash(password);
	}
	
}
