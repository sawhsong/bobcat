package project.common.extend;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import project.conf.resource.ormapper.dto.oracle.SysOrg;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class ReportExcelExportHelper extends ExportHelper {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(ReportExcelExportHelper.class);

	@Override
	public File createFile() throws Exception {
		String filePathName;
		File file = null, dir = null;
		FileOutputStream fout;
		SXSSFWorkbook wb = new SXSSFWorkbook();
		SXSSFSheet sheet;

		if (CommonUtil.isBlank(fileName)) {
			sheet = wb.createSheet();
			filePathName = TARGET_FILE_PATH+"/"+FILE_NAME_PREFIX+"."+fileExtention;
			setFileNameGenerated(FILE_NAME_PREFIX+"."+fileExtention);
		} else {
			sheet = wb.createSheet(fileName);
			filePathName = TARGET_FILE_PATH+"/"+FILE_NAME_PREFIX+"_"+fileName+"."+fileExtention;
			setFileName(fileName+"."+fileExtention);
			setFileNameGenerated(FILE_NAME_PREFIX+"_"+fileName+"."+fileExtention);
		}

		dir = new File(TARGET_FILE_PATH);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		file = new File(filePathName);
		fout = new FileOutputStream(file);

		createSheet(wb, sheet);

		wb.write(fout);
		wb.close();
		fout.close();

		return file;
	}

	private void createSheet(SXSSFWorkbook wb, SXSSFSheet sheet) throws Exception {
		if (CommonUtil.equalsIgnoreCase(reportType, "TrialBalance")) {
			createSheetTrialBalance(wb, sheet);
		} else if (CommonUtil.equalsIgnoreCase(reportType, "GeneralLedger")) {
			createSheetGeneralLedger(wb, sheet);
		} else if (CommonUtil.equalsIgnoreCase(reportType, "ProfitAndLoss")) {
			createSheetProfitAndLoss(wb, sheet);
		}
	}

	/*!
	 * Supporter Methods
	 */
	private void createSheetTrialBalance(SXSSFWorkbook wb, SXSSFSheet sheet) throws Exception {
		int rowIndex = -1, freezeRowIndex = 0;
		SXSSFRow row;
		SXSSFCell cell;
		Footer footer = sheet.getFooter();
		PrintSetup ps = sheet.getPrintSetup();
		Map<String, CellStyle> styles = createStyles(wb);
		String dateFormat = "dd/MM/yyyy", dateTimeFormat = "dd/MM/yyyy HH:mm:ss";
		String defaultDateFormat = ConfigUtil.getProperty("format.date.java");
		SysOrg sysOrg = (SysOrg)queryAdvisor.getObject("sysOrg");
		String financialYear = CommonUtil.nvl((String)queryAdvisor.getObject("financialYear"), "-");
		String quarterName = CommonUtil.nvl((String)queryAdvisor.getObject("quarterName"), "-");
		Date fromDate = CommonUtil.toDate((String)queryAdvisor.getObject("fromDate"), defaultDateFormat);
		Date toDate = CommonUtil.toDate((String)queryAdvisor.getObject("toDate"), defaultDateFormat);

		footer.setRight( "Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());
		sheet.setHorizontallyCenter(true);
		sheet.setAutobreaks(true);
		ps.setFitWidth((short)1);

		// Title
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(30);
		cell = row.createCell(0);
		cell.setCellValue(sysOrg.getLegalName()+"\n"+"Trial Balance");
		cell.setCellStyle(styles.get("pageTitle"));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

		// Description section
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
		cell = row.createCell(0);
		cell.setCellValue(CommonUtil.getSysdate(dateTimeFormat));
		cell.setCellStyle(styles.get("descLeft"));

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 3, 4));
		cell = row.createCell(3);
		cell.setCellValue("Financial Year : "+financialYear);
		cell.setCellStyle(styles.get("descRight"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
		cell = row.createCell(0);
		cell.setCellValue("From First to Last by Account");
		cell.setCellStyle(styles.get("descLeft"));

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 3, 4));
		cell = row.createCell(3);
		cell.setCellValue("Quarter : "+quarterName);
		cell.setCellStyle(styles.get("descRight"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
		cell = row.createCell(0);
		cell.setCellValue("Options : Not summarised, Excluding NIL Balances");
		cell.setCellStyle(styles.get("descLeft"));

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 3, 4));
		cell = row.createCell(3);
		cell.setCellValue("Date Period : "+CommonUtil.toString(fromDate, dateFormat)+" - "+CommonUtil.toString(toDate, dateFormat));
		cell.setCellStyle(styles.get("descRight"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
		cell = row.createCell(0);
		cell.setCellValue("Client Code : ");
		cell.setCellStyle(styles.get("descLeft"));

		// Column Header
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		cell = row.createCell(0);
		cell.setCellValue("Last Year");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(0);

		cell = row.createCell(1);
		cell.setCellValue("Account");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(1);

		cell = row.createCell(2);
		cell.setCellValue("Description");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(2);

		cell = row.createCell(3);
		cell.setCellValue("Debit");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(3);

		cell = row.createCell(4);
		cell.setCellValue("Credit");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(4);

		freezeRowIndex = rowIndex;

		// Data Rows
		for (int i=0; i<sourceDataSet.getRowCnt(); i++) {
			rowIndex++;
			row = sheet.createRow(rowIndex);
			row.setHeightInPoints(18);

			if (i == (sourceDataSet.getRowCnt()-1)) {
				cell = row.createCell(0);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "LAST_YEAR")));
				cell.setCellStyle(styles.get("totalRowNumber"));
				sheet.autoSizeColumn(0);

				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(styles.get("totalRowLt"));
				sheet.autoSizeColumn(1);

				cell = row.createCell(2);
				cell.setCellValue("Total");
				cell.setCellStyle(styles.get("totalRowLt"));
				sheet.autoSizeColumn(2);

				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "DEBIT")));
				cell.setCellStyle(styles.get("totalRowNumber"));
				sheet.autoSizeColumn(3);

				cell = row.createCell(4);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "CREDIT")));
				cell.setCellStyle(styles.get("totalRowNumber"));
				sheet.autoSizeColumn(4);

