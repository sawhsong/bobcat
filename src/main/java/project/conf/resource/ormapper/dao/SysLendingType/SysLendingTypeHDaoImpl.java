/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_LENDING_TYPE - Lending Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysLendingType;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysLendingType;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class SysLendingTypeHDaoImpl extends BaseHDao implements SysLendingTypeDao {
	public DataSet getLendingTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgCategory = requestDataSet.getValue("orgCategory");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addWhereClause("lending_type.org_category(+) = '"+orgCategory+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("com_code.sort_order");

		return selectAsDataSet(queryAdvisor, "query.SysLendingType.getLendingTypeDataSetByCriteria");
	}

	public DataSet getLendingTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		queryAdvisor.addWhereClause("parent_lending_type is null");
		queryAdvisor.addOrderByClause("sort_order");

		return selectAllAsDataSet(queryAdvisor, new SysLendingType());
	}
}