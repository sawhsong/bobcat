/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_QUOTATION - Quotation info for additioanl user service
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrQuotation;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dao.UsrQuotationD.UsrQuotationDDao;
import project.conf.resource.ormapper.dto.oracle.UsrQuotation;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrQuotationHDaoImpl extends BaseHDao implements UsrQuotationDao {
	@Autowired
	private UsrQuotationDDao usrQuotationDDao;

	public int insert(UsrQuotation usrQuotation, DataSet detailDataSet) throws Exception {
		int result = 0;

		result += insertWithSQLQuery(usrQuotation);
		result += usrQuotationDDao.deleteAndInsert(usrQuotation.getQuotationId(), detailDataSet);

		return result;
	}

	public int update(String quotationId, UsrQuotation usrQuotation, DataSet detailDataSet) throws Exception {
		int result = 0;
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("quotation_id = '"+quotationId+"'");

		result += updateColumns(queryAdvisor, usrQuotation);
		result += usrQuotationDDao.deleteAndInsert(quotationId, detailDataSet);

		return result;
	}

	public int updateColumn(String quotationId, UsrQuotation usrQuotation) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("quotation_id = '"+quotationId+"'");
		return updateColumns(queryAdvisor, usrQuotation);
	}

	public int delete(String quotationIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;
		String idsForDel = "";

		result += usrQuotationDDao.delete(quotationIds);

		if (!(quotationIds == null || quotationIds.length == 0)) {
			for (int i=0; i<quotationIds.length; i++) {
				idsForDel += CommonUtil.isBlank(idsForDel) ? "'"+quotationIds[i]+"'" : ",'"+quotationIds[i]+"'";
			}
			queryAdvisor.addWhereClause("quotation_id in ("+idsForDel+")");

			result += deleteWithSQLQuery(queryAdvisor, new UsrQuotation());
		}
		return result;
	}

	public int delete(String quotationId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;

		result += usrQuotationDDao.delete(quotationId);

		queryAdvisor.addWhereClause("quotation_id = '"+quotationId+"'");
		result += deleteWithSQLQuery(queryAdvisor, new UsrQuotation());

		return result;
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String userId = (String)queryAdvisor.getObject("userId");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");
		String customerName = (String)queryAdvisor.getObject("customerName");

		queryAdvisor.addWhereClause("uqm.user_id = '"+userId+"'");
		queryAdvisor.addAutoFillCriteria(fromDate, "trunc(uqm.issue_date) >= trunc(to_date('"+fromDate+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(toDate, "trunc(uqm.issue_date) <= trunc(to_date('"+toDate+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(customerName, "lower(uqm.client_name) "+CommonUtil.getSearchCriteriaWhereClauseString(customerName));

		queryAdvisor.addOrderByClause("uqm.issue_date desc, nvl(uqm.update_date, uqm.insert_date) desc");

		return selectAsDataSet(queryAdvisor, "query.UsrQuotation.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetByQuotationId(String quotationId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("quotation_id = '"+quotationId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrQuotation());
	}

	public UsrQuotation getQuotationByQuotationId(String quotationId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("quotation_id = '"+quotationId+"'");
		return (UsrQuotation)selectAllToDto(queryAdvisor, new UsrQuotation());
	}
}