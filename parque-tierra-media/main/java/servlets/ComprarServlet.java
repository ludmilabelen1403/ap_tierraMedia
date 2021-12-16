package servlets;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import persitence.DAOFactory;
import service.ComprarService;


@WebServlet("/attraction/buy.do")
public class ComprarServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private ComprarService comprarService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.comprarService = new ComprarService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer attractionId = Integer.parseInt(req.getParameter("id"));
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		Map<String, String> errors = comprarService.buy(user.getId(), attractionId);
		
		Usuario user2 = DAOFactory.getUserDAO().find(user.getId());
		req.getSession().setAttribute("usuario", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "Â¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/parque-tierra-media/attraction/index.do");
		dispatcher.forward(req, resp);
	}
}
