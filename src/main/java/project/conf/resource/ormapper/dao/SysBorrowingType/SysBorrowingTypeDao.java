/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_BORROWING_TYPE - Borrowing Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysBorrowingType;

import project.conf.resource.ormapper.dto.oracle.SysBorrowingType;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysBorrowingTypeDao extends IDao {
	public int insert(SysBorrowingType sysBorrowingType) throws Exception;
	public int updateWithKey(SysBorrowingType sysBorrowingType, String borrowingTypeId, String borrowingTypeCode) throws Exception;
	public int deleteByOrgCategory(String orgCategory) throws Exception;
	public DataSet getBorrowingTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getBorrowingTypeDataSetByOrgCategory(String orgCategory) throws Exception;
	public SysBorrowingType getBorrowingTypeByKeys(String borrowingTypeId, String borrowingTypeCode) throws Exception;
	public DataSet getMaxSortOrderDataSetByOrgCategory(String orgCategory) throws Exception;
}