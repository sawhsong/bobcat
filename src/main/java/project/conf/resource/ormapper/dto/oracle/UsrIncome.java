/**************************************************************************************************
 * Framework Generated DTO Source
 * - USR_INCOME - Income Entry (Sales Income and Other Income for Org Category A, Other Income for Org Category B/C/D)
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class UsrIncome extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String incomeId;
	private String INCOME_ID;
	private Date incomeDate;
	private String INCOME_DATE;
	private String incomeEntryType;
	private String INCOME_ENTRY_TYPE;
	private String incomeYear;
	private String INCOME_YEAR;
	private String isCompleted;
	private String IS_COMPLETED;
	private String orgId;
	private String ORG_ID;
	private String quarterName;
	private String QUARTER_NAME;
	private double appliedGst;
	private String APPLIED_GST;
	private double cashAmt;
	private String CASH_AMT;
	private String description;
	private String DESCRIPTION;
	private double grossAmt;
	private String GROSS_AMT;
	private double gstAmt;
	private String GST_AMT;
	private double gstFreeAmt;
	private String GST_FREE_AMT;
	private String incomeTypeId;
	private String INCOME_TYPE_ID;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private double netAmt;
	private String NET_AMT;
	private double nonCashAmt;
	private String NON_CASH_AMT;
	private String recordKeepingType;
	private String RECORD_KEEPING_TYPE;
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
	public UsrIncome() throws Exception {
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
		setFrwVarPrimaryKey("INCOME_ID");
		setFrwVarDateColumn("INCOME_DATE,INSERT_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("APPLIED_GST,CASH_AMT,GROSS_AMT,GST_AMT,GST_FREE_AMT,NET_AMT,NON_CASH_AMT");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(String incomeId) throws Exception {
		this.incomeId = incomeId;
		setValueFromAccessor("INCOME_ID", incomeId);
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) throws Exception {
		this.incomeDate = incomeDate;
		setValueFromAccessor("INCOME_DATE", CommonUtil.toString(incomeDate));
	}

	public String getIncomeEntryType() {
		return incomeEntryType;
	}

	public void setIncomeEntryType(String incomeEntryType) throws Exception {
		this.incomeEntryType = incomeEntryType;
		setValueFromAccessor("INCOME_ENTRY_TYPE", incomeEntryType);
	}

	public String getIncomeYear() {
		return incomeYear;
	}

	public void setIncomeYear(String incomeYear) throws Exception {
		this.incomeYear = incomeYear;
		setValueFromAccessor("INCOME_YEAR", incomeYear);
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

	public double getCashAmt() {
		return cashAmt;
	}

	public void setCashAmt(double cashAmt) throws Exception {
		this.cashAmt = cashAmt;
		setValueFromAccessor("CASH_AMT", CommonUtil.toString(cashAmt));
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

	public double getGstFreeAmt() {
		return gstFreeAmt;
	}

	public void setGstFreeAmt(double gstFreeAmt) throws Exception {
		this.gstFreeAmt = gstFreeAmt;
		setValueFromAccessor("GST_FREE_AMT", CommonUtil.toString(gstFreeAmt));
	}

	public String getIncomeTypeId() {
		return incomeTypeId;
	}

	public void setIncomeTypeId(String incomeTypeId) throws Exception {
		this.incomeTypeId = incomeTypeId;
		setValueFromAccessor("INCOME_TYPE_ID", incomeTypeId);
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

	public double getNonCashAmt() {
		return nonCashAmt;
	}

	public void setNonCashAmt(double nonCashAmt) throws Exception {
		this.nonCashAmt = nonCashAmt;
		setValueFromAccessor("NON_CASH_AMT", CommonUtil.toString(nonCashAmt));
	}

	public String getRecordKeepingType() {
		return recordKeepingType;
	}

	public void setRecordKeepingType(String recordKeepingType) throws Exception {
		this.recordKeepingType = recordKeepingType;
		setValueFromAccessor("RECORD_KEEPING_TYPE", recordKeepingType);
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

		str += "incomeId : "+incomeId+"\n";
		str += "incomeDate : "+incomeDate+"\n";
		str += "incomeEntryType : "+incomeEntryType+"\n";
		str += "incomeYear : "+incomeYear+"\n";
		str += "isCompleted : "+isCompleted+"\n";
		str += "orgId : "+orgId+"\n";
		str += "quarterName : "+quarterName+"\n";
		str += "appliedGst : "+appliedGst+"\n";
		str += "cashAmt : "+cashAmt+"\n";
		str += "description : "+description+"\n";
		str += "grossAmt : "+grossAmt+"\n";
		str += "gstAmt : "+gstAmt+"\n";
		str += "gstFreeAmt : "+gstFreeAmt+"\n";
		str += "incomeTypeId : "+incomeTypeId+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "netAmt : "+netAmt+"\n";
		str += "nonCashAmt : "+nonCashAmt+"\n";
		str += "recordKeepingType : "+recordKeepingType+"\n";
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

		str += "<column name=\"incomeId\" value=\""+incomeId+"\">";
		str += "<column name=\"incomeDate\" value=\""+incomeDate+"\">";
		str += "<column name=\"incomeEntryType\" value=\""+incomeEntryType+"\">";
		str += "<column name=\"incomeYear\" value=\""+incomeYear+"\">";
		str += "<column name=\"isCompleted\" value=\""+isCompleted+"\">";
		str += "<column name=\"orgId\" value=\""+orgId+"\">";
		str += "<column name=\"quarterName\" value=\""+quarterName+"\">";
		str += "<column name=\"appliedGst\" value=\""+appliedGst+"\">";
		str += "<column name=\"cashAmt\" value=\""+cashAmt+"\">";
		str += "<column name=\"description\" value=\""+description+"\">";
		str += "<column name=\"grossAmt\" value=\""+grossAmt+"\">";
		str += "<column name=\"gstAmt\" value=\""+gstAmt+"\">";
		str += "<column name=\"gstFreeAmt\" value=\""+gstFreeAmt+"\">";
		str += "<column name=\"incomeTypeId\" value=\""+incomeTypeId+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"netAmt\" value=\""+netAmt+"\">";
		str += "<column name=\"nonCashAmt\" value=\""+nonCashAmt+"\">";
		str += "<column name=\"recordKeepingType\" value=\""+recordKeepingType+"\">";
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

		str += "\"incomeId\":\""+incomeId+"\", ";
		str += "\"incomeDate\":\""+incomeDate+"\", ";
		str += "\"incomeEntryType\":\""+incomeEntryType+"\", ";
		str += "\"incomeYear\":\""+incomeYear+"\", ";
		str += "\"isCompleted\":\""+isCompleted+"\", ";
		str += "\"orgId\":\""+orgId+"\", ";
		str += "\"quarterName\":\""+quarterName+"\", ";
		str += "\"appliedGst\":\""+appliedGst+"\", ";
		str += "\"cashAmt\":\""+cashAmt+"\", ";
		str += "\"description\":\""+description+"\", ";
		str += "\"grossAmt\":\""+grossAmt+"\", ";
		str += "\"gstAmt\":\""+gstAmt+"\", ";
		str += "\"gstFreeAmt\":\""+gstFreeAmt+"\", ";
		str += "\"incomeTypeId\":\""+incomeTypeId+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"netAmt\":\""+netAmt+"\", ";
		str += "\"nonCashAmt\":\""+nonCashAmt+"\", ";
		str += "\"recordKeepingType\":\""+recordKeepingType+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}