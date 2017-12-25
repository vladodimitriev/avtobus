package mk.mladen.avtobusi.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class SearchBeanTest {

	@Test
	public void replaceTest() {
		String departureDate = "26-12-2017";
		departureDate = departureDate.replace("-", "/");
		assertEquals("26/12/2017", departureDate);
	}
	
	@Test
	public void replaceEmptyTest() {
		String departureDate = StringUtils.EMPTY;
		departureDate = departureDate.replace("-", "/");
		assertNotNull(departureDate);
		assertEquals(StringUtils.EMPTY, departureDate);
	}
	
	@Test
	public void replaceAllTest() {
		String departureDate = "26-12-2017";
		departureDate = departureDate.replaceAll("-", "/");
		assertEquals("26/12/2017", departureDate);
	}
}
