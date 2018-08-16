/**************************************************************************************************
 * Framework Generated DTO Source
 * - USR_FINANCE - Financial Loan Related Data Entry (Repayment / Borrowing / Lending)
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class UsrFinance extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String financeId;
	private String FINANCE_ID;
	private Date financeDate;
	private String FINANCE_DATE;
	private String financeEntryType;
	private String FINANCE_ENTRY_TYPE;
	private String financeTypeId;
	private String FINANCE_TYPE_ID;
	private String financeYear;
	private String FINANCE_YEAR;
	private String isCompleted;
	private String IS_COMPLETED;
	private String orgId;
	private String ORG_ID;
	private String quarterName;
	private String QUARTER_NAME;
	private String description;
	private String DESCRIPTION;
	private Date endDate;
	private String END_DATE;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private double interestPercentage;
	private String INTEREST_PERCENTAGE;
	private double principalAmt;
	private String PRINCIPAL_AMT;
	private double repaymentAmt;
	private String REPAYMENT_AMT;
	private Date startDate;
	private String START_DATE;
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
	public UsrFinance() throws Exception {
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
		setFrwVarPrimaryKey("FINANCE_ID");
		setFrwVarDateColumn("FINANCE_DATE,END_DATE,INSERT_DATE,START_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("INTEREST_PERCENTAGE,PRINCIPAL_AMT,REPAYMENT_AMT");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) throws Exception {
		this.financeId = financeId;
		setValueFromAccessor("FINANCE_ID", financeId);
	}

	public Date getFinanceDate() {
		return financeDate;
	}

	public void setFinanceDate(Date financeDate) throws Exception {
		this.financeDate = financeDate;
		setValueFromAccessor("FINANCE_DATE", CommonUtil.toString(financeDate));
	}

	public String getFinanceEntryType() {
		return financeEntryType;
	}

	public void setFinanceEntryType(String financeEntryType) throws Exception {
		this.financeEntryType = financeEntryType;
		setValueFromAccessor("FINANCE_ENTRY_TYPE", financeEntryType);
	}

	public String getFinanceTypeId() {
		return financeTypeId;
	}

	public void setFinanceTypeId(String financeTypeId) throws Exception {
		this.financeTypeId = financeTypeId;
		setValueFromAccessor("FINANCE_TYPE_ID", financeTypeId);
	}

	public String getFinanceYear() {
		return financeYear;
	}

	public void setFinanceYear(String financeYear) throws Exception {
		this.financeYear = financeYear;
		setValueFromAccessor("FINANCE_YEAR", financeYear);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws Exception {
		this.description = description;
		setValueFromAccessor("DESCRIPTION", description);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) throws Exception {
		this.endDate = endDate;
		setValueFromAccessor("END_DATE", CommonUtil.toString(endDate));
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

	public double getInterestPercentage() {
		return interestPercentage;
	}

	public void setInterestPercentage(double interestPercentage) throws Exception {
		this.interestPercentage = interestPercentage;
		setValueFromAccessor("INTEREST_PERCENTAGE", CommonUtil.toString(interestPercentage));
	}

	public double getPrincipalAmt() {
		return principalAmt;
	}

	public void setPrincipalAmt(double principalAmt) throws Exception {
		this.principalAmt = principalAmt;
		setValueFromAccessor("PRINCIPAL_AMT", CommonUtil.toString(principalAmt));
	}

	public double getRepaymentAmt() {
		return repaymentAmt;
	}

	public void setRepaymentAmt(double repaymentAmt) throws Exception {
		this.repaymentAmt = repaymentAmt;
		setValueFromAccessor("REPAYMENT_AMT", CommonUtil.toString(repaymentAmt));
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) throws Exception {
		this.startDate = startDate;
		setValueFromAccessor("START_DATE", CommonUtil.toString(startDate));
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

		str += "financeId : "+financeId+"\n";
		str += "financeDate : "+financeDate+"\n";
		str += "financeEntryType : "+financeEntryType+"\n";
		str += "financeTypeId : "+financeTypeId+"\n";
		str += "financeYear : "+financeYear+"\n";
		str += "isCompleted : "+isCompleted+"\n";
		str += "orgId : "+orgId+"\n";
		str += "quarterName : "+quarterName+"\n";
		str += "description : "+description+"\n";
		str += "endDate : "+endDate+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "interestPercentage : "+interestPercentage+"\n";
		str += "principalAmt : "+principalAmt+"\n";
		str += "repaymentAmt : "+repaymentAmt+"\n";
		str += "startDate : "+startDate+"\n";
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

		str += "<column name=\"financeId\" value=\""+financeId+"\">";
		str += "<column name=\"financeDate\" value=\""+financeDate+"\">";
		str += "<column name=\"financeEntryType\" value=\""+financeEntryType+"\">";
		str += "<column name=\"financeTypeId\" value=\""+financeTypeId+"\">";
		str += "<column name=\"financeYear\" value=\""+financeYear+"\">";
		str += "<column name=\"isCompleted\" value=\""+isCompleted+"\">";
		str += "<column name=\"orgId\" value=\""+orgId+"\">";
		str += "<column name=\"quarterName\" value=\""+quarterName+"\">";
		str += "<column name=\"description\" value=\""+description+"\">";
		str += "<column name=\"endDate\" value=\""+endDate+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"interestPercentage\" value=\""+interestPercentage+"\">";
		str += "<column name=\"principalAmt\" value=\""+principalAmt+"\">";
		str += "<column name=\"repaymentAmt\" value=\""+repaymentAmt+"\">";
		str += "<column name=\"startDate\" value=\""+startDate+"\">";
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

		str += "\"financeId\":\""+financeId+"\", ";
		str += "\"financeDate\":\""+financeDate+"\", ";
		str += "\"financeEntryType\":\""+financeEntryType+"\", ";
		str += "\"financeTypeId\":\""+financeTypeId+"\", ";
		str += "\"financeYear\":\""+financeYear+"\", ";
		str += "\"isCompleted\":\""+isCompleted+"\", ";
		str += "\"orgId\":\""+orgId+"\", ";
		str += "\"quarterName\":\""+quarterName+"\", ";
		str += "\"description\":\""+description+"\", ";
		str += "\"endDate\":\""+endDate+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"interestPercentage\":\""+interestPercentage+"\", ";
		str += "\"principalAmt\":\""+principalAmt+"\", ";
		str += "\"repaymentAmt\":\""+repaymentAmt+"\", ";
		str += "\"startDate\":\""+startDate+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}