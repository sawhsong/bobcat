package project.common.module.autocompletion;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysCountryCurrency.SysCountryCurrencyDao;
import project.conf.resource.ormapper.dao.SysOrg.SysOrgDao;
import project.conf.resource.ormapper.dao.SysUser.SysUserDao;
import project.conf.resource.ormapper.dao.UsrEmployee.UsrEmployeeDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class AutoCompletionBizImpl extends BaseBiz implements AutoCompletionBiz {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysCountryCurrencyDao sysCountryCurrencyDao;
	@Autowired
	private SysOrgDao sysOrgDao;
	@Autowired
	private UsrEmployeeDao usrEmployeeDao;

	public ParamEntity getUserId(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "lower(user_id) like lower('"+inputValue+"%')");
			queryAdvisor.addOrderByClause("user_name");
			paramEntity.setAjaxResponseDataSet(sysUserDao.getUserNameDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getLoginId(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");
		String orgCategory = requestDataSet.getValue("orgCategoryAdminTool");

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "lower(login_id) like lower('"+inputValue+"%')");
			queryAdvisor.addAutoFillCriteria(orgCategory, "org_id in (select org_id from sys_org where org_category = '"+CommonUtil.upperCase(orgCategory)+"')");
			queryAdvisor.addOrderByClause("login_id");
			paramEntity.setAjaxResponseDataSet(sysUserDao.getUserNameDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getUserName(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");
		String orgCategory = requestDataSet.getValue("orgCategoryAdminTool");

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "lower(user_name) like lower('"+inputValue+"%')");
			queryAdvisor.addAutoFillCriteria(orgCategory, "org_id in (select org_id from sys_org where org_category = '"+CommonUtil.upperCase(orgCategory)+"')");
			queryAdvisor.addOrderByClause("user_name");
			paramEntity.setAjaxResponseDataSet(sysUserDao.getUserNameDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getCurrencyCode(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);
			paramEntity.setAjaxResponseDataSet(sysCountryCurrencyDao.getCurrencyCodeDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getCountryName(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);
			paramEntity.setAjaxResponseDataSet(sysCountryCurrencyDao.getCountryNameDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getOrgName(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "(lower(legal_name) like lower('"+inputValue+"%') or lower(trading_name) like lower('"+inputValue+"%'))");
			queryAdvisor.addOrderByClause("legal_name");
			paramEntity.setAjaxResponseDataSet(sysOrgDao.getOrgNameDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getAbn(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "abn like '"+inputValue+"%'");
			queryAdvisor.addOrderByClause("abn");
			paramEntity.setAjaxResponseDataSet(sysOrgDao.getAbnDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getOrgId(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "(lower(legal_name) like lower('"+inputValue+"%') or lower(trading_name) like lower('"+inputValue+"%'))");
			queryAdvisor.addOrderByClause("legal_name");
			paramEntity.setAjaxResponseDataSet(sysOrgDao.getOrgIdDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getEmployeeSurname(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");
		HttpSession session = paramEntity.getSession();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));

		try {
			queryAdvisor.addAutoFillCriteria(orgId, "org_id = '"+orgId+"'");
			queryAdvisor.addAutoFillCriteria(inputValue, "lower(surname) like lower('%"+inputValue+"%')");
			queryAdvisor.addOrderByClause("surname");
			paramEntity.setAjaxResponseDataSet(usrEmployeeDao.getEmployeeSurnameDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getEmployeeGivenName(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");
		HttpSession session = paramEntity.getSession();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));

		try {
			queryAdvisor.addAutoFillCriteria(orgId, "org_id = '"+orgId+"'");
			queryAdvisor.addAutoFillCriteria(inputValue, "lower(given_name) like lower('%"+inputValue+"%')");
			queryAdvisor.addOrderByClause("given_name");
			paramEntity.setAjaxResponseDataSet(usrEmployeeDao.getEmployeeGivenNameDataSetForAutoCompletion(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}