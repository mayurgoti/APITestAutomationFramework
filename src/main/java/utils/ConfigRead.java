package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigRead {
	public static Properties readConfigProperties(String filename) {
		
		try {
			filename = filename.trim();
			Properties prop = new Properties();
			InputStream inStream;
			inStream = new FileInputStream(filename);
			prop.load(inStream);
			return prop;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
			
}
