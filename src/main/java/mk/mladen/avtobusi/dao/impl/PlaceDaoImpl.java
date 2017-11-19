package mk.mladen.avtobusi.dao.impl;

import mk.mladen.avtobusi.dao.PlaceDao;
import mk.mladen.avtobusi.entity.PlaceEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository(value = "placeDao")
public class PlaceDaoImpl extends GenericDaoImpl<PlaceEntity> implements PlaceDao {
	
	private final static Logger logger = Logger.getLogger(PlaceDaoImpl.class);

	public PlaceDaoImpl() {
		super(PlaceEntity.class);
	}

	@Override
	public long countAll() {
		Query query = getEntityManager().createQuery("select count(ple) from PlaceEntity ple");
		Object obj = query.getSingleResult();
		if(obj instanceof Long) {
			Long ll = (Long)obj;
			return ll.longValue();
		}
		return 0;
	}

	@Override
	public List<String> getAllPlacesNames() {
		List<String> results = new ArrayList<String>();
		Query query = getEntityManager().createQuery("select ple.name from PlaceEntity ple order by ple.name asc");
		query.setMaxResults(100);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}
	
	@Override
	public List<String> getAllPlacesNames(String name) {
		List<String> results = new ArrayList<String>();
		Query query = getEntityManager().createQuery("select ple.name from PlaceEntity ple where (ple.name like :name or ple.nameCyrilic like :name) order by ple.name desc");
		query.setParameter("name", "%" + name + "%");
		query.setMaxResults(10);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}

	@Override
	public List<PlaceEntity> getAllPlaces(String name) {
		List<PlaceEntity> results = new ArrayList<PlaceEntity>();
		Query query = getEntityManager().createQuery("select ple from PlaceEntity ple where (ple.name like :name or ple.nameCyrilic like :name) order by ple.name desc");
		query.setParameter("name", "%" + name + "%");
		query.setMaxResults(100);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}

	@Override
	public List<String> getAllPlacesNamesByLanguage(String language) {
		List<String> results = new ArrayList<String>();
		Query query = getEntityManager().createQuery("select ple.name from PlaceEntity ple");
		query.setMaxResults(10);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}

	@Override
	public PlaceEntity getByCyrilicName(String name) {
		Query query = getEntityManager().createQuery("select ple from PlaceEntity ple where UPPER(ple.nameCyrilic) like UPPER(:name)");
		query.setParameter("name", "%" + name + "%");
		try {
			Object object = query.getSingleResult();
			if(object instanceof PlaceEntity) {
				PlaceEntity pe = (PlaceEntity)object;
				return pe;
			}
		} catch(NoResultException nre) {
			return null;
		} catch(NonUniqueResultException nure) {
			return null;
		}
		return null;
	}
	
	@Override
	public PlaceEntity getByCyrilicNameForInsert(String name) {
		Query query = getEntityManager().createQuery("select ple from PlaceEntity ple where UPPER(ple.nameCyrilic) = UPPER(:name)");
		query.setParameter("name", name);
		try {
			Object object = query.getSingleResult();
			if(object instanceof PlaceEntity) {
				PlaceEntity pe = (PlaceEntity)object;
				return pe;
			}
		} catch(NoResultException nre) {
			return null;
		} catch(NonUniqueResultException nure) {
			return null;
		}
		return null;
	}

	@Override
	public List<String> getAllPlacesNamesByLanguageAndName(String language, String name) {
		List<String> results = new ArrayList<String>();
		String queryStr = "select ple.name from PlaceEntity ple where (LOWER(ple.name) like LOWER(:name) or ple.nameCyrilic like :name) order by importance desc";
		Query query = getEntityManager().createQuery(queryStr);
		query.setParameter("name",  name + "%");
		query.setMaxResults(10);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}
	
	@Override
	public List<String> getAllPlacesCyrillicNamesByName(String language, String name) {
		List<String> results = new ArrayList<String>();
		String queryStr = "select ple.nameCyrilic from PlaceEntity ple where (LOWER(ple.name) like LOWER(:name) or ple.nameCyrilic like :name) order by importance desc";
		Query query = getEntityManager().createQuery(queryStr);
		query.setParameter("name",  name + "%");
		query.setMaxResults(10);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}

	@Override
	public List<String> getAllPlacesNamesByLanguageAndNameAndMask(String language, String name) {
		List<String> results = new ArrayList<String>();
		String queryStr = "select ple.name from PlaceEntity ple where (LOWER(ple.name) like LOWER(:name) or ple.nameCyrilic like :name) order by importance desc";
		Query query = getEntityManager().createQuery(queryStr);
		query.setParameter("name",  "%" + name + "%");
		query.setMaxResults(10);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}
	
	@Override
	public List<String> getAllPlacesCyrillicNamesByNameAndMask(String language, String name) {
		List<String> results = new ArrayList<String>();
		String queryStr = "select ple.nameCyrilic from PlaceEntity ple where (LOWER(ple.name) like LOWER(:name) or ple.nameCyrilic like :name) order by importance desc";
		Query query = getEntityManager().createQuery(queryStr);
		query.setParameter("name",  "%" + name + "%");
		query.setMaxResults(10);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}

	@Override
	public List<String> getCommonPlaces(String language) {
		List<String> results = new ArrayList<String>();
		String queryStr = "select ple.name from PlaceEntity ple";
		if("MK".equals(language)) {
			queryStr = "select ple.nameCyrilic from PlaceEntity ple";
		}
		Query query = getEntityManager().createQuery(queryStr);
		query.setMaxResults(5);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}

	@Override
	public PlaceEntity getByName(String name) {
		Query query = getEntityManager().createQuery("select ple from PlaceEntity ple where ple.name = :name");
		query.setParameter("name", name);
		try {
			Object object = query.getSingleResult();
			if(object instanceof PlaceEntity) {
				PlaceEntity pe = (PlaceEntity)object;
				return pe;
			}
		} catch(NoResultException nre) {
			//logger.info("getByCyrilicName() - nre name: " + name);
			return null;
		} catch(NonUniqueResultException nure) {
			//logger.info("getByCyrilicName() - nure name: " + name);
			return null;
		}
		return null;
	}

}
