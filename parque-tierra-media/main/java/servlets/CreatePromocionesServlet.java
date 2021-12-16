package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import service.AttractionService;
import service.CreatePromocionesService;
@WebServlet("/promociones/CrearPromociones.jsp")
public class CreatePromocionesServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 4569386338176107097L;
	
	private CreatePromocionesService promocionesService;
	private AttractionService atrccservice;
	@Override
	public void init() throws ServletException {
		super.init();
		this.atrccservice = new AttractionService();
		this.promocionesService = new CreatePromocionesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Atraccion> atrccs = atrccservice.list();
		
		req.setAttribute("atrccs", atrccs);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/promociones/CrearPromociones.jsp");
		dispatcher.forward(req, resp);

	}
	

	
	
	

}
