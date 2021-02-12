/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_RECON_CATEGORY - Bank transaction reconciliation category
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysReconCategory;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysReconCategory;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class SysReconCategoryHDaoImpl extends BaseHDao implements SysReconCategoryDao {
	public int insert(SysReconCategory sysReconCategory) throws Exception {
		return insertWithSQLQuery(sysReconCategory);
	}

	public int update(SysReconCategory sysReconCategory, String categoryId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("category_id = '"+categoryId+"'");
		return updateColumns(queryAdvisor, sysReconCategory);
	}

	public int delete(String categoryIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;
		String idsForDel = "";

		if (!(categoryIds == null || categoryIds.length == 0)) {
			for (String id : categoryIds) {
				idsForDel += CommonUtil.isBlank(idsForDel) ? "'"+id+"'" : ",'"+id+"'";
			}
			queryAdvisor.addWhereClause("category_id in ("+idsForDel+")");
			result = deleteWithSQLQuery(queryAdvisor, new SysReconCategory());
		}
		return result;
	}

	public int delete(String categoryId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("category_id = '"+categoryId+"'");
		return deleteWithSQLQuery(queryAdvisor, new SysReconCategory());
	}

	public DataSet getMainCategoryDataSet() throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("parent_category_id is null");
		queryAdvisor.addOrderByClause("sort_order, category_name");
		return selectAllAsDataSet(queryAdvisor, new SysReconCategory());
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String mainCategory = requestDataSet.getValue("mainCategory");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));

		queryAdvisor.addAutoFillCriteria(mainCategory, "root = '"+mainCategory+"'");
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);

		return selectAsDataSet(queryAdvisor, "query.SysReconCategory.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetByCategoryId(String categoryId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("category_id = '"+categoryId+"'");
		return selectAllAsDataSet(queryAdvisor, new SysReconCategory());
	}

	public SysReconCategory getReconCategoryByCategoryId(String categoryId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("category_id = '"+categoryId+"'");
		return (SysReconCategory)selectAllToDto(queryAdvisor, new SysReconCategory());
	}
}