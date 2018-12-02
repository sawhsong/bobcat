/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_EMPLOYEE_WAGE - Employee Wage
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrEmployeeWage;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class UsrEmployeeWageHDaoImpl extends BaseHDao implements UsrEmployeeWageDao {
	public DataSet getEmployeeListDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String surname = requestDataSet.getValue("surname");
		String givenName = requestDataSet.getValue("givenName");
		String visaType = requestDataSet.getValue("visaType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(surname, "lower(uem.surname) like lower('%"+surname+"%')");
		queryAdvisor.addAutoFillCriteria(givenName, "lower(uem.given_name) like lower('%"+givenName+"%')");
		queryAdvisor.addAutoFillCriteria(visaType, "uem.visa_type = '"+visaType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("ewg.wage_year desc, ewg.quarter_name, uem.surname, uem.given_name");

		return selectAsDataSet(queryAdvisor, "query.UsrEmployeeWage.getEmployeeListDataSetByCriteria");
	}

	public DataSet getWageListDataSetByEmployeeId(QueryAdvisor queryAdvisor) throws Exception {
		String employeeId = (String)queryAdvisor.getObject("employeeId");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addVariable("employeeId", employeeId);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);

		return selectAsDataSet(queryAdvisor, "query.UsrEmployeeWage.getWageListDataSetByEmployeeId");
	}
}