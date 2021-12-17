package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;
import model.tipo;
import service.ItinerarioService;

@WebServlet("/miItinerario")


public class ItinerarioServlet extends HttpServlet implements Servlet {

		private static final long serialVersionUID = 785570116967107246L;
		private ItinerarioService ItinerarioService;
		

		@Override
		public void init() throws ServletException {
			ItinerarioService = new ItinerarioService();
			
			super.init();
		}

		
		
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//String nombre = req.getParameter("nombre");
			ArrayList<Producto> el_itinerario = new ArrayList<Producto>();
			
			Usuario user = (Usuario)req.getSession().getAttribute("user");
			
			String nombre = user.getNombre();
			el_itinerario = ItinerarioService.comprado(nombre); 
			
			req.setAttribute("el_itinerario", el_itinerario);
			RequestDispatcher mis_productos = req.getRequestDispatcher("/MisProductos.jsp"); 
			ArrayList<Promocion> promociones = new ArrayList<Promocion>();
			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
			for (int i = 0; i < el_itinerario.size(); i++) {
				if (el_itinerario.get(i).esPromo()) {
					promociones.add((Promocion) el_itinerario.get(i));
				}	else
					atracciones.add((Atraccion) el_itinerario.get(i));
					
			}
			req.setAttribute("promociones", promociones);
			req.setAttribute("atracciones", atracciones);
			ArrayList<String> nombre_atraccion = new ArrayList<String>();
			for (int i = 0; i < atracciones.size(); i++) {
			nombre_atraccion.add( atracciones.get(i).getNombre());
			}
			
			req.setAttribute("nombre_atraccion", nombre_atraccion);
			req.setAttribute("promociones", promociones);
			ArrayList<String> nombre_promo = new ArrayList<String>();
			for (int i = 0; i < promociones.size(); i++) {
			nombre_promo.add( promociones.get(i).getNombre());
			}
			req.setAttribute("nombre_atraccion", nombre_atraccion);
			mis_productos.forward(req, resp);
	
			
			
		
		}
}
