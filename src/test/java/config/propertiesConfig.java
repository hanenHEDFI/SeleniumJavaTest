package config;

import java.io.FileInputStream;
/*
 * @author hanen.hedfi
 */


import java.io.InputStream;
import java.util.Properties;

import Test.GeneralTest;

public class propertiesConfig {

	static Properties prop = new Properties();

	/*
	 * *************Get Properties
	 */
	public static void getProp() {

		try {
			InputStream input = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/config.properties");
			prop.load(input);
			GeneralTest.url = prop.getProperty("url");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	
	}

