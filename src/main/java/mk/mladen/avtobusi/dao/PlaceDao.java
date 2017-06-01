package mk.mladen.avtobusi.dao;

import mk.mladen.avtobusi.entity.PlaceEntity;

public interface PlaceDao extends GenericDao<PlaceEntity> {

	public long countAll();

}
