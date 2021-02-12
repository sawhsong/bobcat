/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_RECON_CATEGORY - Bank transaction reconciliation category
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysReconCategory;

import project.conf.resource.ormapper.dto.oracle.SysReconCategory;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysReconCategoryDao extends IDao {
	public int insert(SysReconCategory sysReconCategory) throws Exception;
	public int update(SysReconCategory sysReconCategory, String categoryId) throws Exception;
	public int delete(String[] categoryIds) throws Exception;
	public int delete(String categoryId) throws Exception;

	public DataSet getMainCategoryDataSet() throws Exception;
	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByCategoryId(String categoryId) throws Exception;
	public SysReconCategory getReconCategoryByCategoryId(String categoryId) throws Exception;
}