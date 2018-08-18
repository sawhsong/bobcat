/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_BORROWING_TYPE - Borrowing Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysBorrowingType;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysBorrowingType;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class SysBorrowingTypeHDaoImpl extends BaseHDao implements SysBorrowingTypeDao {
	public DataSet getBorrowingTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgCategory = requestDataSet.getValue("orgCategory");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addWhereClause("borrowing_type.org_category(+) = '"+orgCategory+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("com_code.sort_order");

		return selectAsDataSet(queryAdvisor, "query.SysBorrowingType.getBorrowingTypeDataSetByCriteria");
	}

	public DataSet getBorrowingTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		queryAdvisor.addWhereClause("parent_borrowing_type is null");
		queryAdvisor.addOrderByClause("sort_order");

		return selectAllAsDataSet(queryAdvisor, new SysBorrowingType());
	}
}