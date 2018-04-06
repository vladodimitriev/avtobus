package mk.mladen.avtobusi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mk.mladen.avtobusi.ApplicationTestConfig;
import mk.mladen.avtobusi.beans.AddBean;
import mk.mladen.avtobusi.beans.UpdateBean;
import mk.mladen.avtobusi.dao.BusLineDao;
import mk.mladen.avtobusi.entity.BusLineEntity;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationTestConfig.class})
public class BusLineServiceTest {

	@Autowired
	private BusLineService busLineService;

	@Autowired
	private BusLineDao busLineDao;
	
    @Ignore
	@Test
	public void dateConverterTest() {
		final String dateString = "18/06/2017";
		Date date = new Date();
		if(StringUtils.isNotBlank(dateString)) {
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		assertNotNull(dateString);
		assertNotNull(date);
	}
    @Ignore
	@Test
	public void updateTest() {
		AddBean aaBean = new AddBean();
		aaBean.setId(""+100);
		aaBean.setComment("AKO");
		busLineService.addNewBusLine(aaBean);

		List<BusLineEntity> bles = busLineDao.getAll();
		assertNotNull(bles);
		assertFalse(bles.isEmpty());

		UpdateBean ub = new UpdateBean();
		int id = bles.get(0).getId();
		ub.setId(""+id);
		ub.setComment("AMA AKO");
		busLineService.updateBusLine(ub);

		BusLineEntity ble = busLineDao.getById(id);
		assertNotNull(ble);
		assertEquals("AMA AKO", ble.getComment());
	}
}
