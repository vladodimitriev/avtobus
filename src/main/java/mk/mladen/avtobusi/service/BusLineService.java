package mk.mladen.avtobusi.service;

import java.util.List;

import mk.mladen.avtobusi.dto.BusLineDto;

public interface BusLineService {

	public List<BusLineDto> getRelation(String departure, String destination);
	
	public List<BusLineDto> getRelation(String departure, String destination, String date);
	
}
