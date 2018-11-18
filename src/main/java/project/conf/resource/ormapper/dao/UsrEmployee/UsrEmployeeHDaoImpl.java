/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_EMPLOYEE - Employee Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrEmployee;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class UsrEmployeeHDaoImpl extends BaseHDao implements UsrEmployeeDao {
	public DataSet getEmployeeSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrEmployee.getEmployeeSummaryDataSet");
	}

	public DataSet getEmployeeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String surname = requestDataSet.getValue("surname");
		String givenName = requestDataSet.getValue("givenName");
		String wageType = requestDataSet.getValue("wageType");
		String visaType = requestDataSet.getValue("visaType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

//		queryAdvisor.addVariable("financialYear", financialYear);
//		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addAutoFillCriteria(surname, "lower(ump.surname) like lower('%"+surname+"%')");
		queryAdvisor.addAutoFillCriteria(givenName, "lower(ump.given_name) like lower('%"+givenName+"%')");
		queryAdvisor.addAutoFillCriteria(wageType, "ump.wage_type = '"+wageType+"'");
		queryAdvisor.addAutoFillCriteria(visaType, "ump.visa_type = '"+visaType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("ump.surname, ump.given_name");

		return selectAsDataSet(queryAdvisor, "query.UsrEmployee.getEmployeeDataSetByCriteria");
	}

	public DataSet getEmployeeSurnameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrEmployee.getEmployeeSurnameDataSetForAutoCompletion");
	}

	public DataSet getEmployeeGivenNameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrEmployee.getEmployeeGivenNameDataSetForAutoCompletion");
	}
}