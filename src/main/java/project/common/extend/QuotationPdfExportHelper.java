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

import project.conf.resource.ormapper.dto.oracle.UsrQuotation;
import zebra.data.DataSet;
import zebra.export.ExcelExportHelper;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.HtmlUtil;

public class QuotationPdfExportHelper extends ExportHelper {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(ExcelExportHelper.class);
	private UsrQuotation usrQuotation;
	private DataSet usrQuotationDDataSet;

	public UsrQuotation getUsrQuotation() {
		return usrQuotation;
	}

	public void setUsrQuotation(UsrQuotation usrQuotation) {
		this.usrQuotation = usrQuotation;
	}

	public DataSet getUsrQuotationDDataSet() {
		return usrQuotationDDataSet;
	}

	public void setUsrQuotationDDataSet(DataSet usrQuotationDDataSet) {
		this.usrQuotationDDataSet = usrQuotationDDataSet;
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
			sourceName = "QuotationPdf.src";
		} else if (CommonUtil.containsIgnoreCase(fileType, "pdf")) {
			sourceName = "QuotationPdf.src";
		} else if (CommonUtil.containsIgnoreCase(fileType, "html")) {
			sourceName = "QuotationPdf.src";
		}

		return new File(SOURCE_FILE_PATH+"/"+sourceName);
	}

	private String getExportDetails(String src) throws Exception {
		String dateFormat = "dd/MM/yyyy", numberFormat = "#,##0.00";
		String webAddress = ConfigUtil.getProperty("webAddress");
		String logoPath = usrQuotation.getProviderLogoPath();
		String providerLogo = "", providerAcn = "";

		if (CommonUtil.isNotBlank(logoPath)) {
			providerLogo = "<img src=\""+webAddress+logoPath+"\" style=\"width:250px;height:80px;\"/>";
		} else {
			providerLogo = "&nbsp;";
		}

		if (CommonUtil.isNotBlank(usrQuotation.getProviderAcn())) {
			providerAcn = "<tr><td class=\"tdDefault Rt\"><font style=\"font-weight:bold\">ACN : </font>"+CommonUtil.getFormatString(usrQuotation.getProviderAcn(), "??? ??? ???")+"</td></tr>";
		}


		src = CommonUtil.replace(src, "#PROVIDER_LOGO#", providerLogo);
		src = CommonUtil.replace(src, "#PROVIDER_NAME#", usrQuotation.getProviderName());
		src = CommonUtil.replace(src, "#ISSUE_DATE#", CommonUtil.toString(usrQuotation.getIssueDate(), dateFormat));
		src = CommonUtil.replace(src, "#QUOTATION_NUMBER#", usrQuotation.getQuotationNumber());

		src = CommonUtil.replace(src, "#CLIENT_NAME#", usrQuotation.getClientName());
		src = CommonUtil.replace(src, "#CLIENT_TELEPHONE#", CommonUtil.getFormatString(usrQuotation.getClientTelephone(), "?? ???? ????"));
		src = CommonUtil.replace(src, "#CLIENT_MOBILE#", CommonUtil.getFormatString(usrQuotation.getClientMobile(), "???? ??? ???"));
		src = CommonUtil.replace(src, "#CLIENT_EMAIL#", usrQuotation.getClientEmail());
		src = CommonUtil.replace(src, "#CLIENT_ADDRESS#", usrQuotation.getClientAddress());
		src = CommonUtil.replace(src, "#PROVIDER_NAME#", usrQuotation.getProviderName());
		src = CommonUtil.replace(src, "#PROVIDER_ABN#", CommonUtil.getFormatString(usrQuotation.getProviderAbn(), "?? ??? ??? ???"));
		src = CommonUtil.replace(src, "#PROVIDER_ACN#", providerAcn);
		src = CommonUtil.replace(src, "#PROVIDER_TELEPHONE#", CommonUtil.getFormatString(usrQuotation.getProviderTelephone(), "?? ???? ????"));
		src = CommonUtil.replace(src, "#PROVIDER_MOBILE#", CommonUtil.getFormatString(usrQuotation.getProviderMobile(), "???? ??? ???"));
		src = CommonUtil.replace(src, "#PROVIDER_EMAIL#", usrQuotation.getProviderEmail());
		src = CommonUtil.replace(src, "#PROVIDER_ADDRESS#", usrQuotation.getProviderAddress());
		src = CommonUtil.replace(src, "#QUOTATION_DETAIL_ROWS#", getQuotationDetailRows());
		src = CommonUtil.replace(src, "#NET_AMT#", CommonUtil.getNumberMask(usrQuotation.getNetAmt(), numberFormat));
		src = CommonUtil.replace(src, "#GST_AMT#", CommonUtil.getNumberMask(usrQuotation.getGstAmt(), numberFormat));
		src = CommonUtil.replace(src, "#TOTAL_AMT#", CommonUtil.getNumberMask(usrQuotation.getTotalAmt(), numberFormat));
		src = CommonUtil.replace(src, "#ADDITIONAL_REMARK#", CommonUtil.nvl(HtmlUtil.stringToHtml(usrQuotation.getAdditionalRemark()), "&nbsp;"));

		return src;
	}

	private String getQuotationDetailRows() throws Exception {
		String str = "", numberFormat = "#,##0.00";

		if (usrQuotationDDataSet.getRowCnt() > 0) {
			for (int i=0; i<usrQuotationDDataSet.getRowCnt(); i++) {
				str += "<tr>";
				str += "<td class=\"tdGrid Lt\">"+CommonUtil.nvl(usrQuotationDDataSet.getValue(i, "DESCRIPTION"), "&nbsp;")+"</td>";
				str += "<td class=\"tdGrid Rt\">"+CommonUtil.getNumberMask(usrQuotationDDataSet.getValue(i, "UNIT"), numberFormat)+"</td>";
				str += "<td class=\"tdGrid Rt\">"+CommonUtil.getNumberMask(usrQuotationDDataSet.getValue(i, "AMT_PER_UNIT"), numberFormat)+"</td>";
				str += "<td class=\"tdGrid Rt\">"+CommonUtil.getNumberMask(usrQuotationDDataSet.getValue(i, "ITEM_AMT"), numberFormat)+"</td>";
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