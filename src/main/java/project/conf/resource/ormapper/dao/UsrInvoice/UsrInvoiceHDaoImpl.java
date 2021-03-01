/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_INVOICE - Invoice info for additioanl user service
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrInvoice;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dao.UsrInvoiceD.UsrInvoiceDDao;
import project.conf.resource.ormapper.dto.oracle.UsrInvoice;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrInvoiceHDaoImpl extends BaseHDao implements UsrInvoiceDao {
	@Autowired
	private UsrInvoiceDDao usrInvoiceDDao;

	public int insert(UsrInvoice usrInvoice, DataSet detailDataSet) throws Exception {
		int result = 0;

		result += insertWithSQLQuery(usrInvoice);
		result += usrInvoiceDDao.deleteAndInsert(usrInvoice.getInvoiceId(), detailDataSet);

		return result;
	}

	public int update(String invoiceId, UsrInvoice usrInvoice, DataSet detailDataSet) throws Exception {
		int result = 0;
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("invoice_id = '"+invoiceId+"'");

		result += updateColumns(queryAdvisor, usrInvoice);
		result += usrInvoiceDDao.deleteAndInsert(invoiceId, detailDataSet);

		return result;
	}

	public int updateColumn(String invoiceId, UsrInvoice usrInvoice) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("invoice_id = '"+invoiceId+"'");
		return updateColumns(queryAdvisor, usrInvoice);
	}

	public int delete(String invoiceIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;
		String idsForDel = "";

		result += usrInvoiceDDao.delete(invoiceIds);

		if (!(invoiceIds == null || invoiceIds.length == 0)) {
			for (int i=0; i<invoiceIds.length; i++) {
				idsForDel += CommonUtil.isBlank(idsForDel) ? "'"+invoiceIds[i]+"'" : ",'"+invoiceIds[i]+"'";
			}
			queryAdvisor.addWhereClause("invoice_id in ("+idsForDel+")");

			result += deleteWithSQLQuery(queryAdvisor, new UsrInvoice());
		}
		return result;
	}

	public int delete(String invoiceId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;

		result += usrInvoiceDDao.delete(invoiceId);

		queryAdvisor.addWhereClause("invoice_id = '"+invoiceId+"'");
		result += deleteWithSQLQuery(queryAdvisor, new UsrInvoice());

		return result;
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));
		String userId = (String)queryAdvisor.getObject("userId");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");
		String customerName = (String)queryAdvisor.getObject("customerName");
		String status = (String)queryAdvisor.getObject("status");

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addWhereClause("uin.user_id = '"+userId+"'");
		queryAdvisor.addAutoFillCriteria(fromDate, "trunc(uin.issue_date) >= trunc(to_date('"+fromDate+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(toDate, "trunc(uin.issue_date) <= trunc(to_date('"+toDate+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(customerName, "lower(uin.client_name) "+CommonUtil.getSearchCriteriaWhereClauseString(customerName));
		queryAdvisor.addAutoFillCriteria(status, "uin.status = '"+status+"'");

		queryAdvisor.addOrderByClause("uin.issue_date desc, nvl(uin.update_date, uin.insert_date) desc");

		return selectAsDataSet(queryAdvisor, "query.UsrInvoice.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetByInvoiceId(String invoiceId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("invoice_id = '"+invoiceId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrInvoice());
	}

	public UsrInvoice getInvoiceByInvoiceId(String invoiceId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("invoice_id = '"+invoiceId+"'");
		return (UsrInvoice)selectAllToDto(queryAdvisor, new UsrInvoice());
	}

	public DataSet getInvoiceBadgeForDashboard(QueryAdvisor queryAdvisor) throws Exception {
		String userId = (String)queryAdvisor.getObject("userId");

		queryAdvisor.addVariable("userId", userId);

		return selectAsDataSet(queryAdvisor, "query.UsrInvoice.getInvoiceBadgeForDashboard");
	}

	public DataSet getInvoiceDataSetForDashboard(QueryAdvisor queryAdvisor) throws Exception {
		String userId = (String)queryAdvisor.getObject("userId");
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addVariable("userId", userId);

		return selectAsDataSet(queryAdvisor, "query.UsrInvoice.getInvoiceDataSetForDashboard");
	}
}