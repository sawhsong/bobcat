/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_FINANCIAL_PERIOD - Quarter type by financial year
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysFinancialPeriod;

import project.conf.resource.ormapper.dto.oracle.SysFinancialPeriod;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysFinancialPeriodDao extends IDao {
	/**
	 * Insert
	 * @param sysFinancialPeriod
	 * @return
	 * @throws Exception
	 */
	public int insert(SysFinancialPeriod sysFinancialPeriod) throws Exception;
	/**
	 * Update with key
	 * @param sysFinancialPeriod
	 * @param periodYear
	 * @param quarterCode
	 * @return
	 * @throws Exception
	 */
	public int updateWithKey(SysFinancialPeriod sysFinancialPeriod, String periodYear, String quarterCode) throws Exception;
	/**
	 * Get this financial year
	 * @return SysFinancialPeriod
	 * @throws Exception
	 */
	public SysFinancialPeriod getCurrentFinancialPeriod() throws Exception;
	/**
	 * Get financial year by year
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getDistinctFinancialYearDataSet() throws Exception;
	/**
	 * Get period data
	 * @param queryAdvisor
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getPeriodDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	/**
	 * Get Financial Year DTO by financialYear and quarterCode
	 * @param financialYear
	 * @param quarterCode
	 * @return
	 * @throws Exception
	 */
	public SysFinancialPeriod getFinancialPeriodByPeriodYearAndCode(String periodYear, String quarterCode) throws Exception;
}