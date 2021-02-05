package project.common.module.bizservice.bankstatement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.UsrBankAccnt.UsrBankAccntDao;
import zebra.data.DataSet;
import zebra.example.common.extend.BaseBiz;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class BankStatementBizServiceImpl extends BaseBiz implements BankStatementBizService {
	@Autowired
	private UsrBankAccntDao usrBankAccntDao;

	public DataSet getBankStatementDataSetFromFileByBank(String bankAccntId, String bankCode, File bankStatementFile) throws Exception {
		DataSet result = new DataSet();

		try {
			if (CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_ANZ")) || CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_CBA"))) {
				// Type1 : Commonwealth / ANZ
				result = getDataSetForType1(bankAccntId, bankCode, bankStatementFile);
			}
		} catch (Exception ex) {
			throw new FrameworkException(ex);
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
		String textLine, dateFormat = ConfigUtil.getProperty("format.date.java");
		String dataArr[];
		int index = 0;

		try {
			result.addName(header);

			usrBankAccnt = usrBankAccntDao.getDataSetByBankAccntId(bankAccntId);

			while ((textLine = br.readLine()) != null) {
				if (CommonUtil.isBlank(textLine)) {continue;}
				dataArr = CommonUtil.split(textLine, ",");
				if (dataArr == null || dataArr.length != 4) {continue;}
				if (CommonUtil.isBlank(dataArr)) {continue;}

				index++;

				result.addRow();
				result.setValue(result.getRowCnt()-1, "BANK_ACCNT_ID", bankAccntId);
				result.setValue(result.getRowCnt()-1, "BANK_CODE", bankCode);
				result.setValue(result.getRowCnt()-1, "ROW_INDEX", CommonUtil.toString(index));
				result.setValue(result.getRowCnt()-1, "PROC_DATE", CommonUtil.getDateMask(getValidDateStringForType1(CommonUtil.trim(dataArr[0])), dateFormat)); // Date : dd/MM/yyyy
				result.setValue(result.getRowCnt()-1, "PROC_AMOUNT", CommonUtil.trim(dataArr[1])); // Amount : no format(2 decimal)
				result.setValue(result.getRowCnt()-1, "DESCRIPTION", dataArr[2]); // Desc
				result.setValue(result.getRowCnt()-1, "BALANCE", CommonUtil.trim(dataArr[3])); // Balance : no format(2 decimal)
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