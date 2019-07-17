/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_EXPENSE_TYPE - Expenditure Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysExpenseType;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysExpenseType;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class SysExpenseTypeHDaoImpl extends BaseHDao implements SysExpenseTypeDao {
	public int insert(SysExpenseType sysExpenseType) throws Exception {
		return insertWithSQLQuery(sysExpenseType);
	}

	public int updateWithKey(SysExpenseType sysExpenseType, String expenseTypeId, String expenseTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("expense_type_id = '" + expenseTypeId + "'");
		queryAdvisor.addWhereClause("expense_type = '" + expenseTypeCode + "'");
		return updateWithSQLQuery(queryAdvisor, sysExpenseType);
	}

	public DataSet getExpenseTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgCategory = requestDataSet.getValue("orgCategory");
		String dateFormat = ConfigUtil.getProperty("format.date.java");

		queryAdvisor.addVariable("orgCategory", orgCategory);
		queryAdvisor.addVariable("dateFormat", dateFormat);

		return selectAsDataSet(queryAdvisor, "query.SysExpenseType.getExpenseTypeDataSetByCriteria");
	}

	public SysExpenseType getExpenseTypeByKeys(String expenseTypeId, String orgCategory, String expenseTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("expense_type_id = '"+expenseTypeId+"'");
		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		queryAdvisor.addWhereClause("expense_type = '"+expenseTypeCode+"'");
		return (SysExpenseType)selectAllToDto(queryAdvisor, new SysExpenseType());
	}

	public DataSet getExpenseMainTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		queryAdvisor.addWhereClause("parent_expense_type is null");
		queryAdvisor.addOrderByClause("sort_order");

		return selectAllAsDataSet(queryAdvisor, new SysExpenseType());
	}

	public DataSet getExpenseSubTypeDataSetByOrgCategoryParentTypeCode(String orgCategory, String parentTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		queryAdvisor.addWhereClause("parent_expense_type = '"+parentTypeCode+"'");
		queryAdvisor.addOrderByClause("sort_order");

		return selectAllAsDataSet(queryAdvisor, new SysExpenseType());
	}
}