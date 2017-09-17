package mk.mladen.avtobusi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import mk.mladen.avtobusi.dao.GenericDao;
import org.springframework.transaction.annotation.Transactional;


public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
    private EntityManager entityManager;
	
	private Class<T> type;
    
	public GenericDaoImpl(Class<T> type) {
		super();
		this.type = type;
	}
     
    public EntityManager getEntityManager(){
        return this.entityManager;
    }

	@Override
	public void persist(T object) {
		entityManager.persist(object);
	}

	@Override
	public void update(T object) {
		entityManager.merge(object);
	}

	@Override
	public void delete(T object) {
		entityManager.remove(object);
	}

	@Override
	public void delete(int id) {
		//TODO to be implemented
	}

	@Override
	public List<T> getAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
	    Root<T> rootQuery = criteriaQuery.from(type);
	    criteriaQuery.select(rootQuery);
	    TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
	    return query.getResultList();
	}

	@Override
	public T getById(int id) {
		return entityManager.find(type, id);
	}
}
