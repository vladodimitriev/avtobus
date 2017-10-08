package mk.mladen.avtobusi.dao.impl;

import mk.mladen.avtobusi.dao.BusLineDao;
import mk.mladen.avtobusi.entity.BusLineEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository(value = "busLineDao")
public class BusLineDaoImpl extends GenericDaoImpl<BusLineEntity> implements BusLineDao {
	
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(BusLineDaoImpl.class);

	public BusLineDaoImpl() {
		super(BusLineEntity.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BusLineEntity> find(String departure, String destination) {
		Query query = getEntityManager().createQuery("select ble from BusLineEntity ble where (ble.departure.name like :departure or ble.departure.nameCyrilic like :departure) and (ble.destination.name like :destination or ble.destination.nameCyrilic like :destination)");
		query.setParameter("departure", "%" + departure + "%");
		query.setParameter("destination", "%" + destination + "%");
		List<BusLineEntity> list = query.getResultList();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BusLineEntity> find(String departure, String destination, String dow) {
		Query query = getEntityManager().createQuery("select ble from BusLineEntity ble where (ble.departure.name like :departure or ble.departure.nameCyrilic like :departure) and (ble.destination.name like :destination or ble.destination.nameCyrilic like :destination) and ble.operationDays like :operationDays");
		query.setParameter("departure", "%" + departure + "%");
		query.setParameter("destination", "%" + destination + "%");
		query.setParameter("operationDays", "%" + dow + "%");
		List<BusLineEntity> list = query.getResultList();
		return list;
	}

	@Override
	public long countBitolaSkopje() {
		Query query = getEntityManager().createQuery("select count(ble) from BusLineEntity ble where (ble.departure.name like :departure) and (ble.destination.name like :destination)");
		query.setParameter("departure", "%Bitola%");
		query.setParameter("destination", "%Skopje%");
		Object obj = query.getSingleResult();
		if(obj instanceof Long) {
			Long ll = (Long)obj;
			return ll.longValue();
		}
		return 0;
	}

	@Override
	public void delete(int id) {
		Query query = getEntityManager().createQuery("delete from BusLineEntity ble where ble.id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
	}

}
