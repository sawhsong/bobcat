/**************************************************************************************************
 * Framework Generated DTO Source
 * - USR_INVOICE - Invoice info for additioanl user service
 *************************************************************************************************/
package project.conf.resource.ormapper.dto.oracle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import zebra.data.DataSet;
import zebra.util.CommonUtil;
import project.common.extend.BaseDto;

@SuppressWarnings("unused")
public class UsrInvoice extends BaseDto implements Serializable {
	/**
	 * Columns
	 */
	private String invoiceId;
	private String INVOICE_ID;
	private String invoiceNumber;
	private String INVOICE_NUMBER;
	private Date issueDate;
	private String ISSUE_DATE;
	private String userId;
	private String USER_ID;
	private String additionalRemark;
	private String ADDITIONAL_REMARK;
	private String bankAccntId;
	private String BANK_ACCNT_ID;
	private String bankAccntName;
	private String BANK_ACCNT_NAME;
	private String bankAccntNumber;
	private String BANK_ACCNT_NUMBER;
	private String bankCode;
	private String BANK_CODE;
	private String bsb;
	private String BSB;
	private String clientAddress;
	private String CLIENT_ADDRESS;
	private String clientEmail;
	private String CLIENT_EMAIL;
	private String clientMobile;
	private String CLIENT_MOBILE;
	private String clientName;
	private String CLIENT_NAME;
	private String clientOrgId;
	private String CLIENT_ORG_ID;
	private String clientTelephone;
	private String CLIENT_TELEPHONE;
	private String clientUserId;
	private String CLIENT_USER_ID;
	private String description;
	private String DESCRIPTION;
	private double gstAmt;
	private String GST_AMT;
	private Date insertDate;
	private String INSERT_DATE;
	private String insertUserId;
	private String INSERT_USER_ID;
	private double netAmt;
	private String NET_AMT;
	private Date paymentDueDate;
	private String PAYMENT_DUE_DATE;
	private String paymentMethod;
	private String PAYMENT_METHOD;
	private String providerAbn;
	private String PROVIDER_ABN;
	private String providerAcn;
	private String PROVIDER_ACN;
	private String providerAddress;
	private String PROVIDER_ADDRESS;
	private String providerEmail;
	private String PROVIDER_EMAIL;
	private String providerLogoPath;
	private String PROVIDER_LOGO_PATH;
	private String providerMobile;
	private String PROVIDER_MOBILE;
	private String providerName;
	private String PROVIDER_NAME;
	private String providerOrgId;
	private String PROVIDER_ORG_ID;
	private String providerTelephone;
	private String PROVIDER_TELEPHONE;
	private String quotationId;
	private String QUOTATION_ID;
	private String refNumber;
	private String REF_NUMBER;
	private double totalAmt;
	private String TOTAL_AMT;
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
	public UsrInvoice() throws Exception {
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
		setFrwVarPrimaryKey("INVOICE_ID");
		setFrwVarDateColumn("ISSUE_DATE,INSERT_DATE,PAYMENT_DUE_DATE,UPDATE_DATE");
		setFrwVarNumberColumn("GST_AMT,NET_AMT,TOTAL_AMT");
		setFrwVarClobColumn("");
		setFrwVarDefaultColumn("INSERT_DATE");
		setFrwVarDefaultValue("sysdate");
		setDefaultValue();
	}

