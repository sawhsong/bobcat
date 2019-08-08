/**************************************************************************************************
 * project
 * Description - Rkm0404 - Asset Expense
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.rkm.rkm0404;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class Rkm0404Action extends BaseAction {
	@Autowired
	private Rkm0404Biz biz;

	public String getDefault() throws Exception {
		biz.getDefault(paramEntity);
		return "list";
	}

	public String getList() throws Exception {
		try {
			biz.getList(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getEdit() throws Exception {
		try {
			biz.getEdit(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getFile() throws Exception {
		try {
			biz.getFile(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String calculateDataEntry() throws Exception {
		try {
			biz.calculateDataEntry(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeSave() throws Exception {
		try {
			biz.exeSave(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeComplete() throws Exception {
		try {
			biz.exeComplete(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeDelete() throws Exception {
		try {
			biz.exeDelete(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeExport() throws Exception {
		biz.exeExport(paramEntity);
		setRequestAttribute("paramEntity", paramEntity);
		return "export";
	}
}