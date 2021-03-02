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
	public int insert(SysFinancialPeriod sysFinancialPeriod) throws Exception;
	public int updateWithKey(SysFinancialPeriod sysFinancialPeriod, String periodYear, String quarterCode) throws Exception;
	public int deleteWithKey(String periodYear, String quarterCode) throws Exception;
	public int deleteWithKeys(String keyIds[]) throws Exception;

	public SysFinancialPeriod getCurrentFinancialPeriod() throws Exception;
	public DataSet getDistinctFinancialYearDataSet() throws Exception;
	public DataSet getPeriodDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public SysFinancialPeriod getFinancialPeriodByPeriodYearAndCode(String periodYear, String quarterCode) throws Exception;
	public DataSet getDataSetByPeriodYearAndCode(String periodYear, String quarterCode) throws Exception;

	public DataSet getFinancialMonthsByPeriodYear(String periodYear) throws Exception;
	public DataSet getStartEndDataSetByPeriodYear(String periodYear) throws Exception;
}