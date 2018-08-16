/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_EMPLOYEE_WAGE - Employee Wage
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrEmployeeWage;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrEmployeeWageDao extends IDao {
	public DataSet getEmployeeListDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getWageListDataSetByEmployeeId(QueryAdvisor queryAdvisor) throws Exception;
}