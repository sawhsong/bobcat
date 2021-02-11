/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_RECON_CATEGORY - Bank transaction reconciliation category
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysReconCategory;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysReconCategory;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public class SysReconCategoryHDaoImpl extends BaseHDao implements SysReconCategoryDao {
	public DataSet getMainCategoryDataSet() throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("parent_category_id is null");
		return selectAllAsDataSet(queryAdvisor, new SysReconCategory());
	}
}