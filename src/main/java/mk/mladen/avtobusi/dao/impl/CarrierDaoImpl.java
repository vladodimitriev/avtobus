package mk.mladen.avtobusi.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mk.mladen.avtobusi.dao.CarrierDao;
import mk.mladen.avtobusi.entity.CarrierEntity;

@Transactional
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

}
