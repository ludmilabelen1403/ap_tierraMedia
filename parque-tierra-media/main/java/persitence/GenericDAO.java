package persitence;

import java.util.ArrayList;
import model.Atraccion;
import model.PromocionPorcentual;

public interface GenericDAO <T> {

	public ArrayList<T> findAll();
	public int countAll();
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
	
	
}