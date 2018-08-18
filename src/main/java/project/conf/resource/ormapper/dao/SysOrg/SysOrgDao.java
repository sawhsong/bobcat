/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_ORG - Organisation Info
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysOrg;

import project.conf.resource.ormapper.dto.oracle.SysOrg;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysOrgDao extends IDao {
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