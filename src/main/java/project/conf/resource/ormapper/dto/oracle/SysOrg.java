/**************************************************************************************************
 * Framework Generated DTO Source
 * - SYS_ORG - Organisation Info
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class SysOrg extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String orgId;
	private String ORG_ID;
	private String baseType;
	private String BASE_TYPE;
	private String businessType;
	private String BUSINESS_TYPE;
	private String entityType;
	private String ENTITY_TYPE;
	private String legalName;
	private String LEGAL_NAME;
	private String orgCategory;
	private String ORG_CATEGORY;
	private String abn;
	private String ABN;
	private String address;
	private String ADDRESS;
	private String email;
	private String EMAIL;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private String isActive;
	private String IS_ACTIVE;
	private String logoPath;
	private String LOGO_PATH;
	private Date registeredDate;
	private String REGISTERED_DATE;
	private double revenueRangeFrom;
	private String REVENUE_RANGE_FROM;
	private double revenueRangeTo;
	private String REVENUE_RANGE_TO;
	private String tradingName;
	private String TRADING_NAME;
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
	public SysOrg() throws Exception {
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
		setFrwVarPrimaryKey("ORG_ID");
		setFrwVarDateColumn("INSERT_DATE,REGISTERED_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("REVENUE_RANGE_FROM,REVENUE_RANGE_TO");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) throws Exception {
		this.orgId = orgId;
		setValueFromAccessor("ORG_ID", orgId);
	}

	public String getBaseType() {
		return baseType;
	}

	public void setBaseType(String baseType) throws Exception {
		this.baseType = baseType;
		setValueFromAccessor("BASE_TYPE", baseType);
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) throws Exception {
		this.businessType = businessType;
		setValueFromAccessor("BUSINESS_TYPE", businessType);
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) throws Exception {
		this.entityType = entityType;
		setValueFromAccessor("ENTITY_TYPE", entityType);
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) throws Exception {
		this.legalName = legalName;
		setValueFromAccessor("LEGAL_NAME", legalName);
	}

	public String getOrgCategory() {
		return orgCategory;
	}

	public void setOrgCategory(String orgCategory) throws Exception {
		this.orgCategory = orgCategory;
		setValueFromAccessor("ORG_CATEGORY", orgCategory);
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) throws Exception {
		this.abn = abn;
		setValueFromAccessor("ABN", abn);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws Exception {
		this.address = address;
		setValueFromAccessor("ADDRESS", address);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		this.email = email;
		setValueFromAccessor("EMAIL", email);
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) throws Exception {
		this.isActive = isActive;
		setValueFromAccessor("IS_ACTIVE", isActive);
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) throws Exception {
		this.logoPath = logoPath;
		setValueFromAccessor("LOGO_PATH", logoPath);
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) throws Exception {
		this.registeredDate = registeredDate;
		setValueFromAccessor("REGISTERED_DATE", CommonUtil.toString(registeredDate));
	}

	public double getRevenueRangeFrom() {
		return revenueRangeFrom;
	}

	public void setRevenueRangeFrom(double revenueRangeFrom) throws Exception {
		this.revenueRangeFrom = revenueRangeFrom;
		setValueFromAccessor("REVENUE_RANGE_FROM", CommonUtil.toString(revenueRangeFrom));
	}

	public double getRevenueRangeTo() {
		return revenueRangeTo;
	}

	public void setRevenueRangeTo(double revenueRangeTo) throws Exception {
		this.revenueRangeTo = revenueRangeTo;
		setValueFromAccessor("REVENUE_RANGE_TO", CommonUtil.toString(revenueRangeTo));
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) throws Exception {
		this.tradingName = tradingName;
		setValueFromAccessor("TRADING_NAME", tradingName);
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

		str += "orgId : "+orgId+"\n";
		str += "baseType : "+baseType+"\n";
		str += "businessType : "+businessType+"\n";
		str += "entityType : "+entityType+"\n";
		str += "legalName : "+legalName+"\n";
		str += "orgCategory : "+orgCategory+"\n";
		str += "abn : "+abn+"\n";
		str += "address : "+address+"\n";
		str += "email : "+email+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "isActive : "+isActive+"\n";
		str += "logoPath : "+logoPath+"\n";
		str += "registeredDate : "+registeredDate+"\n";
		str += "revenueRangeFrom : "+revenueRangeFrom+"\n";
		str += "revenueRangeTo : "+revenueRangeTo+"\n";
		str += "tradingName : "+tradingName+"\n";
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

		str += "<column name=\"orgId\" value=\""+orgId+"\">";
		str += "<column name=\"baseType\" value=\""+baseType+"\">";
		str += "<column name=\"businessType\" value=\""+businessType+"\">";
		str += "<column name=\"entityType\" value=\""+entityType+"\">";
		str += "<column name=\"legalName\" value=\""+legalName+"\">";
		str += "<column name=\"orgCategory\" value=\""+orgCategory+"\">";
		str += "<column name=\"abn\" value=\""+abn+"\">";
		str += "<column name=\"address\" value=\""+address+"\">";
		str += "<column name=\"email\" value=\""+email+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"isActive\" value=\""+isActive+"\">";
		str += "<column name=\"logoPath\" value=\""+logoPath+"\">";
		str += "<column name=\"registeredDate\" value=\""+registeredDate+"\">";
		str += "<column name=\"revenueRangeFrom\" value=\""+revenueRangeFrom+"\">";
		str += "<column name=\"revenueRangeTo\" value=\""+revenueRangeTo+"\">";
		str += "<column name=\"tradingName\" value=\""+tradingName+"\">";
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

		str += "\"orgId\":\""+orgId+"\", ";
		str += "\"baseType\":\""+baseType+"\", ";
		str += "\"businessType\":\""+businessType+"\", ";
		str += "\"entityType\":\""+entityType+"\", ";
		str += "\"legalName\":\""+legalName+"\", ";
		str += "\"orgCategory\":\""+orgCategory+"\", ";
		str += "\"abn\":\""+abn+"\", ";
		str += "\"address\":\""+address+"\", ";
		str += "\"email\":\""+email+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"isActive\":\""+isActive+"\", ";
		str += "\"logoPath\":\""+logoPath+"\", ";
		str += "\"registeredDate\":\""+registeredDate+"\", ";
		str += "\"revenueRangeFrom\":\""+revenueRangeFrom+"\", ";
		str += "\"revenueRangeTo\":\""+revenueRangeTo+"\", ";
		str += "\"tradingName\":\""+tradingName+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}