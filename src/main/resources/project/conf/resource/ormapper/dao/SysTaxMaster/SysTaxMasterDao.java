/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_TAX_MASTER - Tax Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysTaxMaster;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysTaxMasterDao extends IDao {
	public DataSet getTaxMasterDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
}