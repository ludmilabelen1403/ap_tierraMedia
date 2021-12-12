package servlets;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet implements Servlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		//String apellido = req.getParameter("apellido");
		String email = req.getParameter("email");
		String contraseña = req.getParameter("contraseña"); // arreglar
		String provincia = req.getParameter("provincia");
		String ciudad = req.getParameter("ciudad");
		String domicilio = req.getParameter("domicilio");
		Double presupuesto =Double.parseDouble( req.getParameter("presupuesto"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		String preferencia = req.getParameter("preferencia");
		
	
		
		
		
		resp.getWriter().append("Hola " + nombre + " " +  presupuesto + tiempo + preferencia + provincia);

	
	
	}
	
}
