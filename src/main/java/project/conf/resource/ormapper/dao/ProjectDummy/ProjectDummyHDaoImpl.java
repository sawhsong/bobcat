package project.conf.resource.ormapper.dao.ProjectDummy;

import java.util.Date;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class ProjectDummyHDaoImpl extends BaseHDao implements ProjectDummyDao {
	public DataSet getIdBySequenceName(String sequeceName) throws Exception {
		return selectAsDataSetBySQLQuery("select "+sequeceName+".nextval from dual");
	}

	public DataSet getFinacialQuarter(QueryAdvisor queryAdvisor) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String quarterCode = CommonUtil.substring((String)queryAdvisor.getObject("quarterCode"), 1);

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("year", (String)queryAdvisor.getObject("year"));
		queryAdvisor.addAutoFillCriteria(quarterCode, "a.quarter_code = '"+quarterCode+"'");
		queryAdvisor.addOrderByClause("a.quarter_code");

		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getFinacialQuarter");
	}

	public DataSet getFinacialYear() throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getFinacialYear");
	}

	public DataSet getFinacialYear(Date date) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.default.date");

		queryAdvisor.addVariable("asDate", CommonUtil.toString(date, dateFormat));
		queryAdvisor.addVariable("dateFormat", dateFormat);
		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getFinacialYearWithAsOfDate");
	}

	public DataSet getDatabaseSessionList(QueryAdvisor queryAdvisor) throws Exception {
		queryAdvisor.addVariable("dateTimeFormat", ConfigUtil.getProperty("format.dateTime.db.au"));

		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getDatabaseSessionList");
	}

	public DataSet getSqlTextBySqlId(String sqlId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addVariable("dateTimeFormat", ConfigUtil.getProperty("format.dateTime.db.au"));
		queryAdvisor.addWhereClause("sql_id = '"+sqlId+"'");

		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getSqlTextBySqlId");
	}

	public int killSessions(String ids[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;
		String sidWhere = "", serialWhere = "";
		DataSet ds = new DataSet();

		if (!(ids == null || ids.length == 0)) {
			for (int i=0; i<ids.length; i++) {
				String sid = CommonUtil.split(ids[i], "_")[0];
				String serial = CommonUtil.split(ids[i], "_")[1];

				sidWhere += CommonUtil.isBlank(sidWhere) ? "'"+sid+"'" : ", "+"'"+sid+"'";
				serialWhere += CommonUtil.isBlank(serialWhere) ? "'"+serial+"'" : ", "+"'"+serial+"'";
			}
		}
		queryAdvisor.addWhereClause("sid in ("+sidWhere+")");
		queryAdvisor.addWhereClause("serial# in ("+serialWhere+")");

		ds = getDatabaseSessionList(queryAdvisor);
		for (int i=0; i<ds.getRowCnt(); i++) {
			result += executeSql(ds.getValue(i, "QUERY_TO_KILL"));
		}

		return result;
	}

	public DataSet getTableListDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String tableName = requestDataSet.getValue("tableName");

		queryAdvisor.addAutoFillCriteria(tableName, "upper(table_name) like upper('%"+tableName+"%')");

		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getTableListDataSetByCriteria");
	}

	public DataSet getTableListDataSetByCriteriaForAdditionalDataSource(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String tableName = requestDataSet.getValue("tableName");

		queryAdvisor.addAutoFillCriteria(tableName, "upper(table_name) like upper('%"+tableName+"%')");

		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getTableListDataSetByCriteriaForAdditionalDataSource");
	}

	public DataSet getTableDetailDataSetByTableName(String tableName) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addVariable("table_name", tableName);
		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getTableDetailDataSetByTableName");
	}

	public DataSet getTableDetailDataSetByTableNameForAdditionalDataSource(String tableName) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addVariable("table_name", tableName);
		return selectAsDataSet(queryAdvisor, "query.ProjectDummy.getTableDetailDataSetByTableNameForAdditionalDataSource");
	}
}