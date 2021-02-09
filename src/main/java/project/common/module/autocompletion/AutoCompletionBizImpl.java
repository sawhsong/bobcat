package project.common.module.autocompletion;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysOrg.SysOrgDao;
import project.conf.resource.ormapper.dao.SysUser.SysUserDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class AutoCompletionBizImpl extends BaseBiz implements AutoCompletionBiz {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysOrgDao sysOrgDao;

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

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "lower(login_id) like lower('"+inputValue+"%')");
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

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "lower(user_name) like lower('"+inputValue+"%')");
			queryAdvisor.addOrderByClause("user_name");
			paramEntity.setAjaxResponseDataSet(sysUserDao.getUserNameDataSetForAutoCompletion(queryAdvisor));
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

	public ParamEntity getOrgByIdOrName(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");

		try {
			if (CommonUtil.isNumeric(inputValue)) {
				queryAdvisor.addAutoFillCriteria(inputValue, "org_id like '%"+inputValue+"%'");
			} else {
				queryAdvisor.addAutoFillCriteria(inputValue, "(lower(legal_name) like lower('%"+inputValue+"%') or lower(trading_name) like lower('%"+inputValue+"%'))");
			}
			queryAdvisor.addOrderByClause("legal_name asc");

			paramEntity.setAjaxResponseDataSet(sysOrgDao.getOrgInfoDataSetForAutoCompletion(queryAdvisor));
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
}