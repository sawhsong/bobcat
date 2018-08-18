package zebra.example.common.module.autocompletion;

import org.springframework.beans.factory.annotation.Autowired;

import zebra.example.common.extend.BaseAction;

public class AutoCompletionAction extends BaseAction {
	@Autowired
	private AutoCompletionBiz biz;

	public String getDomainDictionaryName() throws Exception {
		try {
			biz.getDomainDictionaryName(paramEntity);
		} catch (Exception ex) {
			return "ajaxResponse";
		} finally {
			setRequestAttribute("paramEntity", paramEntity);
		}
		return "ajaxResponse";
	}
}