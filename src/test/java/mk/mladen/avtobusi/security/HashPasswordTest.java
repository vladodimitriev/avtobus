package mk.mladen.avtobusi.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPasswordTest {

	@Test
	public void hashTest() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedAdminPassword = passwordEncoder.encode("petre");
		System.out.println("hashedAdminPassword: " + hashedAdminPassword);
	}
	
}
