package com.sbbi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sbbi.obesity.ObesityServer2Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ObesityServer2Application.class)
@WebAppConfiguration
public class ObesityServer2ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
