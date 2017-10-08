package mk.mladen.avtobusi.service;

import mk.mladen.avtobusi.beans.AddBean;
import mk.mladen.avtobusi.beans.UpdateBean;
import mk.mladen.avtobusi.dto.BusLineDto;

import java.util.List;

public interface BusLineService {

	public List<BusLineDto> getRelation(String departure, String destination);
	
	public List<BusLineDto> getRelation(String departure, String destination, String date);

    public void deleteBusLine(String id);

    public void updateBusLine(UpdateBean bean);

    public void addNewBusLine(AddBean bean);
}
