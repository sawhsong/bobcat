package project.common.module.entrytypesupporter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.common.module.datahelper.DataHelper;
import project.conf.resource.ormapper.dao.SysExpenseType.SysExpenseTypeDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class EntryTypeSupporterBizImpl extends BaseBiz implements EntryTypeSupporterBiz {
	@Autowired
	private SysExpenseTypeDao sysExpenseTypeDao;

	public ParamEntity getIncomeTypeForSelectbox(ParamEntity paramEntity) throws Exception {
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			paramEntity.setAjaxResponseDataSet(DataHelper.getIncomeTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getExpenseMainTypeForSelectbox(ParamEntity paramEntity) throws Exception {
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			paramEntity.setAjaxResponseDataSet(DataHelper.getExpenseMainTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getExpenseSubTypeForSelectbox(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String parentCode = requestDataSet.getValue("expenseMainType");
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			paramEntity.setAjaxResponseDataSet(DataHelper.getExpenseSubTypeDataSetByOrgCategoryParentTypeCode(orgCategory, parentCode));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getExpenseTypesForContextMenu(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(sysExpenseTypeDao.getExpenseTypeDataSetByCriteria(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getAssetTypeForSelectbox(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(DataHelper.getAssetTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getAssetTypeForContextMenu(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(DataHelper.getAssetTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getRepaymentTypeForSelectbox(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(DataHelper.getRepaymentTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getRepaymentTypeForContextMenu(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(DataHelper.getRepaymentTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getBorrowingTypeForSelectbox(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(DataHelper.getBorrowingTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getBorrowingTypeForContextMenu(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(DataHelper.getBorrowingTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getLendingTypeForSelectbox(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(DataHelper.getLendingTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getLendingTypeForContextMenu(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();
		String orgCategory = CommonUtil.nvl((String)session.getAttribute("OrgCategoryForAdminTool"), (String)session.getAttribute("OrgCategory"));

		try {
			requestDataSet.addColumn("orgCategory", orgCategory);
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(DataHelper.getLendingTypeDataSetByOrgCategory(orgCategory));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}