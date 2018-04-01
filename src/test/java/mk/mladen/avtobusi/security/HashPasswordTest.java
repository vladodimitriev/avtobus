package mk.mladen.avtobusi.security;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPasswordTest {

	@Test
	public void hashTest() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedAdminPassword = passwordEncoder.encode("petre");
		assertNotNull(hashedAdminPassword);
		assertTrue(hashedAdminPassword.length() > 5);
	}
	
}
