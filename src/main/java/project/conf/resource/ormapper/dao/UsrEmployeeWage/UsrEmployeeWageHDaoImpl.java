/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_EMPLOYEE_WAGE - Employee Wage
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrEmployeeWage;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrEmployeeWage;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrEmployeeWageHDaoImpl extends BaseHDao implements UsrEmployeeWageDao {
	public int insert(UsrEmployeeWage usrEmployeeWage) throws Exception {
		return insertWithSQLQuery(usrEmployeeWage);
	}

	public int update(String wageId, UsrEmployeeWage usrEmployeeWage) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("wage_id = '"+wageId+"'");
		return updateWithSQLQuery(queryAdvisor, usrEmployeeWage);
	}

	public int deleteByWageIds(String wageIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String where = "";

		for (int i=0; i<wageIds.length; i++) {
			where += CommonUtil.isBlank(where) ? "'"+wageIds[i]+"'" : ", '"+wageIds[i]+"'";
		}
		queryAdvisor.addWhereClause("wage_id in ("+where+")");

		return deleteWithSQLQuery(queryAdvisor, new UsrEmployeeWage());
	}

	public UsrEmployeeWage getEmployeeWageById(String wageId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("wage_id = '"+wageId+"'");
		return (UsrEmployeeWage)selectAllToDto(queryAdvisor, new UsrEmployeeWage());
	}

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
		String financialYear = (String)queryAdvisor.getObject("financialYear");
		String quarterName = (String)queryAdvisor.getObject("quarterName");
		String employeeId = (String)queryAdvisor.getObject("employeeId");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("employeeId", employeeId);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);

		return selectAsDataSet(queryAdvisor, "query.UsrEmployeeWage.getWageListDataSetByEmployeeId");
	}

	public DataSet getWageDataSetByWageIdForUpdate(String wageId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addWhereClause("wage_id = '"+wageId+"'");

		return selectAsDataSet(queryAdvisor, "query.UsrEmployeeWage.getWageDataSetByWageIdForUpdate");
	}

	public DataSet getEmployeeWageDataSetForExport(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String surname = requestDataSet.getValue("surname");
		String givenName = requestDataSet.getValue("givenName");
		String visaType = requestDataSet.getValue("visaType");

		queryAdvisor.addAutoFillCriteria(surname, "lower(uem.surname) like lower('%"+surname+"%')");
		queryAdvisor.addAutoFillCriteria(givenName, "lower(uem.given_name) like lower('%"+givenName+"%')");
		queryAdvisor.addAutoFillCriteria(visaType, "uem.visa_type = '"+visaType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);

		return selectAsDataSet(queryAdvisor, "query.UsrEmployeeWage.getEmployeeWageDataSetForExport");
	}
}