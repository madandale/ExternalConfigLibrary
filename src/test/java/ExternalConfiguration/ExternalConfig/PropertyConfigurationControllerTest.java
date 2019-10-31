package ExternalConfiguration.ExternalConfig;



import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class PropertyConfigurationControllerTest {

	
	
	
	
	
	
	@Test
	public void testGetPropertyValue() 
	{
		
	//String filePath = "file:///Users/madandale/Documents/Silenium/config/application.properties";
		
		PropertyConfigurationController config = new PropertyConfigurationController(2);

		String configValue = config.getPropertyValue("app.message",String.class);
				
		Assert.assertEquals("This is the primary chnaged for testing", configValue );
		
		
	}
	
	@Test
	public void testGetAllProperties() 
	{
		
	//String filePath = "file:///Users/madandale/Documents/Silenium/config/application.properties";
		
		PropertyConfigurationController config = new PropertyConfigurationController(2);

		Map<String, Object> configValue = config.getAllProperties();
		
		System.out.println("configuration result" + configValue.size());
				
		Assert.assertEquals(37, configValue.size() );
		
		
	}
	
	
	

	
}
