package project.common.module.bizservice.ccstatement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.UsrBankAccnt.UsrBankAccntDao;
import project.conf.resource.ormapper.dao.UsrCcAlloc.UsrCcAllocDao;
import project.conf.resource.ormapper.dao.UsrCcStatement.UsrCcStatementDao;
import project.conf.resource.ormapper.dto.oracle.UsrCcStatement;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseBiz;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class CcStatementBizServiceImpl extends BaseBiz implements CcStatementBizService {
	@Autowired
	private UsrBankAccntDao usrBankAccntDao;
	@Autowired
	private UsrCcStatementDao usrCcStatementDao;
	@Autowired
	private UsrCcAllocDao usrCcAllocDao;

	public int doSave(DataSet fileDataSet, DataSet ccFileData) throws Exception {
		UsrCcStatement usrCcStatement = new UsrCcStatement();
		int result = -1;

		try {
			usrCcStatement.setCcStatementId(CommonUtil.uid());
			usrCcStatement.setBankAccntId(ccFileData.getValue("BANK_ACCNT_ID"));
			usrCcStatement.setOriginalFileName(fileDataSet.getValue("ORIGINAL_NAME"));
			usrCcStatement.setNewName(fileDataSet.getValue("NEW_NAME"));
			usrCcStatement.setFileType(fileDataSet.getValue("TYPE"));
			usrCcStatement.setFileIcon(fileDataSet.getValue("ICON"));
			usrCcStatement.setFileSize(CommonUtil.toDouble(fileDataSet.getValue("SIZE")));
			usrCcStatement.setRepositoryPath(fileDataSet.getValue("REPOSITORY_PATH"));
			usrCcStatement.setInsertUserId(ccFileData.getValue("USER_ID"));
			usrCcStatement.setInsertDate(CommonUtil.getSysdateAsDate());

			result = usrCcStatementDao.insert(usrCcStatement, ccFileData);
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}

	public DataSet getCcStatementDataSetFromFileByBank(String bankAccntId, String bankCode, File ccStatementFile) throws Exception {
		DataSet result = new DataSet();

		try {
			if (CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_ANZ"))
					|| CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_CBA"))
					|| CommonUtil.equalsIgnoreCase(bankCode, CommonCodeManager.getCodeByConstants("BANK_TYPE_NAB"))
					) {
				// Type1 : Commonwealth / ANZ
				result = getDataSetForType1(bankAccntId, bankCode, ccStatementFile);
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
			result = usrCcAllocDao.getDataSetByFileDataForDupCheck(qa);

			qa.resetAll();

			qa.addWhereClause("to_char(proc_date, 'dd-mm-yyyy') = '"+fileData.getValue(fileData.getRowCnt()-1, "PROC_DATE")+"'");
			qa.addWhereClause("proc_amt = "+fileData.getValue(fileData.getRowCnt()-1, "PROC_AMOUNT")+"");
			qa.addWhereClause("proc_description = '"+fileData.getValue(fileData.getRowCnt()-1, "DESCRIPTION")+"'");
			temp = usrCcAllocDao.getDataSetByFileDataForDupCheck(qa);

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
		String header[] = new String[] {"BANK_ACCNT_ID", "BANK_CODE", "ROW_INDEX", "PROC_DATE", "PROC_AMOUNT", "DESCRIPTION"};
		BufferedReader br = new BufferedReader(new FileReader(file));
		String textLine;
		String dataArr[];
		int index = 0;

		try {
			result.addName(header);

			usrBankAccnt = usrBankAccntDao.getDataSetByBankAccntId(bankAccntId);

			while ((textLine = br.readLine()) != null) {
				if (CommonUtil.isBlank(textLine)) {continue;}
				dataArr = CommonUtil.splitPreserveAllTokens(textLine, ",");
				if (dataArr == null || dataArr.length != 3) {continue;}
				if (CommonUtil.isBlank(dataArr)) {continue;}

				index++;

				result.addRow();
				result.setValue(result.getRowCnt()-1, "BANK_ACCNT_ID", bankAccntId);
				result.setValue(result.getRowCnt()-1, "BANK_CODE", bankCode);
				result.setValue(result.getRowCnt()-1, "ROW_INDEX", CommonUtil.toString(index));
				result.setValue(result.getRowCnt()-1, "PROC_DATE", getValidDateStringForType1(CommonUtil.trim(dataArr[0]))); // Date : dd/MM/yyyy
				result.setValue(result.getRowCnt()-1, "PROC_AMOUNT", CommonUtil.trim(dataArr[1])); // Amount : no format(2 decimal)
				result.setValue(result.getRowCnt()-1, "DESCRIPTION", dataArr[2]); // Desc
			}

			if (result.getRowCnt() > 0) {
				result.addColumn("BANK_NAME", CommonCodeManager.getCodeDescription("BANK_TYPE", bankCode));
				result.addColumn("BSB", CommonUtil.getFormatString(usrBankAccnt.getValue("BSB"), "??? ???"));
				result.addColumn("ACCNT_NUMBER", usrBankAccnt.getValue("ACCNT_NUMBER"));
				result.addColumn("ACCNT_NAME", usrBankAccnt.getValue("ACCNT_NAME"));
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