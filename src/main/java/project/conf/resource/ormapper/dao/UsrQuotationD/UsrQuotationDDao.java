/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_QUOTATION_D - Quotation detail info
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrQuotationD;

import zebra.base.IDao;
import zebra.data.DataSet;

public interface UsrQuotationDDao extends IDao {
	public int deleteAndInsert(String quotationId, DataSet quotationDetailDataSet) throws Exception;
	public int delete(String[] quotationIds) throws Exception;
	public int delete(String quotationId) throws Exception;

	public DataSet getDataSetByQuotationId(String quotationId) throws Exception;
}