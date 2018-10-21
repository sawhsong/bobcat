/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_EXPENSE_TYPE - Income Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysExpenseType;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class SysExpenseTypeHDaoImpl extends BaseHDao implements SysExpenseTypeDao {
	public DataSet getMainTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgCategory = requestDataSet.getValue("orgCategory");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addWhereClause("exp_main_type.org_category(+) = '"+orgCategory+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("org_category");

		return selectAsDataSet(queryAdvisor, "query.SysExpenseType.getMainTypeDataSetByCriteria");
	}

	public DataSet getSubTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgCategory = requestDataSet.getValue("orgCategory");
		String mainType = requestDataSet.getValue("mainType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addWhereClause("exp_type.org_category(+) = '"+orgCategory+"'");
		queryAdvisor.addWhereClause("exp_type.expense_main_type(+) = '"+mainType+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("expense_type_id");

		return selectAsDataSet(queryAdvisor, "query.SysExpenseType.getSubTypeDataSetByCriteria");
	}
}