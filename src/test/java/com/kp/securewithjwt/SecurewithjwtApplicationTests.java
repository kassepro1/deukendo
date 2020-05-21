package com.kp.securewithjwt;

import com.kp.securewithjwt.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class SecurewithjwtApplicationTests {
    @Autowired
	private UserService userService;

	@Test
	void contextLoads() {
	}

    @Test
	public void whenTypeProvided_ThenRetrieveBeneficeIsCorrect(){
		Mockito.when(userService.getBenefice("FIN")).thenReturn(100);
		int benef = userService.getBenefice("FIN");
		Assert.assertEquals(benef,100);
	}

}
