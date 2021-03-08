package project.app.index;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysBoard.SysBoardDao;
import project.conf.resource.ormapper.dao.SysBoardFile.SysBoardFileDao;
import project.conf.resource.ormapper.dao.SysFinancialPeriod.SysFinancialPeriodDao;
import project.conf.resource.ormapper.dao.UsrBankAccnt.UsrBankAccntDao;
import project.conf.resource.ormapper.dao.UsrBsTranAlloc.UsrBsTranAllocDao;
import project.conf.resource.ormapper.dao.UsrInvoice.UsrInvoiceDao;
import project.conf.resource.ormapper.dao.UsrQuotation.UsrQuotationDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class IndexBizImpl extends BaseBiz implements IndexBiz {
	@Autowired
	private SysBoardDao sysBoardDao;
	@Autowired
	private SysBoardFileDao sysBoardFileDao;
	@Autowired
	private UsrBankAccntDao usrBankAccntDao;
	@Autowired
	private UsrQuotationDao usrQuotationDao;
	@Autowired
	private UsrInvoiceDao usrInvoiceDao;
	@Autowired
	private UsrBsTranAllocDao usrBsTranAllocDao;
	@Autowired
	private SysFinancialPeriodDao sysFinancialPeriodDao;

	public ParamEntity index(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setObject("resultDataSet", new DataSet());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity dashboard(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setObject("resultDataSet", new DataSet());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getAnnouncementList(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();

		try {
			result = sysBoardDao.getAnnouncementDataSetForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getAttachedFile(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();

		try {
			paramEntity.setAjaxResponseDataSet(sysBoardFileDao.getBoardFileListDataSetByArticleId(requestDataSet.getValue("articleId")));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getBankStatementAllocationStatus(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));

		try {
			qa.setObject("userId", userId);

			result = usrBankAccntDao.getBankStatementAllocationStatusForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getQuotationBadge(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));

		try {
			qa.setObject("userId", userId);

			result = usrQuotationDao.getQuotationBadgeForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getQuotationData(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));

		try {
			qa.setObject("userId", userId);

			result = usrQuotationDao.getQuotationDataSetForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getInvoiceBadge(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));

		try {
			qa.setObject("userId", userId);

			result = usrInvoiceDao.getInvoiceBadgeForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getInvoiceData(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();
		HttpSession session = paramEntity.getSession();
		String userId = CommonUtil.nvl((String)session.getAttribute("UserIdForAdminTool"), (String)session.getAttribute("UserId"));

		try {
			qa.setObject("userId", userId);

			result = usrInvoiceDao.getInvoiceDataSetForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getMonthForChart(ParamEntity paramEntity) throws Exception {
		DataSet result = new DataSet();

		try {
			result = sysFinancialPeriodDao.getFinancialMonthsByPeriodYear(CommonUtil.getSysdate("yyyy"));
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getIncomeChartData(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();
		HttpSession session = paramEntity.getSession();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		DataSet fy;
		String yearFrom = "", yearTo = "";

		try {
			fy = sysFinancialPeriodDao.getStartEndDataSetByPeriodYear(CommonUtil.getSysdate("yyyy"));
			yearFrom = CommonUtil.split(fy.getValue("FINANCIAL_YEAR"), "-")[0];
			yearTo = CommonUtil.split(fy.getValue("FINANCIAL_YEAR"), "-")[1];

			qa.setObject("orgId", orgId);
			qa.setObject("yearFrom", yearFrom);
			qa.setObject("yearTo", yearTo);

			result = usrBsTranAllocDao.getIncomeChartDataSetForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getExpenseChartData(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();
		HttpSession session = paramEntity.getSession();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		DataSet fy;
		String yearFrom = "", yearTo = "";

		try {
			fy = sysFinancialPeriodDao.getStartEndDataSetByPeriodYear(CommonUtil.getSysdate("yyyy"));
			yearFrom = CommonUtil.split(fy.getValue("FINANCIAL_YEAR"), "-")[0];
			yearTo = CommonUtil.split(fy.getValue("FINANCIAL_YEAR"), "-")[1];

			qa.setObject("orgId", orgId);
			qa.setObject("yearFrom", yearFrom);
			qa.setObject("yearTo", yearTo);

			result = usrBsTranAllocDao.getExpenseChartDataSetForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getOtherChartData(ParamEntity paramEntity) throws Exception {
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		DataSet result = new DataSet();
		HttpSession session = paramEntity.getSession();
		String orgId = CommonUtil.nvl((String)session.getAttribute("OrgIdForAdminTool"), (String)session.getAttribute("OrgId"));
		DataSet fy;
		String yearFrom = "", yearTo = "";

		try {
			fy = sysFinancialPeriodDao.getStartEndDataSetByPeriodYear(CommonUtil.getSysdate("yyyy"));
			yearFrom = CommonUtil.split(fy.getValue("FINANCIAL_YEAR"), "-")[0];
			yearTo = CommonUtil.split(fy.getValue("FINANCIAL_YEAR"), "-")[1];

			qa.setObject("orgId", orgId);
			qa.setObject("yearFrom", yearFrom);
			qa.setObject("yearTo", yearTo);

			result = usrBsTranAllocDao.getOtherChartDataSetForDashboard(qa);
			paramEntity.setAjaxResponseDataSet(result);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}