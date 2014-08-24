package util.fileUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUitil {

	/**
	 * get properties file object by file path
	 * @throws FileNotFoundException 
	 */
	public static Properties getPropertiesFile(String filePath) throws FileNotFoundException{
		Properties prop = new Properties();   
		InputStream in = new BufferedInputStream (new FileInputStream(filePath));  
        try {   
            prop.load(in); 
            return prop;
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        return prop;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Properties prop = PropertiesUitil.getPropertiesFile("E:/workspace/narutom/Narutom/src/config/dbconfig.properties");
		System.out.println(prop.getProperty("url"));
//		System.out.println(System.getProperty("user.dir"));
	}
}
