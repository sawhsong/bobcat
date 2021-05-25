package project.conf.resource.ormapper.dao.Report;

import java.util.Date;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class ReportHDaoImpl extends BaseHDao implements ReportDao {
	public DataSet getTrialBalance(QueryAdvisor queryAdvisor) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String orgId = (String)queryAdvisor.getObject("orgId");
		String selectedFinancialYear = (String)queryAdvisor.getObject("financialYear");
		String quarterName = (String)queryAdvisor.getObject("quarterName");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");
		String defaultFinancialYear = CommonUtil.getSysdate("yyyy");
		String thisYear = "", lastYear = "";

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("orgId", orgId);

		if (CommonUtil.isNotBlank(fromDate)) {
			Date dateFrom = CommonUtil.toDate(fromDate, dateFormat);
			thisYear = CommonUtil.toString(dateFrom, "yyyy");
			lastYear = CommonUtil.toString(CommonUtil.toInt(thisYear) - 1);

			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+thisYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "");
			queryAdvisor.addVariable("conFromDate", "and trunc(ubta.proc_date) >= to_date('"+fromDate+"', '"+dateFormat+"')");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");
		} else {
			queryAdvisor.addVariable("conFromDate", "");
		}

		if (CommonUtil.isNotBlank(toDate)) {
			Date dateFrom = CommonUtil.toDate(fromDate, dateFormat);
			thisYear = CommonUtil.toString(dateFrom, "yyyy");
			lastYear = CommonUtil.toString(CommonUtil.toInt(thisYear) - 1);

			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+thisYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "");
			queryAdvisor.addVariable("conToDate", "and trunc(ubta.proc_date) <= to_date('"+toDate+"', '"+dateFormat+"')");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");
		} else {
			queryAdvisor.addVariable("conToDate", "");
		}

		if (CommonUtil.isNotBlank(quarterName)) {
			if (CommonUtil.isBlank(selectedFinancialYear)) {
				selectedFinancialYear = defaultFinancialYear;
			}

			lastYear = CommonUtil.toString(CommonUtil.toInt(selectedFinancialYear) - 1);

			queryAdvisor.addVariable("conQuarterName", "and quarter_name = '"+quarterName+"'");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");
		} else {
			queryAdvisor.addVariable("conQuarterName", "");
		}

		if (CommonUtil.isNotBlank(selectedFinancialYear)) {
			lastYear = CommonUtil.toString(CommonUtil.toInt(selectedFinancialYear) - 1);

			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+selectedFinancialYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");
		}

		if (CommonUtil.isBlank(selectedFinancialYear + quarterName + fromDate + toDate)) {
			lastYear = CommonUtil.toString(CommonUtil.toInt(defaultFinancialYear) - 1);

			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+defaultFinancialYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");

			queryAdvisor.addVariable("conFromDate", "");
			queryAdvisor.addVariable("conToDate", "");
			queryAdvisor.addVariable("conQuarterName", "");
		}

		return selectAsDataSet(queryAdvisor, "query.Report.getTrialBalance");
	}

	public DataSet getGeneralLedger(QueryAdvisor queryAdvisor) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String orgId = (String)queryAdvisor.getObject("orgId");
		String selectedFinancialYear = (String)queryAdvisor.getObject("financialYear");
		String quarterName = (String)queryAdvisor.getObject("quarterName");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");
		String defaultFinancialYear = CommonUtil.getSysdate("yyyy");
		String thisYear = "";

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("orgId", orgId);

		if (CommonUtil.isNotBlank(fromDate)) {
			Date dateFrom = CommonUtil.toDate(fromDate, dateFormat);
			thisYear = CommonUtil.toString(dateFrom, "yyyy");

			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+thisYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "");
			queryAdvisor.addVariable("conFromDate", "and trunc(ubta.proc_date) >= to_date('"+fromDate+"', '"+dateFormat+"')");
		} else {
			queryAdvisor.addVariable("conFromDate", "");
		}

		if (CommonUtil.isNotBlank(toDate)) {
			Date dateFrom = CommonUtil.toDate(fromDate, dateFormat);
			thisYear = CommonUtil.toString(dateFrom, "yyyy");

			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+thisYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "");
			queryAdvisor.addVariable("conToDate", "and trunc(ubta.proc_date) <= to_date('"+toDate+"', '"+dateFormat+"')");
		} else {
			queryAdvisor.addVariable("conToDate", "");
		}

		if (CommonUtil.isNotBlank(quarterName)) {
			if (CommonUtil.isBlank(selectedFinancialYear)) {
				selectedFinancialYear = defaultFinancialYear;
			}

			queryAdvisor.addVariable("conQuarterName", "and quarter_name = '"+quarterName+"'");
		} else {
			queryAdvisor.addVariable("conQuarterName", "");
		}

		if (CommonUtil.isNotBlank(selectedFinancialYear)) {
			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+selectedFinancialYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)");
		}

		if (CommonUtil.isBlank(selectedFinancialYear + quarterName + fromDate + toDate)) {
			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+defaultFinancialYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)");

			queryAdvisor.addVariable("conFromDate", "");
			queryAdvisor.addVariable("conToDate", "");
			queryAdvisor.addVariable("conQuarterName", "");
		}

		return selectAsDataSet(queryAdvisor, "query.Report.getGeneralLedger");
	}

	public DataSet getProfitAndLoss(QueryAdvisor queryAdvisor) throws Exception {
		DataSet dsRtn, dsTemp;
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String orgId = (String)queryAdvisor.getObject("orgId");
		String selectedFinancialYear = (String)queryAdvisor.getObject("financialYear");
		String quarterName = (String)queryAdvisor.getObject("quarterName");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");
		String defaultFinancialYear = CommonUtil.getSysdate("yyyy");
		String thisYear = "", lastYear = "";
		double totInSep = 0, totInDec = 0, totInMar = 0, totInJun = 0, totInThisYear = 0, totInLastYear = 0;
		double totPuSep = 0, totPuDec = 0, totPuMar = 0, totPuJun = 0, totPuThisYear = 0, totPuLastYear = 0;
		double totExSep = 0, totExDec = 0, totExMar = 0, totExJun = 0, totExThisYear = 0, totExLastYear = 0;

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("orgId", orgId);

		if (CommonUtil.isNotBlank(fromDate)) {
			Date dateFrom = CommonUtil.toDate(fromDate, dateFormat);
			thisYear = CommonUtil.toString(dateFrom, "yyyy");
			lastYear = CommonUtil.toString(CommonUtil.toInt(thisYear) - 1);

			queryAdvisor.addVariable("thisYear", thisYear);
			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+thisYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "");
			queryAdvisor.addVariable("conFromDate", "and trunc(ubta.proc_date) >= to_date('"+fromDate+"', '"+dateFormat+"')");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");
		} else {
			queryAdvisor.addVariable("conFromDate", "");
		}

		if (CommonUtil.isNotBlank(toDate)) {
			Date dateFrom = CommonUtil.toDate(fromDate, dateFormat);
			thisYear = CommonUtil.toString(dateFrom, "yyyy");
			lastYear = CommonUtil.toString(CommonUtil.toInt(thisYear) - 1);

			queryAdvisor.addVariable("thisYear", thisYear);
			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+thisYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "");
			queryAdvisor.addVariable("conToDate", "and trunc(ubta.proc_date) <= to_date('"+toDate+"', '"+dateFormat+"')");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");
		} else {
			queryAdvisor.addVariable("conToDate", "");
		}

		if (CommonUtil.isNotBlank(quarterName)) {
			if (CommonUtil.isBlank(selectedFinancialYear)) {
				selectedFinancialYear = defaultFinancialYear;
			}

			lastYear = CommonUtil.toString(CommonUtil.toInt(selectedFinancialYear) - 1);

			queryAdvisor.addVariable("thisYear", selectedFinancialYear);
			queryAdvisor.addVariable("conQuarterName", "and quarter_name = '"+quarterName+"'");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");
		} else {
			queryAdvisor.addVariable("conQuarterName", "");
		}

		if (CommonUtil.isNotBlank(selectedFinancialYear)) {
			lastYear = CommonUtil.toString(CommonUtil.toInt(selectedFinancialYear) - 1);

			queryAdvisor.addVariable("thisYear", selectedFinancialYear);
			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+selectedFinancialYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");
		}

		if (CommonUtil.isBlank(selectedFinancialYear + quarterName + fromDate + toDate)) {
			lastYear = CommonUtil.toString(CommonUtil.toInt(defaultFinancialYear) - 1);

			queryAdvisor.addVariable("thisYear", selectedFinancialYear);
			queryAdvisor.addVariable("conFinancialYear", "and period_year = '"+defaultFinancialYear+"'");
			queryAdvisor.addVariable("conFinancialYearPeriod", "and trunc(ubta.proc_date) between trunc(fy.date_from) and trunc(fy.date_to)");
			queryAdvisor.addVariable("conLastFinancialYear", "and period_year = '"+lastYear+"'");

			queryAdvisor.addVariable("conFromDate", "");
			queryAdvisor.addVariable("conToDate", "");
			queryAdvisor.addVariable("conQuarterName", "");
		}

		// Income
		queryAdvisor.setVariable("datasetName", "Income");
		queryAdvisor.setVariable("mainCategoryName", "Income");
		queryAdvisor.setVariable("additionalCondition", "");
		dsRtn = selectAsDataSet(queryAdvisor, "query.Report.getProfitAndLossDetailByCategory");

		totInSep = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_SEP"));
		totInDec = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_DEC"));
		totInMar = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_MAR"));
		totInJun = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_JUN"));
		totInThisYear = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "THIS_YEAR_PROC_AMT"));
		totInLastYear = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "LAST_YEAR_PROC_AMT"));

		// Less
		queryAdvisor.setVariable("datasetName", "Purchase");
		queryAdvisor.setVariable("accountCode", "270"); // Purchase
		dsTemp = selectAsDataSet(queryAdvisor, "query.Report.getProfitAndLossDetailByAccntCode");
		if (dsTemp.getRowCnt() > 0) {
			dsRtn.addRow();
			dsRtn.setValue(dsRtn.getRowCnt()-1, "CATEGORY_NAME", "");

			dsRtn.addRow();
			dsRtn.setValue(dsRtn.getRowCnt()-1, "DATASET_NAME", "LESS : COST OF GOODS SOLD");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "DIV", "0");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "ROOT", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PARENT_CATEGORY_ID", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "CATEGORY_ID", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "CATEGORY_NAME", "LESS : COST OF GOODS SOLD");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "ACCOUNT_CODE", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "SORT_ORDER", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_SEP", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_DEC", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_MAR", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_JUN", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "THIS_YEAR_PROC_AMT", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "LAST_YEAR_PROC_AMT", "");

			for (int i=0; i<dsTemp.getRowCnt(); i++) {
				dsTemp.setValue(i, "PROC_AMT_SEP", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "PROC_AMT_SEP")))));
				dsTemp.setValue(i, "PROC_AMT_DEC", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "PROC_AMT_DEC")))));
				dsTemp.setValue(i, "PROC_AMT_MAR", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "PROC_AMT_MAR")))));
				dsTemp.setValue(i, "PROC_AMT_JUN", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "PROC_AMT_JUN")))));
				dsTemp.setValue(i, "THIS_YEAR_PROC_AMT", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "THIS_YEAR_PROC_AMT")))));
				dsTemp.setValue(i, "LAST_YEAR_PROC_AMT", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "LAST_YEAR_PROC_AMT")))));
			}
			dsRtn.merge(dsTemp);

			totPuSep = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_SEP"));
			totPuDec = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_DEC"));
			totPuMar = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_MAR"));
			totPuJun = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_JUN"));
			totPuThisYear = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "THIS_YEAR_PROC_AMT"));
			totPuLastYear = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "LAST_YEAR_PROC_AMT"));

			dsRtn.addRow();
			dsRtn.setValue(dsRtn.getRowCnt()-1, "DATASET_NAME", "GROSS PROFIT FROM TRADING");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "DIV", "7");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "ROOT", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PARENT_CATEGORY_ID", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "CATEGORY_ID", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "CATEGORY_NAME", "GROSS PROFIT FROM TRADING");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "ACCOUNT_CODE", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "SORT_ORDER", "");
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_SEP", totInSep - totPuSep);
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_DEC", totInDec - totPuDec);
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_MAR", totInMar - totPuMar);
			dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_JUN", totInJun - totPuJun);
			dsRtn.setValue(dsRtn.getRowCnt()-1, "THIS_YEAR_PROC_AMT", totInThisYear - totPuThisYear);
			dsRtn.setValue(dsRtn.getRowCnt()-1, "LAST_YEAR_PROC_AMT", totInLastYear - totPuLastYear);

			dsRtn.addRow();
			dsRtn.setValue(dsRtn.getRowCnt()-1, "CATEGORY_NAME", "");
		}

		// Expense
		queryAdvisor.setVariable("datasetName", "Expense");
		queryAdvisor.setVariable("mainCategoryName", "Expense");
		queryAdvisor.setVariable("additionalCondition", "and (src.account_code <> '270' or src.account_code is null)");
		dsTemp = selectAsDataSet(queryAdvisor, "query.Report.getProfitAndLossDetailByCategory");

		for (int i=0; i<dsTemp.getRowCnt(); i++) {
			dsTemp.setValue(i, "PROC_AMT_SEP", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "PROC_AMT_SEP")))));
			dsTemp.setValue(i, "PROC_AMT_DEC", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "PROC_AMT_DEC")))));
			dsTemp.setValue(i, "PROC_AMT_MAR", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "PROC_AMT_MAR")))));
			dsTemp.setValue(i, "PROC_AMT_JUN", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "PROC_AMT_JUN")))));
			dsTemp.setValue(i, "THIS_YEAR_PROC_AMT", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "THIS_YEAR_PROC_AMT")))));
			dsTemp.setValue(i, "LAST_YEAR_PROC_AMT", CommonUtil.toString(CommonUtil.abs(CommonUtil.toDouble(dsTemp.getValue(i, "LAST_YEAR_PROC_AMT")))));
		}
		dsRtn.merge(dsTemp);

		totExSep = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_SEP"));
		totExDec = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_DEC"));
		totExMar = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_MAR"));
		totExJun = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "PROC_AMT_JUN"));
		totExThisYear = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "THIS_YEAR_PROC_AMT"));
		totExLastYear = CommonUtil.toDouble(dsRtn.getValue(dsRtn.getRowCnt()-1, "LAST_YEAR_PROC_AMT"));

		dsRtn.addRow();
		dsRtn.setValue(dsRtn.getRowCnt()-1, "DATASET_NAME", "(Loss) Profit before income tax");
		dsRtn.setValue(dsRtn.getRowCnt()-1, "DIV", "7");
		dsRtn.setValue(dsRtn.getRowCnt()-1, "ROOT", "");
		dsRtn.setValue(dsRtn.getRowCnt()-1, "PARENT_CATEGORY_ID", "");
		dsRtn.setValue(dsRtn.getRowCnt()-1, "CATEGORY_ID", "");
		dsRtn.setValue(dsRtn.getRowCnt()-1, "CATEGORY_NAME", "(Loss) Profit before income tax");
		dsRtn.setValue(dsRtn.getRowCnt()-1, "ACCOUNT_CODE", "");
		dsRtn.setValue(dsRtn.getRowCnt()-1, "SORT_ORDER", "");
		dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_SEP", totInSep - totPuSep - totExSep);
		dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_DEC", totInDec - totPuDec - totExDec);
		dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_MAR", totInMar - totPuMar - totExMar);
		dsRtn.setValue(dsRtn.getRowCnt()-1, "PROC_AMT_JUN", totInJun - totPuJun - totExJun);
		dsRtn.setValue(dsRtn.getRowCnt()-1, "THIS_YEAR_PROC_AMT", totInThisYear - totPuThisYear - totExThisYear);
		dsRtn.setValue(dsRtn.getRowCnt()-1, "LAST_YEAR_PROC_AMT", totInLastYear - totPuLastYear - totExLastYear);

		return dsRtn;
	}
}