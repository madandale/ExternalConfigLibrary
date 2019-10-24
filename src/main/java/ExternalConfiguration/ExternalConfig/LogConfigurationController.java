package ExternalConfiguration.ExternalConfig;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.Layers;
import com.netflix.archaius.config.DefaultLayeredConfig;
import com.netflix.archaius.config.PollingDynamicConfig;
import com.netflix.archaius.config.polling.FixedPollingStrategy;
import com.netflix.archaius.readers.URLConfigReader;

public class LogConfigurationController {

	
	public DefaultLayeredConfig config = new DefaultLayeredConfig();
	public DefaultPropertyFactory factory = new DefaultPropertyFactory(config);
	
	

	public LogConfigurationController(String sourceFilePath) {	
		config.addConfig(Layers.REMOTE, new PollingDynamicConfig(
				new URLConfigReader(sourceFilePath), 
				new FixedPollingStrategy(1, TimeUnit.SECONDS)));
	}
	
	public <T> String getPropertyValue(String Key, Class<T> type) {	
		return (String)factory.get(Key,type).get();
	}
	


	public Map<String, String> readConfigByKey(String propertyKey) {
		Map<String, String> properties = new LinkedHashMap<String, String>();

		properties.put(propertyKey, factory.get(propertyKey,String.class).get());
		return properties;

	}
}
