/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_LENDING_TYPE - Lending Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysLendingType;

import project.conf.resource.ormapper.dto.oracle.SysLendingType;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysLendingTypeDao extends IDao {
	public int insert(SysLendingType sysLendingType) throws Exception;
	public int updateWithKey(SysLendingType sysLendingType, String lendingTypeId, String lendingTypeCode) throws Exception;
	public int deleteByOrgCategory(String orgCategory) throws Exception;
	public DataSet getLendingTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getLendingTypeDataSetByOrgCategory(String orgCategory) throws Exception;
	public SysLendingType getLendingTypeByKeys(String lendingTypeId, String lendingTypeCode) throws Exception;
	public DataSet getMaxSortOrderDataSetByOrgCategory(String orgCategory) throws Exception;
}