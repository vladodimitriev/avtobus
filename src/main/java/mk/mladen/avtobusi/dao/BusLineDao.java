package mk.mladen.avtobusi.dao;

import mk.mladen.avtobusi.entity.BusLineEntity;

import java.util.List;

public interface BusLineDao extends GenericDao<BusLineEntity> {

	public List<BusLineEntity> find(String departure, String destination);

	public List<BusLineEntity> find(String departure, String destination, String dow);

	public long countBitolaSkopje();
}
