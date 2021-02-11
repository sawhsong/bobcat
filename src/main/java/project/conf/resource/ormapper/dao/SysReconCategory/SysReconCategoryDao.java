/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_RECON_CATEGORY - Bank transaction reconciliation category
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysReconCategory;

import zebra.base.IDao;
import zebra.data.DataSet;

public interface SysReconCategoryDao extends IDao {
	public DataSet getMainCategoryDataSet() throws Exception;
}