package project.common.module.entrytypesupporter;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;

public class EntryTypeSupporterAction extends BaseAction {
	@Autowired
	private EntryTypeSupporterBiz biz;

	public String getRconCategoryForContextMenu() throws Exception {
		try {
			biz.getRconCategoryForContextMenu(paramEntity);
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}
}