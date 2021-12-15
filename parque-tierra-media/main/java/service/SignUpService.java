package service;

import model.Usuario;
import persitence.DAOFactory;
import persitence.UserDAO;

public class SignUpService {

	public int signUp(Usuario u) {

		UserDAO userDAO = DAOFactory.getUserDAO();
		 int row = userDAO.signUp(u);
		 
		 return row;
		

	}
}
