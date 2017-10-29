package mk.mladen.avtobusi.util;

import org.junit.Test;

public class PlaceTest {

	@Test
	public void test() {
		String kamenica = "Мак.Каменица";
		kamenica = createCityCN(kamenica);
		System.out.println(kamenica);
	}
	
	private String createCityCN(String name) {
		name = name.replace(".", " ");
		name = name.replace("-", "");
		return name;
	}
}
