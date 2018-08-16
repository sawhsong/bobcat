package zebra.example.conf.resource.ormapper.dao.Dummy;

import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseHDao;

public class DummyHDaoImpl extends BaseHDao implements DummyDao {
	public DataSet getTableListDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String tableName = requestDataSet.getValue("tableName");

		queryAdvisor.addAutoFillCriteria(tableName, "upper(table_name) like upper('%"+tableName+"%')");

		return selectAsDataSet(queryAdvisor, "query.zebra.Dummy.getTableListDataSetByCriteria");
	}

	public DataSet getTableListDataSetByCriteriaForMySqlAdditionalDataSource(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String tableName = requestDataSet.getValue("tableName");

		queryAdvisor.addAutoFillCriteria(tableName, "upper(table_name) like upper('%"+tableName+"%')");

		return selectAsDataSet(queryAdvisor, "query.zebra.Dummy.getTableListDataSetByCriteriaForMySqlAdditionalDataSource");
	}

	public DataSet getTableDetailDataSetByTableName(String tableName) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addVariable("table_name", tableName);
		return selectAsDataSet(queryAdvisor, "query.zebra.Dummy.getTableDetailDataSetByTableName");
	}

	public DataSet getTableDetailDataSetByTableNameForMySqlAdditionalDataSource(String tableName) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addVariable("table_name", tableName);
		return selectAsDataSet(queryAdvisor, "query.zebra.Dummy.getTableDetailDataSetByTableNameForMySqlAdditionalDataSource");
	}

	public DataSet getDataSetBySQLQuery(String sqlQuery) throws Exception {
		return selectAsDataSetBySQLQuery(sqlQuery);
	}

	public int createTable(String sql) throws Exception {
		
		return executeSql(sql);
	}

	public int insertData(String sql) throws Exception {
		return executeSql(sql);
	}

	public int deleteData(String sql) throws Exception {
		return executeSql(sql);
	}
}