package service;


import model.Usuario;
import model.nullobjects.NullUser;
import persitence.DAOFactory;
import persitence.UserDAO;

public class LoginService  {

	public Usuario login(String username, String password) {
		UserDAO userDao = DAOFactory.getUserDAO();
		Usuario usuario = userDao.findByUsername(username);
//		System.out.println(password);
//		System.out.println(usuario);
		if(usuario.isNull() || !usuario.checkPassword(password)) {
			usuario = NullUser.build();
		}
		return usuario;
	}
	
	
}
