package project.common.module.entrytypesupporter;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class EntryTypeSupporterAction extends BaseAction {
	@Autowired
	private EntryTypeSupporterBiz biz;

	public String getIncomeTypeForSelectbox() throws Exception {
		try {
			biz.getIncomeTypeForSelectbox(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getExpenseMainTypeForSelectbox() throws Exception {
		try {
			biz.getExpenseMainTypeForSelectbox(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getExpenseSubTypeForSelectbox() throws Exception {
		try {
			biz.getExpenseSubTypeForSelectbox(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getExpenseTypesForContextMenu() throws Exception {
		try {
			biz.getExpenseTypesForContextMenu(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getAssetTypeForSelectbox() throws Exception {
		try {
			biz.getAssetTypeForSelectbox(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getAssetTypeForContextMenu() throws Exception {
		try {
			biz.getAssetTypeForContextMenu(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getRepaymentTypeForSelectbox() throws Exception {
		try {
			biz.getRepaymentTypeForSelectbox(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getRepaymentTypeForContextMenu() throws Exception {
		try {
			biz.getRepaymentTypeForContextMenu(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getBorrowingTypeForSelectbox() throws Exception {
		try {
			biz.getBorrowingTypeForSelectbox(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getBorrowingTypeForContextMenu() throws Exception {
		try {
			biz.getBorrowingTypeForContextMenu(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getLendingTypeForSelectbox() throws Exception {
		try {
			biz.getLendingTypeForSelectbox(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getLendingTypeForContextMenu() throws Exception {
		try {
			biz.getLendingTypeForContextMenu(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}
}