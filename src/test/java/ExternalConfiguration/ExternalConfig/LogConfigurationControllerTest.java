package ExternalConfiguration.ExternalConfig;

import java.net.MalformedURLException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class LogConfigurationControllerTest {

	
	
	@Test
	public void testGetPropertyValue()  
	{
		
	//String filePath = "file:///Users/madandale/Documents/Silenium/config/log.properties";
		
		LogConfigurationController config = new LogConfigurationController(1);

		String configValue = config.getPropertyValue("app.message",String.class);
		
		Assert.assertEquals("This is the primary chnaged for testing", configValue );
	}
	
	@Test
	public void testGetAllProperties()  
	{
		
	//String filePath = "file:///Users/madandale/Documents/Silenium/config/log.properties";
		
		LogConfigurationController config = new LogConfigurationController(0);

		Map<String, Object> configValue = config.getAllProperties();
		
		Assert.assertEquals(37, configValue.size() );
	}
	
	
}
