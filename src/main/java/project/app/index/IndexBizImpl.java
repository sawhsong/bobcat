package project.app.index;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysBoard.SysBoardDao;
import project.conf.resource.ormapper.dao.SysBoardFile.SysBoardFileDao;
import project.conf.resource.ormapper.dao.UsrBankAccnt.UsrBankAccntDao;
import project.conf.resource.ormapper.dao.UsrInvoice.UsrInvoiceDao;
import project.conf.resource.ormapper.dao.UsrQuotation.UsrQuotationDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;

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
		String userId = (String)session.getAttribute("UserId");

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
		String userId = (String)session.getAttribute("UserId");

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
		String userId = (String)session.getAttribute("UserId");

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
		String userId = (String)session.getAttribute("UserId");

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
		String userId = (String)session.getAttribute("UserId");

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
}