/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_EMPLOYEE_WAGE - Employee Wage
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrEmployeeWage;

import project.conf.resource.ormapper.dto.oracle.UsrEmployeeWage;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrEmployeeWageDao extends IDao {
	public int insert(UsrEmployeeWage usrEmployeeWage) throws Exception;
	public int update(String wageId, UsrEmployeeWage usrEmployeeWage) throws Exception;
	public int deleteByWageIds(String wageIds[]) throws Exception;
	public UsrEmployeeWage getEmployeeWageById(String wageId) throws Exception;
	public DataSet getEmployeeListDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getWageListDataSetByEmployeeId(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getWageDataSetByWageIdForUpdate(String wageId) throws Exception;
	public DataSet getEmployeeWageDataSetForExport(QueryAdvisor queryAdvisor) throws Exception;
}