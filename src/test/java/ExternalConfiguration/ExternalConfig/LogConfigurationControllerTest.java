package ExternalConfiguration.ExternalConfig;

import org.junit.Test;

import junit.framework.Assert;

public class LogConfigurationControllerTest {

	
	
	@Test
	public void testGetPropertyValue() 
	{
		
	String filePath = "file:///Users/madandale/Documents/Silenium/config/log.properties";
		
		LogConfigurationController config = new LogConfigurationController(filePath);

		String configValue = config.getPropertyValue("app.message",String.class);
		
		Assert.assertEquals("This is the log property data", configValue );
	}
	
	
}
