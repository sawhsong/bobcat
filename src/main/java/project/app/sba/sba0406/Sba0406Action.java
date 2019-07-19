/**************************************************************************************************
 * project
 * Description - Sba0406 - Expenditure Type
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.sba.sba0406;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class Sba0406Action extends BaseAction {
	@Autowired
	private Sba0406Biz biz;

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

	public String getInsert() throws Exception {
		biz.getInsert(paramEntity);
		return "insert";
	}

	public String getUpdate() throws Exception {
		biz.getUpdate(paramEntity);
		return "update";
	}

	public String getUpdateSortOrder() throws Exception {
		biz.getUpdateSortOrder(paramEntity);
		return "updateSort";
	}

	public String exeInsert() throws Exception {
		try {
			biz.exeInsert(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeUpdate() throws Exception {
		try {
			biz.exeUpdate(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeUpdateSortOrder() throws Exception {
		try {
			biz.exeUpdateSortOrder(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", this.paramEntity);
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