//				sheet.setColumnWidth(0, 13);
//				sheet.setColumnWidth(1, 13);
//				sheet.setColumnWidth(2, 30);
//				sheet.setColumnWidth(3, 13);
//				sheet.setColumnWidth(4, 13);
			} else {
				cell = row.createCell(0);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "LAST_YEAR")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(1);
				cell.setCellValue(sourceDataSet.getValue(i, "ACCOUNT_CODE"));
				cell.setCellStyle(styles.get("dataRowLt"));

				cell = row.createCell(2);
				cell.setCellValue(sourceDataSet.getValue(i, "DESCRIPTION"));
				cell.setCellStyle(styles.get("dataRowLt"));

				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "DEBIT")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(4);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "CREDIT")));
				cell.setCellStyle(styles.get("dataRowNumber"));
			}
		}

		// Bottom description section
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		cell = row.createCell(0);
		cell.setCellValue("No. of Accounts : "+CommonUtil.toString(getNumberOfAccounts(), "#,##0"));
		cell.setCellStyle(styles.get("descLeft"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		cell = row.createCell(0);
		cell.setCellValue("No. of Entries : "+CommonUtil.toString(sourceDataSet.getRowCnt()-1, "#,##0"));
		cell.setCellStyle(styles.get("descLeft"));

		sheet.createFreezePane(0, freezeRowIndex+1, 0, freezeRowIndex+1);
	}

	private void createSheetGeneralLedger(SXSSFWorkbook wb, SXSSFSheet sheet) throws Exception {
		int rowIndex = -1, freezeRowIndex = 0;
		SXSSFRow row;
		SXSSFCell cell;
		Footer footer = sheet.getFooter();
		PrintSetup ps = sheet.getPrintSetup();
		Map<String, CellStyle> styles = createStyles(wb);
		String dateFormat = "dd/MM/yyyy", dateTimeFormat = "dd/MM/yyyy HH:mm:ss";
		String defaultDateFormat = ConfigUtil.getProperty("format.date.java");
		SysOrg sysOrg = (SysOrg)queryAdvisor.getObject("sysOrg");
		String financialYear = CommonUtil.nvl((String)queryAdvisor.getObject("financialYear"), "-");
		String quarterName = CommonUtil.nvl((String)queryAdvisor.getObject("quarterName"), "-");
		Date fromDate = CommonUtil.toDate((String)queryAdvisor.getObject("fromDate"), defaultDateFormat);
		Date toDate = CommonUtil.toDate((String)queryAdvisor.getObject("toDate"), defaultDateFormat);

		footer.setRight( "Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());
		sheet.setHorizontallyCenter(true);
		sheet.setAutobreaks(true);
		ps.setFitWidth((short)1);

		// Title
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(30);
		cell = row.createCell(0);
		cell.setCellValue(sysOrg.getLegalName()+"\n"+"General Ledger");
		cell.setCellStyle(styles.get("pageTitle"));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

		// Description section
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
		cell = row.createCell(0);
		cell.setCellValue(CommonUtil.getSysdate(dateTimeFormat));
		cell.setCellStyle(styles.get("descLeft"));

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 6, 7));
		cell = row.createCell(6);
		cell.setCellValue("Financial Year : "+financialYear);
		cell.setCellStyle(styles.get("descRight"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
		cell = row.createCell(0);
		cell.setCellValue("From First to Last by Account");
		cell.setCellStyle(styles.get("descLeft"));

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 6, 7));
		cell = row.createCell(6);
		cell.setCellValue("Quarter : "+quarterName);
		cell.setCellStyle(styles.get("descRight"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
		cell = row.createCell(0);
		cell.setCellValue("Client Code : ");
		cell.setCellStyle(styles.get("descLeft"));

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 6, 7));
		cell = row.createCell(6);
		cell.setCellValue("Date Period : "+CommonUtil.toString(fromDate, dateFormat)+" - "+CommonUtil.toString(toDate, dateFormat));
		cell.setCellStyle(styles.get("descRight"));

		// Column Header
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		cell = row.createCell(0);
		cell.setCellValue("Account");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(0);

		cell = row.createCell(1);
		cell.setCellValue("Date");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(1);

		cell = row.createCell(2);
		cell.setCellValue("Narration");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(2);

		cell = row.createCell(3);
		cell.setCellValue("GST");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(3);

		cell = row.createCell(4);
		cell.setCellValue("Gross Amount");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(4);

		cell = row.createCell(5);
		cell.setCellValue("Debit");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(5);

		cell = row.createCell(6);
		cell.setCellValue("Credit");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(6);

		cell = row.createCell(7);
		cell.setCellValue("Balance");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(7);

		freezeRowIndex = rowIndex;

		// Data Rows
		for (int i=0; i<sourceDataSet.getRowCnt(); i++) {
			Date dProcDate = CommonUtil.toDate(sourceDataSet.getValue(i, "PROC_DATE"));
			String sProcDate = CommonUtil.toString(dProcDate, dateFormat);

			rowIndex++;
			row = sheet.createRow(rowIndex);
			row.setHeightInPoints(18);

			if (CommonUtil.isBlank(sProcDate)) {
				if (i == sourceDataSet.getRowCnt()-1) {
					cell = row.createCell(0);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("totalRowLt"));
					sheet.autoSizeColumn(0);

					cell = row.createCell(1);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("totalRowLt"));
					sheet.autoSizeColumn(1);

					cell = row.createCell(2);
					cell.setCellValue("Total");
					cell.setCellStyle(styles.get("totalRowLt"));
					sheet.autoSizeColumn(2);

					cell = row.createCell(3);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "GST_AMT")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(3);

					cell = row.createCell(4);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "GROSS_AMT")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(4);

					cell = row.createCell(5);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "DEBIT_AMT")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(5);

					cell = row.createCell(6);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "CREDIT_AMT")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(6);

					cell = row.createCell(7);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "BALANCE")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(7);
				} else {
					cell = row.createCell(0);
					cell.setCellValue(sourceDataSet.getValue(i, "ACCOUNT_CODE"));
					cell.setCellStyle(styles.get("dataRowLt"));
					sheet.autoSizeColumn(0);

					cell = row.createCell(1);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));
					sheet.autoSizeColumn(1);

					cell = row.createCell(2);
					cell.setCellValue(sourceDataSet.getValue(i, "CATEGORY_NAME"));
					cell.setCellStyle(styles.get("dataRowLt"));
					sheet.autoSizeColumn(2);

					cell = row.createCell(3);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));
					sheet.autoSizeColumn(3);

					cell = row.createCell(4);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));
					sheet.autoSizeColumn(4);

					cell = row.createCell(5);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));
					sheet.autoSizeColumn(5);

					cell = row.createCell(6);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));
					sheet.autoSizeColumn(6);

					cell = row.createCell(7);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "BALANCE")));
					cell.setCellStyle(styles.get("dataRowNumber"));
					sheet.autoSizeColumn(7);
				}
			} else {
				cell = row.createCell(0);
				cell.setCellValue("");
				cell.setCellStyle(styles.get("dataRowLt"));
				sheet.autoSizeColumn(0);

				cell = row.createCell(1);
				cell.setCellValue(sProcDate);
				cell.setCellStyle(styles.get("dataRowLt"));
				sheet.autoSizeColumn(1);

				cell = row.createCell(2);
				cell.setCellValue("");
				cell.setCellStyle(styles.get("dataRowLt"));
				sheet.autoSizeColumn(2);

				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "GST_AMT")));
				cell.setCellStyle(styles.get("dataRowNumber"));
				sheet.autoSizeColumn(3);

				cell = row.createCell(4);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "GROSS_AMT")));
				cell.setCellStyle(styles.get("dataRowNumber"));
				sheet.autoSizeColumn(4);

				cell = row.createCell(5);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "DEBIT_AMT")));
				cell.setCellStyle(styles.get("dataRowNumber"));
				sheet.autoSizeColumn(5);

				cell = row.createCell(6);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "CREDIT_AMT")));
				cell.setCellStyle(styles.get("dataRowNumber"));
				sheet.autoSizeColumn(6);

				cell = row.createCell(7);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "BALANCE")));
				cell.setCellStyle(styles.get("dataRowNumber"));
				sheet.autoSizeColumn(7);
			}
		}

		// Bottom description section
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		cell = row.createCell(0);
		cell.setCellValue("No. of Accounts : "+CommonUtil.toString(getNumberOfAccounts(), "#,##0"));
		cell.setCellStyle(styles.get("descLeft"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		cell = row.createCell(0);
		cell.setCellValue("No. of Entries : "+CommonUtil.toString(sourceDataSet.getRowCnt()-1, "#,##0"));
		cell.setCellStyle(styles.get("descLeft"));

		sheet.createFreezePane(0, freezeRowIndex+1, 0, freezeRowIndex+1);
	}

	private void createSheetProfitAndLoss(SXSSFWorkbook wb, SXSSFSheet sheet) throws Exception {
		int rowIndex = -1, freezeRowIndex = 0;
		SXSSFRow row;
		SXSSFCell cell;
		Footer footer = sheet.getFooter();
		PrintSetup ps = sheet.getPrintSetup();
		Map<String, CellStyle> styles = createStyles(wb);
		String dateFormat = "dd/MM/yyyy", dateTimeFormat = "dd/MM/yyyy HH:mm:ss";
		String defaultDateFormat = ConfigUtil.getProperty("format.date.java");
		SysOrg sysOrg = (SysOrg)queryAdvisor.getObject("sysOrg");
		String financialYear = CommonUtil.nvl((String)queryAdvisor.getObject("financialYear"), "-");
		String quarterName = CommonUtil.nvl((String)queryAdvisor.getObject("quarterName"), "-");
		Date fromDate = CommonUtil.toDate((String)queryAdvisor.getObject("fromDate"), defaultDateFormat);
		Date toDate = CommonUtil.toDate((String)queryAdvisor.getObject("toDate"), defaultDateFormat);

		footer.setRight( "Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());
		sheet.setHorizontallyCenter(true);
		sheet.setAutobreaks(true);
		ps.setFitWidth((short)1);

		// Title
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(30);
		cell = row.createCell(0);
		cell.setCellValue(sysOrg.getLegalName()+"\n"+"Profit And Loss Statement");
		cell.setCellStyle(styles.get("pageTitle"));
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(30);
		cell = row.createCell(0);
		cell.setCellValue("ABN : "+CommonUtil.getFormatString(sysOrg.getAbn(), "?? ??? ??? ???"));
		cell.setCellStyle(styles.get("pageTitle"));
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 6));

		// Description section
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));
		cell = row.createCell(0);
		cell.setCellValue(CommonUtil.getSysdate(dateTimeFormat));
		cell.setCellStyle(styles.get("descLeft"));

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 5, 6));
		cell = row.createCell(5);
		cell.setCellValue("Financial Year : "+financialYear);
		cell.setCellStyle(styles.get("descRight"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 5, 6));
		cell = row.createCell(5);
		cell.setCellValue("Quarter : "+quarterName);
		cell.setCellStyle(styles.get("descRight"));

		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 5, 6));
		cell = row.createCell(5);
		cell.setCellValue("Date Period : "+CommonUtil.toString(fromDate, dateFormat)+" - "+CommonUtil.toString(toDate, dateFormat));
		cell.setCellStyle(styles.get("descRight"));

		// Column Header
		rowIndex++;
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(18);

		cell = row.createCell(0);
		cell.setCellValue("Narration");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(0);

		cell = row.createCell(1);
		cell.setCellValue("Sep");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(1);

		cell = row.createCell(2);
		cell.setCellValue("Dec");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(2);

		cell = row.createCell(3);
		cell.setCellValue("Mar");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(3);

		cell = row.createCell(4);
		cell.setCellValue("Jun");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(4);

		cell = row.createCell(5);
		cell.setCellValue("This Year");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(5);

		cell = row.createCell(6);
		cell.setCellValue("Last Year");
		cell.setCellStyle(styles.get("gridHeader"));
		sheet.trackColumnForAutoSizing(6);

		freezeRowIndex = rowIndex;

		// Data Rows
		double totSep = 0, totDec = 0, totMar = 0, totJun = 0, totThisYear = 0, totLastYear = 0;
		for (int i=0; i<sourceDataSet.getRowCnt(); i++) {
			String accntCode = sourceDataSet.getValue(i, "ACCOUNT_CODE");

			rowIndex++;
			row = sheet.createRow(rowIndex);
			row.setHeightInPoints(18);

			if (CommonUtil.isBlank(accntCode)) {
				if (i == (sourceDataSet.getRowCnt()-1)) {
					cell = row.createCell(0);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("subTotalRowLt"));

					cell = row.createCell(1);
					cell.setCellValue(totSep);
					cell.setCellStyle(styles.get("subTotalNumber"));

					cell = row.createCell(2);
					cell.setCellValue(totDec);
					cell.setCellStyle(styles.get("subTotalNumber"));

					cell = row.createCell(3);
					cell.setCellValue(totMar);
					cell.setCellStyle(styles.get("subTotalNumber"));

					cell = row.createCell(4);
					cell.setCellValue(totJun);
					cell.setCellStyle(styles.get("subTotalNumber"));

					cell = row.createCell(5);
					cell.setCellValue(totThisYear);
					cell.setCellStyle(styles.get("subTotalNumber"));

					cell = row.createCell(6);
					cell.setCellValue(totLastYear);
					cell.setCellStyle(styles.get("subTotalNumber"));

					totSep = 0;
					totDec = 0;
					totMar = 0;
					totJun = 0;
					totThisYear = 0;
					totLastYear = 0;

					cell = row.createCell(0);
					cell.setCellValue("(Loss) Profit before income tax");
					cell.setCellStyle(styles.get("totalRowLt"));
					sheet.autoSizeColumn(0);

					cell = row.createCell(1);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_SEP")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(1);

					cell = row.createCell(2);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_DEC")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(2);

					cell = row.createCell(3);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_MAR")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(3);

					cell = row.createCell(4);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_JUN")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(4);

					cell = row.createCell(5);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "THIS_YEAR_PROC_AMT")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(5);

					cell = row.createCell(6);
					cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "LAST_YEAR_PROC_AMT")));
					cell.setCellStyle(styles.get("totalRowNumber"));
					sheet.autoSizeColumn(6);
				} else {
					if (i != 0) {
						cell = row.createCell(0);
						cell.setCellValue("");
						cell.setCellStyle(styles.get("subTotalRowLt"));

						cell = row.createCell(1);
						cell.setCellValue(totSep);
						cell.setCellStyle(styles.get("subTotalNumber"));

						cell = row.createCell(2);
						cell.setCellValue(totDec);
						cell.setCellStyle(styles.get("subTotalNumber"));

						cell = row.createCell(3);
						cell.setCellValue(totMar);
						cell.setCellStyle(styles.get("subTotalNumber"));

						cell = row.createCell(4);
						cell.setCellValue(totJun);
						cell.setCellStyle(styles.get("subTotalNumber"));

						cell = row.createCell(5);
						cell.setCellValue(totThisYear);
						cell.setCellStyle(styles.get("subTotalNumber"));

						cell = row.createCell(6);
						cell.setCellValue(totLastYear);
						cell.setCellStyle(styles.get("subTotalNumber"));

						totSep = 0;
						totDec = 0;
						totMar = 0;
						totJun = 0;
						totThisYear = 0;
						totLastYear = 0;
					}

					cell = row.createCell(0);
					cell.setCellValue(sourceDataSet.getValue(i, "CATEGORY_NAME"));
					cell.setCellStyle(styles.get("boldFont"));

					cell = row.createCell(1);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));

					cell = row.createCell(2);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));

					cell = row.createCell(3);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));

					cell = row.createCell(4);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));

					cell = row.createCell(5);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));

					cell = row.createCell(6);
					cell.setCellValue("");
					cell.setCellStyle(styles.get("dataRowLt"));
				}
			} else {
				cell = row.createCell(0);
				cell.setCellValue(sourceDataSet.getValue(i, "CATEGORY_NAME"));
				cell.setCellStyle(styles.get("dataRowLt"));

				cell = row.createCell(1);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_SEP")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(2);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_DEC")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_MAR")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(4);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_JUN")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(5);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "THIS_YEAR_PROC_AMT")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(6);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "LAST_YEAR_PROC_AMT")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				totSep += CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_SEP"));
				totDec += CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_DEC"));
				totMar += CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_MAR"));
				totJun += CommonUtil.toDouble(sourceDataSet.getValue(i, "PROC_AMT_JUN"));
				totThisYear += CommonUtil.toDouble(sourceDataSet.getValue(i, "THIS_YEAR_PROC_AMT"));
				totLastYear += CommonUtil.toDouble(sourceDataSet.getValue(i, "LAST_YEAR_PROC_AMT"));
			}



			if (i == (sourceDataSet.getRowCnt()-1)) {
				cell = row.createCell(0);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "LAST_YEAR")));
				cell.setCellStyle(styles.get("totalRowNumber"));
				sheet.autoSizeColumn(0);

				cell = row.createCell(1);
				cell.setCellValue("");
				cell.setCellStyle(styles.get("totalRowLt"));
				sheet.autoSizeColumn(1);

				cell = row.createCell(2);
				cell.setCellValue("Total");
				cell.setCellStyle(styles.get("totalRowLt"));
				sheet.autoSizeColumn(2);

				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "DEBIT")));
				cell.setCellStyle(styles.get("totalRowNumber"));
				sheet.autoSizeColumn(3);

				cell = row.createCell(4);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "CREDIT")));
				cell.setCellStyle(styles.get("totalRowNumber"));
				sheet.autoSizeColumn(4);
			} else {
				cell = row.createCell(0);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "LAST_YEAR")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(1);
				cell.setCellValue(sourceDataSet.getValue(i, "ACCOUNT_CODE"));
				cell.setCellStyle(styles.get("dataRowLt"));

				cell = row.createCell(2);
				cell.setCellValue(sourceDataSet.getValue(i, "DESCRIPTION"));
				cell.setCellStyle(styles.get("dataRowLt"));

				cell = row.createCell(3);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "DEBIT")));
				cell.setCellStyle(styles.get("dataRowNumber"));

				cell = row.createCell(4);
				cell.setCellValue(CommonUtil.toDouble(sourceDataSet.getValue(i, "CREDIT")));
				cell.setCellStyle(styles.get("dataRowNumber"));
			}
		}

		sheet.createFreezePane(0, freezeRowIndex+1, 0, freezeRowIndex+1);
	}

	private Map<String, CellStyle> createStyles(SXSSFWorkbook wb) throws Exception {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		XSSFCellStyle style;
		DataFormat format = wb.createDataFormat();

		// Bold font
		Font boldFont = wb.createFont();
		boldFont.setFontHeightInPoints((short)10);
		boldFont.setBold(true);
		style = (XSSFCellStyle)wb.createCellStyle();
		styles.put("boldFont", style);

		// Title
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short)12);
		titleFont.setBold(true);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(titleFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setWrapText(true);
		styles.put("pageTitle", style);

		// Description section left
		Font descLeft = wb.createFont();
		descLeft.setFontHeightInPoints((short)10);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(descLeft);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setWrapText(false);
		styles.put("descLeft", style);

		// Description section right
		Font descRight = wb.createFont();
		descRight.setFontHeightInPoints((short)10);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(descRight);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setWrapText(false);
		styles.put("descRight", style);

		// Grid Header
		Font gridHeaderFont = wb.createFont();
		gridHeaderFont.setFontHeightInPoints((short)10);
		gridHeaderFont.setBold(true);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(gridHeaderFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(new XSSFColor(new Color(252, 248, 227)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setWrapText(false);
		styles.put("gridHeader", style);

		// Data
		Font dataFont = wb.createFont();
		dataFont.setFontHeightInPoints((short)10);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(dataFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(false);
		styles.put("dataRowLt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(dataFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(false);
		styles.put("dataRowCt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(dataFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(false);
		styles.put("dataRowRt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(dataFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setDataFormat(format.getFormat("#,##0.00"));
		style.setWrapText(false);
		styles.put("dataRowNumber", style);

		// Total
		Font totalFont = wb.createFont();
		totalFont.setFontHeightInPoints((short)10);
		totalFont.setBold(true);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(totalFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setFillForegroundColor(new XSSFColor(new Color(252, 248, 227)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setWrapText(false);
		styles.put("totalRowLt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(totalFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(new XSSFColor(new Color(252, 248, 227)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setWrapText(false);
		styles.put("totalRowCt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(totalFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFillForegroundColor(new XSSFColor(new Color(252, 248, 227)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setWrapText(false);
		styles.put("totalRowRt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(totalFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFillForegroundColor(new XSSFColor(new Color(252, 248, 227)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setDataFormat(format.getFormat("#,##0.00"));
		style.setWrapText(false);
		styles.put("totalRowNumber", style);

		// SubTotal
		Font subTotalFont = wb.createFont();
		subTotalFont.setFontHeightInPoints((short)10);
		subTotalFont.setBold(true);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(subTotalFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setFillForegroundColor(new XSSFColor(new Color(255, 254, 244)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(false);
		styles.put("subTotalRowLt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(subTotalFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(new XSSFColor(new Color(255, 254, 244)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(false);
		styles.put("subTotalRowCt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(subTotalFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFillForegroundColor(new XSSFColor(new Color(255, 254, 244)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(false);
		styles.put("subTotalRowRt", style);

		style = (XSSFCellStyle)wb.createCellStyle();
		style.setFont(subTotalFont);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFillForegroundColor(new XSSFColor(new Color(255, 254, 244)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setDataFormat(format.getFormat("#,##0.00"));
		style.setWrapText(false);
		styles.put("subTotalRowNumber", style);

		return styles;
	}

	private int getNumberOfAccounts() throws Exception {
		String preAccntCode = "";
		int accntCodeCnt = 0;

		if (sourceDataSet.getRowCnt() > 0) {
			for (int i=0; i<sourceDataSet.getRowCnt()-1; i++) {
				if (!CommonUtil.equalsIgnoreCase(preAccntCode, sourceDataSet.getValue(i, "ACCOUNT_CODE"))) {
					preAccntCode = sourceDataSet.getValue(i, "ACCOUNT_CODE");
					accntCodeCnt++;
				}
			}
		}

		return accntCodeCnt;
	}
}