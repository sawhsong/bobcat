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
	public int insert(SysRepaymentType sysRepaymentType) throws Exception {
		return insertWithSQLQuery(sysRepaymentType);
	}

	public int updateWithKey(SysRepaymentType sysRepaymentType, String repaymentTypeId, String repaymentTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("repayment_type_id = '" + repaymentTypeId + "'");
		queryAdvisor.addWhereClause("repayment_type = '" + repaymentTypeCode + "'");
		return updateWithSQLQuery(queryAdvisor, sysRepaymentType);
	}

	public int deleteByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		return deleteWithSQLQuery(queryAdvisor, new SysRepaymentType());
	}

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

	public SysRepaymentType getRepaymentTypeByKeys(String repaymentTypeId, String repaymentTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("repayment_type_id = '"+repaymentTypeId+"'");
		queryAdvisor.addWhereClause("repayment_type = '"+repaymentTypeCode+"'");
		return (SysRepaymentType)selectAllToDto(queryAdvisor, new SysRepaymentType());
	}

	public DataSet getMaxSortOrderDataSetByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		return selectAsDataSet(queryAdvisor, "query.SysRepaymentType.getMaxSortOrderDataSetByOrgCategory");
	}
}