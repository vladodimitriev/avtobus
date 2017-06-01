package mk.mladen.avtobusi.dao;

import java.util.List;

public interface GenericDao<T> {

	public void persist(T object);
	
	public void update(T object);
	
	public void delete(T object);
	
	public void delete(int id);
	
	public List<T> getAll();
	
	public T getById(int id);
}
