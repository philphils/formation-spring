package org.formation.spring.core.persistence.database;

import static org.junit.Assert.assertEquals;

import org.formation.spring.core.CoreApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CoreApplication.class)
public class CacheDatabaseTest {

	@Autowired
	private CacheDatabase cacheDatabase;

	@Test
	public void testSetupDestroy() {

		assertEquals(3, cacheDatabase.getSecteurs().size());

	}

}
