package project.common.extend;

import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zefer.pd4ml.PD4ML;

import project.conf.resource.ormapper.dto.oracle.SysOrg;
import project.conf.resource.ormapper.dto.oracle.SysUser;
import zebra.export.ExcelExportHelper;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class ReportPdfExportHelper extends ExportHelper {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(ExcelExportHelper.class);

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

//		pd4PageMark.setHtmlTemplate("<div style=\"width:100%;text-align:right;font:normal normal normal 10px/1.2 \"Verdana\"\">Page $[page] of $[total]</div>");
//		pd4PageMark.setAreaHeight(-1);
//		pd4ml.setPageHeader(pd4PageMark);

		pd4ml.enableTableBreaks(true);
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

		if (CommonUtil.equalsIgnoreCase(reportType, "TrialBalance")) {
			if (CommonUtil.containsIgnoreCase(fileType, "excel")) {
				sourceName = "ReportTrialBalancePdf.src";
			} else if (CommonUtil.containsIgnoreCase(fileType, "pdf")) {
				sourceName = "ReportTrialBalancePdf.src";
			} else if (CommonUtil.containsIgnoreCase(fileType, "html")) {
				sourceName = "ReportTrialBalancePdf.src";
			}
		}

		return new File(SOURCE_FILE_PATH+"/"+sourceName);
	}

	private String getExportDetails(String src) throws Exception {
		String rtn = "";

		if (CommonUtil.equalsIgnoreCase(reportType, "TrialBalance")) {
			rtn = getExportDetailsTrialBalance(src);
		}

		return rtn;
	}

	private String getExportDetailsTrialBalance(String src) throws Exception {
		String dateFormat = "dd/MM/yyyy", dateTimeFormat = "dd/MM/yyyy HH:mm:ss";
		String defaultDateFormat = ConfigUtil.getProperty("format.date.java");
		HttpSession session = paramEntity.getSession();
		SysUser sysUser = (SysUser)session.getAttribute("SysUserForAdminTool") == null ? (SysUser)session.getAttribute("SysUser") : (SysUser)session.getAttribute("SysUserForAdminTool");
		SysOrg sysOrg = (SysOrg)session.getAttribute("SysOrgForAdminTool") == null ? (SysOrg)session.getAttribute("SysOrg") : (SysOrg)session.getAttribute("SysOrgForAdminTool");
		String financialYear = CommonUtil.nvl((String)queryAdvisor.getObject("financialYear"), "-");
		String quarterName = CommonUtil.nvl((String)queryAdvisor.getObject("quarterName"), "-");
		Date fromDate = CommonUtil.toDate((String)queryAdvisor.getObject("fromDate"), defaultDateFormat);
		Date toDate = CommonUtil.toDate((String)queryAdvisor.getObject("toDate"), defaultDateFormat);

		src = CommonUtil.replace(src, "#ORG_NAME#", sysOrg.getLegalName());
		src = CommonUtil.replace(src, "#SYSTEM_DATE_TIME#", CommonUtil.getSysdate(dateTimeFormat));
		src = CommonUtil.replace(src, "#CLIENT_CODE#", sysUser.getLoginId());
		src = CommonUtil.replace(src, "#FINANCIAL_YEAR#", financialYear);
		src = CommonUtil.replace(src, "#QUARTER_NAME#", quarterName);
		src = CommonUtil.replace(src, "#DATE_PERIOD#", CommonUtil.toString(fromDate, dateFormat)+" - "+CommonUtil.toString(toDate, dateFormat));
		src = CommonUtil.replace(src, "#DETAIL_ROWS#", getDetailRowsTrialBalance());
		src = CommonUtil.replace(src, "#NUMBER_OF_ACCOUNTS#", CommonUtil.toString(getNumberOfAccounts(), "#,##0"));
		src = CommonUtil.replace(src, "#NUMBER_OF_ENTRIES#", CommonUtil.toString(sourceDataSet.getRowCnt()-1, "#,##0"));

		return src;
	}

	private String getDetailRowsTrialBalance() throws Exception {
		String str = "";

		if (sourceDataSet.getRowCnt() > 0) {
			for (int i=0; i<sourceDataSet.getRowCnt(); i++) {
				if (i == sourceDataSet.getRowCnt()-1) {
					str += "<tr style=\"font-weight:bold;background:#f5f5f5;\">";
					str += "<td class=\"tdGrid Rt\" style=\"border-left:0px;border-top:1px solid #dddddd;border-bottom:1px solid #dddddd;\">"+CommonUtil.nvl(CommonUtil.getAccountingFormat(sourceDataSet.getValue(i, "LAST_YEAR")), "&nbsp;")+"</td>";
					str += "<td class=\"tdGrid Lt\" style=\"border-top:1px solid #dddddd;border-bottom:1px solid #dddddd;\">"+CommonUtil.nvl(sourceDataSet.getValue(i, "ACCOUNT_CODE"), "&nbsp;")+"</td>";
					str += "<td class=\"tdGrid Lt\" style=\"border-top:1px solid #dddddd;border-bottom:1px solid #dddddd;\">Total</td>";
					str += "<td class=\"tdGrid Rt\" style=\"border-top:1px solid #dddddd;border-bottom:1px solid #dddddd;\">"+CommonUtil.nvl(CommonUtil.getAccountingFormat(sourceDataSet.getValue(i, "DEBIT")), "&nbsp;")+"</td>";
					str += "<td class=\"tdGrid Rt\" style=\"border-right:0px;border-top:1px solid #dddddd;border-bottom:1px solid #dddddd;\">"+CommonUtil.nvl(CommonUtil.getAccountingFormat(sourceDataSet.getValue(i, "CREDIT")), "&nbsp;")+"</td>";
				} else {
					str += "<tr>";
					str += "<td class=\"tdGrid Rt\" style=\"border-left:0px;\">"+CommonUtil.nvl(CommonUtil.getAccountingFormat(sourceDataSet.getValue(i, "LAST_YEAR")), "&nbsp;")+"</td>";
					str += "<td class=\"tdGrid Lt\">"+CommonUtil.nvl(sourceDataSet.getValue(i, "ACCOUNT_CODE"), "&nbsp;")+"</td>";
					str += "<td class=\"tdGrid Lt\">"+CommonUtil.nvl(sourceDataSet.getValue(i, "DESCRIPTION"), "&nbsp;")+"</td>";
					str += "<td class=\"tdGrid Rt\">"+CommonUtil.nvl(CommonUtil.getAccountingFormat(sourceDataSet.getValue(i, "DEBIT")), "&nbsp;")+"</td>";
					str += "<td class=\"tdGrid Rt\" style=\"border-right:0px;\">"+CommonUtil.nvl(CommonUtil.getAccountingFormat(sourceDataSet.getValue(i, "CREDIT")), "&nbsp;")+"</td>";
				}
				str += "</tr>";
			}
		} else {
			str += "<tr>";
			str += "<td class=\"tdGrid Ct\" colspan=\"5\">&nbsp;</td>";
			str += "</tr>";
		}

		return str;
	}

	private int getNumberOfAccounts() throws Exception {
		String preAccntCode = "";
		int accntCodeCnt = 0;

		if (sourceDataSet.getRowCnt() > 0) {
			for (int i=0; i<sourceDataSet.getRowCnt()-1; i++) {
				if (!CommonUtil.equalsIgnoreCase(preAccntCode, sourceDataSet.getValue(i, "ACCOUNT_CODE"))) {
					accntCodeCnt++;
				}
			}
		}

		return accntCodeCnt;
	}
}