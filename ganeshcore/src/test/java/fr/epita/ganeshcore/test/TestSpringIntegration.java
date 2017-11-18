package fr.epita.ganeshcore.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/applicationContex.xml"})
public class TestSpringIntegration {

	@Inject
	@Named("hello2")
	private String helloFromSpring;
	
	@Inject
	DataSource ds;
	
	private static final Logger LOGGER = LogManager.getLogger( TestSpringIntegration.class);
	
	
	@Test
	public void testSpringHello() {
		LOGGER.info("Spring says: {} ", helloFromSpring);
		
	}
	
	@Test
	public void testSpringDataSource() throws SQLException {
		//given ds (injected)
		
		//when ds.getConnection();
		Connection connection = ds.getConnection();
		
		//then connection.getSchema() should not be null
		LOGGER.info("connection schema: {} ", connection.getSchema() );
		
	}

}
