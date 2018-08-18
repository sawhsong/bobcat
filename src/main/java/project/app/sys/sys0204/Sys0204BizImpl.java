package project.app.sys.sys0204;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.common.module.datahelper.DataHelper;
import project.conf.resource.ormapper.dao.SysCountryCurrency.SysCountryCurrencyDao;
import project.conf.resource.ormapper.dto.oracle.SysCountryCurrency;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.ExportUtil;

public class Sys0204BizImpl extends BaseBiz implements Sys0204Biz {
	@Autowired
	private SysCountryCurrencyDao sysCountryCurrencyDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getList(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);
			queryAdvisor.setPagination(true);

			paramEntity.setAjaxResponseDataSet(sysCountryCurrencyDao.getCountryCurrencyDataSetBySearchCriteria(queryAdvisor));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String countryCurrencyId = requestDataSet.getValue("countryCurrencyId");
		SysCountryCurrency sysCountryCurrency;

		try {
			sysCountryCurrency = sysCountryCurrencyDao.getCountryCurrencyByCountryCurrencyId(countryCurrencyId);
			sysCountryCurrency.setInsertUserName(DataHelper.getUserNameById(sysCountryCurrency.getInsertUserId()));
			sysCountryCurrency.setUpdateUserName(DataHelper.getUserNameById(sysCountryCurrency.getUpdateUserId()));
			paramEntity.setObject("sysCountryCurrency", sysCountryCurrency);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getInsert(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getUpdate(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity = getDetail(paramEntity);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String countryCurrencyId = CommonUtil.uid();
		SysCountryCurrency sysCountryCurrency = new SysCountryCurrency();
		int result = -1;

		try {
			sysCountryCurrency.setCountryCurrencyId(countryCurrencyId);
			sysCountryCurrency.setCurrencyName(requestDataSet.getValue("currencyName"));
			sysCountryCurrency.setCurrencySymbol(requestDataSet.getValue("currencySymbol"));
			sysCountryCurrency.setCurrencyAlphabeticCode(CommonUtil.upperCase(requestDataSet.getValue("currencyAlphabeticCode")));
			sysCountryCurrency.setCurrencyNumericCode(requestDataSet.getValue("currencyNumericCode"));
			sysCountryCurrency.setCountryName(requestDataSet.getValue("countryName"));
			sysCountryCurrency.setCountryLanguageCode(requestDataSet.getValue("countryLanguageCode"));
			sysCountryCurrency.setCountryCode2(CommonUtil.upperCase(requestDataSet.getValue("countryCode2")));
			sysCountryCurrency.setCountryCode3(CommonUtil.upperCase(requestDataSet.getValue("countryCode3")));
			sysCountryCurrency.setCountryNumericCode(requestDataSet.getValue("countryNumericCode"));
			sysCountryCurrency.setInsertUserId((String)session.getAttribute("UserId"));
			sysCountryCurrency.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysCountryCurrencyDao.insert(sysCountryCurrency);
			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeUpdate(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String countryCurrencyId = requestDataSet.getValue("countryCurrencyId");
		SysCountryCurrency sysCountryCurrency = new SysCountryCurrency();
		int result = -1;

		try {
			sysCountryCurrency = sysCountryCurrencyDao.getCountryCurrencyByCountryCurrencyId(countryCurrencyId);

			sysCountryCurrency.setCurrencyName(requestDataSet.getValue("currencyName"));
			sysCountryCurrency.setCurrencySymbol(requestDataSet.getValue("currencySymbol"));
			sysCountryCurrency.setCurrencyAlphabeticCode(CommonUtil.upperCase(requestDataSet.getValue("currencyAlphabeticCode")));
			sysCountryCurrency.setCurrencyNumericCode(requestDataSet.getValue("currencyNumericCode"));
			sysCountryCurrency.setCountryName(requestDataSet.getValue("countryName"));
			sysCountryCurrency.setCountryLanguageCode(requestDataSet.getValue("countryLanguageCode"));
			sysCountryCurrency.setCountryCode2(CommonUtil.upperCase(requestDataSet.getValue("countryCode2")));
			sysCountryCurrency.setCountryCode3(CommonUtil.upperCase(requestDataSet.getValue("countryCode3")));
			sysCountryCurrency.setCountryNumericCode(requestDataSet.getValue("countryNumericCode"));
			sysCountryCurrency.setUpdateUserId((String)session.getAttribute("UserId"));
			sysCountryCurrency.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysCountryCurrencyDao.update(countryCurrencyId, sysCountryCurrency);
			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeDelete(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String countryCurrencyId = requestDataSet.getValue("countryCurrencyId");
		String chkForDel = requestDataSet.getValue("chkForDel");
		String countryCurrencyIds[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		int result = -1;

		try {
			if (CommonUtil.isBlank(countryCurrencyId)) {
				result = sysCountryCurrencyDao.delete(countryCurrencyIds);
			} else {
				result = sysCountryCurrencyDao.delete(countryCurrencyId);
			}

			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}

	public ParamEntity exeExport(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		ExportHelper exportHelper;
		String dataRange = requestDataSet.getValue("dataRange");

		try {
			String pageTitle = "Country Currency Code List";
			String fileName = "CountryCurrencyCodeList";
			String[] columnHeader = {"currency_name", "currency_alphabetic_code", "currency_symbol", "country_name", "country_code_3"};

			exportHelper = ExportUtil.getExportHelper(requestDataSet.getValue("fileType"));
			exportHelper.setPageTitle(pageTitle);
			exportHelper.setColumnHeader(columnHeader);
			exportHelper.setFileName(fileName);
			exportHelper.setPdfWidth(1000);

			queryAdvisor.setRequestDataSet(requestDataSet);
			if (CommonUtil.containsIgnoreCase(dataRange, "all"))
				queryAdvisor.setPagination(false);
			else {
				queryAdvisor.setPagination(true);
			}

			exportHelper.setSourceDataSet(sysCountryCurrencyDao.getCountryCurrencyDataSetBySearchCriteria(queryAdvisor));

			paramEntity.setSuccess(true);
			paramEntity.setFileToExport(exportHelper.createFile());
			paramEntity.setFileNameToExport(exportHelper.getFileName());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}
}