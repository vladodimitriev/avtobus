package mk.mladen.avtobusi.dao;

import mk.mladen.avtobusi.entity.CarrierEntity;

public interface CarrierDao extends GenericDao<CarrierEntity> {

	CarrierEntity getByCarrierName(String carrier);

}
