/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_ASSET - Asset Entry (Asset data entry for all organisation category)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrAsset;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class UsrAssetHDaoImpl extends BaseHDao implements UsrAssetDao {
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
}