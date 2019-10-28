package ExternalConfiguration.ExternalConfig;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import org.h2.jdbcx.JdbcDataSource;


public class JDBCConfigControllerTest {

	JDBCConfigController config = null;
	
	@Before
	public void setup() {
		
		JdbcDataSource dataSource = new JdbcDataSource();
	      //  dataSource.setUrl("org.h2.Driver");
	        dataSource.setUrl("jdbc:h2:file:~/data/config");
	        dataSource.setUser("sa");
	        dataSource.setPassword("");
	       
	        
		   config = new JDBCConfigController(dataSource,"select distinct property_key, property_value from MySiteProperties",
				   "property_key", "property_value");
		
		
	}
	
	
	@Test
	public void testReadJDBCPropertyByKey() {
		Map<String, String> properyValue = config.readJDBCPropertyByKey("app.message");
		System.out.print("value is == "+ config.readJDBCPropertyByKey("app.message"));
		 Assert.assertEquals("madan".toString(),properyValue.get("app.message"));
	}
	
	@Test
	public void testGetAllProperties() {
		Map<String, Object> properyValue = config.getAllProperties();
		System.out.print("value is == "+ properyValue);
		 Assert.assertEquals(2,properyValue.size());
	}
	
	
	@After 
	public void shutDown() {
		config = null;
	}
	
}
