package mk.mladen.avtobusi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mk.mladen.avtobusi.AppConfiguration;
import mk.mladen.avtobusi.beans.AddBean;
import mk.mladen.avtobusi.beans.UpdateBean;
import mk.mladen.avtobusi.dao.BusLineDao;
import mk.mladen.avtobusi.entity.BusLineEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
@TestPropertySource("classpath:application-test.properties")
public class BusLineServiceTest {
	
	private final static Logger logger = Logger.getLogger(BusLineServiceTest.class);

	@Autowired
	private BusLineService busLineService;

	@Autowired
	private BusLineDao busLineDao;
	
	@Before
	public void before() {
	}
	
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
	
	@Test
	public void updateTest() {
		AddBean aaBean = new AddBean();
		aaBean.setComment("AKO");
		System.out.println("AABean before = " + aaBean);
		logger.info("AABean before = " + aaBean);
		busLineService.addNewBusLine(aaBean);
		
		System.out.println("AABean after = " + aaBean);
		logger.info("AABean after = " + aaBean);

		List<BusLineEntity> bles = busLineDao.getAll();
		assertNotNull(bles);
		assertFalse(bles.isEmpty());
		System.out.println("Bus lines count = " + bles.size());
		
		UpdateBean ub = new UpdateBean();
		int id = bles.get(0).getId();
		System.out.println("ID = " + id);
		ub.setId(""+id);
		ub.setComment("AMA AKO");
		busLineService.updateBusLine(ub);

		BusLineEntity ble = busLineDao.getById(id);
		assertNotNull(ble);
		assertEquals("AMA AKO", ble.getComment());
	}
}
