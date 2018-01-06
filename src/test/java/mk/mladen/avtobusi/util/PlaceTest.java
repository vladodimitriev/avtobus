package mk.mladen.avtobusi.util;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import mk.mladen.avtobusi.dto.BusLineDto;

public class PlaceTest {
	
	@Test
	@Ignore
	public void sortTest() {
		List<BusLineDto> result = new ArrayList<BusLineDto>();
		BusLineDto dto = new BusLineDto();
		dto.setCarrier("DAJO TURS");
		dto.setDepartureTime("01:23");
		result.add(dto);
		
		dto = new BusLineDto();
		dto.setCarrier("DAJO TURS");
		dto.setDepartureTime("04:23");
		result.add(dto);
		
		dto = new BusLineDto();
		dto.setCarrier("DAJO TURS");
		dto.setDepartureTime("00:23");
		result.add(dto);
		
		dto = new BusLineDto();
		dto.setCarrier("DAJO TURS");
		result.add(dto);
		
		Collections.sort(result, (p1, p2) -> p1.getDepartureTime() != null ? p1.getDepartureTime().compareTo(p2.getDepartureTime()) : 1);
		result.forEach((res) -> assertNotNull(res.getDepartureTime()));
	}

	@Test
	public void test() {
		String kamenica = "Мак.Каменица";
		kamenica = createCityCN(kamenica);
		assertNotNull(kamenica);
	}
	
	private String createCityCN(String name) {
		name = name.replace(".", " ");
		name = name.replace("-", "");
		return name;
	}
}
