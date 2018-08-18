/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_REPAYMENT_TYPE - Repayment Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysRepaymentType;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysRepaymentTypeDao extends IDao {
	public DataSet getRepaymentTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getRepaymentTypeDataSetByOrgCategory(String orgCategory) throws Exception;
}