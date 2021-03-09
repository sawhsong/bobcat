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
}