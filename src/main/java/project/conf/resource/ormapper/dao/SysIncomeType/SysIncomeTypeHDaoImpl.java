/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_INCOME_TYPE - Income Entry Type
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysIncomeType;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysIncomeType;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class SysIncomeTypeHDaoImpl extends BaseHDao implements SysIncomeTypeDao {
	public int insert(SysIncomeType sysIncomeType) throws Exception {
		return insertWithSQLQuery(sysIncomeType);
	}

	public int updateWithKey(SysIncomeType sysIncomeType, String incomeTypeId, String incomeTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("income_type_id = '" + incomeTypeId + "'");
		queryAdvisor.addWhereClause("income_type = '" + incomeTypeCode + "'");
		return updateWithSQLQuery(queryAdvisor, sysIncomeType);
	}

	public int deleteByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		return deleteWithSQLQuery(queryAdvisor, new SysIncomeType());
	}

	public DataSet getIncomeTypeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgCategory = requestDataSet.getValue("orgCategory");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addWhereClause("inc_type.org_category(+) = '"+orgCategory+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("com_code.sort_order");

		return selectAsDataSet(queryAdvisor, "query.SysIncomeType.getIncomeTypeDataSetByCriteria");
	}

	public DataSet getIncomeTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		queryAdvisor.addOrderByClause("sort_order");
		return selectAllAsDataSet(queryAdvisor, new SysIncomeType());
	}

	public SysIncomeType getIncomeTypeByKeys(String incomeTypeId, String incomeTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("income_type_id = '"+incomeTypeId+"'");
		queryAdvisor.addWhereClause("income_type = '"+incomeTypeCode+"'");
		return (SysIncomeType)selectAllToDto(queryAdvisor, new SysIncomeType());
	}

	public DataSet getMaxSortOrderDataSetByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		return selectAsDataSet(queryAdvisor, "query.SysIncomeType.getMaxSortOrderDataSetByOrgCategory");
	}
}