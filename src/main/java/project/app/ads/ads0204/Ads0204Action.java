/**************************************************************************************************
 * project
 * Description - Ads0204 - Invoice Management
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.ads.ads0204;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class Ads0204Action extends BaseAction {
	@Autowired
	private Ads0204Biz biz;

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

	public String doExport() throws Exception {
		biz.doExport(paramEntity);
		setRequestAttribute("paramEntity", paramEntity);
		return "export";
	}
}