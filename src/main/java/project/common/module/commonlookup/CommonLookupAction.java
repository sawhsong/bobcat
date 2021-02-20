package project.common.module.commonlookup;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseAction;
import zebra.data.DataSet;
import zebra.util.CommonUtil;

public class CommonLookupAction extends BaseAction {
	@Autowired
	private CommonLookupBiz biz;

	public String getDefault() throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String lookupType = requestDataSet.getValue("lookupType");
		String returnString = "";

		try {
			biz.getDefault(paramEntity);

			if (CommonUtil.equalsIgnoreCase(lookupType, "organisationName")) {
				returnString = "organisation";
			} else if (CommonUtil.equalsIgnoreCase(lookupType, "quotation")) {
				returnString = "quotation";
			} else if (CommonUtil.equalsIgnoreCase(lookupType, "bankAccnt")) {
				returnString = "bankAccnt";
			}
		} catch (Exception ex) {
		}
		return returnString;
	}

	public String getCommonCodeForSelectbox() throws Exception {
		try {
			biz.getCommonCodeForSelectbox(paramEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getOrganisationLookup() throws Exception {
		try {
			biz.getOrganisationLookup(paramEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getQuotationLookup() throws Exception {
		try {
			biz.getQuotationLookup(paramEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}

	public String getBankAccntLookup() throws Exception {
		try {
			biz.getBankAccntLookup(paramEntity);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setRequestAttribute("paramEntity", paramEntity);
		return "ajaxResponse";
	}
}