package zebra.example.conf.resource.ormapper.dao.Dummy;

import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface DummyDao extends IDao {
	/**
	 * Get table list dataset by search criteria in QueryAdvisor
	 * @param queryAdvisor
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getTableListDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	/**
	 * For Additional datasource - MySql
	 * @param queryAdvisor
	 * @return
	 * @throws Exception
	 */
	public DataSet getTableListDataSetByCriteriaForMySqlAdditionalDataSource(QueryAdvisor queryAdvisor) throws Exception;
	/**
	 * Get table detail by TableName
	 * @param tableName
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getTableDetailDataSetByTableName(String tableName) throws Exception;
	/**
	 * For Additional datasource - MySql
	 * @param queryAdvisor
	 * @return
	 * @throws Exception
	 */
	public DataSet getTableDetailDataSetByTableNameForMySqlAdditionalDataSource(String tableName) throws Exception;
	/**
	 * Select all
	 * @param sqlQuery
	 * @return DataSet
	 * @throws Exception
	 */
	public DataSet getDataSetBySQLQuery(String sqlQuery) throws Exception;
	/**
	 * Create table with given sql
	 * @param sql
	 * @return int
	 * @throws Exception
	 */
	public int createTable(String sql) throws Exception;
	/**
	 * Insert data with given sql
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int insertData(String sql) throws Exception;
	/**
	 * Delete existing data
	 * @param sql
	 * @return int
	 * @throws Exception
	 */
	public int deleteData(String sql) throws Exception;
}