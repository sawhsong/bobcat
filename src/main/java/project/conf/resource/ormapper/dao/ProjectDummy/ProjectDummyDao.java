package project.conf.resource.ormapper.dao.ProjectDummy;

import java.util.Date;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface ProjectDummyDao extends IDao {
	/*!
	 * Key
	 */
	public DataSet getIdBySequenceName(String sequeceName) throws Exception;

	public DataSet getFinacialQuarter(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getFinacialYear() throws Exception;
	public DataSet getFinacialYear(Date date) throws Exception;
	public DataSet getDatabaseSessionList(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getSqlTextBySqlId(String sqlId) throws Exception;
	public int killSessions(String ids[]) throws Exception;

	public DataSet getTableListDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getTableListDataSetByCriteriaForAdditionalDataSource(QueryAdvisor queryAdvisor) throws Exception;
	public DataSet getTableDetailDataSetByTableName(String tableName) throws Exception;
	public DataSet getTableDetailDataSetByTableNameForAdditionalDataSource(String tableName) throws Exception;
}