/**************************************************************************************************
 * project
 * Description - Pro0202 - Payroll Service
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.pro.pro0202;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class Pro0202Action extends BaseAction {
	@Autowired
	private Pro0202Biz biz;

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

	public String doSave() throws Exception {
		try {
			biz.doSave(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}
}