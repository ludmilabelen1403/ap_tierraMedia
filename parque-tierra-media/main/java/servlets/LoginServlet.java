package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("login")
public class LoginServlet extends HttpServlet  {

	
	private static final long serialVersionUID = 4118563311878631458L;
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
if (username.equals("yael") && password.equals("1234")) {
    		req.getSession().setAttribute("username", "yael");
    		resp.sendRedirect("welcome.jsp");
    	} else {
    		req.setAttribute("flash", "username o password incorrectos");
    		
    		RequestDispatcher dispatcher = getServletContext()
      		      .getRequestDispatcher("/login.jsp");
      		    dispatcher.forward(req, resp);
    	}
    }
}
	
	
	
//	private LoginService loginService;

//	@Override
//	public void init() throws ServletException {
//		super.init();
//		loginService = new LoginService();
//	}
	
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	String username = req.getParameter("username");
//    	String password = req.getParameter("password");
//    	
//    	User user = loginService.login(username, password);
//    	
//    	if (!user.isNull()) {
//    		req.getSession().setAttribute("user", user);
//    		resp.sendRedirect("index.jsp");    		
//       	} else {
//    		req.setAttribute("flash", "Nombre de usuario o contraseÃ±a incorrectos");
//    		
//    		RequestDispatcher dispatcher = getServletContext()
//      		      .getRequestDispatcher("/login.jsp");
//      		    dispatcher.forward(req, resp);
//   	}
//    }

