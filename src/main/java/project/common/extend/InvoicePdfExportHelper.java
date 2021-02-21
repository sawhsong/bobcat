package project.common.extend;

import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zefer.pd4ml.PD4ML;

import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dto.oracle.UsrInvoice;
import zebra.data.DataSet;
import zebra.export.ExcelExportHelper;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.HtmlUtil;

public class InvoicePdfExportHelper extends ExportHelper {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(ExcelExportHelper.class);
	private UsrInvoice usrInvoice;
	private DataSet usrInvoiceDDataSet;

	public UsrInvoice getUsrInvoice() {
		return usrInvoice;
	}

	public void setUsrInvoice(UsrInvoice usrInvoice) {
		this.usrInvoice = usrInvoice;
	}

	public DataSet getUsrInvoiceDDataSet() {
		return usrInvoiceDDataSet;
	}

	public void setUsrInvoiceDDataSet(DataSet usrInvoiceDDataSet) {
		this.usrInvoiceDDataSet = usrInvoiceDDataSet;
	}

	@Override
	public File createFile() throws Exception {
		File file = null, dir = null;
		String contentString;
		PD4ML pd4ml = new PD4ML();
		OutputStreamWriter osWriter;

		if (CommonUtil.isBlank(fileName)) {
			setFileNameGenerated(FILE_NAME_PREFIX+"."+fileExtention);
		} else {
			setFileName(fileName+"."+fileExtention);
			setFileNameGenerated(FILE_NAME_PREFIX+"_"+fileName+"."+fileExtention);
		}

		dir = new File(TARGET_FILE_PATH);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		file = new File(getFileNameGenerated());
		osWriter = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");

		contentString = getExportDetails(getSourceString());

		osWriter.write(contentString);
		osWriter.flush();
		osWriter.close();

		// test html file
//		File htmlFile;
//		FileOutputStream htmlOs;
//		htmlFile = new File(TARGET_FILE_PATH + "/" + FILE_NAME_PREFIX+"_"+fileName+".html");
//		htmlOs = new FileOutputStream(htmlFile);
//		htmlOs.write(contentString.getBytes());
//		htmlOs.close();

		pd4ml.setPageInsets(new Insets(10, 10, 10, 10));
		pd4ml.useTTF(PDF_FONT_PATH, true);
		pd4ml.render(new StringReader(contentString), new FileOutputStream(getFileNameGenerated()));

		return file;
	}

	private String getSourceString() throws Exception {
		BufferedReader bufferedReader;
		StringBuffer stringBuffer;
		String tempString, returnString;

		bufferedReader = new BufferedReader(new FileReader(getSourceFile()));
		stringBuffer = new StringBuffer();
		while ((tempString = bufferedReader.readLine()) != null) {
			stringBuffer.append(tempString + "\n");
		}

		returnString = stringBuffer.toString();
		bufferedReader.close();

		return returnString;
	}

	protected File getSourceFile() {
		String sourceName = "";

		if (CommonUtil.containsIgnoreCase(fileType, "excel")) {
			sourceName = "InvoicePdf.src";
		} else if (CommonUtil.containsIgnoreCase(fileType, "pdf")) {
			sourceName = "InvoicePdf.src";
		} else if (CommonUtil.containsIgnoreCase(fileType, "html")) {
			sourceName = "InvoicePdf.src";
		}

		return new File(SOURCE_FILE_PATH+"/"+sourceName);
	}

