package mondrian.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import mondrian.gui.MondrianGuiDef;
import mondrian.gui.MondrianGuiDef.Cube;
import mondrian.gui.SchemaTreeModel;

import org.eigenbase.xom.DOMWrapper;
import org.eigenbase.xom.XOMException;
import org.eigenbase.xom.wrappers.XercesDOMParser;

public class TestSchema {
	public static void main(String[] args) throws FileNotFoundException, XOMException {
		File file=new File("D:/workspace/mondrian-web/src/main/webapp/WEB-INF/FoodMart.bak.xml");
		InputStream in=new FileInputStream(file);
		XercesDOMParser xercesDOMParser=new XercesDOMParser();
		DOMWrapper  domWrapper =xercesDOMParser.parse(in);
		MondrianGuiDef.Schema schema=new MondrianGuiDef.Schema(domWrapper);
		//System.out.println(schema.toXML());
		Cube[] cubes=schema.cubes;
		if(cubes!=null&&cubes.length>0){
			for(Cube cube:cubes){
				//System.out.println(cube.toXML());
			}
		}
		
		SchemaTreeModel treeModel=new SchemaTreeModel(schema);
		int count=treeModel.getChildCount(schema);
		List<Object> childs=treeModel.getChildList(schema);
		if(childs!=null){
			for(Object obj:childs){
				if(obj instanceof Cube){
					Cube cube=(Cube)obj;
					System.out.println(cube.toXML());
				}
			}
		}
	
		System.out.println(count);
	}

}