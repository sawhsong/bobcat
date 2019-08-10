/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_EMPLOYEE - Employee Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrEmployee;

import project.conf.resource.ormapper.dto.oracle.UsrEmployee;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrEmployeeDao extends IDao {
	public int insert(UsrEmployee usrEmployee) throws Exception;
	public int update(String employeeId, UsrEmployee usrEmployee) throws Exception;
	public int delete(String employeeIds[]) throws Exception;
	public UsrEmployee getEmployeeById(String employeeId) throws Exception;
	public DataSet getEmployeeSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getEmployeeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getEmployeeDataSetByEmployeeId(String employeeId) throws Exception;
	public DataSet getEmployeeSurnameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getEmployeeGivenNameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
}