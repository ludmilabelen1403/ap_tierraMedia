package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import service.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet implements Servlet {

	
	private static final long serialVersionUID = -2299770028212892712L;

	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
	}
	 @Override
	    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	String username = req.getParameter("username");
	    	String password = req.getParameter("password");
	    	
	    	Usuario user = loginService.login(username, password);
	    	
	    	if (!user.isNull()) {
	    		req.getSession().setAttribute("user", user);
	    		resp.sendRedirect("index.jsp");    	
//	    		resp.sendRedirect("/parque-tierra-media/attractions/index.do");
	       	} else {
	    		req.setAttribute("flash", "Nombre de usuario o contraseÃ±a incorrectos");
	    		
	    		RequestDispatcher dispatcher = getServletContext()
	      		      .getRequestDispatcher("/login.jsp");
	      		    dispatcher.forward(req, resp);
	    	}
	    }
	
	
	
	
	
}
