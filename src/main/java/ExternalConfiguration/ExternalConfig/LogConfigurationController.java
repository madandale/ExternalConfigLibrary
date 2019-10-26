package ExternalConfiguration.ExternalConfig;

import java.util.Iterator;
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

	public DefaultPropertyFactory factory = null;
	public DefaultLayeredConfig config = null;
	public PollingDynamicConfig pollingDynamicConfig = null;

	public LogConfigurationController(String logFileSourcePath) {
		config = new DefaultLayeredConfig();
		factory = new DefaultPropertyFactory(config);
		
		pollingDynamicConfig = new  PollingDynamicConfig(
				new URLConfigReader(logFileSourcePath), 
				new FixedPollingStrategy(1, TimeUnit.SECONDS));
		config.addConfig(Layers.REMOTE,pollingDynamicConfig);
	}
	
	public Map<String, Object> getAllProperties() {
		Map<String, Object> propertyMap = new LinkedHashMap<String, Object>();

		Iterator<String> keys = pollingDynamicConfig.getKeys();
				 while (keys.hasNext()) {
					 String key = keys.next();
					 propertyMap.put(key,  pollingDynamicConfig.getRawProperty(key));
				 }
				 
		 return propertyMap;
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
