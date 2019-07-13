/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_INCOME_TYPE - Income Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysIncomeType;

import project.conf.resource.ormapper.dto.oracle.SysIncomeType;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysIncomeTypeDao extends IDao {
	/**
	 * Add a new item
	 * @param sysIncomeType
	 * @return
	 * @throws Exception
	 */
	public int insert(SysIncomeType sysIncomeType) throws Exception;
	/**
	 * Update with key(incomeTypeId, incomeTypeCode)
	 * @param sysIncomeType
	 * @param incomeTypeId
	 * @param incomeTypeCode
	 * @return
	 * @throws Exception
	 */
	public int updateWithKey(SysIncomeType sysIncomeType, String incomeTypeId, String incomeTypeCode) throws Exception;
	/**
	 * Delete by org catetory
	 * @param orgCategory
	 * @return
	 * @throws Exception
	 */
	public int deleteByOrgCategory(String orgCategory) throws Exception;
	/**
	 * Get dataset by search criteria
	 * @param queryAdvisor
	 * @return
	 * @throws Exception
	 */
	public DataSet getIncomeTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	/**
	 * Get dataset by org category
	 * @param orgCategory
	 * @return
	 * @throws Exception
	 */
	public DataSet getIncomeTypeDataSetByOrgCategory(String orgCategory) throws Exception;
	/**
	 * Get SysIncomeType by PK(incomeTypeId, incomeTypeCode)
	 * @param incomeTypeId
	 * @param incomeTypeCode
	 * @return
	 * @throws Exception
	 */
	public SysIncomeType getIncomeTypeByKeys(String incomeTypeId, String incomeTypeCode) throws Exception;
	/**
	 * Get sortorder dataset by orgCategory
	 * @param orgCategory
	 * @return
	 * @throws Exception
	 */
	public DataSet getMaxSortOrderDataSetByOrgCategory(String orgCategory) throws Exception;
}