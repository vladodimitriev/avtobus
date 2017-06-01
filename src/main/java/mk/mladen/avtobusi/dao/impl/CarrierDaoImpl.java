package mk.mladen.avtobusi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mk.mladen.avtobusi.dao.CarrierDao;
import mk.mladen.avtobusi.entity.CarrierEntity;

@Transactional
@Repository(value = "carrierDao")
public class CarrierDaoImpl extends GenericDaoImpl<CarrierEntity> implements CarrierDao {

	public CarrierDaoImpl() {
		super(CarrierEntity.class);
	}

}
