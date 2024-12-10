package org.formation.spring.core;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CoreApplication.class)
@ActiveProfiles("prod")
public class ProfileTest {

	@Autowired(required = false)
	private BasicDataSource basicDataSource;

	@Test
	public void testBasicDataSource() {

		assertNotNull(basicDataSource);

	}

}
