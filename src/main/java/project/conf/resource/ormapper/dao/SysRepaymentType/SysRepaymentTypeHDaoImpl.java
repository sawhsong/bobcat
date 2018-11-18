/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_REPAYMENT_TYPE - Repayment Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysRepaymentType;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysRepaymentType;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class SysRepaymentTypeHDaoImpl extends BaseHDao implements SysRepaymentTypeDao {
	public DataSet getRepaymentTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgCategory = requestDataSet.getValue("orgCategory");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addWhereClause("repayment_type.org_category(+) = '"+orgCategory+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("com_code.sort_order");

		return selectAsDataSet(queryAdvisor, "query.SysRepaymentType.getRepaymentTypeDataSetByCriteria");
	}

	public DataSet getRepaymentTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		queryAdvisor.addWhereClause("parent_repayment_type is null");
		queryAdvisor.addOrderByClause("sort_order");

		return selectAllAsDataSet(queryAdvisor, new SysRepaymentType());
	}
}