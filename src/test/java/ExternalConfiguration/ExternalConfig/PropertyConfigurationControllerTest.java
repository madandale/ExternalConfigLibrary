package ExternalConfiguration.ExternalConfig;



import org.junit.Test;

import junit.framework.Assert;

public class PropertyConfigurationControllerTest {

	
	
	
	@Test
	public void testGetPropertyValue() 
	{
		
	String filePath = "file:///Users/madandale/Documents/Silenium/config/application.properties";
		
		PropertyConfigurationController config = new PropertyConfigurationController(filePath);

		String configValue = config.getPropertyValue("app.message",String.class);
		
		Assert.assertEquals("This is the primary chnaged for testing", configValue );
	}
	
}
