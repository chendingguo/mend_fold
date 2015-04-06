package com.livemore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CommonUtils {
	static Map<Long,String> categoryMap=new HashMap<Long,String>();
	static{
		
		categoryMap.put(1L, "手机壳");
		categoryMap.put(2L, "小饰品");
		categoryMap.put(3L, "摆台");
		categoryMap.put(4L, "版画");
		categoryMap.put(5L, "拼图");
	}
	public static String getProperties(String key) {
		Map<String, String> propsMap = new HashMap<String, String>();
		Properties properties = new Properties();
		InputStream in = CommonUtils.class.getClassLoader()
				.getResourceAsStream("application-config.properties");

		try {
			properties.load(in);
			Set keySet = properties.keySet();
			Iterator it = keySet.iterator();
			while (it.hasNext()) {
				String name = (String) it.next();
				propsMap.put(name, properties.getProperty(name));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return propsMap.get(key);

	}
	
	public static String getCategoryName(long id) {
		
	    return categoryMap.get(id);
		
       
        
        
    }

	

}

