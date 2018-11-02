/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_FINANCIAL_PERIOD - Quarter type by financial year
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysFinancialPeriod;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysFinancialPeriod;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class SysFinancialPeriodHDaoImpl extends BaseHDao implements SysFinancialPeriodDao {
	public SysFinancialPeriod getCurrentFinancialPeriod() throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("trunc(sysdate) between trunc(date_from) and trunc(date_to)");
		return (SysFinancialPeriod)selectAllToDto(queryAdvisor, new SysFinancialPeriod());
	}

	public DataSet getDistinctFinancialYearDataSet() throws Exception {
		return selectAsDataSet(new QueryAdvisor(), "query.SysFinancialPeriod.getDistinctFinancialYearDataSet");
	}

	public DataSet getPeriodDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String periodYear = requestDataSet.getValue("periodYear");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(periodYear, "period_year = '"+periodYear+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("period_year desc, quarter_code desc");

		return selectAsDataSet(queryAdvisor, "query.SysFinancialPeriod.getPeriodDataSetByCriteria");
	}
}