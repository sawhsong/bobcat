/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_EMPLOYEE - Employee Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrEmployee;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrEmployeeDao extends IDao {
	public DataSet getEmployeeSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getEmployeeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getEmployeeSurnameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getEmployeeGivenNameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
}