	private String getExportDetails(String src) throws Exception {
		String dateFormat = "dd/MM/yyyy", numberFormat = "#,##0.00";
		String webAddress = ConfigUtil.getProperty("webAddress");
		String logoPath = usrInvoice.getProviderLogoPath();
		String providerLogo = "", providerAcn = "";

		if (CommonUtil.isNotBlank(logoPath)) {
			providerLogo = "<img src=\""+webAddress+logoPath+"\" style=\"width:250px;height:80px;\"/>";
		} else {
			providerLogo = "&nbsp;";
		}

		if (CommonUtil.isNotBlank(usrInvoice.getProviderAcn())) {
			providerAcn = "<tr><td class=\"tdDefault Rt\"><font style=\"font-weight:bold\">ACN : </font>"+CommonUtil.getFormatString(usrInvoice.getProviderAcn(), "??? ??? ???")+"</td></tr>";
		}


		src = CommonUtil.replace(src, "#PROVIDER_LOGO#", providerLogo);
		src = CommonUtil.replace(src, "#PROVIDER_NAME#", usrInvoice.getProviderName());
		src = CommonUtil.replace(src, "#ISSUE_DATE#", CommonUtil.toString(usrInvoice.getIssueDate(), dateFormat));
		src = CommonUtil.replace(src, "#INVOICE_NUMBER#", usrInvoice.getInvoiceNumber());
		src = CommonUtil.replace(src, "#DUE_DATE#", CommonUtil.toString(usrInvoice.getPaymentDueDate(), dateFormat));

		src = CommonUtil.replace(src, "#CLIENT_NAME#", usrInvoice.getClientName());
		src = CommonUtil.replace(src, "#CLIENT_TELEPHONE#", CommonUtil.getFormatString(usrInvoice.getClientTelephone(), "?? ???? ????"));
		src = CommonUtil.replace(src, "#CLIENT_MOBILE#", CommonUtil.getFormatString(usrInvoice.getClientMobile(), "???? ??? ???"));
		src = CommonUtil.replace(src, "#CLIENT_EMAIL#", usrInvoice.getClientEmail());
		src = CommonUtil.replace(src, "#CLIENT_ADDRESS#", usrInvoice.getClientAddress());
		src = CommonUtil.replace(src, "#PROVIDER_NAME#", usrInvoice.getProviderName());
		src = CommonUtil.replace(src, "#PROVIDER_ABN#", CommonUtil.getFormatString(usrInvoice.getProviderAbn(), "?? ??? ??? ???"));
		src = CommonUtil.replace(src, "#PROVIDER_ACN#", providerAcn);
		src = CommonUtil.replace(src, "#PROVIDER_TELEPHONE#", CommonUtil.getFormatString(usrInvoice.getProviderTelephone(), "?? ???? ????"));
		src = CommonUtil.replace(src, "#PROVIDER_MOBILE#", CommonUtil.getFormatString(usrInvoice.getProviderMobile(), "???? ??? ???"));
		src = CommonUtil.replace(src, "#PROVIDER_EMAIL#", usrInvoice.getProviderEmail());
		src = CommonUtil.replace(src, "#PROVIDER_ADDRESS#", usrInvoice.getProviderAddress());
		src = CommonUtil.replace(src, "#INVOICE_DETAIL_ROWS#", getInvoiceDetailRows());
		src = CommonUtil.replace(src, "#NET_AMT#", CommonUtil.getNumberMask(usrInvoice.getNetAmt(), numberFormat));
		src = CommonUtil.replace(src, "#GST_AMT#", CommonUtil.getNumberMask(usrInvoice.getGstAmt(), numberFormat));
		src = CommonUtil.replace(src, "#TOTAL_AMT#", CommonUtil.getNumberMask(usrInvoice.getTotalAmt(), numberFormat));
		src = CommonUtil.replace(src, "#ADDITIONAL_REMARK#", CommonUtil.nvl(HtmlUtil.stringToHtml(usrInvoice.getAdditionalRemark()), "&nbsp;"));
		src = CommonUtil.replace(src, "#BANK_NAME#", CommonCodeManager.getCodeDescription("BANK_TYPE", usrInvoice.getBankCode()));
		src = CommonUtil.replace(src, "#ACCOUNT_NAME#", usrInvoice.getBankAccntName());
		src = CommonUtil.replace(src, "#BSB#", CommonUtil.getFormatString(usrInvoice.getBsb(), "??? ???"));
		src = CommonUtil.replace(src, "#ACCOUNT_NUMBER#", usrInvoice.getBankAccntNumber());
		src = CommonUtil.replace(src, "#REF_NUMBER#", usrInvoice.getRefNumber());

		return src;
	}

	private String getInvoiceDetailRows() throws Exception {
		String str = "", numberFormat = "#,##0.00";

		if (usrInvoiceDDataSet.getRowCnt() > 0) {
			for (int i=0; i<usrInvoiceDDataSet.getRowCnt(); i++) {
				str += "<tr>";
				str += "<td class=\"tdGrid Lt\">"+CommonUtil.nvl(usrInvoiceDDataSet.getValue(i, "DESCRIPTION"), "&nbsp;")+"</td>";
				str += "<td class=\"tdGrid Rt\">"+CommonUtil.getNumberMask(usrInvoiceDDataSet.getValue(i, "UNIT"), numberFormat)+"</td>";
				str += "<td class=\"tdGrid Rt\">"+CommonUtil.getNumberMask(usrInvoiceDDataSet.getValue(i, "AMT_PER_UNIT"), numberFormat)+"</td>";
				str += "<td class=\"tdGrid Rt\">"+CommonUtil.getNumberMask(usrInvoiceDDataSet.getValue(i, "ITEM_AMT"), numberFormat)+"</td>";
				str += "</tr>";
			}
		} else {
			str += "<tr>";
			str += "<td class=\"tdGrid Ct\" colspan=\"4\">&nbsp;</td>";
			str += "</tr>";
		}

		return str;
	}
}