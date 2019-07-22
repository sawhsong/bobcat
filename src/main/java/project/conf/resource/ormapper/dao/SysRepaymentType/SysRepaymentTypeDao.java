/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_REPAYMENT_TYPE - Repayment Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysRepaymentType;

import project.conf.resource.ormapper.dto.oracle.SysRepaymentType;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysRepaymentTypeDao extends IDao {
	public int insert(SysRepaymentType sysRepaymentType) throws Exception;
	public int updateWithKey(SysRepaymentType sysRepaymentType, String repaymentTypeId, String repaymentTypeCode) throws Exception;
	public int deleteByOrgCategory(String orgCategory) throws Exception;
	public DataSet getRepaymentTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getRepaymentTypeDataSetByOrgCategory(String orgCategory) throws Exception;
	public SysRepaymentType getRepaymentTypeByKeys(String repaymentTypeId, String repaymentTypeCode) throws Exception;
	public DataSet getMaxSortOrderDataSetByOrgCategory(String orgCategory) throws Exception;
}