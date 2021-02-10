/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_BS_TRAN_ALLOC - Bank statement transaction allocation - transaction reconcilisation
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBsTranAlloc;

import project.conf.resource.ormapper.dto.oracle.UsrBsTranAlloc;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface UsrBsTranAllocDao extends IDao {
	public int insert(UsrBsTranAlloc usrBsTranAlloc) throws Exception;

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByFileDataForDupCheck(QueryAdvisor queryAdvisor) throws Exception;
}