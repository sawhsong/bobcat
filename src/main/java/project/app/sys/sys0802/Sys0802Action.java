/**************************************************************************************************
 * project
 * Description - Sys0802 - Financial Period Type
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.sys.sys0802;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class Sys0802Action extends BaseAction {
	@Autowired
	private Sys0802Biz biz;

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
		biz.getEdit(paramEntity);
		return "edit";
	}

	public String getFinancialPeriod() throws Exception {
		try {
			biz.getFinancialPeriod(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String doAutoGenerate() throws Exception {
		try {
			biz.doAutoGenerate(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String doSave() throws Exception {
		try {
			biz.doSave(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String doDelete() throws Exception {
		try {
			biz.doDelete(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}
}