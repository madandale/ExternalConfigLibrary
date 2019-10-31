package ExternalConfiguration.ExternalConfig;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.Environment;

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

	public LogConfigurationController(long pollingTimeInterval)  {

		String	propertyDir = "file://"+ System.getProperty("user.home") + File.separator + "application.properties";

		//		String operSys = System.getProperty("os.name").toLowerCase();
//       if (operSys.contains("nix") || operSys.contains("nux")
//                || operSys.contains("aix")) {
//        	homeDir = System.getProperty("user.home");
//        } else if (operSys.contains("mac")) {
//        	homeDir = System.getProperty("user.home");
//        } else {
//        	homeDir = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
//        }
//        

		long interval = (pollingTimeInterval ==0)?1:pollingTimeInterval;
		
		config = new DefaultLayeredConfig();
		factory = new DefaultPropertyFactory(config);
		
		pollingDynamicConfig = new  PollingDynamicConfig(
				new URLConfigReader(propertyDir), 
				new FixedPollingStrategy(interval, TimeUnit.SECONDS));
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
	
	  public enum OS {
          WINDOWS, LINUX, MAC, SOLARIS
      };// Operating systems.

}
