/**************************************************************************************************
 * Project
 * Description
 * - Project Main Index
 *************************************************************************************************/
package project.app.index;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class IndexAction extends BaseAction {
	@Autowired
	private IndexBiz biz;

	public String index() throws Exception {
		biz.index(paramEntity);
		return SUCCESS;
	}

	public String dashboard() throws Exception {
		biz.dashboard(paramEntity);
		return "dashboard";
	}

	public String getAnnouncementList() throws Exception {
		try {
			biz.getAnnouncementList(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getAttachedFile() throws Exception {
		try {
			biz.getAttachedFile(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getBankStatementAllocationStatus() throws Exception {
		try {
			biz.getBankStatementAllocationStatus(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getQuotationBadge() throws Exception {
		try {
			biz.getQuotationBadge(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getQuotationData() throws Exception {
		try {
			biz.getQuotationData(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getInvoiceBadge() throws Exception {
		try {
			biz.getInvoiceBadge(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getInvoiceData() throws Exception {
		try {
			biz.getInvoiceData(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}
}