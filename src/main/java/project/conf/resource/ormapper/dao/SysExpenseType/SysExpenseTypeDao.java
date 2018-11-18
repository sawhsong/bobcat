/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_EXPENSE_TYPE - Expenditure Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysExpenseType;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysExpenseTypeDao extends IDao {
	public DataSet getExpenseTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getExpenseMainTypeDataSetByOrgCategory(String orgCategory) throws Exception;
	public DataSet getExpenseSubTypeDataSetByOrgCategoryParentTypeCode(String orgCategory, String parentTypeCode) throws Exception;
}