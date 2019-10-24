package ExternalConfiguration.ExternalConfig;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.sources.JDBCConfigurationSource;

public class JDBCConfigController {

	
	
	public  JDBCConfigController(DataSource dataSource, String query,
			String keyColumnName, String valueColumnName) {
        try {
            JDBCConfigurationSource source = new JDBCConfigurationSource(dataSource,
            		 query,
        			 keyColumnName,valueColumnName);
            
            FixedDelayPollingScheduler scheduler = new FixedDelayPollingScheduler(
                    0, 10, false);
            DynamicConfiguration configuration = new DynamicConfiguration(source,
                    scheduler);
            DynamicPropertyFactory.initWithConfigurationSource(configuration);
    
        } catch (Exception e) {
        }

	}
	
	public Map<String, String> readJDBCPropertyByKey(String propertyKey) {
		Map<String, String> properties = new LinkedHashMap<String, String>();
		DynamicStringProperty dynamicProperty = DynamicPropertyFactory.getInstance().getStringProperty(propertyKey, "default");

		properties.put(dynamicProperty.getName(), dynamicProperty.get());
		return properties;

	}
	
	
	
}
