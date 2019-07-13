/**************************************************************************************************
 * Framework Generated DTO Source
 * - USR_EMPLOYEE_WAGE - Employee Wage
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class UsrEmployeeWage extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String wageId;
	private String WAGE_ID;
	private String employeeId;
	private String EMPLOYEE_ID;
	private String isCompleted;
	private String IS_COMPLETED;
	private String orgId;
	private String ORG_ID;
	private String quarterName;
	private String QUARTER_NAME;
	private String wageYear;
	private String WAGE_YEAR;
	private String description;
	private String DESCRIPTION;
	private double grossWage;
	private String GROSS_WAGE;
	private double hourlyRate;
	private String HOURLY_RATE;
	private double hourWorked;
	private String HOUR_WORKED;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private double netWage;
	private String NET_WAGE;
	private Date periodEndDate;
	private String PERIOD_END_DATE;
	private Date periodStartDate;
	private String PERIOD_START_DATE;
	private double superAmt;
	private String SUPER_AMT;
	private double tax;
	private String TAX;
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
	public UsrEmployeeWage() throws Exception {
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
		setFrwVarPrimaryKey("WAGE_ID");
		setFrwVarDateColumn("INSERT_DATE,PERIOD_END_DATE,PERIOD_START_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("GROSS_WAGE,HOURLY_RATE,HOUR_WORKED,NET_WAGE,SUPER_AMT,TAX");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getWageId() {
		return wageId;
	}

	public void setWageId(String wageId) throws Exception {
		this.wageId = wageId;
		setValueFromAccessor("WAGE_ID", wageId);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) throws Exception {
		this.employeeId = employeeId;
		setValueFromAccessor("EMPLOYEE_ID", employeeId);
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

	public String getWageYear() {
		return wageYear;
	}

	public void setWageYear(String wageYear) throws Exception {
		this.wageYear = wageYear;
		setValueFromAccessor("WAGE_YEAR", wageYear);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws Exception {
		this.description = description;
		setValueFromAccessor("DESCRIPTION", description);
	}

	public double getGrossWage() {
		return grossWage;
	}

	public void setGrossWage(double grossWage) throws Exception {
		this.grossWage = grossWage;
		setValueFromAccessor("GROSS_WAGE", CommonUtil.toString(grossWage));
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) throws Exception {
		this.hourlyRate = hourlyRate;
		setValueFromAccessor("HOURLY_RATE", CommonUtil.toString(hourlyRate));
	}

	public double getHourWorked() {
		return hourWorked;
	}

	public void setHourWorked(double hourWorked) throws Exception {
		this.hourWorked = hourWorked;
		setValueFromAccessor("HOUR_WORKED", CommonUtil.toString(hourWorked));
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

	public double getNetWage() {
		return netWage;
	}

	public void setNetWage(double netWage) throws Exception {
		this.netWage = netWage;
		setValueFromAccessor("NET_WAGE", CommonUtil.toString(netWage));
	}

	public Date getPeriodEndDate() {
		return periodEndDate;
	}

	public void setPeriodEndDate(Date periodEndDate) throws Exception {
		this.periodEndDate = periodEndDate;
		setValueFromAccessor("PERIOD_END_DATE", CommonUtil.toString(periodEndDate));
	}

	public Date getPeriodStartDate() {
		return periodStartDate;
	}

	public void setPeriodStartDate(Date periodStartDate) throws Exception {
		this.periodStartDate = periodStartDate;
		setValueFromAccessor("PERIOD_START_DATE", CommonUtil.toString(periodStartDate));
	}

	public double getSuperAmt() {
		return superAmt;
	}

	public void setSuperAmt(double superAmt) throws Exception {
		this.superAmt = superAmt;
		setValueFromAccessor("SUPER_AMT", CommonUtil.toString(superAmt));
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) throws Exception {
		this.tax = tax;
		setValueFromAccessor("TAX", CommonUtil.toString(tax));
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

	public void setValues(DataSet dataSet, int rowIndex) throws Exception {
		for (int i=0; i<dataSet.getColumnCnt(); i++) {
			setValue(dataSet.getName(i), dataSet.getValue(rowIndex, i));
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

		str += "wageId : "+wageId+"\n";
		str += "employeeId : "+employeeId+"\n";
		str += "isCompleted : "+isCompleted+"\n";
		str += "orgId : "+orgId+"\n";
		str += "quarterName : "+quarterName+"\n";
		str += "wageYear : "+wageYear+"\n";
		str += "description : "+description+"\n";
		str += "grossWage : "+grossWage+"\n";
		str += "hourlyRate : "+hourlyRate+"\n";
		str += "hourWorked : "+hourWorked+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "netWage : "+netWage+"\n";
		str += "periodEndDate : "+periodEndDate+"\n";
		str += "periodStartDate : "+periodStartDate+"\n";
		str += "superAmt : "+superAmt+"\n";
		str += "tax : "+tax+"\n";
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

		str += "<column name=\"wageId\" value=\""+wageId+"\">";
		str += "<column name=\"employeeId\" value=\""+employeeId+"\">";
		str += "<column name=\"isCompleted\" value=\""+isCompleted+"\">";
		str += "<column name=\"orgId\" value=\""+orgId+"\">";
		str += "<column name=\"quarterName\" value=\""+quarterName+"\">";
		str += "<column name=\"wageYear\" value=\""+wageYear+"\">";
		str += "<column name=\"description\" value=\""+description+"\">";
		str += "<column name=\"grossWage\" value=\""+grossWage+"\">";
		str += "<column name=\"hourlyRate\" value=\""+hourlyRate+"\">";
		str += "<column name=\"hourWorked\" value=\""+hourWorked+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"netWage\" value=\""+netWage+"\">";
		str += "<column name=\"periodEndDate\" value=\""+periodEndDate+"\">";
		str += "<column name=\"periodStartDate\" value=\""+periodStartDate+"\">";
		str += "<column name=\"superAmt\" value=\""+superAmt+"\">";
		str += "<column name=\"tax\" value=\""+tax+"\">";
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

		str += "\"wageId\":\""+wageId+"\", ";
		str += "\"employeeId\":\""+employeeId+"\", ";
		str += "\"isCompleted\":\""+isCompleted+"\", ";
		str += "\"orgId\":\""+orgId+"\", ";
		str += "\"quarterName\":\""+quarterName+"\", ";
		str += "\"wageYear\":\""+wageYear+"\", ";
		str += "\"description\":\""+description+"\", ";
		str += "\"grossWage\":\""+grossWage+"\", ";
		str += "\"hourlyRate\":\""+hourlyRate+"\", ";
		str += "\"hourWorked\":\""+hourWorked+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"netWage\":\""+netWage+"\", ";
		str += "\"periodEndDate\":\""+periodEndDate+"\", ";
		str += "\"periodStartDate\":\""+periodStartDate+"\", ";
		str += "\"superAmt\":\""+superAmt+"\", ";
		str += "\"tax\":\""+tax+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}