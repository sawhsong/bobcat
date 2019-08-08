/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_ASSET_FILE - Attached file for Bulletin board
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrAssetFile;

import project.conf.resource.ormapper.dto.oracle.UsrAsset;
import zebra.base.IDao;
import zebra.data.DataSet;

public interface UsrAssetFileDao extends IDao {
	public int insert(UsrAsset usrAsset, DataSet fileDataSet) throws Exception;
	public int update(String assetId, UsrAsset usrAsset, DataSet fileDataSet) throws Exception;
	public int deleteByAssetId(String assetId) throws Exception;
	public DataSet getAssetFileDataSetByAssetFileId(String assetFileId) throws Exception;
}