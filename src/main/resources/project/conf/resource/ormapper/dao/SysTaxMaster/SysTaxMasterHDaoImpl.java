/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_TAX_MASTER - Tax Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysTaxMaster;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class SysTaxMasterHDaoImpl extends BaseHDao implements SysTaxMasterDao {
	public DataSet getTaxMasterDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String taxYear = requestDataSet.getValue("taxYear");
		String wageType = requestDataSet.getValue("wageType");
		String gross = requestDataSet.getValue("gross");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addWhereClause("tax.tax_year = '"+taxYear+"'");
		queryAdvisor.addAutoFillCriteria(gross, "tax.gross = '"+gross+"'");
		queryAdvisor.addAutoFillCriteria(wageType, "tax.wage_type = '"+wageType+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("tax.tax_year desc, tax.gross desc, tax.wage_type desc");

		return selectAsDataSet(queryAdvisor, "query.SysTaxMaster.getTaxMasterDataSetByCriteria");
	}
}