package mk.mladen.avtobusi.service;

import mk.mladen.avtobusi.entity.CountryEntity;

import java.util.List;

public interface CountryService {

	public List<CountryEntity> findAll();
	
	public CountryEntity findDefaultCountry();
}
