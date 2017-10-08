package mk.mladen.avtobusi.service.impl;

import mk.mladen.avtobusi.dao.CarrierDao;
import mk.mladen.avtobusi.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarrierServiceImpl implements CarrierService {

    @Autowired
    private CarrierDao carrierDao;

    @Override
    public List<String> findAllCarrierNames() {
        return carrierDao.getAllCarrierNames();
    }
}
