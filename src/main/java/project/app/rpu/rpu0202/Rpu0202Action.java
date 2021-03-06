/**************************************************************************************************
 * project
 * Description - Rpu0202 - Profit And Loss Statement
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.rpu.rpu0202;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class Rpu0202Action extends BaseAction {
	@Autowired
	private Rpu0202Biz biz;

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

	public String doExport() throws Exception {
		biz.doExport(paramEntity);
		setRequestAttribute("paramEntity", paramEntity);
		return "export";
	}
}