package mk.mladen.avtobusi.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mk.mladen.avtobusi.dao.CountryDao;
import mk.mladen.avtobusi.entity.CountryEntity;

@Transactional
@Repository(value = "countryDao")
public class CountryDaoImpl extends GenericDaoImpl<CountryEntity> implements CountryDao {

	public CountryDaoImpl() {
		super(CountryEntity.class);
	}

}
