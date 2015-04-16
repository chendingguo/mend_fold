package cube.edit.vo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestSchema {
	static XStream xStream = new XStream(new DomDriver("utf8"));
	
	public static void main(String[] args) {
		xStream.processAnnotations(Schema.class);
		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("Schema.xml");
		Schema schema=xml2Bean(input);
		schema.setId("ddd");
		//System.out.println(BeanUtil.buildString(schema,""));
		JSONObject js=JSONObject.fromObject(schema);
		
		System.out.println(js.toString());
		Map<String, Class> classMap = new HashMap<String, Class>();  
		 
		js=JSONObject.fromObject(js.toString());
		classMap.put("dimensions", Dimension.class); 
		classMap.put("hierarchies", Hierarchy.class); 
		classMap.put("levels", Level.class); 
		  
		
		Schema s=(Schema) JSONObject.toBean(js,Schema.class,classMap);
		for(Dimension dimension:s.getDimensions()){
			System.out.println(dimension.getName());
			List<Hierarchy> hierarchies=dimension.getHierarchies();
			for(Hierarchy hierarchy:hierarchies){
				System.out.println("  "+hierarchy.getPrimaryKey());
				List<Level> levels=hierarchy.getLevels();
				for(Level level:levels){
					System.out.println("      "+level.getColumn());
				}
			}
		}
		
		//System.out.println(xStream.toXML(s));
		
	}

	@SuppressWarnings("unchecked")
	public static <T> T  xml2Bean(InputStream xmlIn) {

		
		Schema cre_schema = (Schema) xStream.fromXML(xmlIn);

		return (T) cre_schema;
	}
}
