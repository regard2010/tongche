package utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 从.properties文件中读取相关测试数据
 * @author shuailiu
 *
 */
public class PropertiesDataProvider {

	public static String getTestData(String configFile,String key){
		Configuration config = null;
		try {
			config = new PropertiesConfiguration(configFile);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return String.valueOf(config.getProperty(key));
	}
}
