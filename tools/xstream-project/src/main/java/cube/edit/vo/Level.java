package cube.edit.vo;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Level {
	@XStreamAsAttribute
	String approxRowCount;
	@XStreamAsAttribute
	String name;
	@XStreamAsAttribute
	String visible;
	@XStreamAsAttribute
	String table;
	@XStreamAsAttribute
	String column;
	@XStreamAsAttribute
	String nameColumn;
	@XStreamAsAttribute
	String ordinalColumn;
	@XStreamAsAttribute
	String parentColumn;
	@XStreamAsAttribute
	String nullParentValue;
	@XStreamAsAttribute
	String type;
	@XStreamAsAttribute
	String internalType;
	@XStreamAsAttribute
	String uniqueMembers;
	@XStreamAsAttribute
	String levelType;
	@XStreamAsAttribute
	String hideMemberIf;
	@XStreamAsAttribute
	String formatter;
	@XStreamAsAttribute
	String caption;
	@XStreamAsAttribute
	String description;
	@XStreamAsAttribute
	String captionColumn;
	@XStreamAsAttribute
	String annotations;

	public String getApproxRowCount() {
		return approxRowCount;
	}

	public void setApproxRowCount(String approxRowCount) {
		this.approxRowCount = approxRowCount;
	}

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

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}

	public String getOrdinalColumn() {
		return ordinalColumn;
	}

	public void setOrdinalColumn(String ordinalColumn) {
		this.ordinalColumn = ordinalColumn;
	}

	public String getParentColumn() {
		return parentColumn;
	}

	public void setParentColumn(String parentColumn) {
		this.parentColumn = parentColumn;
	}

	public String getNullParentValue() {
		return nullParentValue;
	}

	public void setNullParentValue(String nullParentValue) {
		this.nullParentValue = nullParentValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInternalType() {
		return internalType;
	}

	public void setInternalType(String internalType) {
		this.internalType = internalType;
	}

	public String getUniqueMembers() {
		return uniqueMembers;
	}

	public void setUniqueMembers(String uniqueMembers) {
		this.uniqueMembers = uniqueMembers;
	}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	public String getHideMemberIf() {
		return hideMemberIf;
	}

	public void setHideMemberIf(String hideMemberIf) {
		this.hideMemberIf = hideMemberIf;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
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

	public String getCaptionColumn() {
		return captionColumn;
	}

	public void setCaptionColumn(String captionColumn) {
		this.captionColumn = captionColumn;
	}

	public String getAnnotations() {
		return annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}

	public String toString() {
		return BeanUtil.buildString(this, "      ");
	}
}
