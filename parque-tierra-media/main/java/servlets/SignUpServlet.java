package servlets;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import model.tipo;
import service.SignUpService;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet implements Servlet {

	private SignUpService signUpService;

	@Override
	public void init() throws ServletException {
		signUpService = new SignUpService();
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String contraseña = req.getParameter("contraseña");
		Double presupuesto = Double.parseDouble(req.getParameter("presupuesto"));
		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		String preferencia = req.getParameter("preferencia");
		tipo preferencia_tipo = convertirPreferencia(preferencia);
		
		Usuario objeto_usuario = new Usuario(nombre, contraseña, presupuesto,tiempo, false, preferencia_tipo);
		signUpService.signUp(objeto_usuario);
		resp.sendRedirect("login.jsp");
	}

	public static tipo convertirPreferencia(String preferencia) {
		if (preferencia.equals( "AVENTURA")) {
			return tipo.AVENTURA;
		} else if (preferencia.equals( "DEGUSTACION")) {
			return tipo.DEGUSTACION;
		} else if (preferencia.equals( "PAISAJE")) {
			return tipo.PAISAJE;
		} else {
			return tipo.INDETERMINADO;
		}
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String nombre = req.getParameter("nombre");
//		String apellido = req.getParameter("apellido");
//		String email = req.getParameter("email");
//		String contraseña = req.getParameter("contraseña"); // arreglar
//		String provincia = req.getParameter("provincia");
//		String ciudad = req.getParameter("ciudad");
//		String domicilio = req.getParameter("domicilio");
//		Double presupuesto = Double.parseDouble(req.getParameter("presupuesto"));
//		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
//		String preferencia = req.getParameter("preferencia");
//
//		req.getSession().setAttribute("nombre", nombre);
//		req.getSession().setAttribute("apellido", apellido);
//		req.getSession().setAttribute("email", email);
//		req.getSession().setAttribute("provincia", provincia);
//		req.getSession().setAttribute("ciudad", ciudad);
//		req.getSession().setAttribute("domicilio", domicilio);
//		req.getSession().setAttribute("presupuesto", presupuesto);
//		req.getSession().setAttribute("tiempo", tiempo);
//		req.getSession().setAttribute("preferencia", preferencia);
//
//		resp.sendRedirect("index.jsp");
//	}

}
