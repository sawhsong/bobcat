/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_ASSET - Asset Entry (Asset data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrAsset;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dao.UsrAssetFile.UsrAssetFileDao;
import project.conf.resource.ormapper.dto.oracle.UsrAsset;
import zebra.base.Dto;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class UsrAssetHDaoImpl extends BaseHDao implements UsrAssetDao {
	@Autowired
	private UsrAssetFileDao usrAssetFileDao;

	public int insert(Dto dto, DataSet fileDataSet) throws Exception {
		insertWithSQLQuery(dto);
		usrAssetFileDao.insert((UsrAsset)dto, fileDataSet);
		return 1;
	}

	public int update(String assetId, Dto dto, DataSet fileDataSet) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("asset_id = '"+assetId+"'");
		updateWithSQLQuery(queryAdvisor, dto);
		usrAssetFileDao.update(assetId, (UsrAsset)dto, fileDataSet);
		return 1;
	}

	public DataSet getAssetSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrAsset.getAssetSummaryDataSet");
	}

	public DataSet getAssetDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String assetType = requestDataSet.getValue("assetType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(assetType, "sat.asset_type = '"+assetType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("uat.asset_date desc, sat.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrAsset.getAssetDataSetByCriteria");
	}

	public DataSet getAssetDataSetByAssetIdForUpdate(String assetId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addWhereClause("uat.asset_id = '"+assetId+"'");
		queryAdvisor.addOrderByClause("uat.asset_date desc, sat.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrAsset.getAssetDataSetByAssetIdForUpdate");
	}

	public UsrAsset getAssetById(String assetId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("asset_id = '"+assetId+"'");
		return (UsrAsset)selectAllToDto(queryAdvisor, new UsrAsset());
	}
}