package project.app.sys.sys0202;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;
import project.common.module.commoncode.CommonCodeManager;

public class Sys0202Action extends BaseAction {
	@Autowired
	private Sys0202Biz biz;

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

	public String getDetail() throws Exception {
		biz.getDetail(paramEntity);
		return "detail";
	}

	public String getInsert() throws Exception {
		biz.getInsert(paramEntity);
		return "insert";
	}

	public String getUpdate() throws Exception {
		biz.getUpdate(paramEntity);
		return "update";
	}

	public String exeInsert() throws Exception {
		try {
			biz.exeInsert(paramEntity);
			CommonCodeManager.reload();
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeUpdate() throws Exception {
		try {
			biz.exeUpdate(paramEntity);
			CommonCodeManager.reload();
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeDelete() throws Exception {
		try {
			biz.exeDelete(paramEntity);
			CommonCodeManager.reload();
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