	/**
	 * Accessors
	 */
	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) throws Exception {
		this.invoiceId = invoiceId;
		setValueFromAccessor("INVOICE_ID", invoiceId);
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) throws Exception {
		this.invoiceNumber = invoiceNumber;
		setValueFromAccessor("INVOICE_NUMBER", invoiceNumber);
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) throws Exception {
		this.issueDate = issueDate;
		setValueFromAccessor("ISSUE_DATE", CommonUtil.toString(issueDate));
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) throws Exception {
		this.userId = userId;
		setValueFromAccessor("USER_ID", userId);
	}

	public String getAdditionalRemark() {
		return additionalRemark;
	}

	public void setAdditionalRemark(String additionalRemark) throws Exception {
		this.additionalRemark = additionalRemark;
		setValueFromAccessor("ADDITIONAL_REMARK", additionalRemark);
	}

	public String getBankAccntId() {
		return bankAccntId;
	}

	public void setBankAccntId(String bankAccntId) throws Exception {
		this.bankAccntId = bankAccntId;
		setValueFromAccessor("BANK_ACCNT_ID", bankAccntId);
	}

	public String getBankAccntName() {
		return bankAccntName;
	}

	public void setBankAccntName(String bankAccntName) throws Exception {
		this.bankAccntName = bankAccntName;
		setValueFromAccessor("BANK_ACCNT_NAME", bankAccntName);
	}

	public String getBankAccntNumber() {
		return bankAccntNumber;
	}

	public void setBankAccntNumber(String bankAccntNumber) throws Exception {
		this.bankAccntNumber = bankAccntNumber;
		setValueFromAccessor("BANK_ACCNT_NUMBER", bankAccntNumber);
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) throws Exception {
		this.bankCode = bankCode;
		setValueFromAccessor("BANK_CODE", bankCode);
	}

	public String getBsb() {
		return bsb;
	}

	public void setBsb(String bsb) throws Exception {
		this.bsb = bsb;
		setValueFromAccessor("BSB", bsb);
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) throws Exception {
		this.clientAddress = clientAddress;
		setValueFromAccessor("CLIENT_ADDRESS", clientAddress);
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) throws Exception {
		this.clientEmail = clientEmail;
		setValueFromAccessor("CLIENT_EMAIL", clientEmail);
	}

	public String getClientMobile() {
		return clientMobile;
	}

	public void setClientMobile(String clientMobile) throws Exception {
		this.clientMobile = clientMobile;
		setValueFromAccessor("CLIENT_MOBILE", clientMobile);
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) throws Exception {
		this.clientName = clientName;
		setValueFromAccessor("CLIENT_NAME", clientName);
	}

	public String getClientOrgId() {
		return clientOrgId;
	}

	public void setClientOrgId(String clientOrgId) throws Exception {
		this.clientOrgId = clientOrgId;
		setValueFromAccessor("CLIENT_ORG_ID", clientOrgId);
	}

	public String getClientTelephone() {
		return clientTelephone;
	}

	public void setClientTelephone(String clientTelephone) throws Exception {
		this.clientTelephone = clientTelephone;
		setValueFromAccessor("CLIENT_TELEPHONE", clientTelephone);
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) throws Exception {
		this.clientUserId = clientUserId;
		setValueFromAccessor("CLIENT_USER_ID", clientUserId);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) throws Exception {
		this.description = description;
		setValueFromAccessor("DESCRIPTION", description);
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

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) throws Exception {
		this.paymentDueDate = paymentDueDate;
		setValueFromAccessor("PAYMENT_DUE_DATE", CommonUtil.toString(paymentDueDate));
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) throws Exception {
		this.paymentMethod = paymentMethod;
		setValueFromAccessor("PAYMENT_METHOD", paymentMethod);
	}

	public String getProviderAbn() {
		return providerAbn;
	}

	public void setProviderAbn(String providerAbn) throws Exception {
		this.providerAbn = providerAbn;
		setValueFromAccessor("PROVIDER_ABN", providerAbn);
	}

	public String getProviderAcn() {
		return providerAcn;
	}

	public void setProviderAcn(String providerAcn) throws Exception {
		this.providerAcn = providerAcn;
		setValueFromAccessor("PROVIDER_ACN", providerAcn);
	}

	public String getProviderAddress() {
		return providerAddress;
	}

	public void setProviderAddress(String providerAddress) throws Exception {
		this.providerAddress = providerAddress;
		setValueFromAccessor("PROVIDER_ADDRESS", providerAddress);
	}

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) throws Exception {
		this.providerEmail = providerEmail;
		setValueFromAccessor("PROVIDER_EMAIL", providerEmail);
	}

	public String getProviderLogoPath() {
		return providerLogoPath;
	}

	public void setProviderLogoPath(String providerLogoPath) throws Exception {
		this.providerLogoPath = providerLogoPath;
		setValueFromAccessor("PROVIDER_LOGO_PATH", providerLogoPath);
	}

	public String getProviderMobile() {
		return providerMobile;
	}

	public void setProviderMobile(String providerMobile) throws Exception {
		this.providerMobile = providerMobile;
		setValueFromAccessor("PROVIDER_MOBILE", providerMobile);
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) throws Exception {
		this.providerName = providerName;
		setValueFromAccessor("PROVIDER_NAME", providerName);
	}

	public String getProviderOrgId() {
		return providerOrgId;
	}

	public void setProviderOrgId(String providerOrgId) throws Exception {
		this.providerOrgId = providerOrgId;
		setValueFromAccessor("PROVIDER_ORG_ID", providerOrgId);
	}

	public String getProviderTelephone() {
		return providerTelephone;
	}

	public void setProviderTelephone(String providerTelephone) throws Exception {
		this.providerTelephone = providerTelephone;
		setValueFromAccessor("PROVIDER_TELEPHONE", providerTelephone);
	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) throws Exception {
		this.quotationId = quotationId;
		setValueFromAccessor("QUOTATION_ID", quotationId);
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) throws Exception {
		this.refNumber = refNumber;
		setValueFromAccessor("REF_NUMBER", refNumber);
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) throws Exception {
		this.totalAmt = totalAmt;
		setValueFromAccessor("TOTAL_AMT", CommonUtil.toString(totalAmt));
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

		str += "invoiceId : "+invoiceId+"\n";
		str += "invoiceNumber : "+invoiceNumber+"\n";
		str += "issueDate : "+issueDate+"\n";
		str += "userId : "+userId+"\n";
		str += "additionalRemark : "+additionalRemark+"\n";
		str += "bankAccntId : "+bankAccntId+"\n";
		str += "bankAccntName : "+bankAccntName+"\n";
		str += "bankAccntNumber : "+bankAccntNumber+"\n";
		str += "bankCode : "+bankCode+"\n";
		str += "bsb : "+bsb+"\n";
		str += "clientAddress : "+clientAddress+"\n";
		str += "clientEmail : "+clientEmail+"\n";
		str += "clientMobile : "+clientMobile+"\n";
		str += "clientName : "+clientName+"\n";
		str += "clientOrgId : "+clientOrgId+"\n";
		str += "clientTelephone : "+clientTelephone+"\n";
		str += "clientUserId : "+clientUserId+"\n";
		str += "description : "+description+"\n";
		str += "gstAmt : "+gstAmt+"\n";
		str += "insertDate : "+insertDate+"\n";
		str += "insertUserId : "+insertUserId+"\n";
		str += "netAmt : "+netAmt+"\n";
		str += "paymentDueDate : "+paymentDueDate+"\n";
		str += "paymentMethod : "+paymentMethod+"\n";
		str += "providerAbn : "+providerAbn+"\n";
		str += "providerAcn : "+providerAcn+"\n";
		str += "providerAddress : "+providerAddress+"\n";
		str += "providerEmail : "+providerEmail+"\n";
		str += "providerLogoPath : "+providerLogoPath+"\n";
		str += "providerMobile : "+providerMobile+"\n";
		str += "providerName : "+providerName+"\n";
		str += "providerOrgId : "+providerOrgId+"\n";
		str += "providerTelephone : "+providerTelephone+"\n";
		str += "quotationId : "+quotationId+"\n";
		str += "refNumber : "+refNumber+"\n";
		str += "totalAmt : "+totalAmt+"\n";
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

		str += "<column name=\"invoiceId\" value=\""+invoiceId+"\">";
		str += "<column name=\"invoiceNumber\" value=\""+invoiceNumber+"\">";
		str += "<column name=\"issueDate\" value=\""+issueDate+"\">";
		str += "<column name=\"userId\" value=\""+userId+"\">";
		str += "<column name=\"additionalRemark\" value=\""+additionalRemark+"\">";
		str += "<column name=\"bankAccntId\" value=\""+bankAccntId+"\">";
		str += "<column name=\"bankAccntName\" value=\""+bankAccntName+"\">";
		str += "<column name=\"bankAccntNumber\" value=\""+bankAccntNumber+"\">";
		str += "<column name=\"bankCode\" value=\""+bankCode+"\">";
		str += "<column name=\"bsb\" value=\""+bsb+"\">";
		str += "<column name=\"clientAddress\" value=\""+clientAddress+"\">";
		str += "<column name=\"clientEmail\" value=\""+clientEmail+"\">";
		str += "<column name=\"clientMobile\" value=\""+clientMobile+"\">";
		str += "<column name=\"clientName\" value=\""+clientName+"\">";
		str += "<column name=\"clientOrgId\" value=\""+clientOrgId+"\">";
		str += "<column name=\"clientTelephone\" value=\""+clientTelephone+"\">";
		str += "<column name=\"clientUserId\" value=\""+clientUserId+"\">";
		str += "<column name=\"description\" value=\""+description+"\">";
		str += "<column name=\"gstAmt\" value=\""+gstAmt+"\">";
		str += "<column name=\"insertDate\" value=\""+insertDate+"\">";
		str += "<column name=\"insertUserId\" value=\""+insertUserId+"\">";
		str += "<column name=\"netAmt\" value=\""+netAmt+"\">";
		str += "<column name=\"paymentDueDate\" value=\""+paymentDueDate+"\">";
		str += "<column name=\"paymentMethod\" value=\""+paymentMethod+"\">";
		str += "<column name=\"providerAbn\" value=\""+providerAbn+"\">";
		str += "<column name=\"providerAcn\" value=\""+providerAcn+"\">";
		str += "<column name=\"providerAddress\" value=\""+providerAddress+"\">";
		str += "<column name=\"providerEmail\" value=\""+providerEmail+"\">";
		str += "<column name=\"providerLogoPath\" value=\""+providerLogoPath+"\">";
		str += "<column name=\"providerMobile\" value=\""+providerMobile+"\">";
		str += "<column name=\"providerName\" value=\""+providerName+"\">";
		str += "<column name=\"providerOrgId\" value=\""+providerOrgId+"\">";
		str += "<column name=\"providerTelephone\" value=\""+providerTelephone+"\">";
		str += "<column name=\"quotationId\" value=\""+quotationId+"\">";
		str += "<column name=\"refNumber\" value=\""+refNumber+"\">";
		str += "<column name=\"totalAmt\" value=\""+totalAmt+"\">";
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

		str += "\"invoiceId\":\""+invoiceId+"\", ";
		str += "\"invoiceNumber\":\""+invoiceNumber+"\", ";
		str += "\"issueDate\":\""+issueDate+"\", ";
		str += "\"userId\":\""+userId+"\", ";
		str += "\"additionalRemark\":\""+additionalRemark+"\", ";
		str += "\"bankAccntId\":\""+bankAccntId+"\", ";
		str += "\"bankAccntName\":\""+bankAccntName+"\", ";
		str += "\"bankAccntNumber\":\""+bankAccntNumber+"\", ";
		str += "\"bankCode\":\""+bankCode+"\", ";
		str += "\"bsb\":\""+bsb+"\", ";
		str += "\"clientAddress\":\""+clientAddress+"\", ";
		str += "\"clientEmail\":\""+clientEmail+"\", ";
		str += "\"clientMobile\":\""+clientMobile+"\", ";
		str += "\"clientName\":\""+clientName+"\", ";
		str += "\"clientOrgId\":\""+clientOrgId+"\", ";
		str += "\"clientTelephone\":\""+clientTelephone+"\", ";
		str += "\"clientUserId\":\""+clientUserId+"\", ";
		str += "\"description\":\""+description+"\", ";
		str += "\"gstAmt\":\""+gstAmt+"\", ";
		str += "\"insertDate\":\""+insertDate+"\", ";
		str += "\"insertUserId\":\""+insertUserId+"\", ";
		str += "\"netAmt\":\""+netAmt+"\", ";
		str += "\"paymentDueDate\":\""+paymentDueDate+"\", ";
		str += "\"paymentMethod\":\""+paymentMethod+"\", ";
		str += "\"providerAbn\":\""+providerAbn+"\", ";
		str += "\"providerAcn\":\""+providerAcn+"\", ";
		str += "\"providerAddress\":\""+providerAddress+"\", ";
		str += "\"providerEmail\":\""+providerEmail+"\", ";
		str += "\"providerLogoPath\":\""+providerLogoPath+"\", ";
		str += "\"providerMobile\":\""+providerMobile+"\", ";
		str += "\"providerName\":\""+providerName+"\", ";
		str += "\"providerOrgId\":\""+providerOrgId+"\", ";
		str += "\"providerTelephone\":\""+providerTelephone+"\", ";
		str += "\"quotationId\":\""+quotationId+"\", ";
		str += "\"refNumber\":\""+refNumber+"\", ";
		str += "\"totalAmt\":\""+totalAmt+"\", ";
		str += "\"updateDate\":\""+updateDate+"\", ";
		str += "\"updateUserId\":\""+updateUserId+"\", ";
		str += "\"insertUserName\":\""+insertUserName+"\", ";
		str += "\"updateUserName\":\""+updateUserName+"\"";

		return str;
	}
}