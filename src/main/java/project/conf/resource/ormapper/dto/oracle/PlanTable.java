/**************************************************************************************************
 * Framework Generated DTO Source
 * - PLAN_TABLE - 
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class PlanTable extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String accessPredicates;
	private String ACCESS_PREDICATES;
	private double bytes;
	private String BYTES;
	private double cardinality;
	private String CARDINALITY;
	private double cost;
	private String COST;
	private double cpuCost;
	private String CPU_COST;
	private double depth;
	private String DEPTH;
	private String distribution;
	private String DISTRIBUTION;
	private String filterPredicates;
	private String FILTER_PREDICATES;
	private double id;
	private String ID;
	private double ioCost;
	private String IO_COST;
	private String objectAlias;
	private String OBJECT_ALIAS;
	private double objectInstance;
	private String OBJECT_INSTANCE;
	private String objectName;
	private String OBJECT_NAME;
	private String objectNode;
	private String OBJECT_NODE;
	private String objectOwner;
	private String OBJECT_OWNER;
	private String objectType;
	private String OBJECT_TYPE;
	private String operation;
	private String OPERATION;
	private String optimizer;
	private String OPTIMIZER;
	private String options;
	private String OPTIONS;
	private String other;
	private String OTHER;
	private String otherTag;
	private String OTHER_TAG;
	private String otherXml;
	private String OTHER_XML;
	private double parentId;
	private String PARENT_ID;
	private double partitionId;
	private String PARTITION_ID;
	private String partitionStart;
	private String PARTITION_START;
	private String partitionStop;
	private String PARTITION_STOP;
	private double planId;
	private String PLAN_ID;
	private double position;
	private String POSITION;
	private String projection;
	private String PROJECTION;
	private String qblockName;
	private String QBLOCK_NAME;
	private String remarks;
	private String REMARKS;
	private double searchColumns;
	private String SEARCH_COLUMNS;
	private String statementId;
	private String STATEMENT_ID;
	private double tempSpace;
	private String TEMP_SPACE;
	private double time;
	private String TIME;
	private Date timestamp;
	private String TIMESTAMP;
	private String insertUserName;
	private String INSERT_USER_NAME;
	private String updateUserName;
	private String UPDATE_USER_NAME;

	/**
	 * Constructor
	 */
	@SuppressWarnings("rawtypes")
	public PlanTable() throws Exception {
		Class cls = getClass();
		Field field[] = cls.getDeclaredFields();

		for (int i=0; i<field.length; i++) {
			if (field[i].getType().isInstance(dataSet) || field[i].getType().isInstance(updateColumnsDataSet) || field[i].getName().equals("updateColumnsDataSetHeader") ||
				field[i].getName().equals("FRW_VAR_PRIMARY_KEY") || field[i].getName().equals("FRW_VAR_DATE_COLUMN") ||
				field[i].getName().equals("FRW_VAR_NUMBER_COLUMN") || field[i].getName().equals("FRW_VAR_CLOB_COLUMN") ||
				field[i].getName().equals("FRW_VAR_DEFAULT_COLUMN") || field[i].getName().equals("FRW_VAR_DEFAULT_VALUE") ||
				!CommonUtil.isUpperCaseWithNumeric(CommonUtil.remove(field[i].getName(), "_"))) {
				continue;
			}

			dataSet.addName(field[i].getName());
		}

		dataSet.addRow();
		updateColumnsDataSet.addName(updateColumnsDataSetHeader);
		setFrwVarPrimaryKey("");
		setFrwVarDateColumn("TIMESTAMP");
		setFrwVarNumberColumn("BYTES,CARDINALITY,COST,CPU_COST,DEPTH,ID,IO_COST,OBJECT_INSTANCE,PARENT_ID,PARTITION_ID,PLAN_ID,POSITION,SEARCH_COLUMNS,TEMP_SPACE,TIME");
		setFrwVarClobColumn("OTHER_XML");
		setFrwVarDefaultColumn("");
		setFrwVarDefaultValue("");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getAccessPredicates() {
		return accessPredicates;
	}

	public void setAccessPredicates(String accessPredicates) throws Exception {
		this.accessPredicates = accessPredicates;
		setValueFromAccessor("ACCESS_PREDICATES", accessPredicates);
	}

	public double getBytes() {
		return bytes;
	}

	public void setBytes(double bytes) throws Exception {
		this.bytes = bytes;
		setValueFromAccessor("BYTES", CommonUtil.toString(bytes));
	}

	public double getCardinality() {
		return cardinality;
	}

	public void setCardinality(double cardinality) throws Exception {
		this.cardinality = cardinality;
		setValueFromAccessor("CARDINALITY", CommonUtil.toString(cardinality));
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) throws Exception {
		this.cost = cost;
		setValueFromAccessor("COST", CommonUtil.toString(cost));
	}

	public double getCpuCost() {
		return cpuCost;
	}

	public void setCpuCost(double cpuCost) throws Exception {
		this.cpuCost = cpuCost;
		setValueFromAccessor("CPU_COST", CommonUtil.toString(cpuCost));
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) throws Exception {
		this.depth = depth;
		setValueFromAccessor("DEPTH", CommonUtil.toString(depth));
	}

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) throws Exception {
		this.distribution = distribution;
		setValueFromAccessor("DISTRIBUTION", distribution);
	}

	public String getFilterPredicates() {
		return filterPredicates;
	}

	public void setFilterPredicates(String filterPredicates) throws Exception {
		this.filterPredicates = filterPredicates;
		setValueFromAccessor("FILTER_PREDICATES", filterPredicates);
	}

	public double getId() {
		return id;
	}

	public void setId(double id) throws Exception {
		this.id = id;
		setValueFromAccessor("ID", CommonUtil.toString(id));
	}

	public double getIoCost() {
		return ioCost;
	}

	public void setIoCost(double ioCost) throws Exception {
		this.ioCost = ioCost;
		setValueFromAccessor("IO_COST", CommonUtil.toString(ioCost));
	}

	public String getObjectAlias() {
		return objectAlias;
	}

	public void setObjectAlias(String objectAlias) throws Exception {
		this.objectAlias = objectAlias;
		setValueFromAccessor("OBJECT_ALIAS", objectAlias);
	}

	public double getObjectInstance() {
		return objectInstance;
	}

	public void setObjectInstance(double objectInstance) throws Exception {
		this.objectInstance = objectInstance;
		setValueFromAccessor("OBJECT_INSTANCE", CommonUtil.toString(objectInstance));
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) throws Exception {
		this.objectName = objectName;
		setValueFromAccessor("OBJECT_NAME", objectName);
	}

	public String getObjectNode() {
		return objectNode;
	}

	public void setObjectNode(String objectNode) throws Exception {
		this.objectNode = objectNode;
		setValueFromAccessor("OBJECT_NODE", objectNode);
	}

	public String getObjectOwner() {
		return objectOwner;
	}

	public void setObjectOwner(String objectOwner) throws Exception {
		this.objectOwner = objectOwner;
		setValueFromAccessor("OBJECT_OWNER", objectOwner);
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) throws Exception {
		this.objectType = objectType;
		setValueFromAccessor("OBJECT_TYPE", objectType);
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) throws Exception {
		this.operation = operation;
		setValueFromAccessor("OPERATION", operation);
	}

	public String getOptimizer() {
		return optimizer;
	}

	public void setOptimizer(String optimizer) throws Exception {
		this.optimizer = optimizer;
		setValueFromAccessor("OPTIMIZER", optimizer);
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) throws Exception {
		this.options = options;
		setValueFromAccessor("OPTIONS", options);
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) throws Exception {
		this.other = other;
		setValueFromAccessor("OTHER", other);
	}

	public String getOtherTag() {
		return otherTag;
	}

	public void setOtherTag(String otherTag) throws Exception {
		this.otherTag = otherTag;
		setValueFromAccessor("OTHER_TAG", otherTag);
	}

	public String getOtherXml() {
		return otherXml;
	}

	public void setOtherXml(String otherXml) throws Exception {
		this.otherXml = otherXml;
		setValueFromAccessor("OTHER_XML", otherXml);
	}

	public double getParentId() {
		return parentId;
	}

	public void setParentId(double parentId) throws Exception {
		this.parentId = parentId;
		setValueFromAccessor("PARENT_ID", CommonUtil.toString(parentId));
	}

	public double getPartitionId() {
		return partitionId;
	}

	public void setPartitionId(double partitionId) throws Exception {
		this.partitionId = partitionId;
		setValueFromAccessor("PARTITION_ID", CommonUtil.toString(partitionId));
	}

	public String getPartitionStart() {
		return partitionStart;
	}

	public void setPartitionStart(String partitionStart) throws Exception {
		this.partitionStart = partitionStart;
		setValueFromAccessor("PARTITION_START", partitionStart);
	}

	public String getPartitionStop() {
		return partitionStop;
	}

	public void setPartitionStop(String partitionStop) throws Exception {
		this.partitionStop = partitionStop;
		setValueFromAccessor("PARTITION_STOP", partitionStop);
	}

	public double getPlanId() {
		return planId;
	}

	public void setPlanId(double planId) throws Exception {
		this.planId = planId;
		setValueFromAccessor("PLAN_ID", CommonUtil.toString(planId));
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) throws Exception {
		this.position = position;
		setValueFromAccessor("POSITION", CommonUtil.toString(position));
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) throws Exception {
		this.projection = projection;
		setValueFromAccessor("PROJECTION", projection);
	}

	public String getQblockName() {
		return qblockName;
	}

	public void setQblockName(String qblockName) throws Exception {
		this.qblockName = qblockName;
		setValueFromAccessor("QBLOCK_NAME", qblockName);
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) throws Exception {
		this.remarks = remarks;
		setValueFromAccessor("REMARKS", remarks);
	}

	public double getSearchColumns() {
		return searchColumns;
	}

	public void setSearchColumns(double searchColumns) throws Exception {
		this.searchColumns = searchColumns;
		setValueFromAccessor("SEARCH_COLUMNS", CommonUtil.toString(searchColumns));
	}

	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) throws Exception {
		this.statementId = statementId;
		setValueFromAccessor("STATEMENT_ID", statementId);
	}

	public double getTempSpace() {
		return tempSpace;
	}

	public void setTempSpace(double tempSpace) throws Exception {
		this.tempSpace = tempSpace;
		setValueFromAccessor("TEMP_SPACE", CommonUtil.toString(tempSpace));
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) throws Exception {
		this.time = time;
		setValueFromAccessor("TIME", CommonUtil.toString(time));
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) throws Exception {
		this.timestamp = timestamp;
		setValueFromAccessor("TIMESTAMP", CommonUtil.toString(timestamp));
	}

	public String getInsertUserName() {
		return insertUserName;
	}

	public void setInsertUserName(String insertUserName) throws Exception {
		this.insertUserName = insertUserName;
		setValueFromAccessor("INSERT_USER_NAME", insertUserName);
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) throws Exception {
		this.updateUserName = updateUserName;
		setValueFromAccessor("UPDATE_USER_NAME", updateUserName);
	}

	/**
	 * Hibernate Methods - If the primary key is composed of multiple columns
	 */
	
	/**
	 * Framework Methods
	 */
	public void setDefaultValue() throws Exception {
		String columns[] = CommonUtil.split(getFrwVarDefaultColumn(), ",");
		String values[] = CommonUtil.split(getFrwVarDefaultValue(), ",");

		if (CommonUtil.isNotEmpty(columns)) {
			for (int i=0; i<columns.length; i++) {
				setValue(columns[i], values[i]);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void setValue(String columnName, String value) throws Exception {
		Class cls = getClass();
		Field field[] = cls.getDeclaredFields();

		dataSet.setValue(dataSet.getRowCnt()-1, columnName, value);
		for (int i=0; i<field.length; i++) {
			if (field[i].getName().equals(CommonUtil.toCamelCaseStartLowerCase(columnName))) {
				if (CommonUtil.containsIgnoreCase(getFrwVarNumberColumn(), columnName)) {
					field[i].set(this, CommonUtil.toDouble(value));
				} else if (CommonUtil.containsIgnoreCase(getFrwVarDateColumn(), columnName)) {
					if (CommonUtil.equalsIgnoreCase(value, "SYSDATE")) {
						field[i].set(this, CommonUtil.toDate(CommonUtil.getSysdate()));
					} else {
						field[i].set(this, CommonUtil.toDate(value));
					}
				} else {
					field[i].set(this, value);
				}
			}
		}
	}

	public void setValues(DataSet dataSet) throws Exception {
		for (int i=0; i<dataSet.getColumnCnt(); i++) {
			setValue(dataSet.getName(i), dataSet.getValue(i));
		}
	}

	private void setValueFromAccessor(String columnName, String value) throws Exception {
		dataSet.setValue(dataSet.getRowCnt()-1, columnName, value);
	}

	public void setConvertedTypeValue(String columnName, String value) throws Exception {
		String numberColumn = "", dateColumn = "";

		numberColumn = getFrwVarNumberColumn();
		dateColumn = getFrwVarDateColumn();

		setValue(columnName, value);

		numberColumn += (CommonUtil.isEmpty(numberColumn)) ? CommonUtil.upperCase(columnName) : "," + CommonUtil.upperCase(columnName);
		dateColumn = CommonUtil.replace(dateColumn, columnName, "");

		setFrwVarNumberColumn(numberColumn);
		setFrwVarDateColumn(dateColumn);
	}

	public String getValue(String columnName) throws Exception {
		return dataSet.getValue(dataSet.getRowCnt()-1, columnName);
	}

	public void addUpdateColumn(String columnName, String columnValue) throws Exception {
		String dataType = "";

		if (CommonUtil.containsIgnoreCase(getFrwVarNumberColumn(), columnName)) {
			dataType = "Number";
		} else if (CommonUtil.containsIgnoreCase(getFrwVarDateColumn(), columnName)) {
			dataType = "Date";
		} else {
			dataType = "String";
		}

		addUpdateColumn(columnName, columnValue, dataType);
	}

	/**
	 * dataType : String / Number / Date
	 */
	public void addUpdateColumn(String columnName, String columnValue, String dataType) throws Exception {
		updateColumnsDataSet.addRow();
		updateColumnsDataSet.setValue(updateColumnsDataSet.getRowCnt()-1, "COLUMN_NAME", columnName);
		updateColumnsDataSet.setValue(updateColumnsDataSet.getRowCnt()-1, "COLUMN_VALUE", columnValue);
		updateColumnsDataSet.setValue(updateColumnsDataSet.getRowCnt()-1, "DATA_TYPE", CommonUtil.nvl(dataType, "String"));
	}

	public void addUpdateColumnFromField() throws Exception {
		for (int i=0; i<dataSet.getColumnCnt(); i++) {
			if (CommonUtil.isNotBlank(dataSet.getValue(i))) {
				if (CommonUtil.equalsIgnoreCase(dataSet.getName(i), "INSERT_DATE") && CommonUtil.equalsIgnoreCase(dataSet.getValue(i), "SYSDATE")) {
					continue;
				}
				addUpdateColumn(dataSet.getName(i), dataSet.getValue(i));
			}
		}
	}

	/**
	 * toString
	 */
	public String toString() {
		String str = "";

		str += "accessPredicates : "+accessPredicates+"\n";
		str += "bytes : "+bytes+"\n";
		str += "cardinality : "+cardinality+"\n";
		str += "cost : "+cost+"\n";
		str += "cpuCost : "+cpuCost+"\n";
		str += "depth : "+depth+"\n";
		str += "distribution : "+distribution+"\n";
		str += "filterPredicates : "+filterPredicates+"\n";
		str += "id : "+id+"\n";
		str += "ioCost : "+ioCost+"\n";
		str += "objectAlias : "+objectAlias+"\n";
		str += "objectInstance : "+objectInstance+"\n";
		str += "objectName : "+objectName+"\n";
		str += "objectNode : "+objectNode+"\n";
		str += "objectOwner : "+objectOwner+"\n";
		str += "objectType : "+objectType+"\n";
		str += "operation : "+operation+"\n";
		str += "optimizer : "+optimizer+"\n";
		str += "options : "+options+"\n";
		str += "other : "+other+"\n";
		str += "otherTag : "+otherTag+"\n";
		str += "otherXml : "+otherXml+"\n";
		str += "parentId : "+parentId+"\n";
		str += "partitionId : "+partitionId+"\n";
		str += "partitionStart : "+partitionStart+"\n";
		str += "partitionStop : "+partitionStop+"\n";
		str += "planId : "+planId+"\n";
		str += "position : "+position+"\n";
		str += "projection : "+projection+"\n";
		str += "qblockName : "+qblockName+"\n";
		str += "remarks : "+remarks+"\n";
		str += "searchColumns : "+searchColumns+"\n";
		str += "statementId : "+statementId+"\n";
		str += "tempSpace : "+tempSpace+"\n";
		str += "time : "+time+"\n";
		str += "timestamp : "+timestamp+"\n";
		str += "insertUserName : "+insertUserName+"\n";
		str += "updateUserName : "+updateUserName+"\n";

		return str;
	}

	/**
	 * toXmlString
	 */
	public String toXmlString() {
		String str = "";

		str += "<column name=\"accessPredicates\" value=\""+accessPredicates+"\">";
		str += "<column name=\"bytes\" value=\""+bytes+"\">";
		str += "<column name=\"cardinality\" value=\""+cardinality+"\">";
		str += "<column name=\"cost\" value=\""+cost+"\">";
		str += "<column name=\"cpuCost\" value=\""+cpuCost+"\">";
		str += "<column name=\"depth\" value=\""+depth+"\">";
		str += "<column name=\"distribution\" value=\""+distribution+"\">";
		str += "<column name=\"filterPredicates\" value=\""+filterPredicates+"\">";
		str += "<column name=\"id\" value=\""+id+"\">";
		str += "<column name=\"ioCost\" value=\""+ioCost+"\">";
		str += "<column name=\"objectAlias\" value=\""+objectAlias+"\">";
		str += "<column name=\"objectInstance\" value=\""+objectInstance+"\">";
		str += "<column name=\"objectName\" value=\""+objectName+"\">";
		str += "<column name=\"objectNode\" value=\""+objectNode+"\">";
		str += "<column name=\"objectOwner\" value=\""+objectOwner+"\">";
		str += "<column name=\"objectType\" value=\""+objectType+"\">";
		str += "<column name=\"operation\" value=\""+operation+"\">";
		str += "<column name=\"optimizer\" value=\""+optimizer+"\">";
		str += "<column name=\"options\" value=\""+options+"\">";
		str += "<column name=\"other\" value=\""+other+"\">";
		str += "<column name=\"otherTag\" value=\""+otherTag+"\">";
		str += "<column name=\"otherXml\" value=\""+otherXml+"\">";
		str += "<column name=\"parentId\" value=\""+parentId+"\">";
		str += "<column name=\"partitionId\" value=\""+partitionId+"\">";
		str += "<column name=\"partitionStart\" value=\""+partitionStart+"\">";
		str += "<column name=\"partitionStop\" value=\""+partitionStop+"\">";
		str += "<column name=\"planId\" value=\""+planId+"\">";
		str += "<column name=\"position\" value=\""+position+"\">";
		str += "<column name=\"projection\" value=\""+projection+"\">";
		str += "<column name=\"qblockName\" value=\""+qblockName+"\">";
		str += "<column name=\"remarks\" value=\""+remarks+"\">";
		str += "<column name=\"searchColumns\" value=\""+searchColumns+"\">";
		str += "<column name=\"statementId\" value=\""+statementId+"\">";
		str += "<column name=\"tempSpace\" value=\""+tempSpace+"\">";
		str += "<column name=\"time\" value=\""+time+"\">";
		str += "<column name=\"timestamp\" value=\""+timestamp+"\">";
		str += "<column name=\"insertUserName\" value=\""+insertUserName+"\">";
		str += "<column name=\"updateUserName\" value=\""+updateUserName+"\">";

		return str;
	}

	/**
	 * toJsonString
	 */
	public String toJsonString() {
		String str = "";

		str += "\"accessPredicates\":\""+accessPredicates+"\", ";
		str += "\"bytes\":\""+bytes+"\", ";
		str += "\"cardinality\":\""+cardinality+"\", ";
		str += "\"cost\":\""+cost+"\", ";
		str += "\"cpuCost\":\""+cpuCost+"\", ";
		str += "\"depth\":\""+depth+"\", ";
		str += "\"distribution\":\""+distribution+"\", ";
		str += "\"filterPredicates\":\""+filterPredicates+"\", ";
		str += "\"id\":\""+id+"\", ";
		str += "\"ioCost\":\""+ioCost+"\", ";
		str += "\"objectAlias\":\""+objectAlias+"\", ";
		str += "\"objectInstance\":\""+objectInstance+"\", ";
		str += "\"objectName\":\""+objectName+"\", ";
		str += "\"objectNode\":\""+objectNode+"\", ";
		str += "\"objectOwner\":\""+objectOwner+"\", ";
		str += "\"objectType\":\""+objectType+"\", ";
		str += "\"operation\":\""+operation+"\", ";
		str += "\"optimizer\":\""+optimizer+"\", ";
		str += "\"options\":\""+options+"\", ";
		str += "\"other\":\""+other+"\", ";
		str += "\"otherTag\":\""+otherTag+"\", ";
		str += "\"otherXml\":\""+otherXml+"\", ";
		str += "\"parentId\":\""+parentId+"\", ";
		str += "\"partitionId\":\""+partitionId+"\", ";
		str += "\"partitionStart\":\""+partitionStart+"\", ";
		str += "\"partitionStop\":\""+partitionStop+"\", ";
		str += "\"planId\":\""+planId+"\", ";
		str += "\"position\":\""+position+"\", ";
		str += "\"projection\":\""+projection+"\", ";
		str += "\"qblockName\":\""+qblockName+"\", ";
		str += "\"remarks\":\""+remarks+"\", ";
		str += "\"searchColumns\":\""+searchColumns+"\", ";
		str += "\"statementId\":\""+statementId+"\", ";
		str += "\"tempSpace\":\""+tempSpace+"\", ";
		str += "\"time\":\""+time+"\", ";
		str += "\"timestamp\":\""+timestamp+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}