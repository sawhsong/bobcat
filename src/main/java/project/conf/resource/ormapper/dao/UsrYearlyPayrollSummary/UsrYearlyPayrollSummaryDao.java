/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_YEARLY_PAYROLL_SUMMARY - Yerarly payroll summary
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrYearlyPayrollSummary;

import project.conf.resource.ormapper.dto.oracle.UsrYearlyPayrollSummary;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrYearlyPayrollSummaryDao extends IDao {
	public int deleteByKeys(String orgId, String financialYear) throws Exception;
	public int insert(UsrYearlyPayrollSummary usrYearlyPayrollSummary) throws Exception;
	public int update(UsrYearlyPayrollSummary usrYearlyPayrollSummary) throws Exception;

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
}