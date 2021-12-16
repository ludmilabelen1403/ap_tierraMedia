package attractions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import service.AtraccionService;

@WebServlet("/attraction/index.do")
public class ListAttractionServlet extends HttpServlet implements Servlet{
	private static final long serialVersionUID = -5589905252472126032L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		this.atraccionService = new AtraccionService();
	}
    

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = atraccionService.list();
		req.setAttribute("atracciones", atracciones);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracttion/index.jsp");
		dispatcher.forward(req, resp);
	}

}
