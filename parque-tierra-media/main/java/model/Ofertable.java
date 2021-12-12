package model;

import java.util.Comparator;

public class Ofertable implements Comparator<Producto> {

	private tipo preferido;

	public Ofertable(tipo tipo) {
		this.preferido = tipo;
	}
	
	
	public int compare(Producto o1, Producto o2) {
		if (o1.tipoDeAtraccion == this.preferido && o2.tipoDeAtraccion == this.preferido) {
			if (o1.esPromo() && o2.esPromo()) {
				if (Double.compare(o1.costo, o2.costo) == 0) {
					return -Double.compare(o1.tiempo, o2.tiempo);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}

			} else {
				return -Boolean.compare(o1.esPromo(), o2.esPromo());
			}
		} else if (o1.tipoDeAtraccion != this.preferido && o2.tipoDeAtraccion != this.preferido) {
			if (o1.esPromo() && o2.esPromo()) {
				if (Double.compare(o1.costo, o2.costo) == 0) {
					return -Double.compare(o1.tiempo, o2.tiempo);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}

			}
			if (!o1.esPromo() && !o2.esPromo()) {
				if (Double.compare(o1.costo, o2.costo) == 0) {
					return -Double.compare(o1.tiempo, o2.tiempo);
				} else {
					return -Double.compare(o1.costo, o2.costo);
				}
			} else {
				return -Boolean.compare(o1.esPromo(), o2.esPromo());
			}
		} else {
			if (o1.tipoDeAtraccion == this.preferido)return -1;
			return 1;
		}
	}
}
