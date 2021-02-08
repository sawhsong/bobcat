/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_ORG - Organisation Info
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysOrg;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysOrg;
import zebra.base.Dto;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class SysOrgHDaoImpl extends BaseHDao implements SysOrgDao {
	public int insert(Dto dto) throws Exception {
		return insertWithSQLQuery(dto);
	}

	public int update(String orgId, Dto dto) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_id = '"+orgId+"'");
		return updateColumns(queryAdvisor, dto);
	}

	public int delete(String orgIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;
		String idsForDel = "";

		if (!(orgIds == null || orgIds.length == 0)) {
			for (int i=0; i<orgIds.length; i++) {
				idsForDel += CommonUtil.isBlank(idsForDel) ? "'"+orgIds[i]+"'" : ",'"+orgIds[i]+"'";
			}
			queryAdvisor.addWhereClause("org_id in ("+idsForDel+")");
			result = deleteWithSQLQuery(queryAdvisor, new SysOrg());
		}
		return result;
	}

	public int delete(String orgId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_id = '"+orgId+"'");
		return deleteWithSQLQuery(queryAdvisor, new SysOrg());
	}

	public DataSet getOrgNameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.SysOrg.getOrgNameDataSetForAutoCompletion");
	}

	public DataSet getAbnDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.SysOrg.getAbnDataSetForAutoCompletion");
	}

	public DataSet getOrgIdDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.SysOrg.getOrgIdDataSetForAutoCompletion");
	}

	public DataSet getOrgInfoDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.SysOrg.getOrgInfoDataSetForAutoCompletion");
	}

	public DataSet getOrgDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String langCode = (String)queryAdvisor.getObject("langCode");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String orgName = requestDataSet.getValue("orgName");
		String abn = requestDataSet.getValue("abn");
		String entityType = requestDataSet.getValue("entityType");
		String businessType = requestDataSet.getValue("businessType");
		String orgCategory = requestDataSet.getValue("orgCategory");

		queryAdvisor.addAutoFillCriteria(orgName, "lower(legal_name||' '||trading_name) like lower('%"+orgName+"%')");
		queryAdvisor.addAutoFillCriteria(abn, "abn like '%"+abn+"%'");
		queryAdvisor.addAutoFillCriteria(entityType, "entity_type = '"+entityType+"'");
		queryAdvisor.addAutoFillCriteria(businessType, "business_type = '"+businessType+"'");
		queryAdvisor.addAutoFillCriteria(orgCategory, "org_category = '"+orgCategory+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("legal_name asc");

		return selectAsDataSet(queryAdvisor, "query.SysOrg.getOrgDataSetByCriteria");
	}

	public SysOrg getOrgByOrgId(String orgId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_id = '"+orgId+"'");
		return (SysOrg)selectAllToDto(queryAdvisor, new SysOrg());
	}

	public DataSet getDataSetByOrgId(String orgId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_id = '"+orgId+"'");
		return selectAllAsDataSet(queryAdvisor, new SysOrg());
	}
}