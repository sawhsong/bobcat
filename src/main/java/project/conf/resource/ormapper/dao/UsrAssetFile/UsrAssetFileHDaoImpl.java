/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_ASSET_FILE - Attached file for Bulletin board
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrAssetFile;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrAsset;
import project.conf.resource.ormapper.dto.oracle.UsrAssetFile;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.FileUtil;

public class UsrAssetFileHDaoImpl extends BaseHDao implements UsrAssetFileDao {
	public int insert(UsrAsset usrAsset, DataSet fileDataSet) throws Exception {
		int result = 0;

		for (int i=0; i<fileDataSet.getRowCnt(); i++) {
			UsrAssetFile usrAssetFile = new UsrAssetFile();

			usrAssetFile.setFileId(CommonUtil.uid());
			usrAssetFile.setAssetId(usrAsset.getAssetId());
			usrAssetFile.setOriginalName(fileDataSet.getValue(i, "ORIGINAL_NAME"));
			usrAssetFile.setNewName(fileDataSet.getValue(i, "NEW_NAME"));
			usrAssetFile.setFileType(fileDataSet.getValue(i, "TYPE"));
			usrAssetFile.setFileIcon(fileDataSet.getValue(i, "ICON"));
			usrAssetFile.setFileSize(CommonUtil.toDouble(fileDataSet.getValue(i, "SIZE")));
			usrAssetFile.setRepositoryPath(fileDataSet.getValue(i, "REPOSITORY_PATH"));
			usrAssetFile.setInsertUserId(usrAsset.getInsertUserId());
			usrAssetFile.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));
			usrAssetFile.setUpdateUserId(usrAsset.getInsertUserId());
			usrAssetFile.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result += insertWithDto(usrAssetFile);
		}

		FileUtil.moveFile(fileDataSet);

		return result;
	}

	public int update(String assetId, UsrAsset usrAsset, DataSet fileDataSet) throws Exception {
		int result = 0;
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("asset_id = '"+usrAsset.getAssetId()+"'");
		result = deleteWithSQLQuery(queryAdvisor, new UsrAssetFile());

		for (int i=0; i<fileDataSet.getRowCnt(); i++) {
			UsrAssetFile usrAssetFile = new UsrAssetFile();

			usrAssetFile.setFileId(CommonUtil.uid());
			usrAssetFile.setAssetId(usrAsset.getAssetId());
			usrAssetFile.setOriginalName(fileDataSet.getValue(i, "ORIGINAL_NAME"));
			usrAssetFile.setNewName(fileDataSet.getValue(i, "NEW_NAME"));
			usrAssetFile.setFileType(fileDataSet.getValue(i, "TYPE"));
			usrAssetFile.setFileIcon(fileDataSet.getValue(i, "ICON"));
			usrAssetFile.setFileSize(CommonUtil.toDouble(fileDataSet.getValue(i, "SIZE")));
			usrAssetFile.setRepositoryPath(fileDataSet.getValue(i, "REPOSITORY_PATH"));
			usrAssetFile.setInsertUserId(usrAsset.getInsertUserId());
			usrAssetFile.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));
			usrAssetFile.setUpdateUserId(usrAsset.getInsertUserId());
			usrAssetFile.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result += insertWithDto(usrAssetFile);
		}

		FileUtil.moveFile(fileDataSet);

		return result;
	}
}