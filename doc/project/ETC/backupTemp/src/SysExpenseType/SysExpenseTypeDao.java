/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_EXPENSE_TYPE - Income Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysExpenseType;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysExpenseTypeDao extends IDao {
	public DataSet getMainTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getSubTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
}