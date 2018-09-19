package zebra.example.app.framework.sourcegenerator;

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
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import project.conf.resource.ormapper.dao.SysMenu.SysMenuDao;
import zebra.config.MemoryBean;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseBiz;
import zebra.example.common.module.commoncode.ZebraCommonCodeManager;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class SourceGeneratorBizImpl extends BaseBiz implements SourceGeneratorBiz {
	@Autowired
	private SysMenuDao sysMenuDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor qaMenu = paramEntity.getQueryAdvisor();
		QueryAdvisor qaList = paramEntity.getQueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String searchMenu = requestDataSet.getValue("searchMenu");

		try {
			qaMenu.addVariable("dateFormat", dateFormat);
			qaMenu.addAutoFillCriteria("searchMenu", "menu_level = '1'");
			qaMenu.setPagination(false);

			qaList.addVariable("dateFormat", dateFormat);
			qaList.addAutoFillCriteria(searchMenu, "root = '"+searchMenu+"'");
			qaList.setPagination(false);

			paramEntity.setObject("searchMenu", sysMenuDao.getAllActiveMenuDataSetBySearchCriteria(qaMenu));
			paramEntity.setObject("resultDataSet", sysMenuDao.getAllActiveMenuDataSetBySearchCriteria(qaList));

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	public ParamEntity getList(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor qaMenu = paramEntity.getQueryAdvisor();
		QueryAdvisor qaList = paramEntity.getQueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String searchMenu = requestDataSet.getValue("searchMenu");

		try {
			qaMenu.addVariable("dateFormat", dateFormat);
			qaMenu.addAutoFillCriteria("searchMenu", "menu_level = '1'");
			qaMenu.setPagination(false);

			qaList.addVariable("dateFormat", dateFormat);
			qaList.addAutoFillCriteria(searchMenu, "root = '"+searchMenu+"'");
			qaList.setPagination(false);

			paramEntity.setObject("searchMenu", sysMenuDao.getAllActiveMenuDataSetBySearchCriteria(qaMenu));
			paramEntity.setObject("resultDataSet", sysMenuDao.getAllActiveMenuDataSetBySearchCriteria(qaList));

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	public ParamEntity getGeneratorInfo(ParamEntity paramEntity) throws Exception {
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String javaPath = ConfigUtil.getProperty("path.java.app");
		String jspPath = ConfigUtil.getProperty("path.web.app");
		String springPath = ConfigUtil.getProperty("path.conf.menuSpring");
		String strutsPath = ConfigUtil.getProperty("path.conf.menuStruts");
		String messagePath = ConfigUtil.getProperty("path.resource.menuMessage");

		try {
			paramEntity.setObject("javaPath", rootPath + javaPath);
			paramEntity.setObject("jspPath", rootPath + jspPath);
			paramEntity.setObject("springPath", rootPath + springPath);
			paramEntity.setObject("strutsPath", rootPath + strutsPath);
			paramEntity.setObject("messagePath", rootPath + messagePath);

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeGenerate(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(requestDataSet.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String javaPath = requestDataSet.getValue("javaSourcePath");
		String jspPath = requestDataSet.getValue("jspSourcePath");
		String springPath = requestDataSet.getValue("springConfigPath");
		String strutsPath = requestDataSet.getValue("strutsConfigPath");
		String messagePath = requestDataSet.getValue("messageConfigPath");
		boolean isCreateSpring = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("createSpring"), "N"));
		boolean isCreateStruts = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("createStruts"), "N"));
		boolean isCreateMessage = CommonUtil.toBoolean(CommonUtil.nvl(requestDataSet.getValue("createMessage"), "N"));

		try {
			createFolder(javaPath+"/"+menuPathStr);
			createFolder(jspPath+"/"+menuPathStr);

			if (isCreateSpring) {
				createFolder(springPath);
			}

			if (isCreateStruts) {
				createFolder(strutsPath);
			}

			if (isCreateMessage) {
				createFolder(messagePath+"/"+menuPathStr);
			}

			createJavaAction(requestDataSet);
			createJavaBiz(requestDataSet);
			createJavaBizImpl(requestDataSet);

			createJspList(requestDataSet);
			createJspDetail(requestDataSet);
			createJspInsert(requestDataSet);
			createJspUpdate(requestDataSet);

			createConfSpring(requestDataSet);
			createConfStruts(requestDataSet);
			createMessageFile(requestDataSet);

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	private boolean createJavaAction(DataSet requestDataSet) throws Exception {
		String projectName = CommonUtil.lowerCase(ConfigUtil.getProperty("name.project"));
		String javaPath = requestDataSet.getValue("javaSourcePath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(requestDataSet.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = requestDataSet.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String actionHandlerType = requestDataSet.getValue("actionHandlerType");
		String actionNameAjaxResponse = ConfigUtil.getProperty("name.source.javaAction.ajaxResponse");
		String actionNamePageHandler = ConfigUtil.getProperty("name.source.javaAction.pageHandler");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String javaTargetpath = javaPath + "/" + rootMenuId + "/" + thisMenuId;
		String sourcePath, sourceString, packageString, tempString;
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(actionHandlerType, "P")) {
				sourcePath = srcPath+"/"+actionNamePageHandler;
			} else {
				sourcePath = srcPath+"/"+actionNameAjaxResponse;
			}

			targetFile = new File(javaTargetpath+"/"+thisMenuIdUpperCamelCase+"Action.java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString + "\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(javaTargetpath, rootPath + "src/"), "/", ".");

			String menuUrl = rootMenuId + "/" + CommonUtil.remove(thisMenuId, rootMenuId);

			sourceString = CommonUtil.replace(sourceString, "#PROJECT_NAME#", projectName);
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE_NAME#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
			sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);
			sourceString = CommonUtil.replace(sourceString, "#MENU_PATH#", menuPathStr);
			sourceString = CommonUtil.replace(sourceString, "#MENU_URL#", menuUrl);

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createJavaBiz(DataSet requestDataSet) throws Exception {
		String projectName = CommonUtil.lowerCase(ConfigUtil.getProperty("name.project"));
		String javaPath = requestDataSet.getValue("javaSourcePath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(requestDataSet.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = requestDataSet.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String srcBizFileName = ConfigUtil.getProperty("name.source.javaBiz");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String javaTargetpath = javaPath + "/" + rootMenuId + "/" + thisMenuId;
		String sourcePath, tempString, sourceString, packageString;
		File targetFile;

		try {
			sourcePath = srcPath+"/"+srcBizFileName;

			targetFile = new File(javaTargetpath+"/"+thisMenuIdUpperCamelCase+"Biz.java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString + "\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(javaTargetpath, rootPath + "src/"), "/", ".");

			sourceString = CommonUtil.replace(sourceString, "#PROJECT_NAME#", projectName);
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE_NAME#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
			sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createJavaBizImpl(DataSet requestDataSet) throws Exception {
		String projectName = CommonUtil.lowerCase(ConfigUtil.getProperty("name.project"));
		String javaPath = requestDataSet.getValue("javaSourcePath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(requestDataSet.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = requestDataSet.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String srcBizImplFileName = ConfigUtil.getProperty("name.source.javaBizImpl");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String thisMenuIdLowerCamelCase = CommonUtil.toCamelCaseStartLowerCase(thisMenuId);
		String javaTargetpath = javaPath + "/" + rootMenuId + "/" + thisMenuId;
		String sourcePath, tempString, sourceString, packageString;
		File targetFile;

		try {
			sourcePath = srcPath+"/"+srcBizImplFileName;

			targetFile = new File(javaTargetpath+"/"+thisMenuIdUpperCamelCase+"BizImpl.java");
			createEmptyFile(targetFile);

			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourcePath));
			StringBuffer stringBuffer = new StringBuffer();
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuffer.append(tempString + "\n");
			}
			OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
			sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

			packageString = CommonUtil.replace(CommonUtil.remove(javaTargetpath, rootPath + "src/"), "/", ".");

			sourceString = CommonUtil.replace(sourceString, "#PROJECT_NAME#", projectName);
			sourceString = CommonUtil.replace(sourceString, "#PACKAGE_NAME#", packageString);
			sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
			sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_LOWER#", thisMenuIdLowerCamelCase);
			sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);

			osWriter.write(sourceString);
			osWriter.flush();
			osWriter.close();
			bufferedReader.close();

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createJspList(DataSet requestDataSet) throws Exception {
		String isCreate = CommonUtil.nvl(requestDataSet.getValue("jspCreateList"));
		String pageType = requestDataSet.getValue("jspSubPageType");
		String jspPath = requestDataSet.getValue("jspSourcePath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(requestDataSet.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = requestDataSet.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String jspTargetpath = jspPath + "/" + rootMenuId + "/" + thisMenuId;
		String srcJspFileName, sourceString, tempString;
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(isCreate, "Y")) {
				if (CommonUtil.equalsIgnoreCase(pageType, "Page")) {
					srcJspFileName = ConfigUtil.getProperty("name.source.jspListForPage");
				} else {
					srcJspFileName = ConfigUtil.getProperty("name.source.jspListForPop");
				}

				targetFile = new File(jspTargetpath+"/"+thisMenuIdUpperCamelCase+"List.jsp");
				createEmptyFile(targetFile);

				BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath + "/" + srcJspFileName));
				StringBuffer stringBuffer = new StringBuffer();
				while ((tempString = bufferedReader.readLine()) != null) {
					stringBuffer.append(tempString + "\n");
				}
				OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
				sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

				String menuUrl = rootMenuId + "/" + CommonUtil.remove(thisMenuId, rootMenuId);

				sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
				sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);
				sourceString = CommonUtil.replace(sourceString, "#MENU_URL#", menuUrl);
				sourceString = CommonUtil.replace(sourceString, "#THIS_MENU_ID#", thisMenuId);

				osWriter.write(sourceString);
				osWriter.flush();
				osWriter.close();
				bufferedReader.close();
			}

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createJspDetail(DataSet requestDataSet) throws Exception {
		String isCreate = CommonUtil.nvl(requestDataSet.getValue("jspCreateDetail"));
		String pageType = requestDataSet.getValue("jspSubPageType");
		String jspPath = requestDataSet.getValue("jspSourcePath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(requestDataSet.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = requestDataSet.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String jspTargetpath = jspPath + "/" + rootMenuId + "/" + thisMenuId;
		String srcJspFileName, targetFileSuffix, sourceString, menuUrl;
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(isCreate, "Y")) {
				if (CommonUtil.equalsIgnoreCase(pageType, "Page")) {
					targetFileSuffix = "";
					srcJspFileName = ConfigUtil.getProperty("name.source.jspDetailPage");
				} else {
					targetFileSuffix = "Pop";
					srcJspFileName = ConfigUtil.getProperty("name.source.jspDetailPop");
				}

				targetFile = new File(jspTargetpath + "/" + thisMenuIdUpperCamelCase + "Detail" + targetFileSuffix + ".jsp");
				createEmptyFile(targetFile);

				BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath + "/" + srcJspFileName));
				StringBuffer stringBuffer = new StringBuffer();
				String tempString;
				while ((tempString = bufferedReader.readLine()) != null) {
					stringBuffer.append(tempString + "\n");
				}
				OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
				sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

				menuUrl = rootMenuId + "/" + CommonUtil.remove(thisMenuId, rootMenuId);

				sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
				sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);
				sourceString = CommonUtil.replace(sourceString, "#MENU_URL#", menuUrl);
				sourceString = CommonUtil.replace(sourceString, "#THIS_MENU_ID#", thisMenuId);

				osWriter.write(sourceString);
				osWriter.flush();
				osWriter.close();
				bufferedReader.close();
			}

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createJspInsert(DataSet requestDataSet) throws Exception {
		String isCreate = CommonUtil.nvl(requestDataSet.getValue("jspCreateInsert"));
		String pageType = requestDataSet.getValue("jspSubPageType");
		String jspPath = requestDataSet.getValue("jspSourcePath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(requestDataSet.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = requestDataSet.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String jspTargetpath = jspPath + "/" + rootMenuId + "/" + thisMenuId;
		String srcJspFileName, targetFileSuffix, sourceString, menuUrl;
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(isCreate, "Y")) {
				if (CommonUtil.equalsIgnoreCase(pageType, "Page")) {
					targetFileSuffix = "";
					srcJspFileName = ConfigUtil.getProperty("name.source.jspInsertPage");
				} else {
					targetFileSuffix = "Pop";
					srcJspFileName = ConfigUtil.getProperty("name.source.jspInsertPop");
				}

				targetFile = new File(jspTargetpath + "/" + thisMenuIdUpperCamelCase + "Insert" + targetFileSuffix + ".jsp");
				createEmptyFile(targetFile);

				BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath + "/" + srcJspFileName));
				StringBuffer stringBuffer = new StringBuffer();
				String tempString;
				while ((tempString = bufferedReader.readLine()) != null) {
					stringBuffer.append(tempString + "\n");
				}
				OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
				sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

				menuUrl = rootMenuId + "/" + CommonUtil.remove(thisMenuId, rootMenuId);

				sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
				sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);
				sourceString = CommonUtil.replace(sourceString, "#MENU_URL#", menuUrl);
				sourceString = CommonUtil.replace(sourceString, "#THIS_MENU_ID#", thisMenuId);

				osWriter.write(sourceString);
				osWriter.flush();
				osWriter.close();
				bufferedReader.close();
			}

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createJspUpdate(DataSet requestDataSet) throws Exception {
		String isCreate = CommonUtil.nvl(requestDataSet.getValue("jspCreateUpdate"));
		String pageType = requestDataSet.getValue("jspSubPageType");
		String jspPath = requestDataSet.getValue("jspSourcePath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(requestDataSet.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = requestDataSet.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String jspTargetpath = jspPath + "/" + rootMenuId + "/" + thisMenuId;
		String srcJspFileName, targetFileSuffix, sourceString, menuUrl;
		File targetFile;

		try {
			if (CommonUtil.equalsIgnoreCase(isCreate, "Y")) {
				if (CommonUtil.equalsIgnoreCase(pageType, "Page")) {
					targetFileSuffix = "";
					srcJspFileName = ConfigUtil.getProperty("name.source.jspUpdatePage");
				} else {
					targetFileSuffix = "Pop";
					srcJspFileName = ConfigUtil.getProperty("name.source.jspUpdatePop");
				}
				targetFile = new File(jspTargetpath + "/" + thisMenuIdUpperCamelCase + "Update" + targetFileSuffix + ".jsp");
				createEmptyFile(targetFile);

				BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath + "/" + srcJspFileName));
				StringBuffer stringBuffer = new StringBuffer();
				String tempString;
				while ((tempString = bufferedReader.readLine()) != null) {
					stringBuffer.append(tempString + "\n");
				}
				OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
				sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

				menuUrl = rootMenuId + "/" + CommonUtil.remove(thisMenuId, rootMenuId);

				sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
				sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);
				sourceString = CommonUtil.replace(sourceString, "#MENU_URL#", menuUrl);
				sourceString = CommonUtil.replace(sourceString, "#THIS_MENU_ID#", thisMenuId);

				osWriter.write(sourceString);
				osWriter.flush();
				osWriter.close();
				bufferedReader.close();
			}

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createConfSpring(DataSet dsRequest) throws Exception {
		String projectName = CommonUtil.lowerCase(ConfigUtil.getProperty("name.project"));
		String packageName = CommonUtil.lowerCase(ConfigUtil.getProperty("name.package.project"));
		String isCreate = CommonUtil.nvl(dsRequest.getValue("createSpring"));
		String javaPath = dsRequest.getValue("javaSourcePath");
		String targetPath = dsRequest.getValue("springConfigPath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(dsRequest.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = dsRequest.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String srcFileName = ConfigUtil.getProperty("name.source.xmlMenuSpringConf");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String thisMenuIdLowerCamelCase = CommonUtil.toCamelCaseStartLowerCase(thisMenuId);
		String javaTargetpath = javaPath + "/" + rootMenuId + "/" + thisMenuId;
		String sourceString, packageString;
		File targetFile;

		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		XPath xpath = XPathFactory.newInstance().newXPath();

		try {
			if (CommonUtil.equalsIgnoreCase(isCreate, "Y")) {
				targetFile = new File(targetPath + "/" + "spring-"+projectName+"-app-"+rootMenuId+".xml");

				if (!targetFile.exists()) {
					targetFile.createNewFile();

					BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath + "/" + srcFileName));
					StringBuffer stringBuffer = new StringBuffer();
					String tempString;
					while ((tempString = bufferedReader.readLine()) != null) {
						stringBuffer.append(tempString + "\n");
					}
					OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
					sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

					osWriter.write(sourceString);
					osWriter.flush();
					osWriter.close();
					bufferedReader.close();
				}

				Document document = docBuilder.parse(targetFile);
				Element rootElement = document.getDocumentElement();
				DOMSource domSource = new DOMSource(document);
				packageString = CommonUtil.replace(CommonUtil.replace(CommonUtil.remove(javaTargetpath, rootPath + "src/"), "/", "."), packageName, "${name.package.project}");

				Element beanElement = document.createElement("bean");
				beanElement.setAttribute("id", thisMenuIdLowerCamelCase + "Action");
				beanElement.setAttribute("name", thisMenuIdLowerCamelCase + "Action");
				beanElement.setAttribute("class", packageString + "." + thisMenuIdUpperCamelCase + "Action");
				rootElement.appendChild(beanElement);

				Comment commentElement = document.createComment(thisMenuIdUpperCamelCase + " - " + menuName);
				beanElement.getParentNode().insertBefore(commentElement, beanElement);

				beanElement = document.createElement("bean");
				beanElement.setAttribute("id", thisMenuIdLowerCamelCase + "Biz");
				beanElement.setAttribute("name", thisMenuIdLowerCamelCase + "Biz");
				beanElement.setAttribute("class", packageString + "." + thisMenuIdUpperCamelCase + "BizImpl");
				beanElement.setAttribute("parent", "baseBiz");
				rootElement.appendChild(beanElement);

				NodeList nodeList = (NodeList)xpath.evaluate("//text()[normalize-space()='']", document, XPathConstants.NODESET);
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					node.getParentNode().removeChild(node);
				}
				transformer.setOutputProperty("encoding", "UTF-8");
				transformer.setOutputProperty("omit-xml-declaration", "false");
				transformer.setOutputProperty("indent", "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");

				StreamResult streamResult = new StreamResult(targetFile.getPath());
				transformer.transform(domSource, streamResult);
			}

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createConfStruts(DataSet dsRequest) throws Exception {
		String projectName = CommonUtil.lowerCase(ConfigUtil.getProperty("name.project"));
		String isCreate = CommonUtil.nvl(dsRequest.getValue("createStruts"));
		String pageType = dsRequest.getValue("jspSubPageType");
		String javaPath = dsRequest.getValue("javaSourcePath");
		String jspPath = dsRequest.getValue("jspSourcePath");
		String targetPath = dsRequest.getValue("strutsConfigPath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(dsRequest.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String menuId[] = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = dsRequest.getValue("menuName");
		String menuNumber = CommonUtil.remove(thisMenuId, rootMenuId);
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String srcFileName = ConfigUtil.getProperty("name.source.xmlMenuStrutsConf");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		String jspRelPath = CommonUtil.substringAfter(jspPath, "WebRoot");
		String javaTargetpath = javaPath + "/" + rootMenuId + "/" + thisMenuId;
		String sourceString, pageNameSuffix = "";

		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		XPath xpath = XPathFactory.newInstance().newXPath();

		try {
			if (CommonUtil.equalsIgnoreCase(isCreate, "Y")) {
				File regFile = new File(targetPath + "/" + "struts.xml");
				File targetFile = new File(targetPath + "/" + "struts-"+projectName+"-app-"+rootMenuId+".xml");

				if (!targetFile.exists()) {
					targetFile.createNewFile();

					BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath + "/" + srcFileName));
					StringBuffer stringBuffer = new StringBuffer();
					String tempString;
					while ((tempString = bufferedReader.readLine()) != null) {
						stringBuffer.append(tempString + "\n");
					}
					OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
					sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

					sourceString = CommonUtil.replace(sourceString, "#ROOT_MENU_ID#", rootMenuId);

					osWriter.write(sourceString);
					osWriter.flush();
					osWriter.close();
					bufferedReader.close();

					Document document = docBuilder.parse(regFile);
					Element rootElement = document.getDocumentElement();
					DOMSource domSource = new DOMSource(document);

					Element includeElement = document.createElement("include");
					includeElement.setAttribute("file", "main/"+projectName+"/conf/struts/struts-"+projectName+"-app-"+rootMenuId+".xml");
					rootElement.appendChild(includeElement);

					DOMImplementation domImpl = document.getImplementation();
					DocumentType docType = domImpl.createDocumentType("doctype", "-//Apache Software Foundation//DTD Struts Configuration 2.5//EN", "http://struts.apache.org/dtds/struts-2.5.dtd");

					NodeList nodeListFormat = (NodeList)xpath.evaluate("//text()[normalize-space()='']", document, XPathConstants.NODESET);
					for (int i = 0; i < nodeListFormat.getLength(); i++) {
						Node node = nodeListFormat.item(i);
						node.getParentNode().removeChild(node);
					}
					transformer.setOutputProperty("encoding", "UTF-8");
					transformer.setOutputProperty("omit-xml-declaration", "false");
					transformer.setOutputProperty("indent", "yes");
					transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");
					transformer.setOutputProperty("doctype-public", docType.getPublicId());
					transformer.setOutputProperty("doctype-system", docType.getSystemId());

					StreamResult streamResult = new StreamResult(regFile.getPath());
					transformer.transform(domSource, streamResult);
				}

				Document document = docBuilder.parse(targetFile);
				Element rootElement = document.getDocumentElement();
				DOMSource domSource = new DOMSource(document);

				NodeList nodeList = rootElement.getChildNodes();
				for (int i=0; i<nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == 1) {
						Element packageElement = (Element)node;
						String packageString = CommonUtil.replace(CommonUtil.remove(javaTargetpath, rootPath + "src/"), "/", ".");

						Element actionElement = document.createElement("action");
						actionElement.setAttribute("name", menuNumber + "/*");
						actionElement.setAttribute("method", "{1}");
						actionElement.setAttribute("class", packageString + "." + thisMenuIdUpperCamelCase + "Action");

						Element resultElement = document.createElement("result");
						resultElement.setAttribute("name", "list");
						resultElement.setAttribute("type", "debugDispatcherResult");
						resultElement.setTextContent(jspRelPath + "/" + menuPathStr + "/" + thisMenuIdUpperCamelCase + "List.jsp");
						actionElement.appendChild(resultElement);

						pageNameSuffix = (CommonUtil.equalsIgnoreCase(pageType, "Page")) ? ".jsp" : "Pop.jsp";
						resultElement = document.createElement("result");
						resultElement.setAttribute("name", "detail");
						resultElement.setAttribute("type", "debugDispatcherResult");
						resultElement.setTextContent(jspRelPath + "/" + menuPathStr + "/" + thisMenuIdUpperCamelCase + "Detail"+pageNameSuffix);
						actionElement.appendChild(resultElement);

						resultElement = document.createElement("result");
						resultElement.setAttribute("name", "insert");
						resultElement.setAttribute("type", "debugDispatcherResult");
						resultElement.setTextContent(jspRelPath + "/" + menuPathStr + "/" + thisMenuIdUpperCamelCase + "Insert"+pageNameSuffix);
						actionElement.appendChild(resultElement);

						resultElement = document.createElement("result");
						resultElement.setAttribute("name", "update");
						resultElement.setAttribute("type", "debugDispatcherResult");
						resultElement.setTextContent(jspRelPath + "/" + menuPathStr + "/" + thisMenuIdUpperCamelCase + "Update"+pageNameSuffix);
						actionElement.appendChild(resultElement);
						packageElement.appendChild(actionElement);

						Comment commentElement = document.createComment(thisMenuIdUpperCamelCase + " - " + menuName);
						actionElement.getParentNode().insertBefore(commentElement, actionElement);

						DOMImplementation domImpl = document.getImplementation();
						DocumentType docType = domImpl.createDocumentType("doctype", "-//Apache Software Foundation//DTD Struts Configuration 2.5//EN", "http://struts.apache.org/dtds/struts-2.5.dtd");

						NodeList nodeListFormat = (NodeList)xpath.evaluate("//text()[normalize-space()='']", document, XPathConstants.NODESET);
						for (int j = 0; j < nodeListFormat.getLength(); j++) {
							Node nodeFormat = nodeListFormat.item(j);
							nodeFormat.getParentNode().removeChild(nodeFormat);
						}
						transformer.setOutputProperty("encoding", "UTF-8");
						transformer.setOutputProperty("omit-xml-declaration", "false");
						transformer.setOutputProperty("indent", "yes");
						transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");
						transformer.setOutputProperty("doctype-public", docType.getPublicId());
						transformer.setOutputProperty("doctype-system", docType.getSystemId());

						StreamResult streamResult = new StreamResult(targetFile.getPath());
						transformer.transform(domSource, streamResult);
					}
				}
			}

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private boolean createMessageFile(DataSet dsRequest) throws Exception {
		String isCreate = CommonUtil.nvl(dsRequest.getValue("createMessage"));
		String targetPath = dsRequest.getValue("messageConfigPath");
		String menuPathStr = CommonUtil.lowerCase(CommonUtil.replace(dsRequest.getValue("menuId"), ConfigUtil.getProperty("delimiter.data"), "/"));
		String[] menuId = CommonUtil.split(menuPathStr, "/");
		String rootMenuId = CommonUtil.lowerCase(menuId[0]);
		String thisMenuId = CommonUtil.lowerCase(menuId[1]);
		String menuName = dsRequest.getValue("menuName");
		String rootPath = CommonUtil.remove((String)MemoryBean.get("applicationRealPath"), "WebRoot");
		String srcPath = rootPath + ConfigUtil.getProperty("path.sourceFile");
		String srcFileName = ConfigUtil.getProperty("name.source.propMessage");
		String thisMenuIdUpperCamelCase = CommonUtil.toCamelCaseStartUpperCase(thisMenuId);
		DataSet dsLang = ZebraCommonCodeManager.getCodeDataSetByCodeType("LANGUAGE_TYPE");

		try {
			if (CommonUtil.equalsIgnoreCase(isCreate, "Y")) {
				targetPath = targetPath + "/" + rootMenuId + "/" + thisMenuId;
				String targetFileName = "app-" + rootMenuId + "-" + thisMenuId;
				File targetFile = new File(targetPath + "/" + targetFileName + ".properties");
				createEmptyFile(targetFile);

				BufferedReader bufferedReader = new BufferedReader(new FileReader(srcPath + "/" + srcFileName));
				StringBuffer stringBuffer = new StringBuffer();
				String tempString;
				while ((tempString = bufferedReader.readLine()) != null) {
					stringBuffer.append(tempString + "\n");
				}
				OutputStreamWriter osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
				String sourceString = CommonUtil.removeEnd(stringBuffer.toString(), "\n");

				sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
				sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);
				sourceString = CommonUtil.replace(sourceString, "#THIS_MENU_ID#", thisMenuId);

				osWriter.write(sourceString);
				osWriter.flush();

				for (int i=0; i<dsLang.getRowCnt(); i++) {
					String lang = dsLang.getValue(i, "COMMON_CODE");
					String srcNameTemp = CommonUtil.substringBefore(srcFileName, ".");
					String srcNameExt = CommonUtil.substringAfter(srcFileName, ".");
					String srcFileNameByLang = srcNameTemp + "_" + lang + "." + srcNameExt;

					targetFileName = "app-" + rootMenuId + "-" + thisMenuId + "_" + lang;
					targetFile = new File(targetPath + "/" + targetFileName + ".properties");
					createEmptyFile(targetFile);

					bufferedReader = new BufferedReader(new FileReader(srcPath + "/" + srcFileNameByLang));
					stringBuffer = new StringBuffer();
					while ((tempString = bufferedReader.readLine()) != null) {
						stringBuffer.append(tempString + "\n");
					}
					osWriter = new OutputStreamWriter(new FileOutputStream(targetFile, true), "utf-8");
					sourceString = stringBuffer.toString();

					sourceString = CommonUtil.replace(sourceString, "#MENU_ID_START_UPPER#", thisMenuIdUpperCamelCase);
					sourceString = CommonUtil.replace(sourceString, "#MENU_NAME#", menuName);
					sourceString = CommonUtil.replace(sourceString, "#THIS_MENU_ID#", thisMenuId);

					osWriter.write(sourceString);
					osWriter.flush();
				}

				osWriter.close();
				bufferedReader.close();
			}

			return true;
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
	}

	private void createFolder(String folderPath) {
		File dir = new File(folderPath);
		if (!dir.isDirectory())
			dir.mkdirs();
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
}