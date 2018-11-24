/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_ORG - Organisation Info
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysOrg;

import project.conf.resource.ormapper.dto.oracle.SysOrg;
import zebra.base.Dto;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysOrgDao extends IDao {
	/**
	 * Insert new org record
	 * @param dto(SysOrg)
	 * @return int
	 * @throws Exception
	 */
	public int insert(Dto dto) throws Exception;
	/**
	 * Update SysOrg
	 * @param orgId
	 * @param dto(SysOrg)
	 * @return
	 * @throws Exception
	 */
	public int update(String orgId, Dto dto) throws Exception;
	/**
	 * Delete SysOrg record by Id array
	 * @param orgIds
	 * @return int
	 * @throws Exception
	 */
	public int delete(String[] orgIds) throws Exception;
	/**
	 * Delete SysOrg record by Id
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int delete(String orgId) throws Exception;
	/**
	 * Auto Completion - Org Name
	 * @param queryAdvisor
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getOrgNameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	/**
	 * Auto Completion - Org ABN
	 * @param queryAdvisor
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getAbnDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	/**
	 * Auto Completion - Org ID and Org Legal Name
	 * @param queryAdvisor
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getOrgIdDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	/**
	 * Organisation Search
	 * @param queryAdvisor
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getOrgDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	/**
	 * Get SysOrg object with given orgId
	 * @param orgId
	 * @return SysOrg
	 * @throws Exception
	 */
	public SysOrg getOrgByOrgId(String orgId) throws Exception;
}