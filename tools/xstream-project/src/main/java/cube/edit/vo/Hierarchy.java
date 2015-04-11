package cube.edit.vo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Hierarchy")
public class Hierarchy {
	@XStreamAsAttribute
	String name;
	@XStreamAsAttribute
	String visible;
	@XStreamAsAttribute
	String hasAll;
	@XStreamAsAttribute
	String allMemberName;
	@XStreamAsAttribute
	String allMemberCaption;
	@XStreamAsAttribute
	String allLevelName;
	@XStreamAsAttribute
	String primaryKey;
	@XStreamAsAttribute
	String primaryKeyTable;
	@XStreamAsAttribute
	String defaultMember;
	@XStreamAsAttribute
	String memberReaderClass;
	@XStreamAsAttribute
	String caption;
	@XStreamAsAttribute
	String description;
	@XStreamAsAttribute
	String uniqueKeyLevelName;
	@XStreamAlias("Table")
	Table table;
	@XStreamImplicit(itemFieldName = "Level")
	List<Level> levels;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getHasAll() {
		return hasAll;
	}

	public void setHasAll(String hasAll) {
		this.hasAll = hasAll;
	}

	public String getAllMemberName() {
		return allMemberName;
	}

	public void setAllMemberName(String allMemberName) {
		this.allMemberName = allMemberName;
	}

	public String getAllMemberCaption() {
		return allMemberCaption;
	}

	public void setAllMemberCaption(String allMemberCaption) {
		this.allMemberCaption = allMemberCaption;
	}

	public String getAllLevelName() {
		return allLevelName;
	}

	public void setAllLevelName(String allLevelName) {
		this.allLevelName = allLevelName;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getPrimaryKeyTable() {
		return primaryKeyTable;
	}

	public void setPrimaryKeyTable(String primaryKeyTable) {
		this.primaryKeyTable = primaryKeyTable;
	}

	public String getDefaultMember() {
		return defaultMember;
	}

	public void setDefaultMember(String defaultMember) {
		this.defaultMember = defaultMember;
	}

	public String getMemberReaderClass() {
		return memberReaderClass;
	}

	public void setMemberReaderClass(String memberReaderClass) {
		this.memberReaderClass = memberReaderClass;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUniqueKeyLevelName() {
		return uniqueKeyLevelName;
	}

	public void setUniqueKeyLevelName(String uniqueKeyLevelName) {
		this.uniqueKeyLevelName = uniqueKeyLevelName;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public String toString() {
		return BeanUtil.buildString(this, "    ");
	}

}
