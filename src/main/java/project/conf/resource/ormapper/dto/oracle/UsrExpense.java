/**************************************************************************************************
 * Framework Generated DTO Source
 * - USR_EXPENSE - Expense Entry (Expenditure data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class UsrExpense extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String expenseId;
	private String EXPENSE_ID;
	private Date expenseDate;
	private String EXPENSE_DATE;
	private String expenseTypeId;
	private String EXPENSE_TYPE_ID;
	private String expenseYear;
	private String EXPENSE_YEAR;
	private String isCompleted;
	private String IS_COMPLETED;
	private String orgId;
	private String ORG_ID;
	private String quarterName;
	private String QUARTER_NAME;
	private double appliedGst;
	private String APPLIED_GST;
	private String description;
	private String DESCRIPTION;
	private double grossAmt;
	private String GROSS_AMT;
	private double gstAmt;
	private String GST_AMT;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private double netAmt;
	private String NET_AMT;
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
	public UsrExpense() throws Exception {
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
		setFrwVarPrimaryKey("EXPENSE_ID");
		setFrwVarDateColumn("EXPENSE_DATE,INSERT_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("APPLIED_GST,GROSS_AMT,GST_AMT,NET_AMT");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(String expenseId) throws Exception {
		this.expenseId = expenseId;
		setValueFromAccessor("EXPENSE_ID", expenseId);
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) throws Exception {
		this.expenseDate = expenseDate;
		setValueFromAccessor("EXPENSE_DATE", CommonUtil.toString(expenseDate));
	}

	public String getExpenseTypeId() {
		return expenseTypeId;
	}

	public void setExpenseTypeId(String expenseTypeId) throws Exception {
		this.expenseTypeId = expenseTypeId;
		setValueFromAccessor("EXPENSE_TYPE_ID", expenseTypeId);
	}

	public String getExpenseYear() {
		return expenseYear;
	}

	public void setExpenseYear(String expenseYear) throws Exception {
		this.expenseYear = expenseYear;
		setValueFromAccessor("EXPENSE_YEAR", expenseYear);
	}

	public String getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(String isCompleted) throws Exception {
		this.isCompleted = isCompleted;
		setValueFromAccessor("IS_COMPLETED", isCompleted);
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) throws Exception {
		this.orgId = orgId;
		setValueFromAccessor("ORG_ID", orgId);
	}

	public String getQuarterName() {
		return quarterName;
	}

	public void setQuarterName(String quarterName) throws Exception {
		this.quarterName = quarterName;
		setValueFromAccessor("QUARTER_NAME", quarterName);
	}

	public double getAppliedGst() {
		return appliedGst;
	}

	public void setAppliedGst(double appliedGst) throws Exception {
		this.appliedGst = appliedGst;
		setValueFromAccessor("APPLIED_GST", CommonUtil.toString(appliedGst));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws Exception {
		this.description = description;
		setValueFromAccessor("DESCRIPTION", description);
	}

	public double getGrossAmt() {
		return grossAmt;
	}

	public void setGrossAmt(double grossAmt) throws Exception {
		this.grossAmt = grossAmt;
		setValueFromAccessor("GROSS_AMT", CommonUtil.toString(grossAmt));
	}

	public double getGstAmt() {
		return gstAmt;
	}

	public void setGstAmt(double gstAmt) throws Exception {
		this.gstAmt = gstAmt;
		setValueFromAccessor("GST_AMT", CommonUtil.toString(gstAmt));
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

	public double getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(double netAmt) throws Exception {
		this.netAmt = netAmt;
		setValueFromAccessor("NET_AMT", CommonUtil.toString(netAmt));
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

		str += "expenseId : "+expenseId+"\n";
		str += "expenseDate : "+expenseDate+"\n";
		str += "expenseTypeId : "+expenseTypeId+"\n";
		str += "expenseYear : "+expenseYear+"\n";
		str += "isCompleted : "+isCompleted+"\n";
		str += "orgId : "+orgId+"\n";
		str += "quarterName : "+quarterName+"\n";
		str += "appliedGst : "+appliedGst+"\n";
		str += "description : "+description+"\n";
		str += "grossAmt : "+grossAmt+"\n";
		str += "gstAmt : "+gstAmt+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "netAmt : "+netAmt+"\n";
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

		str += "<column name=\"expenseId\" value=\""+expenseId+"\">";
		str += "<column name=\"expenseDate\" value=\""+expenseDate+"\">";
		str += "<column name=\"expenseTypeId\" value=\""+expenseTypeId+"\">";
		str += "<column name=\"expenseYear\" value=\""+expenseYear+"\">";
		str += "<column name=\"isCompleted\" value=\""+isCompleted+"\">";
		str += "<column name=\"orgId\" value=\""+orgId+"\">";
		str += "<column name=\"quarterName\" value=\""+quarterName+"\">";
		str += "<column name=\"appliedGst\" value=\""+appliedGst+"\">";
		str += "<column name=\"description\" value=\""+description+"\">";
		str += "<column name=\"grossAmt\" value=\""+grossAmt+"\">";
		str += "<column name=\"gstAmt\" value=\""+gstAmt+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"netAmt\" value=\""+netAmt+"\">";
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

		str += "\"expenseId\":\""+expenseId+"\", ";
		str += "\"expenseDate\":\""+expenseDate+"\", ";
		str += "\"expenseTypeId\":\""+expenseTypeId+"\", ";
		str += "\"expenseYear\":\""+expenseYear+"\", ";
		str += "\"isCompleted\":\""+isCompleted+"\", ";
		str += "\"orgId\":\""+orgId+"\", ";
		str += "\"quarterName\":\""+quarterName+"\", ";
		str += "\"appliedGst\":\""+appliedGst+"\", ";
		str += "\"description\":\""+description+"\", ";
		str += "\"grossAmt\":\""+grossAmt+"\", ";
		str += "\"gstAmt\":\""+gstAmt+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"netAmt\":\""+netAmt+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}