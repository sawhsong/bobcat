package zebra.example.app.framework.dtogenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import zebra.config.MemoryBean;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseBiz;
import zebra.example.conf.resource.ormapper.dao.Dummy.DummyDao;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class DtoGeneratorBizImpl extends BaseBiz implements DtoGeneratorBiz {
	@Autowired
	private DummyDao dummyDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String dataSourceNames[] = CommonUtil.split(ConfigUtil.getProperty("jdbc.multipleDatasource"), ConfigUtil.getProperty("delimiter.data"));

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setObject("datasourceDataSet", getDatasourceDataSet(dataSourceNames));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	public ParamEntity getList(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String defaultDataSourceUser = ConfigUtil.getProperty("jdbc.user.name");
		String dataSource = CommonUtil.nvl(requestDataSet.getValue("dataSource"), defaultDataSourceUser);
		String dataSourceNames[] = CommonUtil.split(ConfigUtil.getProperty("jdbc.multipleDatasource"), ConfigUtil.getProperty("delimiter.data"));

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);
			queryAdvisor.setPagination(false);

			if (!CommonUtil.equalsIgnoreCase(dataSource, "defaultDataSourceUser")) {
				dummyDao.setDataSourceName(dataSource);
				paramEntity.setAjaxResponseDataSet(dummyDao.getTableListDataSetByCriteriaForAdditionalDataSource(queryAdvisor));
			} else {
				dummyDao.resetDataSourceName();
				paramEntity.setAjaxResponseDataSet(dummyDao.getTableListDataSetByCriteria(queryAdvisor));
			}

			paramEntity.setObject("datasourceDataSet", getDatasourceDataSet(dataSourceNames));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String defaultDataSourceUser = ConfigUtil.getProperty("jdbc.user.name");
		String dataSource = requestDataSet.getValue("dataSource");
		String tableName = requestDataSet.getValue("tableName");

		try {
			if (!CommonUtil.equalsIgnoreCase(dataSource, defaultDataSourceUser)) {
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

	public ParamEntity getGeneratorInfo(ParamEntity paramEntity) throws Exception {
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String dbVendor = CommonUtil.lowerCase(ConfigUtil.getProperty("db.vendor"));

		String dtoProject = CommonUtil.replace(ConfigUtil.getProperty("path.common.dtoProject"), "#DB_VENDOR#", dbVendor);
		String daoProject = ConfigUtil.getProperty("path.common.daoProject");
		String hibernateQueryProject = CommonUtil.replace(ConfigUtil.getProperty("path.source.hibernateQueryProject"), "#DB_VENDOR#", dbVendor);
		String mybatisQueryProject = CommonUtil.replace(ConfigUtil.getProperty("path.source.mybatisQueryProject"), "#DB_VENDOR#", dbVendor);

		String dtoFramework = CommonUtil.replace(ConfigUtil.getProperty("path.common.dtoFwk"), "#DB_VENDOR#", dbVendor);
		String daoFramework = ConfigUtil.getProperty("path.common.daoFwk");
		String hibernateQueryFramework = CommonUtil.replace(ConfigUtil.getProperty("path.source.hibernateQueryFwk"), "#DB_VENDOR#", dbVendor);
		String mybatisQueryFramework = CommonUtil.replace(ConfigUtil.getProperty("path.source.mybatisQueryFwk"), "#DB_VENDOR#", dbVendor);

		try {
			paramEntity.setObject("dtoProjectPath", rootPath + dtoProject);
			paramEntity.setObject("daoProjectPath", rootPath + daoProject);
			paramEntity.setObject("hibernateQueryProjectPath", rootPath + hibernateQueryProject);
			paramEntity.setObject("mybatisQueryProjectPath", rootPath + mybatisQueryProject);

			paramEntity.setObject("dtoFrameworkPath", rootPath + dtoFramework);
			paramEntity.setObject("daoFrameworkPath", rootPath + daoFramework);
			paramEntity.setObject("hibernateQueryFrameworkPath", rootPath + hibernateQueryFramework);
			paramEntity.setObject("mybatisQueryFrameworkPath", rootPath + mybatisQueryFramework);

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
		String dataSource = requestDataSet.getValue("dataSource");

		String system = requestDataSet.getValue("system");

		boolean dtoProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("dtoProject"), "N"));
		boolean hibernateDtoConfigProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("hibernateDtoConfigProject"), "N"));
		boolean mybatisDtoMapperConfigProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("mybatisDtoMapperConfigProject"), "N"));
		boolean daoProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("daoProject"), "N"));
		boolean hibernateDaoImplProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("hibernateDaoImplProject"), "N"));
		boolean mybatisDaoImplProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("mybatisDaoImplProject"), "N"));
		boolean daoSpringConfigProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("daoSpringConfigProject"), "N"));
		boolean hibernateQueryProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("hibernateQueryProject"), "N"));
		boolean mybatisQueryProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("mybatisQueryProject"), "N"));

		boolean dtoFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("dtoFramework"), "N"));
		boolean hibernateDtoConfigFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("hibernateDtoConfigFramework"), "N"));
		boolean mybatisDtoMapperConfigFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("mybatisDtoMapperConfigFramework"), "N"));
		boolean daoFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("daoFramework"), "N"));
		boolean hibernateDaoImplFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("hibernateDaoImplFramework"), "N"));
		boolean mybatisDaoImplFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("mybatisDaoImplFramework"), "N"));
		boolean daoSpringConfigFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("daoSpringConfigFramework"), "N"));
		boolean hibernateQueryFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("hibernateQueryFramework"), "N"));
		boolean mybatisQueryFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("mybatisQueryFramework"), "N"));

		try {
			if (!CommonUtil.equalsIgnoreCase(dataSource, "ZEBRA")) {
				dummyDao.setDataSourceName(dataSource);
			} else {
				dummyDao.resetDataSourceName();
			}

			tableInfoDataSet = dummyDao.getTableDetailDataSetByTableName(tableName);

			if (dtoProject || dtoFramework) {
				generateDto(system, requestDataSet, tableInfoDataSet);
			}

			if (hibernateDtoConfigProject || hibernateDtoConfigFramework) {
				generateHibernateDtoConfig(system, requestDataSet, tableInfoDataSet);
			}

			if (mybatisDtoMapperConfigProject || mybatisDtoMapperConfigFramework) {
				generateMybatisDtoMapper(system, requestDataSet, tableInfoDataSet);
				generateMybatisDtoMapperXml(system, requestDataSet, tableInfoDataSet);
			}

			if (daoProject || daoFramework) {
				generateDao(system, requestDataSet, tableInfoDataSet);
			}

			if (hibernateDaoImplProject || hibernateDaoImplFramework) {
				generateHDaoImpl(system, requestDataSet, tableInfoDataSet);
			}

			if (mybatisDaoImplProject || mybatisDaoImplFramework) {
				generateDaoImpl(system, requestDataSet, tableInfoDataSet);
				generateDaoMapper(system, requestDataSet, tableInfoDataSet);
			}

			if (daoSpringConfigProject || daoSpringConfigFramework) {
				generateDaoSpringConfig(system, requestDataSet, tableInfoDataSet);
			}

			if (hibernateQueryProject || hibernateQueryFramework) {
				generateHibernateQuery(system, requestDataSet, tableInfoDataSet);
			}

			if (mybatisQueryProject || mybatisQueryFramework) {
				generateMybatisQuery(system, requestDataSet, tableInfoDataSet);
			}

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	private boolean generateDto(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String projectPackage = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.project"));
		String frameworkPackage = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.framework"));
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.javaDto");
		String dbVendor = CommonUtil.lowerCase(ConfigUtil.getProperty("db.vendor"));

		String dtoProjectPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.common.dtoProject"), "#DB_VENDOR#", dbVendor);
		String dtoFrameworkPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.common.dtoFwk"), "#DB_VENDOR#", dbVendor);

		String projectName = "", targetPath = "", packageString = "", tempString = "", sourceString = "";
		String columns = "", primaryKey = "", dateColumn = "", numberColumn = "", clobColumn = "";
		String accessors = "", defaultColumn = "", defaultValue = "", hibernateMethods = "";
		String toString = "", toXmlString = "", toJsonString = "", dataDefault = "";
		int numberOfPK = -1;
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				projectName = projectPackage;
				targetPath = dtoProjectPath;
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				projectName = frameworkPackage+".example";
				targetPath = dtoFrameworkPath;
			}

			createFolder(targetPath);

			targetFile = new File(targetPath+"/"+CommonUtil.toCamelCase(tableName)+".java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString+"\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(targetPath, rootPath+"src/main/java/"), "/", ".");

			toString = "String str = \"\";\n\n";
			toXmlString = "String str = \"\";\n\n";
			toJsonString = "String str = \"\";\n\n";

			for (int i=0; i<tableInfoDataSet.getRowCnt(); i++) {
				String dataType = tableInfoDataSet.getValue(i, "DATA_TYPE");
				String convertedDataType = getDataTypeString(dataType);
				String colNameUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(tableInfoDataSet.getValue(i, "COLUMN_NAME"));
				String colNameLowerCamelCase = CommonUtil.toCamelCaseStartLowerCase(tableInfoDataSet.getValue(i, "COLUMN_NAME"));

				columns += (CommonUtil.isBlank(columns) ? "" : "\t");
				columns += "private "+convertedDataType+" "+colNameLowerCamelCase+";\n";
				columns += "\tprivate String "+tableInfoDataSet.getValue(i, "COLUMN_NAME")+";\n";

				accessors += (CommonUtil.isBlank(accessors) ? "" : "\t");
				accessors += "public "+convertedDataType+" get"+colNameUpperCamelCase+"() {\n";
				accessors += "\t\treturn "+colNameLowerCamelCase+";\n";
				accessors += "\t}\n\n";
				accessors += "\tpublic void set"+colNameUpperCamelCase+"("+convertedDataType+" "+colNameLowerCamelCase+") throws Exception {\n";
				accessors += "\t\tthis."+colNameLowerCamelCase+" = "+colNameLowerCamelCase+";\n";
				if ((CommonUtil.equalsIgnoreCase(dataType, "NUMBER")) || (CommonUtil.equalsIgnoreCase(dataType, "DATE"))) {
					accessors += "\t\tsetValueFromAccessor(\""+tableInfoDataSet.getValue(i, "COLUMN_NAME")+"\", CommonUtil.toString("+colNameLowerCamelCase+"));\n";
				} else {
					accessors += "\t\tsetValueFromAccessor(\""+tableInfoDataSet.getValue(i, "COLUMN_NAME")+"\", "+colNameLowerCamelCase+");\n";
				}
				accessors += "\t}\n";
				if (i != (tableInfoDataSet.getRowCnt() - 1)) {
					accessors += "\n";
				}

				if (CommonUtil.equalsIgnoreCase(tableInfoDataSet.getValue(i, "CONSTRAINT_TYPE"), "P")) {
					numberOfPK++;
					primaryKey += CommonUtil.isBlank(primaryKey) ? tableInfoDataSet.getValue(i, "COLUMN_NAME") : ","+tableInfoDataSet.getValue(i, "COLUMN_NAME");
				}

				if (CommonUtil.equalsIgnoreCase(dataType, "DATE")) {
					dateColumn += CommonUtil.isBlank(dateColumn) ? tableInfoDataSet.getValue(i, "COLUMN_NAME") : ","+tableInfoDataSet.getValue(i, "COLUMN_NAME");
				}

				if (CommonUtil.equalsIgnoreCase(dataType, "NUMBER")) {
					numberColumn += CommonUtil.isBlank(numberColumn) ? tableInfoDataSet.getValue(i, "COLUMN_NAME") : ","+tableInfoDataSet.getValue(i, "COLUMN_NAME");
				}

				if (CommonUtil.equalsIgnoreCase(dataType, "CLOB")) {
					clobColumn += CommonUtil.isBlank(clobColumn) ? tableInfoDataSet.getValue(i, "COLUMN_NAME") : ","+tableInfoDataSet.getValue(i, "COLUMN_NAME");
				}

				dataDefault = CommonUtil.replace(tableInfoDataSet.getValue(i, "DATA_DEFAULT"), " ", "");
				if (!(CommonUtil.isBlank(dataDefault) || CommonUtil.equalsIgnoreCase(dataDefault, "''") || CommonUtil.equalsIgnoreCase(dataDefault, "'"))) {
					defaultColumn += CommonUtil.isBlank(defaultColumn) ? tableInfoDataSet.getValue(i, "COLUMN_NAME") : ","+tableInfoDataSet.getValue(i, "COLUMN_NAME");

					if (CommonUtil.equalsIgnoreCase(dataType, "CLOB")) {
						defaultValue += CommonUtil.isBlank(defaultValue) ? "EMPTY_CLOB()" : ",EMPTY_CLOB()";
					} else {
						defaultValue += CommonUtil.isBlank(defaultValue) ? CommonUtil.replace(dataDefault, "'", "") : ","+CommonUtil.replace(dataDefault, "'", "");
					}
				}

				toString += "\t\tstr += \""+colNameLowerCamelCase+" : \"+"+colNameLowerCamelCase+"+\"\\n\";\n";
				toXmlString += "\t\tstr += \"<column name=\\\""+colNameLowerCamelCase+"\\\" value=\\\"\"+"+colNameLowerCamelCase+"+\"\\\">\";\n";
				toJsonString += "\t\tstr += \"\\\""+colNameLowerCamelCase+"\\\":\\\"\"+"+colNameLowerCamelCase+"+\"\\\", \";\n";
			}

			if (numberOfPK > 0) {
				hibernateMethods += "public boolean equals(Object object) {\n";
				hibernateMethods += "\t\tif (!(object instanceof "+CommonUtil.toCamelCase(tableName)+")) {\n";
				hibernateMethods += "\t\t\treturn false;\n";
				hibernateMethods += "\t\t}\n";
				hibernateMethods += "\t\treturn true;\n";
				hibernateMethods += "\t}\n";
				hibernateMethods += "\n";
				hibernateMethods += "\tpublic int hashCode() {\n";
				hibernateMethods += "\t\treturn 1;\n";
				hibernateMethods += "\t}\n";
			}

			columns += "\tprivate String insertUserName;\n";
			columns += "\tprivate String INSERT_USER_NAME;\n";
			columns += "\tprivate String updateUserName;\n";
			columns += "\tprivate String UPDATE_USER_NAME;\n";

			accessors += "\n";
			accessors += "\tpublic String getInsertUserName() {\n";
			accessors += "\t\treturn insertUserName;\n";
			accessors += "\t}\n";

			accessors += "\n";
			accessors += "\tpublic void setInsertUserName(String insertUserName) throws Exception {\n";
			accessors += "\t\tthis.insertUserName = insertUserName;\n";
			accessors += "\t\tsetValueFromAccessor(\"INSERT_USER_NAME\", insertUserName);\n";
			accessors += "\t}\n";

			accessors += "\n";
			accessors += "\tpublic String getUpdateUserName() {\n";
			accessors += "\t\treturn updateUserName;\n";
			accessors += "\t}\n";

			accessors += "\n";
			accessors += "\tpublic void setUpdateUserName(String updateUserName) throws Exception {\n";
			accessors += "\t\tthis.updateUserName = updateUserName;\n";
			accessors += "\t\tsetValueFromAccessor(\"UPDATE_USER_NAME\", updateUserName);\n";
			accessors += "\t}\n";

			toString += "\t\tstr += \"insertUserName : \"+insertUserName+\"\\n\";\n";
			toString += "\t\tstr += \"updateUserName : \"+updateUserName+\"\\n\";\n";
			toXmlString += "\t\tstr += \"<column name=\\\"insertUserName\\\" value=\\\"\"+insertUserName+\"\\\">\";\n";
			toXmlString += "\t\tstr += \"<column name=\\\"updateUserName\\\" value=\\\"\"+updateUserName+\"\\\">\";\n";
			toJsonString += "\t\tstr += \"\\\"insertUserName\\\":\\\"\"+insertUserName+\"\\\", \";\n";
			toJsonString += "\t\tstr += \"\\\"updateUserName\\\":\\\"\"+updateUserName+\"\\\"\";\n";

			toString += "\n\t\treturn str;";
			toXmlString += "\n\t\treturn str;";
			toJsonString += "\n\t\treturn str;";

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#PROJECT_NAME#", projectName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));
			sourceString = CommonUtil.replace(sourceString, "#COLUMNS#", columns);
			sourceString = CommonUtil.replace(sourceString, "#FRW_VAR_PRIMARY_KEY#", primaryKey);
			sourceString = CommonUtil.replace(sourceString, "#FRW_VAR_DATE_COLUMN#", dateColumn);
			sourceString = CommonUtil.replace(sourceString, "#FRW_VAR_NUMBER_COLUMN#", numberColumn);
			sourceString = CommonUtil.replace(sourceString, "#FRW_VAR_CLOB_COLUMN#", clobColumn);
			sourceString = CommonUtil.replace(sourceString, "#FRW_VAR_DEFAULT_COLUMN#", defaultColumn);
			sourceString = CommonUtil.replace(sourceString, "#FRW_VAR_DEFAULT_VALUE#", defaultValue);
			sourceString = CommonUtil.replace(sourceString, "#ACCESSORS#", accessors);
			sourceString = CommonUtil.replace(sourceString, "#HIBERNATE_METHODS#", hibernateMethods);
			sourceString = CommonUtil.replace(sourceString, "#TO_STRING#", toString);
			sourceString = CommonUtil.replace(sourceString, "#TO_XML_STRING#", toXmlString);
			sourceString = CommonUtil.replace(sourceString, "#TO_JSON_STRING#", toJsonString);

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateHibernateDtoConfig(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.hibernateDtoConfig");
		String dbVendor = CommonUtil.lowerCase(ConfigUtil.getProperty("db.vendor"));

		String dtoProjectPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.common.dtoProject"), "#DB_VENDOR#", dbVendor);
		String dtoFrameworkPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.common.dtoFwk"), "#DB_VENDOR#", dbVendor);
		String hibernateConfProjectPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.hibernateDtoConfProject"), "#DB_VENDOR#", dbVendor);
		String hibernateConfFrameworkPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.hibernateDtoConfFwk"), "#DB_VENDOR#", dbVendor);

		String autoFillString = ConfigUtil.getProperty("db.constants.autoFillString");
		String whereString = ConfigUtil.getProperty("db.constants.whereString");
		String orderByString = ConfigUtil.getProperty("db.constants.orderByString");
		String columnsToUpdateString = ConfigUtil.getProperty("db.constants.columnsToUpdateString");

		String packageString = "", targetPath = "", tempString = "", sourceString = "", dtoPath = "";
		String columns = "", primaryKey = "", qrySelectAll = "", qrySelectAllDetail = "", qryUpdateColumn = "";
		String qryDelete = "", qryInsertColumn = "", qryInsertValues = "", qryUpdate = "", qryUpdateDetails = "";
		int primaryKeyColumnCnt = 0;
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				targetPath = hibernateConfProjectPath;
				dtoPath = dtoProjectPath;
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				targetPath = hibernateConfFrameworkPath;
				dtoPath = dtoFrameworkPath;
			}

			primaryKeyColumnCnt = getPrimaryKeyColumnCnt(tableInfoDataSet);

			targetFile = new File(targetPath + "/" + CommonUtil.toCamelCase(tableName) + ".hbm.xml");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString + "\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(dtoPath, rootPath + "src/main/java/"), "/", ".");

			if (primaryKeyColumnCnt > 1) {
				primaryKey += "<composite-id>\n";
			}

			qrySelectAll += "SELECT ";

			qryInsertColumn += "INSERT INTO "+tableName+" (\n";
			qryInsertValues += "\t\tVALUES (\n";

			qryUpdate += "UPDATE "+tableName+"\n";
			qryUpdate += "\t\t   SET ";

			qryUpdateColumn += "UPDATE "+tableName+"\n";
			qryUpdateColumn += "\t\t   SET "+columnsToUpdateString+"\n";
			qryUpdateColumn += "\t\t WHERE 1 = 1\n";
			qryUpdateColumn += "\t\t "+whereString;

			qryDelete += "DELETE "+tableName+"\n";
			qryDelete += "\t\t WHERE 1 = 1\n";
			qryDelete += "\t\t   "+whereString;

			for (int i=0; i<tableInfoDataSet.getRowCnt(); i++) {
				String dataType = tableInfoDataSet.getValue(i, "DATA_TYPE");
				String dataLength = tableInfoDataSet.getValue(i, "DATA_LENGTH");
				String colNameOriginal = tableInfoDataSet.getValue(i, "COLUMN_NAME");
				String colNameLowerCamelCase = CommonUtil.toCamelCaseStartLowerCase(colNameOriginal);

				if (CommonUtil.equalsIgnoreCase(tableInfoDataSet.getValue(i, "CONSTRAINT_TYPE"), "P")) {
					if (primaryKeyColumnCnt > 1) {
						primaryKey += "\t\t\t<key-property name=\""+colNameLowerCamelCase+"\" column=\""+colNameOriginal+"\"";
						primaryKey += getXmlTypeProperty(dataType, dataLength);
						primaryKey += "/>\n";
					} else {
						primaryKey += "<id name=\""+colNameLowerCamelCase+"\" column=\""+colNameOriginal+"\"";
						primaryKey += getXmlTypeProperty(dataType, dataLength);
						primaryKey += "></id>";
					}
				} else {
					columns += (CommonUtil.isBlank(columns) ? "" : "\t\t");
					columns += "<property name=\""+colNameLowerCamelCase+"\" column=\""+colNameOriginal+"\"";
					columns += (CommonUtil.equalsIgnoreCase(tableInfoDataSet.getValue(i, "NULLABLE"), "N") ? " not-null=\"true\"" : "");
					columns += getXmlTypeProperty(dataType, dataLength);
					columns += "/>";
					columns += (i == tableInfoDataSet.getRowCnt()-1) ? "" : "\n";
				}

				if (CommonUtil.equalsIgnoreCase(dataType, "date")) {
					qrySelectAllDetail += CommonUtil.isBlank(qrySelectAllDetail) ? "TO_CHAR("+colNameOriginal+", 'yyyymmddhh24miss') AS "+colNameOriginal : "\t\t       TO_CHAR("+colNameOriginal+", 'yyyymmddhh24miss') AS "+colNameOriginal;
				} else {
					qrySelectAllDetail += CommonUtil.isBlank(qrySelectAllDetail) ? colNameOriginal : "\t\t       "+colNameOriginal;
				}
				qrySelectAllDetail += (i == tableInfoDataSet.getRowCnt() - 1) ? "\n" : ",\n";

				qryInsertColumn += "\t\t       "+colNameOriginal;
				qryInsertColumn += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";

				qryInsertValues += "\t\t       ${"+colNameOriginal+"}";
				qryInsertValues += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";

				if (!CommonUtil.equalsIgnoreCase(tableInfoDataSet.getValue(i, "CONSTRAINT_TYPE"), "P")) {
					qryUpdateDetails += CommonUtil.isBlank(qryUpdateDetails) ? colNameOriginal : "\t\t       "+colNameOriginal;
					qryUpdateDetails += " = ${"+colNameOriginal+"}";
					qryUpdateDetails += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";
				}
			}

			if (primaryKeyColumnCnt > 1) {
				primaryKey += "\t\t</composite-id>";
			}

			if (CommonUtil.isBlank(primaryKey)) {
				primaryKey = "<id column=\"ROWID\" type=\"java.lang.String\"/>";
			}

			qrySelectAll += qrySelectAllDetail + "\t\t  FROM "+tableName+"\n";
			qrySelectAll += "\t\t WHERE 1 = 1\n";
			qrySelectAll += "\t\t "+autoFillString+"\n";
			qrySelectAll += "\t\t "+whereString+"\n";
			qrySelectAll += "\t\t "+orderByString;

			qryInsertColumn += "\t\t       )\n";
			qryInsertValues += "\t\t       )";

			qryUpdate += qryUpdateDetails;
			qryUpdate += "\t\t WHERE 1 = 1\n";
			qryUpdate += "\t\t   " + whereString;

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE_LOWER#", CommonUtil.toCamelCaseStartLowerCase(tableName));
			sourceString = CommonUtil.replace(sourceString, "#PK_COLUMN#", primaryKey);
			sourceString = CommonUtil.replace(sourceString, "#COLUMNS#", columns);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_SELECT_ALL#", qrySelectAll);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_INSERT#", qryInsertColumn + qryInsertValues);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_UPDATE#", qryUpdate);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_UPDATE_COLUMNS#", qryUpdateColumn);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_DELETE#", qryDelete);

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateMybatisDtoMapper(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.mybatisDtoMapper");
		String dbVendor = CommonUtil.lowerCase(ConfigUtil.getProperty("db.vendor"));

		String mybatisMapperProjectPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.mybatisDtoMapperClassProject"), "#DB_VENDOR#", dbVendor);
		String mybatisMapperFrameworkPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.mybatisDtoMapperClassFwk"), "#DB_VENDOR#", dbVendor);

		String targetPath = "", packageString = "", tempString = "", sourceString = "";
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				targetPath = mybatisMapperProjectPath;
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				targetPath = mybatisMapperFrameworkPath;
			}
			targetPath += "/"+CommonUtil.toCamelCase(tableName);

			createFolder(targetPath);

			targetFile = new File(targetPath+"/"+CommonUtil.toCamelCase(tableName)+"Mapper.java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString + "\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(targetPath, rootPath + "src/main/java/"), "/", ".");

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateMybatisDtoMapperXml(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.mybatisDtoMapperXml");
		String dbVendor = CommonUtil.lowerCase(ConfigUtil.getProperty("db.vendor"));

		String dtoProjectPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.common.dtoProject"), "#DB_VENDOR#", dbVendor);
		String dtoFrameworkPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.common.dtoFwk"), "#DB_VENDOR#", dbVendor);
		String mybatisMapperProjectPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.mybatisDtoMapperXmlProject"), "#DB_VENDOR#", dbVendor);
		String mybatisMapperFrameworkPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.mybatisDtoMapperXmlFwk"), "#DB_VENDOR#", dbVendor);

		String autoFillString = ConfigUtil.getProperty("db.constants.autoFillString");
		String whereString = ConfigUtil.getProperty("db.constants.whereString");
		String orderByString = ConfigUtil.getProperty("db.constants.orderByString");
		String columnsToUpdateString = ConfigUtil.getProperty("db.constants.columnsToUpdateString");

		String packageString = "", targetPath = "", tempString = "", sourceString = "", dtoPath = "";
		String qrySelectAll = "", qrySelectAllDetail = "", qryUpdateColumn = "";
		String dtoName = "", qryInsertWithDtoValues = "", qryUpdateWithDto = "", qryUpdateWithDtoDetails = "";
		String qryDelete = "", qryInsertColumn = "", qryInsertValues = "", qryUpdate = "", qryUpdateDetails = "";
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				targetPath = mybatisMapperProjectPath;
				dtoPath = dtoProjectPath;
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				targetPath = mybatisMapperFrameworkPath;
				dtoPath = dtoFrameworkPath;
			}
			targetPath += "/"+CommonUtil.toCamelCase(tableName);

			createFolder(targetPath);

			targetFile = new File(targetPath+"/"+CommonUtil.toCamelCase(tableName)+"Mapper.xml");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString + "\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(targetPath, rootPath + "src/main/java/"), "/", ".");

			dtoName = CommonUtil.replace(CommonUtil.remove(dtoPath, rootPath + "src/main/java/"), "/", ".")+"."+CommonUtil.toCamelCase(tableName);

			qrySelectAll += "SELECT ";

			qryInsertColumn += "INSERT INTO "+tableName+" (\n";
			qryInsertValues += "\t\tVALUES (\n";
			qryInsertWithDtoValues += qryInsertValues;

			qryUpdate += "UPDATE "+tableName+"\n";
			qryUpdate += "\t\t   SET ";
			qryUpdateWithDto += qryUpdate;

			qryUpdateColumn += "UPDATE "+tableName+"\n";
			qryUpdateColumn += "\t\t   SET "+columnsToUpdateString+"\n";
			qryUpdateColumn += "\t\t WHERE 1 = 1\n";
			qryUpdateColumn += "\t\t "+whereString;

			qryDelete += "DELETE "+tableName+"\n";
			qryDelete += "\t\t WHERE 1 = 1\n";
			qryDelete += "\t\t "+whereString;

			for (int i=0; i<tableInfoDataSet.getRowCnt(); i++) {
				String colNameOriginal = tableInfoDataSet.getValue(i, "COLUMN_NAME");
				String colNameLowerCamelCase = CommonUtil.toCamelCaseStartLowerCase(colNameOriginal);
				String dataType = tableInfoDataSet.getValue(i, "DATA_TYPE");

				if (CommonUtil.equalsIgnoreCase(dataType, "date")) {
					qrySelectAllDetail += CommonUtil.isBlank(qrySelectAllDetail) ? "TO_CHAR("+colNameOriginal+", 'yyyymmddhh24miss') AS "+colNameOriginal : "\t\t       TO_CHAR("+colNameOriginal+", 'yyyymmddhh24miss') AS "+colNameOriginal;
				} else {
					qrySelectAllDetail += CommonUtil.isEmpty(qrySelectAllDetail) ? colNameOriginal : "\t\t       "+colNameOriginal;
				}
				qrySelectAllDetail += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";

				qryInsertColumn += "\t\t       "+colNameOriginal;
				qryInsertColumn += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";

				qryInsertValues += "\t\t       ${"+colNameOriginal+"}";
				qryInsertValues += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";

				qryInsertWithDtoValues += "\t\t       #{"+colNameLowerCamelCase+",jdbcType="+getDataTypeStringForMyBatisXml(dataType)+"}";
				qryInsertWithDtoValues += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";

				if (!CommonUtil.equalsIgnoreCase(tableInfoDataSet.getValue(i, "CONSTRAINT_TYPE"), "P")) {
					qryUpdateDetails += CommonUtil.isBlank(qryUpdateDetails) ? colNameOriginal : "\t\t       "+colNameOriginal;
					qryUpdateDetails += " = ${"+colNameOriginal+"}";
					qryUpdateDetails += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";

					qryUpdateWithDtoDetails += CommonUtil.isBlank(qryUpdateWithDtoDetails) ? colNameOriginal : "\t\t       "+colNameOriginal;
					qryUpdateWithDtoDetails += " = #{"+colNameLowerCamelCase+",jdbcType="+getDataTypeStringForMyBatisXml(dataType)+"}";
					qryUpdateWithDtoDetails += (i == tableInfoDataSet.getRowCnt()-1) ? "\n" : ",\n";
				}
			}

			qrySelectAll += qrySelectAllDetail + "\t\t  FROM "+tableName+"\n";
			qrySelectAll += "\t\t WHERE 1 = 1\n";
			qrySelectAll += "\t\t "+autoFillString+"\n";
			qrySelectAll += "\t\t "+whereString+"\n";
			qrySelectAll += "\t\t "+orderByString;

			qryInsertColumn += "\t\t       )\n";
			qryInsertValues += "\t\t       )";
			qryInsertWithDtoValues += "\t\t       )";

			qryUpdate += qryUpdateDetails;
			qryUpdate += "\t\t WHERE 1 = 1\n";
			qryUpdate += "\t\t " + whereString;
			qryUpdateWithDto += qryUpdateWithDtoDetails;
			qryUpdateWithDto += "\t\t WHERE 1 = 1\n";
			qryUpdateWithDto += "\t\t ${additionalAttributesForUpdateWithDto}";

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));
			sourceString = CommonUtil.replace(sourceString, "#DTO_NAME#", dtoName);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_SELECT_ALL#", qrySelectAll);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_INSERT#", qryInsertColumn + qryInsertValues);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_INSERT_WITH_DTO#", qryInsertColumn + qryInsertWithDtoValues);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_UPDATE#", qryUpdate);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_UPDATE_WITH_DTO#", qryUpdateWithDto);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_UPDATE_COLUMNS#", qryUpdateColumn);
			sourceString = CommonUtil.replace(sourceString, "#QUERY_DELETE#", qryDelete);

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateDao(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.javaDao");

		String daoProjectPath = rootPath + ConfigUtil.getProperty("path.common.daoProject");
		String daoFrameworkPath = rootPath + ConfigUtil.getProperty("path.common.daoFwk");

		String targetPath = "", packageString = "", tempString = "", sourceString = "";
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				targetPath = daoProjectPath;
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				targetPath = daoFrameworkPath;
			}
			targetPath += "/"+CommonUtil.toCamelCase(tableName);

			createFolder(targetPath);

			targetFile = new File(targetPath+"/"+CommonUtil.toCamelCase(tableName)+"Dao.java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString+"\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(targetPath, rootPath+"src/main/java/"), "/", ".");

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateHDaoImpl(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String projectPackage = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.project"));
		String frameworkPackage = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.framework"));
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.javaHDaoImpl");

		String daoProjectPath = rootPath + ConfigUtil.getProperty("path.common.daoProject");
		String daoFrameworkPath = rootPath + ConfigUtil.getProperty("path.common.daoFwk");

		String projectName = "", targetPath = "", packageString = "", tempString = "", sourceString = "";
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				projectName = projectPackage;
				targetPath = daoProjectPath;
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				projectName = frameworkPackage+".example";
				targetPath = daoFrameworkPath;
			}
			targetPath += "/"+CommonUtil.toCamelCase(tableName);

			createFolder(targetPath);

			targetFile = new File(targetPath+"/"+CommonUtil.toCamelCase(tableName)+"HDaoImpl.java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString+"\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(targetPath, rootPath+"src/main/java/"), "/", ".");

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#PROJECT_NAME#", projectName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateDaoImpl(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String projectPackage = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.project"));
		String frameworkPackage = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.framework"));
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.javaDaoImpl");

		String daoProjectPath = rootPath + ConfigUtil.getProperty("path.common.daoProject");
		String daoFrameworkPath = rootPath + ConfigUtil.getProperty("path.common.daoFwk");

		String projectName = "", targetPath = "", packageString = "", tempString = "", sourceString = "";
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				projectName = projectPackage;
				targetPath = daoProjectPath;
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				projectName = frameworkPackage+".example";
				targetPath = daoFrameworkPath;
			}
			targetPath += "/"+CommonUtil.toCamelCase(tableName);

			createFolder(targetPath);

			targetFile = new File(targetPath+"/"+CommonUtil.toCamelCase(tableName)+"DaoImpl.java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString+"\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(targetPath, rootPath+"src/main/java/"), "/", ".");

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#PROJECT_NAME#", projectName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE_LOWER#", CommonUtil.toCamelCaseStartLowerCase(tableName));

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateDaoMapper(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String projectPackage = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.project"));
		String frameworkPackage = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.framework"));
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.javaDaoMapper");

		String daoProjectPath = rootPath + ConfigUtil.getProperty("path.common.daoProject");
		String daoFrameworkPath = rootPath + ConfigUtil.getProperty("path.common.daoFwk");

		String projectName = "", targetPath = "", packageString = "", tempString = "", sourceString = "";
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				projectName = projectPackage;
				targetPath = daoProjectPath;
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				projectName = frameworkPackage+".example";
				targetPath = daoFrameworkPath;
			}
			targetPath += "/"+CommonUtil.toCamelCase(tableName);

			createFolder(targetPath);

			targetFile = new File(targetPath+"/"+CommonUtil.toCamelCase(tableName)+"DaoMapper.java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString+"\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(targetPath, rootPath+"src/main/java/"), "/", ".");

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#PROJECT_NAME#", projectName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateHibernateQuery(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String frameworkName = ConfigUtil.getProperty("name.framework");
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.hibernateQuery");
		String dbVendor = CommonUtil.lowerCase(ConfigUtil.getProperty("db.vendor"));

		String hibernateQueryProjectPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.hibernateQueryProject"), "#DB_VENDOR#", dbVendor);
		String hibernateQueryFrameworkPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.hibernateQueryFwk"), "#DB_VENDOR#", dbVendor);

		String targetPath = "", tempString = "", sourceString = "", fileNamePrefix = "";
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				targetPath = hibernateQueryProjectPath;
				fileNamePrefix = "query-";
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				targetPath = hibernateQueryFrameworkPath;
				fileNamePrefix = "query-"+frameworkName+"-";
			}

			targetFile = new File(targetPath + "/" + fileNamePrefix + CommonUtil.toCamelCase(tableName) + ".hbm.xml");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString + "\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateMybatisQuery(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String frameworkName = ConfigUtil.getProperty("name.framework");
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.mybatisQuery");
		String dbVendor = CommonUtil.lowerCase(ConfigUtil.getProperty("db.vendor"));

		String daoProjectPath = rootPath + ConfigUtil.getProperty("path.common.daoProject");
		String daoFrameworkPath = rootPath + ConfigUtil.getProperty("path.common.daoFwk");
		String mybatisQueryProjectPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.mybatisQueryProject"), "#DB_VENDOR#", dbVendor);
		String mybatisQueryFrameworkPath = rootPath + CommonUtil.replace(ConfigUtil.getProperty("path.source.mybatisQueryFwk"), "#DB_VENDOR#", dbVendor);

		String targetPath = "", tempString = "", packageString = "", sourceString = "", fileNamePrefix = "", daoPath = "";
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				targetPath = mybatisQueryProjectPath;
				fileNamePrefix = "query-";
				daoPath = daoProjectPath+"/"+CommonUtil.toCamelCase(tableName);
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				targetPath = mybatisQueryFrameworkPath;
				fileNamePrefix = "query-"+frameworkName+"-";
				daoPath = daoFrameworkPath+"/"+CommonUtil.toCamelCase(tableName);
			}

			targetFile = new File(targetPath+"/"+fileNamePrefix+CommonUtil.toCamelCase(tableName)+"Mapper.xml");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString + "\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(daoPath, rootPath+"src/main/java/"), "/", ".");

			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME#", tableName);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_DESCRIPTION#", tableInfoDataSet.getValue("TABLE_DESCRIPTION"));
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#TABLE_NAME_CAMELCASE#", CommonUtil.toCamelCaseStartUpperCase(tableName));

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean generateDaoSpringConfig(String systemType, DataSet requestDataSet, DataSet tableInfoDataSet) throws Exception {
		String projectName = ConfigUtil.getProperty("name.project");
		String frameworkName = ConfigUtil.getProperty("name.framework");
		String projectPath = ConfigUtil.getProperty("name.path.project");
		String frameworkPath = ConfigUtil.getProperty("name.path.framework");
		String tableName = requestDataSet.getValue("tableName");

		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "/target/alpaca");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String sourcePath = srcPath + "/" + ConfigUtil.getProperty("name.source.daoSpringConf");

		boolean hibernateDaoImplProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("hibernateDaoImplProject"), "N"));
		boolean mybatisDaoImplProject = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("mybatisDaoImplProject"), "N"));
		boolean hibernateDaoImplFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("hibernateDaoImplFramework"), "N"));
		boolean mybatisDaoImplFramework = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("mybatisDaoImplFramework"), "N"));

		String daoProjectPath = ConfigUtil.getProperty("path.common.daoProject");
		String daoFrameworkPath = ConfigUtil.getProperty("path.common.daoFwk");
		String daoSpringConfProjectPath = rootPath + ConfigUtil.getProperty("path.source.daoSpringConfProject");
		String daoSpringConfFrameworkPath = rootPath + ConfigUtil.getProperty("path.source.daoSpringConfFwk");

		String tableNameUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(tableName);
		String tableNameLowerCamelCase = CommonUtil.toCamelCaseStartLowerCase(tableName);
		String targetPath = "", tempString = "", sourceString = "", springConfFileName = "", existingFilePath = "";
		String confVariableName = "", daoPath = "";
		boolean isHibernateChecked = false, isMyBatisChecked = false;
		File targetFile, backupFile;

		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Document document;
		Element rootElement;
		XPath xpath;
		DOMSource domSource;

		try {
			if (CommonUtil.equalsIgnoreCase(systemType, "Project")) {
				springConfFileName = "spring-"+projectName+"-conf-resource-ormapper-dao.xml";
				targetPath = daoSpringConfProjectPath;
				daoPath = CommonUtil.substringAfter(daoProjectPath, projectPath);
				confVariableName = "${name.package.project}";

				if (hibernateDaoImplProject) {
					isHibernateChecked = true;
				}

				if (mybatisDaoImplProject) {
					isMyBatisChecked = true;
				}
			} else if (CommonUtil.equalsIgnoreCase(systemType, "Framework")) {
				springConfFileName = "spring-"+frameworkName+"-conf-resource-ormapper-dao.xml";
				targetPath = daoSpringConfFrameworkPath;
				daoPath = CommonUtil.substringAfter(daoFrameworkPath, frameworkPath);
				confVariableName = "${name.package.framework}";

				if (hibernateDaoImplFramework) {
					isHibernateChecked = true;
				}

				if (mybatisDaoImplFramework) {
					isMyBatisChecked = true;
				}
			}
			createFolder(targetPath);

			daoPath = CommonUtil.replace(daoPath, "/", ".")+"."+CommonUtil.toCamelCase(tableName);

			targetFile = new File(targetPath+"/"+springConfFileName);
			backupFile = new File(targetPath+"/"+springConfFileName+".bak");
			if (!targetFile.exists()) {
				targetFile.createNewFile();

				BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
				StringBuffer stringBuffer = new StringBuffer();
				while ((tempString = bufferedReader.readLine()) != null) {
					stringBuffer.append(tempString + "\n");
				}
				OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
				sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

				osWriter.write(sourceString);
				osWriter.flush();
				osWriter.close();
				bufferedReader.close();
			} else {
				if (!backupFile.exists()) {
					existingFilePath = targetFile.getAbsolutePath();
					Files.copy(Paths.get(existingFilePath), Paths.get(existingFilePath+".bak"), StandardCopyOption.REPLACE_EXISTING);
				}
			}

			document = docBuilder.parse(targetFile);
			rootElement = document.getDocumentElement();
			xpath = XPathFactory.newInstance().newXPath();
			domSource = new DOMSource(document);

			if (isHibernateChecked) {
				if (!isExistingHDao(document, tableNameLowerCamelCase+"Dao")) {
					Element beanElement = document.createElement("bean");

					beanElement.setAttribute("id", tableNameLowerCamelCase+"Dao");
					beanElement.setAttribute("name", tableNameLowerCamelCase+"Dao");
					beanElement.setAttribute("class", confVariableName+daoPath+"."+tableNameUpperCamelCase+"HDaoImpl");
					beanElement.setAttribute("parent", "baseHDao");
					beanElement.setAttribute("scope", "prototype");
					beanElement.setAttribute("init-method", "init");
					rootElement.appendChild(beanElement);

					Comment commentElement = document.createComment(tableNameUpperCamelCase+" DAO");
					beanElement.getParentNode().insertBefore(commentElement, beanElement);
				}
			}

			if (isMyBatisChecked) {
				if (!isExistingDao(document, tableNameLowerCamelCase+"Dao")) {
					Element beanElementDao = document.createElement("bean");
					Element beanElementMapper = document.createElement("bean");
					Element propertyInterface = document.createElement("property");
					Element propertyFactory = document.createElement("property");

					beanElementDao.setAttribute("id", tableNameLowerCamelCase+"Dao");
					beanElementDao.setAttribute("name", tableNameLowerCamelCase+"Dao");
					beanElementDao.setAttribute("class", confVariableName+daoPath+"."+tableNameUpperCamelCase+"DaoImpl");
					beanElementDao.setAttribute("parent", "namedParameterJdbcDao");
					beanElementDao.setAttribute("scope", "prototype");
					beanElementDao.setAttribute("init-method", "init");

					beanElementMapper.setAttribute("id", tableNameLowerCamelCase+"DaoMapper");
					beanElementMapper.setAttribute("name", tableNameLowerCamelCase+"DaoMapper");
					beanElementMapper.setAttribute("class", "org.mybatis.spring.mapper.MapperFactoryBean");

					propertyInterface.setAttribute("name", "mapperInterface");
					propertyInterface.setAttribute("value", confVariableName+daoPath+"."+tableNameUpperCamelCase+"DaoMapper");
					beanElementMapper.appendChild(propertyInterface);

					propertyFactory.setAttribute("name", "sqlSessionFactory");
					propertyFactory.setAttribute("ref", "sqlSessionFactory");
					beanElementMapper.appendChild(propertyFactory);

					rootElement.appendChild(beanElementDao);
					rootElement.appendChild(beanElementMapper);

					Comment commentElement = document.createComment(tableNameUpperCamelCase+" DAO");
					beanElementDao.getParentNode().insertBefore(commentElement, beanElementDao);
				}
			}

			if (isHibernateChecked || isMyBatisChecked) {
				StreamResult streamResult = new StreamResult(targetFile);

				NodeList nodeList = (NodeList)xpath.evaluate("//text()[normalize-space()='']", document, XPathConstants.NODESET);
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					node.getParentNode().removeChild(node);
				}
				transformer.setOutputProperty("encoding", "UTF-8");
				transformer.setOutputProperty("omit-xml-declaration", "false");
				transformer.setOutputProperty("indent", "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");
				transformer.transform(domSource, streamResult);
			}

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean isExistingHDao(Document document, String daoId) throws Exception {
		boolean isExist = false;
		NodeList nodeList = document.getElementsByTagName("bean");
		Node node;
		String idValue, parentValue;

		for (int i=0; i<nodeList.getLength(); i++) {
			node = nodeList.item(i);
			idValue = node.getAttributes().getNamedItem("id").getNodeValue();

			if (CommonUtil.contains(idValue, "Mapper")) {
				continue;
			}

			parentValue = node.getAttributes().getNamedItem("parent").getNodeValue();

			if (CommonUtil.equalsIgnoreCase(idValue, daoId) && CommonUtil.equalsIgnoreCase(parentValue, "baseHDao")) {
				isExist = true;
				break;
			}
		}

		return isExist;
	}

	private boolean isExistingDao(Document document, String daoId) throws Exception {
		boolean isExist = false;
		NodeList nodeList = document.getElementsByTagName("bean");
		Node node;
		String idValue, parentValue;

		for (int i=0; i<nodeList.getLength(); i++) {
			node = nodeList.item(i);
			idValue = node.getAttributes().getNamedItem("id").getNodeValue();

			if (CommonUtil.contains(idValue, "Mapper")) {
				continue;
			}

			parentValue = node.getAttributes().getNamedItem("parent").getNodeValue();

			if (CommonUtil.equalsIgnoreCase(idValue, daoId) && CommonUtil.equalsIgnoreCase(parentValue, "namedParameterJdbcDao")) {
				isExist = true;
				break;
			}
		}

		return isExist;
	}

	private String getDataTypeString(String value) {
		if (CommonUtil.contains(CommonUtil.upperCase(value), "NUMBER")) {
			return "double";
		} else if (CommonUtil.contains(CommonUtil.upperCase(value), "DATE")) {
			return "Date";
		} else {
			return "String";
		}
	}

	private String getDataTypeStringForMyBatisXml(String value) {
		if (CommonUtil.contains(CommonUtil.upperCase(value), "NUMBER")) {
			return "NUMERIC";
		} else if (CommonUtil.contains(CommonUtil.upperCase(value), "DATE")) {
			return "TIME";
		} else if (CommonUtil.contains(CommonUtil.upperCase(value), "CLOB")) {
			return "CLOB";
		} else {
			return "VARCHAR";
		}
	}

	private void createFolder(String folderPath) {
		File dir = new File(folderPath);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
	}

	private void createEmptyFile(File file) throws Exception {
		if (file.exists()) {
			String path = file.getAbsolutePath();
			Path pathSrc = Paths.get(path);
			Path pathTarget = Paths.get(path+".bak");

			Files.move(pathSrc, pathTarget, StandardCopyOption.REPLACE_EXISTING);
		}

		file.createNewFile();
	}

	private int getPrimaryKeyColumnCnt(DataSet dsTableInfo) throws Exception {
		int cnt = 0;

		for (int i=0; i<dsTableInfo.getRowCnt(); i++) {
			if (CommonUtil.equalsIgnoreCase("P", dsTableInfo.getValue(i, "CONSTRAINT_TYPE"))) {
				cnt++;
			}
		}

		return cnt;
	}

	private String getXmlTypeProperty(String dataType, String dataLength) {
		if (CommonUtil.equalsIgnoreCase(dataType, "NUMBER")) {
			return " type=\"java.lang.Double\"";
		} else if (CommonUtil.equalsIgnoreCase(dataType, "DATE")) {
			return " type=\"java.util.Date\"";
		} else if (CommonUtil.equalsIgnoreCase(dataType, "CLOB")) {
			return " type=\"java.lang.String\"";
		} else {
			return " type=\"java.lang.String\" length=\""+dataLength+"\"";
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