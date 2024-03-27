package org.formation.spring.core;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.formation.spring.core.CoreApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CoreApplication.class)
public class TestProdProfile {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void testDatasource() {

		// Vérifie la présence d'un bean de type DataSource au sein du contexte Spring
		assertNotNull(applicationContext.getBean(DataSource.class));

	}

}
