/**************************************************************************************************
 * Framework Generated DTO Source
 * - SYS_USER - User Info
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class SysUser extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String userId;
	private String USER_ID;
	private String authGroupId;
	private String AUTH_GROUP_ID;
	private String isActive;
	private String IS_ACTIVE;
	private String language;
	private String LANGUAGE;
	private String loginId;
	private String LOGIN_ID;
	private String loginPassword;
	private String LOGIN_PASSWORD;
	private double maxRowPerPage;
	private String MAX_ROW_PER_PAGE;
	private double pageNumPerPage;
	private String PAGE_NUM_PER_PAGE;
	private String orgId;
	private String ORG_ID;
	private String themeType;
	private String THEME_TYPE;
	private String userName;
	private String USER_NAME;
	private String userStatus;
	private String USER_STATUS;
	private String userType;
	private String USER_TYPE;
	private String email;
	private String EMAIL;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private String photoPath;
	private String PHOTO_PATH;
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
	public SysUser() throws Exception {
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
		setFrwVarPrimaryKey("USER_ID");
		setFrwVarDateColumn("DISABLED_DATE,INSERT_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("MAX_ROW_PER_PAGE,PAGE_NUM_PER_PAGE");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("AUTH_GROUP_ID,INSERT_DATE");
		setFrwVarDefaultValue("Z,sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) throws Exception {
		this.userId = userId;
		setValueFromAccessor("USER_ID", userId);
	}

	public String getAuthGroupId() {
		return authGroupId;
	}

	public void setAuthGroupId(String authGroupId) throws Exception {
		this.authGroupId = authGroupId;
		setValueFromAccessor("AUTH_GROUP_ID", authGroupId);
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) throws Exception {
		this.isActive = isActive;
		setValueFromAccessor("IS_ACTIVE", isActive);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) throws Exception {
		this.language = language;
		setValueFromAccessor("LANGUAGE", language);
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) throws Exception {
		this.loginId = loginId;
		setValueFromAccessor("LOGIN_ID", loginId);
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) throws Exception {
		this.loginPassword = loginPassword;
		setValueFromAccessor("LOGIN_PASSWORD", loginPassword);
	}

	public double getMaxRowPerPage() {
		return maxRowPerPage;
	}

	public void setMaxRowPerPage(double maxRowPerPage) throws Exception {
		this.maxRowPerPage = maxRowPerPage;
		setValueFromAccessor("MAX_ROW_PER_PAGE", CommonUtil.toString(maxRowPerPage));
	}

	public double getPageNumPerPage() {
		return pageNumPerPage;
	}

	public void setPageNumPerPage(double pageNumPerPage) throws Exception {
		this.pageNumPerPage = pageNumPerPage;
		setValueFromAccessor("PAGE_NUM_PER_PAGE", CommonUtil.toString(pageNumPerPage));
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) throws Exception {
		this.orgId = orgId;
		setValueFromAccessor("ORG_ID", orgId);
	}

	public String getThemeType() {
		return themeType;
	}

	public void setThemeType(String themeType) throws Exception {
		this.themeType = themeType;
		setValueFromAccessor("THEME_TYPE", themeType);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) throws Exception {
		this.userName = userName;
		setValueFromAccessor("USER_NAME", userName);
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) throws Exception {
		this.userStatus = userStatus;
		setValueFromAccessor("USER_STATUS", userStatus);
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) throws Exception {
		this.userType = userType;
		setValueFromAccessor("USER_TYPE", userType);
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

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) throws Exception {
		this.photoPath = photoPath;
		setValueFromAccessor("PHOTO_PATH", photoPath);
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

		str += "userId : "+userId+"\n";
		str += "authGroupId : "+authGroupId+"\n";
		str += "isActive : "+isActive+"\n";
		str += "language : "+language+"\n";
		str += "loginId : "+loginId+"\n";
		str += "loginPassword : "+loginPassword+"\n";
		str += "maxRowPerPage : "+maxRowPerPage+"\n";
		str += "pageNumPerPage : "+pageNumPerPage+"\n";
		str += "orgId : "+orgId+"\n";
		str += "themeType : "+themeType+"\n";
		str += "userName : "+userName+"\n";
		str += "userStatus : "+userStatus+"\n";
		str += "userType : "+userType+"\n";
		str += "email : "+email+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "photoPath : "+photoPath+"\n";
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

		str += "<column name=\"userId\" value=\""+userId+"\">";
		str += "<column name=\"authGroupId\" value=\""+authGroupId+"\">";
		str += "<column name=\"isActive\" value=\""+isActive+"\">";
		str += "<column name=\"language\" value=\""+language+"\">";
		str += "<column name=\"loginId\" value=\""+loginId+"\">";
		str += "<column name=\"loginPassword\" value=\""+loginPassword+"\">";
		str += "<column name=\"maxRowPerPage\" value=\""+maxRowPerPage+"\">";
		str += "<column name=\"pageNumPerPage\" value=\""+pageNumPerPage+"\">";
		str += "<column name=\"orgId\" value=\""+orgId+"\">";
		str += "<column name=\"themeType\" value=\""+themeType+"\">";
		str += "<column name=\"userName\" value=\""+userName+"\">";
		str += "<column name=\"userStatus\" value=\""+userStatus+"\">";
		str += "<column name=\"userType\" value=\""+userType+"\">";
		str += "<column name=\"email\" value=\""+email+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"photoPath\" value=\""+photoPath+"\">";
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

		str += "\"userId\":\""+userId+"\", ";
		str += "\"authGroupId\":\""+authGroupId+"\", ";
		str += "\"isActive\":\""+isActive+"\", ";
		str += "\"language\":\""+language+"\", ";
		str += "\"loginId\":\""+loginId+"\", ";
		str += "\"loginPassword\":\""+loginPassword+"\", ";
		str += "\"maxRowPerPage\":\""+maxRowPerPage+"\", ";
		str += "\"pageNumPerPage\":\""+pageNumPerPage+"\", ";
		str += "\"orgId\":\""+orgId+"\", ";
		str += "\"themeType\":\""+themeType+"\", ";
		str += "\"userName\":\""+userName+"\", ";
		str += "\"userStatus\":\""+userStatus+"\", ";
		str += "\"userType\":\""+userType+"\", ";
		str += "\"email\":\""+email+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"photoPath\":\""+photoPath+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}