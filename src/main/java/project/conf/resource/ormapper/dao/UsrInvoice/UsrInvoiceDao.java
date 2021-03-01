/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_INVOICE - Invoice info for additioanl user service
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrInvoice;

import project.conf.resource.ormapper.dto.oracle.UsrInvoice;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrInvoiceDao extends IDao {
	public int insert(UsrInvoice usrInvoice, DataSet detailDataSet) throws Exception;
	public int update(String invoiceId, UsrInvoice usrInvoice, DataSet detailDataSet) throws Exception;
	public int updateColumn(String invoiceId, UsrInvoice usrInvoice) throws Exception;
	public int delete(String[] invoiceIds) throws Exception;
	public int delete(String invoiceId) throws Exception;

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByInvoiceId(String invoiceId) throws Exception;
	public UsrInvoice getInvoiceByInvoiceId(String invoiceId) throws Exception;
	public DataSet getInvoiceBadgeForDashboard(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getInvoiceDataSetForDashboard(QueryAdvisor queryAdvisor) throws Exception;
}