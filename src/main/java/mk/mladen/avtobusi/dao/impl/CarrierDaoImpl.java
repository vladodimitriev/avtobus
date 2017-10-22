package mk.mladen.avtobusi.dao.impl;

import mk.mladen.avtobusi.dao.CarrierDao;
import mk.mladen.avtobusi.entity.CarrierEntity;
import mk.mladen.avtobusi.entity.PlaceEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "carrierDao")
public class CarrierDaoImpl extends GenericDaoImpl<CarrierEntity> implements CarrierDao {
	
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(CarrierDaoImpl.class);

	public CarrierDaoImpl() {
		super(CarrierEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CarrierEntity getByCarrierName(String carrier) {
		Query query = getEntityManager().createQuery("select ce from CarrierEntity ce where ce.nameCyrilic like :name");
		query.setParameter("name", "%" + carrier + "%");
		try {
			Object object = query.getSingleResult();
			if(object instanceof CarrierEntity) {
				CarrierEntity ce = (CarrierEntity)object;
				return ce;
			}
		} catch(NoResultException nre) {
			return null;
		} catch(NonUniqueResultException nure) {
			List<Object> list = query.getResultList();
			Object object = list.get(0);
			if(object instanceof CarrierEntity) {
				CarrierEntity ce = (CarrierEntity)object;
				return ce;
			}
			return null;
		}
		return null;
	}

	@Override
	public long countAll() {
		Query query = getEntityManager().createQuery("select count(ple) from CarrierEntity ple");
		Object obj = query.getSingleResult();
		if(obj instanceof Long) {
			Long ll = (Long)obj;
			return ll.longValue();
		}
		return 0;
	}

	@Override
	public List<String> getAllCarrierNames() {
		List<String> results = new ArrayList<String>();
		Query query = getEntityManager().createQuery("select ce.name from CarrierEntity ce order by ce.name asc");
		query.setMaxResults(100);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}

	@Override
	public CarrierEntity getByName(String carrier) {
		Query query = getEntityManager().createQuery("select ple from CarrierEntity ple where ple.name = :name");
		query.setParameter("name", carrier);
		try {
			Object object = query.getSingleResult();
			if(object instanceof CarrierEntity) {
				CarrierEntity ce = (CarrierEntity)object;
				return ce;
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

	@Override
	public List<CarrierEntity> getCarriers(String carrier) {
		List<CarrierEntity> results = new ArrayList<CarrierEntity>();
		Query query = getEntityManager().createQuery("select ple from CarrierEntity ple where (ple.name like :name or ple.nameCyrilic like :name) order by ple.name desc");
		query.setParameter("name", "%" + carrier + "%");
		query.setMaxResults(100);
		Object object = query.getResultList();
		if(object instanceof List) {
			results = (List)object;
		}
		return results;
	}

}
