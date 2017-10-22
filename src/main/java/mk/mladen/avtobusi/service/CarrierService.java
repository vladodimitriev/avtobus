package mk.mladen.avtobusi.service;

import mk.mladen.avtobusi.dto.CarrierDto;

import java.util.List;

public interface CarrierService {

    public List<String> findAllCarrierNames();

    public List<CarrierDto> getCarriers(String carrier);

    public List<CarrierDto> getAllCarriers();

    public void updateCarrier(CarrierDto dto);

    public void deleteCarrier(int id);
}
