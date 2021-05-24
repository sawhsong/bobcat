/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_CC_ALLOC - Credit card statement transaction allocation - transaction reconciliation
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrCcAlloc;

import project.conf.resource.ormapper.dto.oracle.UsrCcAlloc;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrCcAllocDao extends IDao {
	public int insert(UsrCcAlloc usrCcAlloc) throws Exception;
	public int update(String ccAllocId, UsrCcAlloc usrCcAlloc) throws Exception;
	public int updateColumn(String ccAllocId, UsrCcAlloc usrCcAlloc) throws Exception;

	public UsrCcAlloc getCcAllocByCcAllocId(String ccAllocId) throws Exception;
	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByFileDataForDupCheck(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByCcAllocId(String ccAllocId) throws Exception;
}