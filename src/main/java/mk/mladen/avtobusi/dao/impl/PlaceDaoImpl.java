package mk.mladen.avtobusi.dao.impl;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mk.mladen.avtobusi.dao.PlaceDao;
import mk.mladen.avtobusi.entity.PlaceEntity;

@Transactional
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
			long longValue = ll.longValue();
			if(logger.isInfoEnabled()){
				logger.info("count longValue: " + longValue);
			}
			return longValue;
		}
		return 0;
	}

}
