/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_EMPLOYEE - Employee Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrEmployee;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrEmployee;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrEmployeeHDaoImpl extends BaseHDao implements UsrEmployeeDao {
	public int insert(UsrEmployee usrEmployee) throws Exception {
		return insertWithSQLQuery(usrEmployee);
	}

	public int update(String employeeId, UsrEmployee usrEmployee) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("employee_id = '"+employeeId+"'");
		return updateWithSQLQuery(queryAdvisor, usrEmployee);
	}

	public int delete(String employeeIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String where = "";

		for (int i=0; i<employeeIds.length; i++) {
			where += CommonUtil.isBlank(where) ? "'"+employeeIds[i]+"'" : ", '"+employeeIds[i]+"'";
		}
		queryAdvisor.addWhereClause("employee_id in ("+where+")");

		return deleteWithSQLQuery(queryAdvisor, new UsrEmployee());
	}

	public UsrEmployee getEmployeeById(String employeeId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("employee_id = '"+employeeId+"'");
		return (UsrEmployee)selectAllToDto(queryAdvisor, new UsrEmployee());
	}

	public DataSet getEmployeeSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrEmployee.getEmployeeSummaryDataSet");
	}

	public DataSet getEmployeeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String surname = requestDataSet.getValue("surname");
		String givenName = requestDataSet.getValue("givenName");
		String wageType = requestDataSet.getValue("wageType");
		String visaType = requestDataSet.getValue("visaType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

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

	public DataSet getEmployeeDataSetByEmployeeId(String employeeId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addWhereClause("employee_id = '"+employeeId+"'");
		queryAdvisor.addOrderByClause("ump.surname, ump.given_name");

		return selectAsDataSet(queryAdvisor, "query.UsrEmployee.getEmployeeDataSetByEmployeeId");
	}

	public DataSet getEmployeeSurnameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrEmployee.getEmployeeSurnameDataSetForAutoCompletion");
	}

	public DataSet getEmployeeGivenNameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrEmployee.getEmployeeGivenNameDataSetForAutoCompletion");
	}
}