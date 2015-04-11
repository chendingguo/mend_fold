package cube.edit.vo;

import java.io.InputStream;

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
		
		System.out.println(xStream.toXML(schema));
		
	}

	@SuppressWarnings("unchecked")
	public static <T> T  xml2Bean(InputStream xmlIn) {

		
		Schema cre_schema = (Schema) xStream.fromXML(xmlIn);

		return (T) cre_schema;
	}
}
