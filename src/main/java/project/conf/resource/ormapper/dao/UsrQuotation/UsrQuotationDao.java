/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_QUOTATION - Quotation info for additioanl user service
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrQuotation;

import project.conf.resource.ormapper.dto.oracle.UsrQuotation;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrQuotationDao extends IDao {
	public int insert(UsrQuotation usrQuotation, DataSet detailDataSet) throws Exception;
	public int update(String quotationId, UsrQuotation usrQuotation, DataSet detailDataSet) throws Exception;
	public int updateColumn(String quotationId, UsrQuotation usrQuotation) throws Exception;

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByQuotationId(String quotationId) throws Exception;
}