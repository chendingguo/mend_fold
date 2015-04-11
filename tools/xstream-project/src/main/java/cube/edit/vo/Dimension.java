package cube.edit.vo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("Dimension")
public class Dimension {
	@XStreamAsAttribute
	String name;
	@XStreamImplicit(itemFieldName="Hierarchy")
	List<Hierarchy> hierarchies;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Hierarchy> getHierarchies() {
		return hierarchies;
	}
	public void setHierarchies(List<Hierarchy> hierarchies) {
		this.hierarchies = hierarchies;
	}

	public String toString(){
		return BeanUtil.buildString(this,"  ");
	}
}
