package project.common.module.bizservice.bankstatement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import project.common.module.commoncode.CommonCodeManager;
import zebra.data.DataSet;
import zebra.example.common.extend.BaseBiz;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class BankStatementBizServiceImpl extends BaseBiz implements BankStatementBizService {
	public DataSet getBankStatementDataSetFromFileByBank(String bankCode, File bankStatementFile) throws Exception {
		DataSet result = new DataSet();

		try {
			if (CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_ANZ")) || CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_CBA"))) {
				// Type1 : Commonwealth / ANZ
				result = getDataSetForType1(bankStatementFile);
			}
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}

	/**
	 * Type1 : [ProcDate, ProcAmount, Description, Balance]
	 */
	private DataSet getDataSetForType1(File file) throws Exception {
		DataSet result = new DataSet();
		String header[] = new String[] {"ROW_INDEX", "PROC_DATE", "PROC_AMOUNT", "DESCRIPTION", "BALANCE"};
		BufferedReader br = new BufferedReader(new FileReader(file));
		String textLine;
		String dataArr[];
		int index = 0;

		try {
			result.addName(header);

			while ((textLine = br.readLine()) != null) {
				if (CommonUtil.isBlank(textLine)) {continue;}
				dataArr = CommonUtil.split(textLine);
				if (CommonUtil.isBlank(dataArr)) {continue;}

				index++;

				result.addRow();
				result.setValue(result.getRowCnt()-1, "ROW_INDEX", CommonUtil.toString(index));
				result.setValue(result.getRowCnt()-1, "PROC_DATE", CommonUtil.getDateMask(CommonUtil.trim(dataArr[0]), "dd/MM/yyyy")); // Date : dd/MM/yyyy
				result.setValue(result.getRowCnt()-1, "PROC_AMOUNT", CommonUtil.trim(dataArr[1])); // Amount : no format(2 decimal)
				result.setValue(result.getRowCnt()-1, "DESCRIPTION", dataArr[2]); // Desc
				result.setValue(result.getRowCnt()-1, "BALANCE", CommonUtil.trim(dataArr[3])); // Balance : no format(2 decimal)
			}
			br.close();
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}
}