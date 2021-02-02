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
	public int insert(Dto dto) throws Exception;
	public int update(String orgId, Dto dto) throws Exception;
	public int delete(String[] orgIds) throws Exception;
	public int delete(String orgId) throws Exception;

	public DataSet getOrgNameDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getAbnDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getOrgIdDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getOrgInfoDataSetForAutoCompletion(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getOrgDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getDataSetByOrgId(String orgId) throws Exception;
	public SysOrg getOrgByOrgId(String orgId) throws Exception;
}