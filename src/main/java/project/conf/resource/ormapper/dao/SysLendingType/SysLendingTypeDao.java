/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_LENDING_TYPE - Lending Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysLendingType;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysLendingTypeDao extends IDao {
	public DataSet getLendingTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getLendingTypeDataSetByOrgCategory(String orgCategory) throws Exception;
}