/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_BORROWING_TYPE - Borrowing Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysBorrowingType;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysBorrowingTypeDao extends IDao {
	public DataSet getBorrowingTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getBorrowingTypeDataSetByOrgCategory(String orgCategory) throws Exception;
}