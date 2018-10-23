/**************************************************************************************************
 * Framework Generated DTO Source
 * - SYS_LENDING_TYPE - Lending Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class SysLendingType extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String lendingTypeId;
	private String LENDING_TYPE_ID;
	private String lendingType;
	private String LENDING_TYPE;
	private String orgCategory;
	private String ORG_CATEGORY;
	private String description;
	private String DESCRIPTION;
	private String accountCode;
	private String ACCOUNT_CODE;
	private double gstPercentage;
	private String GST_PERCENTAGE;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private String isApplyGst;
	private String IS_APPLY_GST;
	private String parentLendingType;
	private String PARENT_LENDING_TYPE;
	private String sortOrder;
	private String SORT_ORDER;
	private Date updateDate;
	private String UPDATE_DATE;
	private String updateUserId;
	private String UPDATE_USER_ID;
	private String insertUserName;
	private String INSERT_USER_NAME;
	private String updateUserName;
	private String UPDATE_USER_NAME;

	/**
	 * Constructor
	 */
	@SuppressWarnings("rawtypes")
	public SysLendingType() throws Exception {
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
		setFrwVarPrimaryKey("LENDING_TYPE_ID");
		setFrwVarDateColumn("INSERT_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("GST_PERCENTAGE");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getLendingTypeId() {
		return lendingTypeId;
	}

	public void setLendingTypeId(String lendingTypeId) throws Exception {
		this.lendingTypeId = lendingTypeId;
		setValueFromAccessor("LENDING_TYPE_ID", lendingTypeId);
	}

	public String getLendingType() {
		return lendingType;
	}

	public void setLendingType(String lendingType) throws Exception {
		this.lendingType = lendingType;
		setValueFromAccessor("LENDING_TYPE", lendingType);
	}

	public String getOrgCategory() {
		return orgCategory;
	}

	public void setOrgCategory(String orgCategory) throws Exception {
		this.orgCategory = orgCategory;
		setValueFromAccessor("ORG_CATEGORY", orgCategory);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws Exception {
		this.description = description;
		setValueFromAccessor("DESCRIPTION", description);
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) throws Exception {
		this.accountCode = accountCode;
		setValueFromAccessor("ACCOUNT_CODE", accountCode);
	}

	public double getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(double gstPercentage) throws Exception {
		this.gstPercentage = gstPercentage;
		setValueFromAccessor("GST_PERCENTAGE", CommonUtil.toString(gstPercentage));
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) throws Exception {
		this.insertDate = insertDate;
		setValueFromAccessor("INSERT_DATE", CommonUtil.toString(insertDate));
	}

	public String getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(String insertUserId) throws Exception {
		this.insertUserId = insertUserId;
		setValueFromAccessor("INSERT_USER_ID", insertUserId);
	}

	public String getIsApplyGst() {
		return isApplyGst;
	}

	public void setIsApplyGst(String isApplyGst) throws Exception {
		this.isApplyGst = isApplyGst;
		setValueFromAccessor("IS_APPLY_GST", isApplyGst);
	}

	public String getParentLendingType() {
		return parentLendingType;
	}

	public void setParentLendingType(String parentLendingType) throws Exception {
		this.parentLendingType = parentLendingType;
		setValueFromAccessor("PARENT_LENDING_TYPE", parentLendingType);
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) throws Exception {
		this.sortOrder = sortOrder;
		setValueFromAccessor("SORT_ORDER", sortOrder);
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) throws Exception {
		this.updateDate = updateDate;
		setValueFromAccessor("UPDATE_DATE", CommonUtil.toString(updateDate));
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) throws Exception {
		this.updateUserId = updateUserId;
		setValueFromAccessor("UPDATE_USER_ID", updateUserId);
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

		str += "lendingTypeId : "+lendingTypeId+"\n";
		str += "lendingType : "+lendingType+"\n";
		str += "orgCategory : "+orgCategory+"\n";
		str += "description : "+description+"\n";
		str += "accountCode : "+accountCode+"\n";
		str += "gstPercentage : "+gstPercentage+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "isApplyGst : "+isApplyGst+"\n";
		str += "parentLendingType : "+parentLendingType+"\n";
		str += "sortOrder : "+sortOrder+"\n";
		str += "updateDate : "+updateDate+"\n";
		str += "updateUserId : "+updateUserId+"\n";
		str += "insertUserName : "+insertUserName+"\n";
		str += "updateUserName : "+updateUserName+"\n";

		return str;
	}

	/**
	 * toXmlString
	 */
	public String toXmlString() {
		String str = "";

		str += "<column name=\"lendingTypeId\" value=\""+lendingTypeId+"\">";
		str += "<column name=\"lendingType\" value=\""+lendingType+"\">";
		str += "<column name=\"orgCategory\" value=\""+orgCategory+"\">";
		str += "<column name=\"description\" value=\""+description+"\">";
		str += "<column name=\"accountCode\" value=\""+accountCode+"\">";
		str += "<column name=\"gstPercentage\" value=\""+gstPercentage+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"isApplyGst\" value=\""+isApplyGst+"\">";
		str += "<column name=\"parentLendingType\" value=\""+parentLendingType+"\">";
		str += "<column name=\"sortOrder\" value=\""+sortOrder+"\">";
		str += "<column name=\"updateDate\" value=\""+updateDate+"\">";
		str += "<column name=\"updateUserId\" value=\""+updateUserId+"\">";
		str += "<column name=\"insertUserName\" value=\""+insertUserName+"\">";
		str += "<column name=\"updateUserName\" value=\""+updateUserName+"\">";

		return str;
	}

	/**
	 * toJsonString
	 */
	public String toJsonString() {
		String str = "";

		str += "\"lendingTypeId\":\""+lendingTypeId+"\", ";
		str += "\"lendingType\":\""+lendingType+"\", ";
		str += "\"orgCategory\":\""+orgCategory+"\", ";
		str += "\"description\":\""+description+"\", ";
		str += "\"accountCode\":\""+accountCode+"\", ";
		str += "\"gstPercentage\":\""+gstPercentage+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"isApplyGst\":\""+isApplyGst+"\", ";
		str += "\"parentLendingType\":\""+parentLendingType+"\", ";
		str += "\"sortOrder\":\""+sortOrder+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}