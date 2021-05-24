package project.common.module.bizservice.ccstatement;

import java.io.File;

import zebra.data.DataSet;

public interface CcStatementBizService {
	public int doSave(DataSet fileDataSet, DataSet bankFileData) throws Exception;

	public DataSet getCcStatementDataSetFromFileByBank(String bankAccntId, String bankCode, File ccStatementFile) throws Exception;
	public DataSet getDuplicatedDataSet(DataSet fileData) throws Exception;
}