package mk.mladen.avtobusi.dao;

import mk.mladen.avtobusi.entity.CarrierEntity;

import java.util.List;

public interface CarrierDao extends GenericDao<CarrierEntity> {

	CarrierEntity getByCarrierName(String carrier);

	long countAll();

    List<String> getAllCarrierNames();

	CarrierEntity getByName(String carrier);
}
