/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_YEARLY_PAYROLL_SUMMARY - Yerarly payroll summary
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrYearlyPayrollSummary;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrYearlyPayrollSummary;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public class UsrYearlyPayrollSummaryHDaoImpl extends BaseHDao implements UsrYearlyPayrollSummaryDao {
	public int deleteByKeys(String orgId, String financialYear) throws Exception {
		QueryAdvisor qa = new QueryAdvisor();

		qa.addWhereClause("org_id = '"+orgId+"'");
		qa.addWhereClause("financial_year = '"+financialYear+"'");

		return deleteWithSQLQuery(qa, new UsrYearlyPayrollSummary());
	}

	public int insert(UsrYearlyPayrollSummary usrYearlyPayrollSummary) throws Exception {
		return insertWithSQLQuery(usrYearlyPayrollSummary);
	}

	public int update(UsrYearlyPayrollSummary usrYearlyPayrollSummary) throws Exception {
		QueryAdvisor qa = new QueryAdvisor();

		qa.addWhereClause("org_id = '"+usrYearlyPayrollSummary.getOrgId()+"'");
		qa.addWhereClause("financial_year = '"+usrYearlyPayrollSummary.getFinancialYear()+"'");

		return updateWithSQLQuery(qa, usrYearlyPayrollSummary);
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrYearlyPayrollSummary.getDataSetBySearchCriteria");
	}
}