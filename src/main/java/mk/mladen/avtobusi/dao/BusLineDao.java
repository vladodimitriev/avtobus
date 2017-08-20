package mk.mladen.avtobusi.dao;

import java.util.List;

import mk.mladen.avtobusi.entity.BusLineEntity;

public interface BusLineDao extends GenericDao<BusLineEntity> {

	public List<BusLineEntity> find(String departure, String destination);

	public List<BusLineEntity> find(String departure, String destination, String dow);

	public long countBitolaSkopje();
}
