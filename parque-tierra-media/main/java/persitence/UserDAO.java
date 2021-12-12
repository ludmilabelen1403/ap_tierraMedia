package persitence;


import java.util.ArrayList;

import model.Usuario;

public interface  UserDAO extends GenericDAO<Usuario> {
	public Usuario findByUsername(String usuario);
	public  ArrayList<Usuario> getUsuaries ();
	public int updateDineroTiempo(Usuario usuario, double tiempo, double monto);
}