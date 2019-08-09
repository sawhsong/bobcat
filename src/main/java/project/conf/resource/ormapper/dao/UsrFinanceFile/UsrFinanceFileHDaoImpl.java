/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_FINANCE_FILE - Attached file for usr_finance table
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrFinanceFile;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrAssetFile;
import project.conf.resource.ormapper.dto.oracle.UsrFinance;
import project.conf.resource.ormapper.dto.oracle.UsrFinanceFile;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.FileUtil;

public class UsrFinanceFileHDaoImpl extends BaseHDao implements UsrFinanceFileDao {
	public int insert(UsrFinance usrFinance, DataSet fileDataSet) throws Exception {
		int result = 0;

		for (int i=0; i<fileDataSet.getRowCnt(); i++) {
			UsrFinanceFile usrFinanceFile = new UsrFinanceFile();

			usrFinanceFile.setFileId(CommonUtil.uid());
			usrFinanceFile.setFinanceId(usrFinance.getFinanceId());
			usrFinanceFile.setOriginalName(fileDataSet.getValue(i, "ORIGINAL_NAME"));
			usrFinanceFile.setNewName(fileDataSet.getValue(i, "NEW_NAME"));
			usrFinanceFile.setFileType(fileDataSet.getValue(i, "TYPE"));
			usrFinanceFile.setFileIcon(fileDataSet.getValue(i, "ICON"));
			usrFinanceFile.setFileSize(CommonUtil.toDouble(fileDataSet.getValue(i, "SIZE")));
			usrFinanceFile.setRepositoryPath(fileDataSet.getValue(i, "REPOSITORY_PATH"));
			usrFinanceFile.setInsertUserId(usrFinance.getInsertUserId());
			usrFinanceFile.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));
			usrFinanceFile.setUpdateUserId(usrFinance.getInsertUserId());
			usrFinanceFile.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result += insertWithDto(usrFinanceFile);
		}

		FileUtil.moveFile(fileDataSet);

		return result;
	}

	public int update(String financeId, UsrFinance usrFinance, DataSet fileDataSet) throws Exception {
		int result = 0;
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("finance_id = '"+financeId+"'");
		deletePhysicalFileByAssetId(financeId);
		result = deleteWithSQLQuery(queryAdvisor, new UsrFinanceFile());

		for (int i=0; i<fileDataSet.getRowCnt(); i++) {
			UsrFinanceFile usrFinanceFile = new UsrFinanceFile();

			usrFinanceFile.setFileId(CommonUtil.uid());
			usrFinanceFile.setFinanceId(usrFinance.getFinanceId());
			usrFinanceFile.setOriginalName(fileDataSet.getValue(i, "ORIGINAL_NAME"));
			usrFinanceFile.setNewName(fileDataSet.getValue(i, "NEW_NAME"));
			usrFinanceFile.setFileType(fileDataSet.getValue(i, "TYPE"));
			usrFinanceFile.setFileIcon(fileDataSet.getValue(i, "ICON"));
			usrFinanceFile.setFileSize(CommonUtil.toDouble(fileDataSet.getValue(i, "SIZE")));
			usrFinanceFile.setRepositoryPath(fileDataSet.getValue(i, "REPOSITORY_PATH"));
			usrFinanceFile.setInsertUserId(usrFinance.getInsertUserId());
			usrFinanceFile.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));
			usrFinanceFile.setUpdateUserId(usrFinance.getInsertUserId());
			usrFinanceFile.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result += insertWithDto(usrFinanceFile);
		}

		FileUtil.moveFile(fileDataSet);

		return result;
	}

	public int deleteByFinanceId(String financeId) throws Exception {
		int result = 0;
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("finance_id = '"+financeId+"'");
		deletePhysicalFileByAssetId(financeId);
		result = deleteWithSQLQuery(queryAdvisor, new UsrFinanceFile());

		return result;
	}

	public DataSet getFinanceFileDataSetByFileId(String financeFileId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("file_id = '"+financeFileId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrFinanceFile());
	}

	private void deletePhysicalFileByAssetId(String assetId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("asset_id = '"+assetId+"'");
		FileUtil.deleteFile(selectAllAsDataSet(queryAdvisor, new UsrAssetFile()));
	}
}