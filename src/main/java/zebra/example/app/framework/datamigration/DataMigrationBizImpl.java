package zebra.example.app.framework.datamigration;

import org.springframework.beans.factory.annotation.Autowired;

import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseBiz;
import zebra.example.conf.resource.ormapper.dao.Dummy.DummyDao;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class DataMigrationBizImpl extends BaseBiz implements DataMigrationBiz {
	@Autowired
	private DummyDao dummyDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		String dataSourceNames[] = CommonUtil.split(ConfigUtil.getProperty("jdbc.multipleDatasource"), ConfigUtil.getProperty("delimiter.data"));

		try {
			paramEntity.setObject("datasourceDataSet", getDatasourceDataSet(dataSourceNames));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	public ParamEntity getTableList(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String dataSource = CommonUtil.nvl(requestDataSet.getValue("dataSource"), "hkaccount");

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);

			if (!CommonUtil.equalsIgnoreCase(dataSource, "hkaccount")) {
				dummyDao.setDataSourceName(dataSource);
				paramEntity.setAjaxResponseDataSet(dummyDao.getTableListDataSetByCriteriaForAdditionalDataSource(queryAdvisor));
			} else {
				dummyDao.resetDataSourceName();
				queryAdvisor.addAutoFillCriteria("table_name", "table_name like 'DM_%'");
				paramEntity.setAjaxResponseDataSet(dummyDao.getTableListDataSetByCriteria(queryAdvisor));
			}

			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String dataSource = requestDataSet.getValue("dataSource");
		String tableName = requestDataSet.getValue("tableName");

		try {
			if (!CommonUtil.equalsIgnoreCase(dataSource, "hkaccount")) {
				dummyDao.setDataSourceName(dataSource);
				paramEntity.setObject("resultDataSet", dummyDao.getTableDetailDataSetByTableNameForAdditionalDataSource(tableName));
			} else {
				dummyDao.resetDataSourceName();
				paramEntity.setObject("resultDataSet", dummyDao.getTableDetailDataSetByTableName(tableName));
			}

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeGenerate(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		DataSet tableInfoDataSet;
		String tableName = requestDataSet.getValue("tableName");
		String sourceDb = requestDataSet.getValue("sourceDb");
		String targetDb = requestDataSet.getValue("targetDb");
		String tableNamePrefix = "DM_";

		try {
			if (CommonUtil.containsIgnoreCase(tableName, "e5zg0_")) {
				if (CommonUtil.equalsIgnoreCase(tableName, "e5zg0_users")) {
					tableName = "users";
				} else {
					paramEntity.setSuccess(true);
					return paramEntity;
				}
			}

			if (CommonUtil.containsIgnoreCase(tableName, "zo7dp_")) {
				paramEntity.setSuccess(true);
				return paramEntity;
			}

			if (!CommonUtil.equalsIgnoreCase(targetDb, "hkaccount")) {
				dummyDao.setDataSourceName(targetDb);
				tableInfoDataSet = dummyDao.getTableDetailDataSetByTableNameForAdditionalDataSource(tableNamePrefix+tableName);
			} else {
				dummyDao.resetDataSourceName();
				tableInfoDataSet = dummyDao.getTableDetailDataSetByTableName(tableNamePrefix+tableName);
			}

			if (tableInfoDataSet == null || tableInfoDataSet.getRowCnt() <= 0) {
				createTable(sourceDb, targetDb, tableName, tableNamePrefix);
			} else {
				deleteData(targetDb, tableName, tableNamePrefix);
			}

			insertData(sourceDb, targetDb, tableName, tableNamePrefix);

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	private int createTable(String sourceDb, String targetDb, String tableName, String tableNamePrefix) throws Exception {
		DataSet tableInfoDataSet;
		String sql = "create table ", columnName, dataType, srcTableName, tgtTableName;

		try {
			if (CommonUtil.equalsIgnoreCase(tableName, "users")) {
				srcTableName = "e5zg0_users";
				tgtTableName = tableNamePrefix+tableName;
			} else {
				srcTableName = tableName;
				tgtTableName = tableNamePrefix+tableName;
			}

			if (!CommonUtil.equalsIgnoreCase(sourceDb, "hkaccount")) {
				dummyDao.setDataSourceName(sourceDb);
				tableInfoDataSet = dummyDao.getTableDetailDataSetByTableNameForAdditionalDataSource(srcTableName);
			} else {
				dummyDao.resetDataSourceName();
				tableInfoDataSet = dummyDao.getTableDetailDataSetByTableName(srcTableName);
			}

			sql += tgtTableName+" (";
			for (int i=0; i<tableInfoDataSet.getRowCnt(); i++) {
				columnName = tableInfoDataSet.getValue(i, "COLUMN_NAME");
				dataType = tableInfoDataSet.getValue(i, "DATA_TYPE");

				columnName = (CommonUtil.equalsIgnoreCase(columnName, "date")) ? tableName+"_"+columnName : columnName;
				sql += columnName+" "+getDataTypeString(dataType)+", ";
			}
			sql += "insert_date date";
			sql += ")";

			if (!CommonUtil.equalsIgnoreCase(targetDb, "hkaccount")) {
				dummyDao.setDataSourceName(targetDb);
			} else {
				dummyDao.resetDataSourceName();
			}

			return dummyDao.createTable(sql);
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private int deleteData(String targetDb, String tableName, String tableNamePrefix) throws Exception {
		try {
			if (!CommonUtil.equalsIgnoreCase(targetDb, "hkaccount")) {
				dummyDao.setDataSourceName(targetDb);
			} else {
				dummyDao.resetDataSourceName();
			}

			return dummyDao.deleteData("delete from "+tableNamePrefix+tableName);
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private int insertData(String sourceDb, String targetDb, String tableName, String tableNamePrefix) throws Exception {
		DataSet dataSet, tableInfoDataSet;
		String selectColumn = "", insertColumn = "", sqlInsert = "", sqlSelect = "select ", srcTableName, tgtTableName;
		String insertColumns[];
		int result = 0;

		try {
			if (CommonUtil.equalsIgnoreCase(tableName, "users")) {
				srcTableName = "e5zg0_users";
				tgtTableName = tableNamePrefix+tableName;
			} else {
				srcTableName = tableName;
				tgtTableName = tableNamePrefix+tableName;
			}

			if (!CommonUtil.equalsIgnoreCase(sourceDb, "hkaccount")) {
				dummyDao.setDataSourceName(sourceDb);
				tableInfoDataSet = dummyDao.getTableDetailDataSetByTableNameForAdditionalDataSource(srcTableName);
			} else {
				dummyDao.resetDataSourceName();
				tableInfoDataSet = dummyDao.getTableDetailDataSetByTableName(srcTableName);
			}

			for (int i=0; i<tableInfoDataSet.getRowCnt(); i++) {
				String dataType = getDataType(tableInfoDataSet.getValue(i, "DATA_TYPE"));

				if (CommonUtil.equalsIgnoreCase(dataType, "date")) {
					selectColumn += "date_format("+tableInfoDataSet.getValue(i, "COLUMN_NAME")+", '%Y%m%d%H%i%s') as "+tableInfoDataSet.getValue(i, "COLUMN_NAME");
				} else {
					selectColumn += tableInfoDataSet.getValue(i, "COLUMN_NAME");
				}

				if (i != tableInfoDataSet.getRowCnt()-1) {
					selectColumn += ",";
				}
			}

			if (!CommonUtil.equalsIgnoreCase(sourceDb, "hkaccount")) {
				dummyDao.setDataSourceName(sourceDb);
			} else {
				dummyDao.resetDataSourceName();
			}
			dataSet = dummyDao.getDataSetBySQLQuery(sqlSelect+selectColumn+" from "+srcTableName);

			for (int i=0; i<dataSet.getColumnCnt(); i++) {
				insertColumn += CommonUtil.equalsIgnoreCase(tableInfoDataSet.getValue(i, "COLUMN_NAME"), "date") ? tableName+"_"+tableInfoDataSet.getValue(i, "COLUMN_NAME")+"," : tableInfoDataSet.getValue(i, "COLUMN_NAME")+",";
			}
			insertColumn += "insert_date";
			insertColumns = CommonUtil.split(insertColumn, ",");

			for (int i=0; i<dataSet.getRowCnt(); i++) {
				sqlInsert = "";
				sqlInsert += "insert into "+tgtTableName+" ("+insertColumn+") ";
				sqlInsert += "values (";
				for (int j=0; j<insertColumns.length; j++) {
					String colName = CommonUtil.equalsIgnoreCase(insertColumns[j], tableName+"_date") ? CommonUtil.remove(insertColumns[j], tableName+"_") : insertColumns[j];
					if (CommonUtil.equalsIgnoreCase(colName, "insert_date")) {continue;}
					int rowIndex = tableInfoDataSet.getRowIndex("COLUMN_NAME", colName);
					String dataType = getDataType(tableInfoDataSet.getValue(rowIndex, "DATA_TYPE"));
					String value = CommonUtil.replace(dataSet.getValue(i, insertColumns[j]), "'", "''");

					if (CommonUtil.equalsIgnoreCase(dataType, "date")) {
						if (CommonUtil.isBlank(value) || CommonUtil.equals(value, "00000000000000")) {
							sqlInsert += "null,";
						} else {
							sqlInsert += "to_date('"+value+"', 'yyyymmddhh24miss'),";
						}
					} else if (CommonUtil.equalsIgnoreCase(dataType, "number")) {
						if (CommonUtil.isBlank(value)) {
							sqlInsert += "null,";
						} else {
							sqlInsert += value+",";
						}
					} else {
						if (CommonUtil.isBlank(value)) {
							sqlInsert += "null,";
						} else {
							sqlInsert += "'"+value+"',";
						}
					}
				}

				sqlInsert += "sysdate";
				sqlInsert += ")";

				if (!CommonUtil.equalsIgnoreCase(targetDb, "hkaccount")) {
					dummyDao.setDataSourceName(targetDb);
				} else {
					dummyDao.resetDataSourceName();
				}

				result += dummyDao.insertData(sqlInsert);
			}
			return result;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private String getDataTypeString(String dataType) {
		if (CommonUtil.containsIgnoreCase(dataType, "int")) {
			return "number(12)";
		} else if (CommonUtil.containsIgnoreCase(dataType, "decimal")) {
			return "number(12, 2)";
		} else if (CommonUtil.containsIgnoreCase(dataType, "date") || CommonUtil.containsIgnoreCase(dataType, "datetime")) {
			return "date";
		} else {
			return "varchar2(500)";
		}
	}

	private String getDataType(String value) {
		if (CommonUtil.containsIgnoreCase(value, "int") || CommonUtil.containsIgnoreCase(value, "decimal")) {
			return "number";
		} else if (CommonUtil.containsIgnoreCase(value, "date") || CommonUtil.containsIgnoreCase(value, "datetime")) {
			return "date";
		} else {
			return "varchar";
		}
	}

	private DataSet getDatasourceDataSet(String[] dataSourceNames) throws Exception {
		DataSet dataSourceDataSet = new DataSet();

		dataSourceDataSet.addName(new String[] {"VALUE", "NAME"});
		for (int i=0; i<dataSourceNames.length; i++) {
			dataSourceDataSet.addRow();
			dataSourceDataSet.setValue(i, "VALUE", dataSourceNames[i]);
			dataSourceDataSet.setValue(i, "NAME", dataSourceNames[i]);
		}

		return dataSourceDataSet;
	}
}