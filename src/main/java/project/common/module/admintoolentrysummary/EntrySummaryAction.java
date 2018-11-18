package project.common.module.admintoolentrysummary;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class EntrySummaryAction extends BaseAction {
	@Autowired
	private EntrySummaryBiz biz;

	public String getIncomeSummary() throws Exception {
		try {
			biz.getIncomeSummary(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getExpenseSummary() throws Exception {
		try {
			biz.getExpenseSummary(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getAssetSummary() throws Exception {
		try {
			biz.getAssetSummary(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getRepaymentSummary() throws Exception {
		try {
			biz.getRepaymentSummary(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getBorrowingSummary() throws Exception {
		try {
			biz.getBorrowingSummary(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getLendingSummary() throws Exception {
		try {
			biz.getLendingSummary(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getEmployeeSummary() throws Exception {
		try {
			biz.getEmployeeSummary(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}
}