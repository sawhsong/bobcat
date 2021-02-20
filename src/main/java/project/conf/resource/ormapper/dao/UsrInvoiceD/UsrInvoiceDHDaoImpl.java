/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_INVOICE_D - Invoice detail info
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrInvoiceD;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrInvoiceD;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;

public class UsrInvoiceDHDaoImpl extends BaseHDao implements UsrInvoiceDDao {
	public int deleteAndInsert(String invoiceId, DataSet invoiceDetailDataSet) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;

		queryAdvisor.addWhereClause("invoice_id = '"+invoiceId+"'");

		result += deleteWithSQLQuery(queryAdvisor, new UsrInvoiceD());

		for (int i=0; i<invoiceDetailDataSet.getRowCnt(); i++) {
			UsrInvoiceD usrInvoiceD = new UsrInvoiceD();

			usrInvoiceD.setInvoiceDId(CommonUtil.uid());
			usrInvoiceD.setInvoiceId(invoiceId);
			usrInvoiceD.setRowIndex(CommonUtil.toDouble(invoiceDetailDataSet.getValue(i, "ROW_INDEX")));
			usrInvoiceD.setUnit(CommonUtil.toDouble(invoiceDetailDataSet.getValue(i, "UNIT")));
			usrInvoiceD.setAmtPerUnit(CommonUtil.toDouble(invoiceDetailDataSet.getValue(i, "PRICE")));
			usrInvoiceD.setItemAmt(CommonUtil.toDouble(invoiceDetailDataSet.getValue(i, "AMOUNT")));
			usrInvoiceD.setDescription(invoiceDetailDataSet.getValue(i, "DESCRIPTION"));
			usrInvoiceD.setInsertUserId(invoiceDetailDataSet.getValue(i, "USER_ID"));
			usrInvoiceD.setInsertDate(CommonUtil.getSysdateAsDate());
			usrInvoiceD.setUpdateUserId(invoiceDetailDataSet.getValue(i, "USER_ID"));
			usrInvoiceD.setUpdateDate(CommonUtil.getSysdateAsDate());

			result += insertWithSQLQuery(usrInvoiceD);
		}

		return result;
	}

	public int delete(String invoiceIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String idsForDel = "";

		if (!(invoiceIds == null || invoiceIds.length == 0)) {
			for (int i=0; i<invoiceIds.length; i++) {
				idsForDel += CommonUtil.isBlank(idsForDel) ? "'"+invoiceIds[i]+"'" : ",'"+invoiceIds[i]+"'";
			}
			queryAdvisor.addWhereClause("invoice_id in ("+idsForDel+")");
		}

		return deleteWithSQLQuery(queryAdvisor, new UsrInvoiceD());
	}

	public int delete(String invoiceId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("invoice_id = '"+invoiceId+"'");
		return deleteWithSQLQuery(queryAdvisor, new UsrInvoiceD());
	}

	public DataSet getDataSetByInvoiceId(String invoiceId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("invoice_id = '"+invoiceId+"'");
		queryAdvisor.addOrderByClause("row_index");
		return selectAllAsDataSet(queryAdvisor, new UsrInvoiceD());
	}
}