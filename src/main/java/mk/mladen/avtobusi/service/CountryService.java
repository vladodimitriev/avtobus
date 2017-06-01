package mk.mladen.avtobusi.service;

import java.util.List;

import mk.mladen.avtobusi.entity.CountryEntity;

public interface CountryService {

	public List<CountryEntity> findAll();
	
	public CountryEntity findDefaultCountry();
}
