package mk.mladen.avtobusi.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mk.mladen.avtobusi.dao.BusLineDao;
import mk.mladen.avtobusi.entity.BusLineEntity;

@Transactional
@Repository(value = "busLineDao")
public class BusLineDaoImpl extends GenericDaoImpl<BusLineEntity> implements BusLineDao {

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

}
