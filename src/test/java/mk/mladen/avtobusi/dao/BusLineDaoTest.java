package mk.mladen.avtobusi.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import mk.mladen.avtobusi.ApplicationTestConfig;
import mk.mladen.avtobusi.entity.BusLineEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationTestConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class BusLineDaoTest {

	@Autowired
	private BusLineDao busLineDao;
	
	@Test
	@DatabaseSetup("BusLineSampleData.xml")
	public void getAllTest() {
		List<BusLineEntity> busLines = busLineDao.getAll();
		assertNotNull(busLines);
	}
}
