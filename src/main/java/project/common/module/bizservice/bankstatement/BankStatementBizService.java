package project.common.module.bizservice.bankstatement;

import java.io.File;

import zebra.data.DataSet;

public interface BankStatementBizService {
	public int doSave(DataSet fileDataSet, DataSet bankFileData) throws Exception;

	public DataSet getBankStatementDataSetFromFileByBank(String bankAccntId, String bankCode, File bankStatementFile) throws Exception;
}