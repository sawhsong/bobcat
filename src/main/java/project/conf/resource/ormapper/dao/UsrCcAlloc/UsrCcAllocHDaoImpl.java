/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_CC_ALLOC - Credit card statement transaction allocation - transaction reconciliation
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrCcAlloc;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrCcAlloc;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrCcAllocHDaoImpl extends BaseHDao implements UsrCcAllocDao {
	public int insert(UsrCcAlloc usrCcAlloc) throws Exception {
		return insertWithDto(usrCcAlloc);
	}

	public int update(String ccAllocId, UsrCcAlloc usrCcAlloc) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("cc_alloc_id = '"+ccAllocId+"'");
		return updateWithSQLQuery(queryAdvisor, usrCcAlloc);
	}

	public int updateColumn(String ccAllocId, UsrCcAlloc usrCcAlloc) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("cc_alloc_id = '"+ccAllocId+"'");
		return updateColumns(queryAdvisor, usrCcAlloc);
	}

	public UsrCcAlloc getCcAllocByCcAllocId(String ccAllocId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("cc_alloc_id = '"+ccAllocId+"'");
		return (UsrCcAlloc)selectAllToDto(queryAdvisor, new UsrCcAlloc());
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String allocationStatus = (String)queryAdvisor.getObject("allocationStatus");
		String userId = (String)queryAdvisor.getObject("userId");
		String transactionDateFrom = (String)queryAdvisor.getObject("transactionDateFrom");
		String transactionDateTo = (String)queryAdvisor.getObject("transactionDateTo");
		String updatedDateFrom = (String)queryAdvisor.getObject("updatedDateFrom");
		String updatedDateTo = (String)queryAdvisor.getObject("updatedDateTo");
		String bankAccntId = (String)queryAdvisor.getObject("bankAccntId");

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(allocationStatus, "cta.status = '"+allocationStatus+"'");
		queryAdvisor.addAutoFillCriteria(userId, "cta.user_id = '"+userId+"'");
		queryAdvisor.addAutoFillCriteria(updatedDateFrom, "trunc(nvl(cta.update_date, cta.insert_date)) >= trunc(to_date('"+updatedDateFrom+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(updatedDateTo, "trunc(nvl(cta.update_date, cta.insert_date)) <= trunc(to_date('"+updatedDateTo+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(transactionDateFrom, "trunc(cta.proc_date) >= trunc(to_date('"+transactionDateFrom+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(transactionDateTo, "trunc(cta.proc_date) <= trunc(to_date('"+transactionDateTo+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(bankAccntId, "cta.bank_accnt_id = '"+bankAccntId+"'");

		queryAdvisor.addOrderByClause("cta.proc_date desc, cta.row_index, nvl(cta.update_date, cta.insert_date) desc");

		return selectAsDataSet(queryAdvisor, "query.UsrCcAlloc.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetByFileDataForDupCheck(QueryAdvisor queryAdvisor) throws Exception {
		return selectAllAsDataSet(queryAdvisor, new UsrCcAlloc());
	}

	public DataSet getDataSetByCcAllocId(String ccAllocId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("cc_alloc_id = '"+ccAllocId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrCcAlloc());
	}
}