package ExternalConfiguration.ExternalConfig;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.netflix.archaius.readers.JDCConfigReader;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.sources.JDBCConfigurationSource;

public class JDBCConfigController {

	
	 DynamicConfiguration configuration = null;
	
	public  JDBCConfigController(DataSource dataSource, String query,
			String keyColumnName, String valueColumnName) {
        try {
        	
            JDBCConfigurationSource source = new JDBCConfigurationSource(dataSource,
            		 query,
        			 keyColumnName,valueColumnName);
            
            FixedDelayPollingScheduler scheduler = new FixedDelayPollingScheduler(
                    0, 10, false);
             configuration = new DynamicConfiguration(source,
                    scheduler);
          //  DynamicPropertyFactory.initWithConfigurationSource(configuration);
            
    
        } catch (Exception e) {
        }

	}
	
	public Map<String, Object> getAllProperties() {
		Map<String, Object> propertyMap = new LinkedHashMap<String, Object>();

		Iterator keys = configuration.getKeys();
				 while (keys.hasNext()) {
					 String key =  (String) keys.next();
					 propertyMap.put(key,  configuration.getProperty(key));
				 }
				 
		 return propertyMap;
	}
	
	public Map<String, String> readJDBCPropertyByKey(String propertyKey) {
		Map<String, String> properties = new LinkedHashMap<String, String>();
		//DynamicStringProperty dynamicProperty = DynamicPropertyFactory.getInstance().getStringProperty(propertyKey, "default");

		String propertyValue =(String) configuration.getProperty(propertyKey);
		properties.put(propertyKey, propertyValue);
		return properties;

	}
	
	
	
}
