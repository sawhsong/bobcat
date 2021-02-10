/**************************************************************************************************
 * Framework Generated DTO Source
 * - USR_BS_TRAN_ALLOC - Bank statement transaction allocation - transaction reconcilisation
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class UsrBsTranAlloc extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String bsTranAllocId;
	private String BS_TRAN_ALLOC_ID;
	private String bankAccntId;
	private String BANK_ACCNT_ID;
	private String bankStatementDId;
	private String BANK_STATEMENT_D_ID;
	private String bankStatementId;
	private String BANK_STATEMENT_ID;
	private double procAmt;
	private String PROC_AMT;
	private Date procDate;
	private String PROC_DATE;
	private double rowIndex;
	private String ROW_INDEX;
	private String userId;
	private String USER_ID;
	private double balance;
	private String BALANCE;
	private double gstAmt;
	private String GST_AMT;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private String mainCategory;
	private String MAIN_CATEGORY;
	private double netAmt;
	private String NET_AMT;
	private String procDescription;
	private String PROC_DESCRIPTION;
	private String sourceId;
	private String SOURCE_ID;
	private String sourceType;
	private String SOURCE_TYPE;
	private String status;
	private String STATUS;
	private String subCategory;
	private String SUB_CATEGORY;
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
	public UsrBsTranAlloc() throws Exception {
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
		setFrwVarPrimaryKey("BS_TRAN_ALLOC_ID");
		setFrwVarDateColumn("PROC_DATE,INSERT_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("PROC_AMT,ROW_INDEX,BALANCE,GST_AMT,NET_AMT");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getBsTranAllocId() {
		return bsTranAllocId;
	}

	public void setBsTranAllocId(String bsTranAllocId) throws Exception {
		this.bsTranAllocId = bsTranAllocId;
		setValueFromAccessor("BS_TRAN_ALLOC_ID", bsTranAllocId);
	}

	public String getBankAccntId() {
		return bankAccntId;
	}

	public void setBankAccntId(String bankAccntId) throws Exception {
		this.bankAccntId = bankAccntId;
		setValueFromAccessor("BANK_ACCNT_ID", bankAccntId);
	}

	public String getBankStatementDId() {
		return bankStatementDId;
	}

	public void setBankStatementDId(String bankStatementDId) throws Exception {
		this.bankStatementDId = bankStatementDId;
		setValueFromAccessor("BANK_STATEMENT_D_ID", bankStatementDId);
	}

	public String getBankStatementId() {
		return bankStatementId;
	}

	public void setBankStatementId(String bankStatementId) throws Exception {
		this.bankStatementId = bankStatementId;
		setValueFromAccessor("BANK_STATEMENT_ID", bankStatementId);
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) throws Exception {
		this.userId = userId;
		setValueFromAccessor("USER_ID", userId);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) throws Exception {
		this.balance = balance;
		setValueFromAccessor("BALANCE", CommonUtil.toString(balance));
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

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) throws Exception {
		this.mainCategory = mainCategory;
		setValueFromAccessor("MAIN_CATEGORY", mainCategory);
	}

	public double getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(double netAmt) throws Exception {
		this.netAmt = netAmt;
		setValueFromAccessor("NET_AMT", CommonUtil.toString(netAmt));
	}

	public String getProcDescription() {
		return procDescription;
	}

	public void setProcDescription(String procDescription) throws Exception {
		this.procDescription = procDescription;
		setValueFromAccessor("PROC_DESCRIPTION", procDescription);
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) throws Exception {
		this.sourceId = sourceId;
		setValueFromAccessor("SOURCE_ID", sourceId);
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) throws Exception {
		this.sourceType = sourceType;
		setValueFromAccessor("SOURCE_TYPE", sourceType);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) throws Exception {
		this.status = status;
		setValueFromAccessor("STATUS", status);
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) throws Exception {
		this.subCategory = subCategory;
		setValueFromAccessor("SUB_CATEGORY", subCategory);
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

		str += "bsTranAllocId : "+bsTranAllocId+"\n";
		str += "bankAccntId : "+bankAccntId+"\n";
		str += "bankStatementDId : "+bankStatementDId+"\n";
		str += "bankStatementId : "+bankStatementId+"\n";
		str += "procAmt : "+procAmt+"\n";
		str += "procDate : "+procDate+"\n";
		str += "rowIndex : "+rowIndex+"\n";
		str += "userId : "+userId+"\n";
		str += "balance : "+balance+"\n";
		str += "gstAmt : "+gstAmt+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "mainCategory : "+mainCategory+"\n";
		str += "netAmt : "+netAmt+"\n";
		str += "procDescription : "+procDescription+"\n";
		str += "sourceId : "+sourceId+"\n";
		str += "sourceType : "+sourceType+"\n";
		str += "status : "+status+"\n";
		str += "subCategory : "+subCategory+"\n";
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

		str += "<column name=\"bsTranAllocId\" value=\""+bsTranAllocId+"\">";
		str += "<column name=\"bankAccntId\" value=\""+bankAccntId+"\">";
		str += "<column name=\"bankStatementDId\" value=\""+bankStatementDId+"\">";
		str += "<column name=\"bankStatementId\" value=\""+bankStatementId+"\">";
		str += "<column name=\"procAmt\" value=\""+procAmt+"\">";
		str += "<column name=\"procDate\" value=\""+procDate+"\">";
		str += "<column name=\"rowIndex\" value=\""+rowIndex+"\">";
		str += "<column name=\"userId\" value=\""+userId+"\">";
		str += "<column name=\"balance\" value=\""+balance+"\">";
		str += "<column name=\"gstAmt\" value=\""+gstAmt+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"mainCategory\" value=\""+mainCategory+"\">";
		str += "<column name=\"netAmt\" value=\""+netAmt+"\">";
		str += "<column name=\"procDescription\" value=\""+procDescription+"\">";
		str += "<column name=\"sourceId\" value=\""+sourceId+"\">";
		str += "<column name=\"sourceType\" value=\""+sourceType+"\">";
		str += "<column name=\"status\" value=\""+status+"\">";
		str += "<column name=\"subCategory\" value=\""+subCategory+"\">";
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

		str += "\"bsTranAllocId\":\""+bsTranAllocId+"\", ";
		str += "\"bankAccntId\":\""+bankAccntId+"\", ";
		str += "\"bankStatementDId\":\""+bankStatementDId+"\", ";
		str += "\"bankStatementId\":\""+bankStatementId+"\", ";
		str += "\"procAmt\":\""+procAmt+"\", ";
		str += "\"procDate\":\""+procDate+"\", ";
		str += "\"rowIndex\":\""+rowIndex+"\", ";
		str += "\"userId\":\""+userId+"\", ";
		str += "\"balance\":\""+balance+"\", ";
		str += "\"gstAmt\":\""+gstAmt+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"mainCategory\":\""+mainCategory+"\", ";
		str += "\"netAmt\":\""+netAmt+"\", ";
		str += "\"procDescription\":\""+procDescription+"\", ";
		str += "\"sourceId\":\""+sourceId+"\", ";
		str += "\"sourceType\":\""+sourceType+"\", ";
		str += "\"status\":\""+status+"\", ";
		str += "\"subCategory\":\""+subCategory+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"userDescription\":\""+userDescription+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}