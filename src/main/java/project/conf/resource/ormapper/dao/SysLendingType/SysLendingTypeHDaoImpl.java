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
	public int insert(SysLendingType sysLendingType) throws Exception {
		return insertWithSQLQuery(sysLendingType);
	}

	public int updateWithKey(SysLendingType sysLendingType, String lendingTypeId, String lendingTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("lending_type_id = '" + lendingTypeId + "'");
		queryAdvisor.addWhereClause("lending_type = '" + lendingTypeCode + "'");
		return updateWithSQLQuery(queryAdvisor, sysLendingType);
	}

	public int deleteByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		return deleteWithSQLQuery(queryAdvisor, new SysLendingType());
	}

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

	public SysLendingType getLendingTypeByKeys(String lendingTypeId, String lendingTypeCode) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("lending_type_id = '"+lendingTypeId+"'");
		queryAdvisor.addWhereClause("lending_type = '"+lendingTypeCode+"'");
		return (SysLendingType)selectAllToDto(queryAdvisor, new SysLendingType());
	}

	public DataSet getMaxSortOrderDataSetByOrgCategory(String orgCategory) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("org_category = '"+orgCategory+"'");
		return selectAsDataSet(queryAdvisor, "query.SysLendingType.getMaxSortOrderDataSetByOrgCategory");
	}
}