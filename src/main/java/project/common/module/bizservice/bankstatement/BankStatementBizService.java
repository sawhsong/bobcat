package project.common.module.bizservice.bankstatement;

import java.io.File;

import zebra.data.DataSet;

public interface BankStatementBizService {
	public DataSet getBankStatementDataSetFromFileByBank(String bankCode, File bankStatementFile) throws Exception;
}