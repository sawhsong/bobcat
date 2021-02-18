/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_QUOTATION_D - Quotation detail info
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrQuotationD;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrQuotationD;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;

public class UsrQuotationDHDaoImpl extends BaseHDao implements UsrQuotationDDao {
	public int deleteAndInsert(String quotationId, DataSet quotationDetailDataSet) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;

		queryAdvisor.addWhereClause("quotation_id = '"+quotationId+"'");

		result += deleteWithSQLQuery(queryAdvisor, new UsrQuotationD());

		for (int i=0; i<quotationDetailDataSet.getRowCnt(); i++) {
			UsrQuotationD usrQuotationD = new UsrQuotationD();

			usrQuotationD.setQuotationDId(CommonUtil.uid());
			usrQuotationD.setQuotationId(quotationId);
			usrQuotationD.setRowIndex(CommonUtil.toDouble(quotationDetailDataSet.getValue(i, "ROW_INDEX")));
			usrQuotationD.setUnit(CommonUtil.toDouble(quotationDetailDataSet.getValue(i, "UNIT")));
			usrQuotationD.setAmtPerUnit(CommonUtil.toDouble(quotationDetailDataSet.getValue(i, "PRICE")));
			usrQuotationD.setItemAmt(CommonUtil.toDouble(quotationDetailDataSet.getValue(i, "AMOUNT")));
			usrQuotationD.setDescription(quotationDetailDataSet.getValue(i, "DESCRIPTION"));
			usrQuotationD.setInsertUserId(quotationDetailDataSet.getValue(i, "USER_ID"));
			usrQuotationD.setInsertDate(CommonUtil.getSysdateAsDate());
			usrQuotationD.setUpdateUserId(quotationDetailDataSet.getValue(i, "USER_ID"));
			usrQuotationD.setUpdateDate(CommonUtil.getSysdateAsDate());

			result += insertWithSQLQuery(usrQuotationD);
		}

		return result;
	}

	public int delete(String quotationIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String idsForDel = "";

		if (!(quotationIds == null || quotationIds.length == 0)) {
			for (int i=0; i<quotationIds.length; i++) {
				idsForDel += CommonUtil.isBlank(idsForDel) ? "'"+quotationIds[i]+"'" : ",'"+quotationIds[i]+"'";
			}
			queryAdvisor.addWhereClause("quotation_id in ("+idsForDel+")");
		}

		return deleteWithSQLQuery(queryAdvisor, new UsrQuotationD());
	}

	public int delete(String quotationId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("quotation_id = '"+quotationId+"'");
		return deleteWithSQLQuery(queryAdvisor, new UsrQuotationD());
	}

	public DataSet getDataSetByQuotationId(String quotationId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("quotation_id = '"+quotationId+"'");
		queryAdvisor.addOrderByClause("row_index");
		return selectAllAsDataSet(queryAdvisor, new UsrQuotationD());
	}
}