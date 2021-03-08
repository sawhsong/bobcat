/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_BS_TRAN_ALLOC - Bank statement transaction allocation - transaction reconcilisation
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBsTranAlloc;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrBsTranAlloc;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrBsTranAllocHDaoImpl extends BaseHDao implements UsrBsTranAllocDao {
	public int insert(UsrBsTranAlloc usrBsTranAlloc) throws Exception {
		return insertWithDto(usrBsTranAlloc);
	}

	public int update(String bsTranAllocId, UsrBsTranAlloc usrBsTranAlloc) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bs_tran_alloc_id = '"+bsTranAllocId+"'");
		return updateWithSQLQuery(queryAdvisor, usrBsTranAlloc);
	}

	public int updateColumn(String bsTranAllocId, UsrBsTranAlloc usrBsTranAlloc) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bs_tran_alloc_id = '"+bsTranAllocId+"'");
		return updateColumns(queryAdvisor, usrBsTranAlloc);
	}

	public UsrBsTranAlloc getBsTranAllocByBsTranAllocId(String bsTranAllocId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bs_tran_alloc_id = '"+bsTranAllocId+"'");
		return (UsrBsTranAlloc)selectAllToDto(queryAdvisor, new UsrBsTranAlloc());
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
		queryAdvisor.addAutoFillCriteria(allocationStatus, "bta.status = '"+allocationStatus+"'");
		queryAdvisor.addAutoFillCriteria(userId, "bta.user_id = '"+userId+"'");
		queryAdvisor.addAutoFillCriteria(updatedDateFrom, "trunc(nvl(bta.update_date, bta.insert_date)) >= trunc(to_date('"+updatedDateFrom+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(updatedDateTo, "trunc(nvl(bta.update_date, bta.insert_date)) <= trunc(to_date('"+updatedDateTo+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(transactionDateFrom, "trunc(bta.proc_date) >= trunc(to_date('"+transactionDateFrom+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(transactionDateTo, "trunc(bta.proc_date) <= trunc(to_date('"+transactionDateTo+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(bankAccntId, "bta.bank_accnt_id = '"+bankAccntId+"'");

		queryAdvisor.addOrderByClause("bta.proc_date desc, bta.row_index, nvl(bta.update_date, bta.insert_date) desc");

		return selectAsDataSet(queryAdvisor, "query.UsrBsTranAlloc.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetByFileDataForDupCheck(QueryAdvisor queryAdvisor) throws Exception {
		return selectAllAsDataSet(queryAdvisor, new UsrBsTranAlloc());
	}

	public DataSet getDataSetByBsTranAllocId(String bsTranAllocId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bs_tran_alloc_id = '"+bsTranAllocId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrBsTranAlloc());
	}

	public DataSet getIncomeChartDataSetForDashboard(QueryAdvisor queryAdvisor) throws Exception {
		String orgId = (String)queryAdvisor.getObject("orgId");
		String yearFrom = (String)queryAdvisor.getObject("yearFrom");
		String yearTo = (String)queryAdvisor.getObject("yearTo");

		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("yearFrom", yearFrom);
		queryAdvisor.addVariable("yearTo", yearTo);
		queryAdvisor.addWhereClause("lower(src.main_category_name) like lower('%Income%')");

		return selectAsDataSet(queryAdvisor, "query.UsrBsTranAlloc.getChartDataSetForDashboard");
	}

	public DataSet getExpenseChartDataSetForDashboard(QueryAdvisor queryAdvisor) throws Exception {
		String orgId = (String)queryAdvisor.getObject("orgId");
		String yearFrom = (String)queryAdvisor.getObject("yearFrom");
		String yearTo = (String)queryAdvisor.getObject("yearTo");

		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("yearFrom", yearFrom);
		queryAdvisor.addVariable("yearTo", yearTo);
		queryAdvisor.addWhereClause("lower(src.main_category_name) like lower('%Expense%')");

		return selectAsDataSet(queryAdvisor, "query.UsrBsTranAlloc.getChartDataSetForDashboard");
	}

	public DataSet getOtherChartDataSetForDashboard(QueryAdvisor queryAdvisor) throws Exception {
		String orgId = (String)queryAdvisor.getObject("orgId");
		String yearFrom = (String)queryAdvisor.getObject("yearFrom");
		String yearTo = (String)queryAdvisor.getObject("yearTo");

		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("yearFrom", yearFrom);
		queryAdvisor.addVariable("yearTo", yearTo);
		queryAdvisor.addWhereClause("lower(src.main_category_name) not like lower('%Income%')");
		queryAdvisor.addWhereClause("lower(src.main_category_name) not like lower('%Expense%')");

		return selectAsDataSet(queryAdvisor, "query.UsrBsTranAlloc.getChartDataSetForDashboard");
	}
}