package zebra.export;

import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import project.conf.resource.ormapper.dto.oracle.UsrQuotation;
import zebra.data.DataSet;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class QuotationPdfExportHelper extends ExportHelper {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(ExcelExportHelper.class);
	UsrQuotation usrQuotation;
	DataSet usrQuotationDDataSet;

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
		PD4ML pd4ml = new PD4ML();
		File file = null, dir = null;
		String contentString, exportDetails;
		OutputStreamWriter osWriter;
		String webAddress = ConfigUtil.getProperty("webAddress");
		String logoPath = usrQuotation.getProviderLogoPath();
		String providerLogo = "";
		File htmlFile;
		FileOutputStream htmlOs;

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

		contentString = getSourceString();
		exportDetails = getExportDetails();

		if (CommonUtil.isNotBlank(logoPath)) {
			providerLogo = "<img src=\""+webAddress+logoPath+"\" style=\"width:250px;height:80px;\"/>";
		}

		contentString = CommonUtil.replace(contentString, "#PROVIDER_LOGO#", providerLogo);
		contentString = CommonUtil.replace(contentString, "#PROVIDER_NAME#", usrQuotation.getProviderName());
		contentString = CommonUtil.replace(contentString, "#EXPORT_DETAILS#", exportDetails);

		osWriter.write(contentString);
		osWriter.flush();
		osWriter.close();

		// test html file
		htmlFile = new File(TARGET_FILE_PATH + "/" + FILE_NAME_PREFIX+"_"+fileName+".html");
		htmlOs = new FileOutputStream(htmlFile);
		htmlOs.write(contentString.getBytes());
		htmlOs.close();

		pd4ml.setPageInsets(new Insets(10, 10, 10, 10));
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

	private String getExportDetails() throws Exception {
		StringBuffer str = new StringBuffer();
		String dateFormat = "dd/MM/yyyy", numberFormat = "#,##0.00";
		String logoPath = usrQuotation.getProviderLogoPath();

		str.append("<div class=\"verGap20\"></div>");
		str.append("<table class=\"tblDefault\">");
		str.append("<colgroup>");
		str.append("<col width=\"50%\"/>");
		str.append("<col width=\"50%\"/>");
		str.append("<tr>");
		str.append("<td class=\"tdDefault Lt\" style=\"vertical-align:top;border-bottom:2px solid #DDDDDD;\">");
		str.append("<table class=\"tblDefault withPadding\">");
		str.append("<tr>");
		str.append("<td class=\"tdDefault Lt\" style=\"height:88px;\">");
		if (CommonUtil.isNotBlank(logoPath)) {
			str.append("<img src=\""+logoPath+"\" style=\"width:250px;height:80px;\"/>");
		}
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<td class=\"tdDefault Lt\" style=\"font-size:14px;font-weight:bold;\"><%=uqm.getProviderName()%></td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("</td>");
		str.append("<td class=\"tdDefault Rt\" style=\"vertical-align:top;border-bottom:2px solid #DDDDDD;\">");
		str.append("<table class=\"tblDefault withPadding\">");
		str.append("<colgroup>");
		str.append("<col width=\"*\"/>");
		str.append("<col width=\"25%\"/>");
		str.append("</colgroup>");
		str.append("<tr>");
		str.append("<td class=\"tdDefault Rt\" colspan=\"2\" style=\"vertical-align:top;font-size:16px;font-weight:bold;height:68px;\">Quotation</td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<th class=\"thDefault Rt\">Date</th>");
		str.append("<td class=\"tdDefault Rt\">"+CommonUtil.toString(usrQuotation.getIssueDate(), dateFormat)+"</td>");
		str.append("</tr>");
		str.append("<tr>");
		str.append("<th class=\"thDefault Rt\">Quotation Number</th>");
		str.append("<td class=\"tdDefault Rt\">"+usrQuotation.getQuotationNumber()+"</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");


		return str.toString();
	}
}