/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_INCOME_TYPE - Income Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysIncomeType;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysIncomeTypeDao extends IDao {
	public DataSet getIncomeTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getIncomeTypeDataSetByOrgCategory(String orgCategory) throws Exception;
}