package mk.mladen.avtobusi.service.impl;

import mk.mladen.avtobusi.dao.CountryDao;
import mk.mladen.avtobusi.entity.CountryEntity;
import mk.mladen.avtobusi.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	@Override
	public List<CountryEntity> findAll() {
		return countryDao.getAll();
	}

	@Override
	public CountryEntity findDefaultCountry() {
		return countryDao.getById(1);
	}
}
