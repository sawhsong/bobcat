/**************************************************************************************************
 * Framework Generated DTO Source
 * - USR_BANK_STATEMENT_D - Bank statement details
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class UsrBankStatementD extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String bankStatementId;
	private String BANK_STATEMENT_ID;
	private String bankStatementDId;
	private String BANK_STATEMENT_D_ID;
	private double procAmt;
	private String PROC_AMT;
	private Date procDate;
	private String PROC_DATE;
	private double rowIndex;
	private String ROW_INDEX;
	private double balance;
	private String BALANCE;
	private String bankAccount;
	private String BANK_ACCOUNT;
	private String category;
	private String CATEGORY;
	private double creditAmt;
	private String CREDIT_AMT;
	private double debitAmt;
	private String DEBIT_AMT;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private String procDescription;
	private String PROC_DESCRIPTION;
	private String serial;
	private String SERIAL;
	private Date updateDate;
	private String UPDATE_DATE;
	private String updateUserId;
	private String UPDATE_USER_ID;
	private String userDescription;
	private String USER_DESCRIPTION;
	private String insertUserName;
	private String INSERT_USER_NAME;
	private String updateUserName;
	private String UPDATE_USER_NAME;

	/**
	 * Constructor
	 */
	@SuppressWarnings("rawtypes")
	public UsrBankStatementD() throws Exception {
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
		setFrwVarPrimaryKey("BANK_STATEMENT_D_ID");
		setFrwVarDateColumn("PROC_DATE,INSERT_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("PROC_AMT,ROW_INDEX,BALANCE,CREDIT_AMT,DEBIT_AMT");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getBankStatementId() {
		return bankStatementId;
	}

	public void setBankStatementId(String bankStatementId) throws Exception {
		this.bankStatementId = bankStatementId;
		setValueFromAccessor("BANK_STATEMENT_ID", bankStatementId);
	}

	public String getBankStatementDId() {
		return bankStatementDId;
	}

	public void setBankStatementDId(String bankStatementDId) throws Exception {
		this.bankStatementDId = bankStatementDId;
		setValueFromAccessor("BANK_STATEMENT_D_ID", bankStatementDId);
	}

	public double getProcAmt() {
		return procAmt;
	}

	public void setProcAmt(double procAmt) throws Exception {
		this.procAmt = procAmt;
		setValueFromAccessor("PROC_AMT", CommonUtil.toString(procAmt));
	}

	public Date getProcDate() {
		return procDate;
	}

	public void setProcDate(Date procDate) throws Exception {
		this.procDate = procDate;
		setValueFromAccessor("PROC_DATE", CommonUtil.toString(procDate));
	}

	public double getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(double rowIndex) throws Exception {
		this.rowIndex = rowIndex;
		setValueFromAccessor("ROW_INDEX", CommonUtil.toString(rowIndex));
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) throws Exception {
		this.balance = balance;
		setValueFromAccessor("BALANCE", CommonUtil.toString(balance));
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) throws Exception {
		this.bankAccount = bankAccount;
		setValueFromAccessor("BANK_ACCOUNT", bankAccount);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) throws Exception {
		this.category = category;
		setValueFromAccessor("CATEGORY", category);
	}

	public double getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(double creditAmt) throws Exception {
		this.creditAmt = creditAmt;
		setValueFromAccessor("CREDIT_AMT", CommonUtil.toString(creditAmt));
	}

	public double getDebitAmt() {
		return debitAmt;
	}

	public void setDebitAmt(double debitAmt) throws Exception {
		this.debitAmt = debitAmt;
		setValueFromAccessor("DEBIT_AMT", CommonUtil.toString(debitAmt));
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

	public String getProcDescription() {
		return procDescription;
	}

	public void setProcDescription(String procDescription) throws Exception {
		this.procDescription = procDescription;
		setValueFromAccessor("PROC_DESCRIPTION", procDescription);
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) throws Exception {
		this.serial = serial;
		setValueFromAccessor("SERIAL", serial);
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

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) throws Exception {
		this.userDescription = userDescription;
		setValueFromAccessor("USER_DESCRIPTION", userDescription);
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
				if (CommonUtil.isIn(columnName, CommonUtil.split(getFrwVarNumberColumn(), ","))) {
					field[i].set(this, CommonUtil.toDouble(value));
				} else if (CommonUtil.isIn(columnName, CommonUtil.split(getFrwVarDateColumn(), ","))) {
					if (CommonUtil.equalsIgnoreCase(value, "SYSDATE") || CommonUtil.containsIgnoreCase(value, "SYSDATE")) {
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

		if (CommonUtil.isIn(columnName, CommonUtil.split(getFrwVarNumberColumn(), ","))) {
			dataType = "Number";
		} else if (CommonUtil.isIn(columnName, CommonUtil.split(getFrwVarDateColumn(), ","))) {
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

		str += "bankStatementId : "+bankStatementId+"\n";
		str += "bankStatementDId : "+bankStatementDId+"\n";
		str += "procAmt : "+procAmt+"\n";
		str += "procDate : "+procDate+"\n";
		str += "rowIndex : "+rowIndex+"\n";
		str += "balance : "+balance+"\n";
		str += "bankAccount : "+bankAccount+"\n";
		str += "category : "+category+"\n";
		str += "creditAmt : "+creditAmt+"\n";
		str += "debitAmt : "+debitAmt+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "procDescription : "+procDescription+"\n";
		str += "serial : "+serial+"\n";
		str += "updateDate : "+updateDate+"\n";
		str += "updateUserId : "+updateUserId+"\n";
		str += "userDescription : "+userDescription+"\n";
		str += "insertUserName : "+insertUserName+"\n";
		str += "updateUserName : "+updateUserName+"\n";

		return str;
	}

	/**
	 * toXmlString
	 */
	public String toXmlString() {
		String str = "";

		str += "<column name=\"bankStatementId\" value=\""+bankStatementId+"\">";
		str += "<column name=\"bankStatementDId\" value=\""+bankStatementDId+"\">";
		str += "<column name=\"procAmt\" value=\""+procAmt+"\">";
		str += "<column name=\"procDate\" value=\""+procDate+"\">";
		str += "<column name=\"rowIndex\" value=\""+rowIndex+"\">";
		str += "<column name=\"balance\" value=\""+balance+"\">";
		str += "<column name=\"bankAccount\" value=\""+bankAccount+"\">";
		str += "<column name=\"category\" value=\""+category+"\">";
		str += "<column name=\"creditAmt\" value=\""+creditAmt+"\">";
		str += "<column name=\"debitAmt\" value=\""+debitAmt+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"procDescription\" value=\""+procDescription+"\">";
		str += "<column name=\"serial\" value=\""+serial+"\">";
		str += "<column name=\"updateDate\" value=\""+updateDate+"\">";
		str += "<column name=\"updateUserId\" value=\""+updateUserId+"\">";
		str += "<column name=\"userDescription\" value=\""+userDescription+"\">";
		str += "<column name=\"insertUserName\" value=\""+insertUserName+"\">";
		str += "<column name=\"updateUserName\" value=\""+updateUserName+"\">";

		return str;
	}

	/**
	 * toJsonString
	 */
	public String toJsonString() {
		String str = "";

		str += "\"bankStatementId\":\""+bankStatementId+"\", ";
		str += "\"bankStatementDId\":\""+bankStatementDId+"\", ";
		str += "\"procAmt\":\""+procAmt+"\", ";
		str += "\"procDate\":\""+procDate+"\", ";
		str += "\"rowIndex\":\""+rowIndex+"\", ";
		str += "\"balance\":\""+balance+"\", ";
		str += "\"bankAccount\":\""+bankAccount+"\", ";
		str += "\"category\":\""+category+"\", ";
		str += "\"creditAmt\":\""+creditAmt+"\", ";
		str += "\"debitAmt\":\""+debitAmt+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"procDescription\":\""+procDescription+"\", ";
		str += "\"serial\":\""+serial+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"userDescription\":\""+userDescription+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}