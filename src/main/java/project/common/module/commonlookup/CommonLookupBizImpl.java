package project.common.module.commonlookup;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.SysOrg.SysOrgDao;
import project.conf.resource.ormapper.dao.UsrBankAccnt.UsrBankAccntDao;
import project.conf.resource.ormapper.dao.UsrQuotation.UsrQuotationDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class CommonLookupBizImpl extends BaseBiz implements CommonLookupBiz {
	@Autowired
	private SysOrgDao sysOrgDao;
	@Autowired
	private UsrQuotationDao usrQuotationDao;
	@Autowired
	private UsrBankAccntDao usrBankAccntDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getCommonCodeForSelectbox(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String codeType = requestDataSet.getValue("codeType");
		DataSet ds;

		try {
			ds = CommonCodeManager.getCodeDataSetByCodeType(codeType);
			paramEntity.setAjaxResponseDataSet(ds);
			paramEntity.setTotalResultRows(ds.getRowCnt());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getOrganisationLookup(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();

		try {
			queryAdvisor.setObject("langCode", (String)session.getAttribute("langCode"));
			queryAdvisor.setRequestDataSet(requestDataSet);
			queryAdvisor.setPagination(true);

			paramEntity.setAjaxResponseDataSet(sysOrgDao.getOrgDataSetByCriteria(queryAdvisor));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getQuotationLookup(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));
		String fromDate = requestDataSet.getValue("fromDate");
		String toDate = requestDataSet.getValue("toDate");
		String customerName = requestDataSet.getValue("customerName");

		try {
			queryAdvisor.setObject("userId", userId);
			queryAdvisor.setObject("fromDate", fromDate);
			queryAdvisor.setObject("toDate", toDate);
			queryAdvisor.setObject("customerName", customerName);
			queryAdvisor.setPagination(true);

			paramEntity.setAjaxResponseDataSet(usrQuotationDao.getDataSetBySearchCriteria(queryAdvisor));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getBankAccntLookup(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));

		try {
			queryAdvisor.setObject("userId", userId);
			queryAdvisor.setObject("bankCode", requestDataSet.getValue("bankCode"));
			queryAdvisor.setPagination(true);

			paramEntity.setAjaxResponseDataSet(usrBankAccntDao.getDataSetBySearchCriteria(queryAdvisor));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}