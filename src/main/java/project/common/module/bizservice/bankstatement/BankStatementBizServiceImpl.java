package project.common.module.bizservice.bankstatement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.UsrBankAccnt.UsrBankAccntDao;
import project.conf.resource.ormapper.dao.UsrBankStatement.UsrBankStatementDao;
import project.conf.resource.ormapper.dao.UsrBsTranAlloc.UsrBsTranAllocDao;
import project.conf.resource.ormapper.dto.oracle.UsrBankStatement;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseBiz;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class BankStatementBizServiceImpl extends BaseBiz implements BankStatementBizService {
	@Autowired
	private UsrBankAccntDao usrBankAccntDao;
	@Autowired
	private UsrBankStatementDao usrBankStatementDao;
	@Autowired
	private UsrBsTranAllocDao usrBsTranAllocDao;

	public int doSave(DataSet fileDataSet, DataSet bankFileData) throws Exception {
		UsrBankStatement usrBankStatement = new UsrBankStatement();
		int result = -1;

		try {
			usrBankStatement.setBankStatementId(CommonUtil.uid());
			usrBankStatement.setBankAccntId(bankFileData.getValue("BANK_ACCNT_ID"));
			usrBankStatement.setOriginalFileName(fileDataSet.getValue("ORIGINAL_NAME"));
			usrBankStatement.setNewName(fileDataSet.getValue("NEW_NAME"));
			usrBankStatement.setFileType(fileDataSet.getValue("TYPE"));
			usrBankStatement.setFileIcon(fileDataSet.getValue("ICON"));
			usrBankStatement.setFileSize(CommonUtil.toDouble(fileDataSet.getValue("SIZE")));
			usrBankStatement.setRepositoryPath(fileDataSet.getValue("REPOSITORY_PATH"));
			usrBankStatement.setInsertUserId(bankFileData.getValue("USER_ID"));
			usrBankStatement.setInsertDate(CommonUtil.getSysdateAsDate());

			result = usrBankStatementDao.insert(usrBankStatement, bankFileData);
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}

	public DataSet getBankStatementDataSetFromFileByBank(String bankAccntId, String bankCode, File bankStatementFile) throws Exception {
		DataSet result = new DataSet();

		try {
			if (CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_ANZ"))
					|| CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_CBA"))
					|| CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_NAB"))
					) {
				// Type1 : Commonwealth / ANZ
				result = getDataSetForType1(bankAccntId, bankCode, bankStatementFile);
			} else if (CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_WESTPAC"))) {
				// Type2 : Westpac
				result = getDataSetForType2(bankAccntId, bankCode, bankStatementFile);
			}
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}

	public DataSet getDuplicatedDataSet(DataSet fileData) throws Exception {
		DataSet result = new DataSet(), temp = new DataSet();
		QueryAdvisor qa = new QueryAdvisor();

		if (fileData.getRowCnt() > 0) {
			// Check the first row and the last row
			qa.addWhereClause("to_char(proc_date, 'dd-mm-yyyy') = '"+fileData.getValue(0, "PROC_DATE")+"'");
			qa.addWhereClause("proc_amt = "+fileData.getValue(0, "PROC_AMOUNT")+"");
			qa.addWhereClause("proc_description = '"+fileData.getValue(0, "DESCRIPTION")+"'");
			if (CommonUtil.isNotBlank(fileData.getValue(0, "BALANCE"))) {
				qa.addWhereClause("balance = "+fileData.getValue(0, "BALANCE")+"");
			}
			result = usrBsTranAllocDao.getDataSetByFileDataForDupCheck(qa);

			qa.resetAll();

			qa.addWhereClause("to_char(proc_date, 'dd-mm-yyyy') = '"+fileData.getValue(fileData.getRowCnt()-1, "PROC_DATE")+"'");
			qa.addWhereClause("proc_amt = "+fileData.getValue(fileData.getRowCnt()-1, "PROC_AMOUNT")+"");
			qa.addWhereClause("proc_description = '"+fileData.getValue(fileData.getRowCnt()-1, "DESCRIPTION")+"'");
			if (CommonUtil.isNotBlank(fileData.getValue(fileData.getRowCnt()-1, "BALANCE"))) {
				qa.addWhereClause("balance = "+fileData.getValue(fileData.getRowCnt()-1, "BALANCE")+"");
			}
			temp = usrBsTranAllocDao.getDataSetByFileDataForDupCheck(qa);

			result.merge(temp);
		}

		return result;
	}

	/**
	 * Type1 : [ProcDate, ProcAmount, Description, Balance]
	 */
	private DataSet getDataSetForType1(String bankAccntId, String bankCode, File file) throws Exception {
		DataSet result = new DataSet();
		DataSet usrBankAccnt = new DataSet();
		String header[] = new String[] {"BANK_ACCNT_ID", "BANK_CODE", "ROW_INDEX", "PROC_DATE", "PROC_AMOUNT", "DESCRIPTION", "BALANCE"};
		BufferedReader br = new BufferedReader(new FileReader(file));
		String textLine;
		String dataArr[];
		int index = 0;

		try {
			result.addName(header);

			usrBankAccnt = usrBankAccntDao.getDataSetByBankAccntId(bankAccntId);

			while ((textLine = br.readLine()) != null) {
				if (CommonUtil.isBlank(textLine)) {continue;}
				dataArr = CommonUtil.splitPreserveAllTokens(CommonUtil.removeString(textLine, "\"", "\\"), ",");
//				if (dataArr == null || dataArr.length != 4) {continue;}
				if (CommonUtil.isBlank(dataArr)) {continue;}

				index++;

				result.addRow();
				result.setValue(result.getRowCnt()-1, "BANK_ACCNT_ID", bankAccntId);
				result.setValue(result.getRowCnt()-1, "BANK_CODE", bankCode);
				result.setValue(result.getRowCnt()-1, "ROW_INDEX", CommonUtil.toString(index));
				result.setValue(result.getRowCnt()-1, "PROC_DATE", getValidDateStringForType1(CommonUtil.trim(dataArr[0]))); // Date : dd/MM/yyyy
				result.setValue(result.getRowCnt()-1, "PROC_AMOUNT", CommonUtil.trim(dataArr[1])); // Amount : no format(2 decimal)
				result.setValue(result.getRowCnt()-1, "DESCRIPTION", dataArr[2]); // Desc
				if (dataArr.length >= 4) {
					result.setValue(result.getRowCnt()-1, "BALANCE", CommonUtil.trim(dataArr[3])); // Balance : no format(2 decimal)
				}
			}

			if (result.getRowCnt() > 0) {
				result.addColumn("BANK_NAME", CommonCodeManager.getCodeDescription("BANK_TYPE", bankCode));
				result.addColumn("BSB", CommonUtil.getFormatString(usrBankAccnt.getValue("BSB"), "??? ???"));
				result.addColumn("ACCNT_NUMBER", usrBankAccnt.getValue("ACCNT_NUMBER"));
				result.addColumn("ACCNT_NAME", usrBankAccnt.getValue("ACCNT_NAME"));
				result.addColumn("LAST_BALANCE", usrBankAccnt.getValue("BALANCE"));
			}

			br.close();
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}

	/**
	 * Type2 : [Bank Account, Date, Narrative, Debit Amount, Credit Amount, Balance, Categories, Serial]
	 */
	private DataSet getDataSetForType2(String bankAccntId, String bankCode, File file) throws Exception {
		DataSet result = new DataSet();
		DataSet usrBankAccnt = new DataSet();
		String header[] = new String[] {"BANK_ACCNT_ID", "BANK_CODE", "ROW_INDEX", "BANK_ACCOUNT", "PROC_DATE", "DEBIT_AMOUNT", "CREDIT_AMOUNT", "PROC_AMOUNT", "DESCRIPTION", "BALANCE", "CATEGORIES", "SERIAL"};
		BufferedReader br = new BufferedReader(new FileReader(file));
		String textLine, amtString = "";
		String dataArr[];
		int index = 0;

		try {
			result.addName(header);

			usrBankAccnt = usrBankAccntDao.getDataSetByBankAccntId(bankAccntId);

			while ((textLine = br.readLine()) != null) {
				if (CommonUtil.isBlank(textLine)) {continue;}
				if (CommonUtil.contains(textLine, "Date,Narrative")) {continue;}
				dataArr = CommonUtil.splitPreserveAllTokens(CommonUtil.removeString(textLine, "\"", "\\"), ",");
				if (dataArr == null || dataArr.length != 8) {continue;}
				if (CommonUtil.isBlank(dataArr)) {continue;}

				index++;

				result.addRow();
				result.setValue(result.getRowCnt()-1, "BANK_ACCNT_ID", bankAccntId);
				result.setValue(result.getRowCnt()-1, "BANK_CODE", bankCode);
				result.setValue(result.getRowCnt()-1, "ROW_INDEX", CommonUtil.toString(index));
				result.setValue(result.getRowCnt()-1, "BANK_ACCOUNT", dataArr[0]);
				result.setValue(result.getRowCnt()-1, "PROC_DATE", getValidDateStringForType1(CommonUtil.trim(dataArr[1]))); // Date : dd/MM/yyyy
				result.setValue(result.getRowCnt()-1, "DESCRIPTION", dataArr[2]);
				result.setValue(result.getRowCnt()-1, "DEBIT_AMOUNT", CommonUtil.trim(dataArr[3]));
				result.setValue(result.getRowCnt()-1, "CREDIT_AMOUNT", CommonUtil.trim(dataArr[4]));

				amtString = CommonUtil.isNotBlank(CommonUtil.trim(dataArr[3])) ? "-"+CommonUtil.trim(dataArr[3]) : "";

				result.setValue(result.getRowCnt()-1, "PROC_AMOUNT", CommonUtil.nvl(amtString, CommonUtil.trim(dataArr[4])));
				result.setValue(result.getRowCnt()-1, "BALANCE", CommonUtil.trim(dataArr[5]));
				result.setValue(result.getRowCnt()-1, "CATEGORIES", CommonUtil.trim(dataArr[6]));
				result.setValue(result.getRowCnt()-1, "SERIAL", CommonUtil.trim(dataArr[7]));
			}

			if (result.getRowCnt() > 0) {
				result.addColumn("BANK_NAME", CommonCodeManager.getCodeDescription("BANK_TYPE", bankCode));
				result.addColumn("BSB", CommonUtil.getFormatString(usrBankAccnt.getValue("BSB"), "??? ???"));
				result.addColumn("ACCNT_NUMBER", usrBankAccnt.getValue("ACCNT_NUMBER"));
				result.addColumn("ACCNT_NAME", usrBankAccnt.getValue("ACCNT_NAME"));
				result.addColumn("LAST_BALANCE", usrBankAccnt.getValue("BALANCE"));
			}

			br.close();
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}

	private String getValidDateStringForType1(String value) {
		String rtn = "", dd = "", mm = "";
		String arr[];

		if (!CommonUtil.isBlank(value)) {
			arr = CommonUtil.split(value, "/");

			if (arr == null || arr.length <= 0) {return value;}

			dd = CommonUtil.leftPad(arr[0], 2, "0");
			mm = CommonUtil.leftPad(arr[1], 2, "0");
			rtn = dd+"-"+mm+"-"+arr[2];
		}

		return rtn;
	}
}