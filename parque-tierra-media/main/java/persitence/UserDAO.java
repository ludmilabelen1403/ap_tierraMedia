package persitence;


import java.util.ArrayList;

import model.Usuario;

public interface  UserDAO extends GenericDAO<Usuario> {
	
	public int signUp(Usuario u);
	public int insert(Usuario u);
	public Usuario findByUsername(String usuario);
	public  ArrayList<Usuario> getUsuaries ();
	public int updateDineroTiempo(Usuario usuario, double tiempo, double monto);
	public Usuario find(Integer id);
}