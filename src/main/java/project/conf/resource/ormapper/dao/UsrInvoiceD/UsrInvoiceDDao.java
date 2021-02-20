/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_INVOICE_D - Invoice detail info
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrInvoiceD;

import zebra.base.IDao;
import zebra.data.DataSet;

public interface UsrInvoiceDDao extends IDao {
	public int deleteAndInsert(String invoiceId, DataSet invoiceDetailDataSet) throws Exception;
	public int delete(String[] invoiceIds) throws Exception;
	public int delete(String invoiceId) throws Exception;

	public DataSet getDataSetByInvoiceId(String invoiceId) throws Exception;
}