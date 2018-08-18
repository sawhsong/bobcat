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
	public SysFinancialPeriod getCurrentFinancialPeriod() throws Exception;
	public DataSet getDistinctFinancialYearDataSet() throws Exception;
	public DataSet getPeriodDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
}