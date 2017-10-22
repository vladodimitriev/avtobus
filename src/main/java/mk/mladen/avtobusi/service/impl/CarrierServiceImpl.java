package mk.mladen.avtobusi.service.impl;

import mk.mladen.avtobusi.dao.CarrierDao;
import mk.mladen.avtobusi.dto.CarrierDto;
import mk.mladen.avtobusi.dto.PlaceDto;
import mk.mladen.avtobusi.entity.CarrierEntity;
import mk.mladen.avtobusi.entity.PlaceEntity;
import mk.mladen.avtobusi.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarrierServiceImpl implements CarrierService {

    @Autowired
    private CarrierDao carrierDao;

    @Override
    public List<String> findAllCarrierNames() {
        return carrierDao.getAllCarrierNames();
    }

    @Override
    public List<CarrierDto> getCarriers(String carrier) {
        List<CarrierDto> result = new ArrayList<CarrierDto>();
        List<CarrierEntity> carriers = carrierDao.getCarriers(carrier);
        if(carriers == null || carriers.isEmpty()) {
            return result;
        }
        for(CarrierEntity ce : carriers) {
            CarrierDto dto = new CarrierDto();
            dto.setId(ce.getId());
            dto.setName(ce.getName());
            dto.setNameCyrilic(ce.getNameCyrilic());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<CarrierDto> getAllCarriers() {
        List<CarrierDto> result = new ArrayList<CarrierDto>();
        List<CarrierEntity> carriers = carrierDao.getAll();
        if(carriers == null || carriers.isEmpty()) {
            return result;
        }
        for(CarrierEntity ce : carriers) {
            CarrierDto dto = new CarrierDto();
            dto.setId(ce.getId());
            dto.setName(ce.getName());
            dto.setNameCyrilic(ce.getNameCyrilic());
            result.add(dto);
        }
        return result;
    }

    @Override
    public void updateCarrier(CarrierDto placeDto) {
        try {
            int id = Integer.valueOf(placeDto.getId()).intValue();
            CarrierEntity ble = carrierDao.getById(id);
            if(ble != null) {
                updateCarrierEntity(ble, placeDto);
                carrierDao.persist(ble);
            }
        }catch(NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCarrier(int id) {
        try {
            int blId = Integer.valueOf(id);
            carrierDao.delete(blId);
        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    private void updateCarrierEntity(CarrierEntity place, CarrierDto placeDto) {
        place.setName(placeDto.getName());
        place.setNameCyrilic(placeDto.getNameCyrilic());
    }
}
