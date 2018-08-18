package zebra.example.app.sample.soapwebservice;

import org.springframework.beans.factory.annotation.Autowired;

import zebra.data.DataSet;
import zebra.example.common.extend.BaseAction;
import zebra.util.CommonUtil;

public class SoapWebServiceClientAction extends BaseAction {
	@Autowired
	private SoapWebServiceClientBiz biz;

	public String getDefault() throws Exception {
		biz.getDefault(paramEntity);
		return "list";
	}

	public String getList() throws Exception {
		biz.getList(paramEntity);
		return "list";
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

	public String getAttachedFile() throws Exception {
		try {
			biz.getAttachedFile(paramEntity);
			paramEntity.setAjaxResponseDataSet((DataSet)paramEntity.getObject("fileDataSet"));
		} catch (Exception ex) {
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String exeDownload() throws Exception {
		biz.exeDownload(paramEntity);
		// Remember - Struts chain action
		setRequestAttribute("paramEntity", paramEntity);
		return "soapServiceDownload";
	}

	public String exeInsert() throws Exception {
		try {
			biz.exeInsert(paramEntity);

			if (paramEntity.isSuccess()) {
				paramEntity.setObject("script", "parent.popupNotice.close(); parent.doSearch();");
			} else {
				paramEntity.setObject("script", "history.go(-1);");
			}
		} catch (Exception ex) {
			paramEntity.setObject("script", "history.go(-1);");
		} finally {
			paramEntity.setObject("messageCode", paramEntity.getMessageCode());
			paramEntity.setObject("message", paramEntity.getMessage());
		}

		return "pageHandler";
	}

	public String exeUpdate() throws Exception {
		try {
			biz.exeUpdate(paramEntity);

			if (paramEntity.isSuccess()) {
				paramEntity.setObject("script", "parent.popupNotice.close(); parent.doSearch();");
			} else {
				paramEntity.setObject("script", "history.go(-1);");
			}
		} catch (Exception ex) {
			paramEntity.setObject("script", "history.go(-1);");
		} finally {
			paramEntity.setObject("messageCode", paramEntity.getMessageCode());
			paramEntity.setObject("message", paramEntity.getMessage());
		}

		return "pageHandler";
	}

	public String exeDelete() throws Exception {
		try {
			biz.exeDelete(paramEntity);

			if (paramEntity.isSuccess()) {
				paramEntity.setObject("action", "/zebra/sample/soapwebservice/getList.do");
				if (CommonUtil.isNotEmpty(paramEntity.getRequestDataSet().getValue("articleId"))) {
					paramEntity.setObject("script", "parent.popupNotice.close(); parent.doSearch();");
				}
			} else {
				paramEntity.setObject("script", "history.go(-1);");
			}
		} catch (Exception ex) {
			paramEntity.setObject("script", "history.go(-1);");
		} finally {
			paramEntity.setObject("messageCode", paramEntity.getMessageCode());
			paramEntity.setObject("message", paramEntity.getMessage());
		}

		return "pageHandler";
	}

	public String exeExport() throws Exception {
		biz.exeExport(paramEntity);
		// Remember - Struts chain action
		setRequestAttribute("paramEntity", paramEntity);
		return "soapServiceExport";
	}
}