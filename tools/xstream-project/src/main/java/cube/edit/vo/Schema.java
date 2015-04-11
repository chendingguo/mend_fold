package cube.edit.vo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Schema")
public class Schema extends BaseVO{
	@XStreamAsAttribute
	String name;
	@XStreamAsAttribute
	String description;
	@XStreamAsAttribute
	String measuresCaption;
	@XStreamAsAttribute
	String defaultRole;
	@XStreamImplicit(itemFieldName="Dimension")
	List<Dimension> dimensions;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMeasuresCaption() {
		return measuresCaption;
	}
	public void setMeasuresCaption(String measuresCaption) {
		this.measuresCaption = measuresCaption;
	}
	public String getDefaultRole() {
		return defaultRole;
	}
	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}
	public List<Dimension> getDimensions() {
		return dimensions;
	}
	public void setDimensions(List<Dimension> dimensions) {
		this.dimensions = dimensions;
	}
	
	

}
