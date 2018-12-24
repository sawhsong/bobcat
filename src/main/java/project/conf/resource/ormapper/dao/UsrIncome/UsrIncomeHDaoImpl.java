/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_INCOME - Income Entry (Sales Income and Other Income for Org Category A, Other Income for Org Category B/C/D)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrIncome;

import project.common.extend.BaseHDao;
import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dto.oracle.UsrIncome;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrIncomeHDaoImpl extends BaseHDao implements UsrIncomeDao {
	public DataSet getSalesIncomeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String recordKeepingType = requestDataSet.getValue("recordKeepingType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(recordKeepingType, "uin.record_keeping_type(+) = '"+recordKeepingType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("incomeEntryType", CommonCodeManager.getCodeByConstants("INCOME_ENTRY_TYPE_SALES"));
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("src.quarter_date desc");

		return selectAsDataSet(queryAdvisor, "query.UsrIncome.getSalesIncomeDataSetByCriteria");
	}

	public DataSet getOtherIncomeDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String orgCategory = (String)queryAdvisor.getObject("orgCategory");
		String financialYear = requestDataSet.getValue("financialYear");
		String incomeTypeId = requestDataSet.getValue("incomeType");
		String quarterName = requestDataSet.getValue("quarterName");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addVariable("orgId", orgId);
		if (CommonUtil.equalsIgnoreCase(orgCategory, "A")) {
			queryAdvisor.addVariable("incomeEntryType", CommonCodeManager.getCodeByConstants("INCOME_ENTRY_TYPE_OTHTA"));
		} else {
			queryAdvisor.addVariable("incomeEntryType", CommonCodeManager.getCodeByConstants("INCOME_ENTRY_TYPE_OTHETC"));
		}
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(incomeTypeId, "uin.income_type_id = '"+incomeTypeId+"'");
		queryAdvisor.addOrderByClause("uin.income_date desc");

		return selectAsDataSet(queryAdvisor, "query.UsrIncome.getOtherIncomeDataSetByCriteria");
	}

	public DataSet getIncomeSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		String orgCategory = (String)queryAdvisor.getObject("orgCategory");

		if (CommonUtil.equalsIgnoreCase(orgCategory, "A")) {
			return selectAsDataSet(queryAdvisor, "query.UsrIncome.getIncomeSummaryDataSetForCategoryA");
		} else {
			return selectAsDataSet(queryAdvisor, "query.UsrIncome.getIncomeSummaryDataSetForCategoryEtc");
		}
	}

	public DataSet getIncomePerformanceDataSet(String orgCategory, String orgId, String financialYear, String quarterName) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addVariable("orgCategory", orgCategory);
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);

		if (CommonUtil.equalsIgnoreCase(orgCategory, "A")) {
			return selectAsDataSet(queryAdvisor, "query.UsrIncome.getIncomePerformanceDataSetForCategoryA");
		} else {
			return selectAsDataSet(queryAdvisor, "query.UsrIncome.getIncomePerformanceDataSetForCategoryEtc");
		}
	}

	public DataSet getIncomeDataSetById(String incomeId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("income_id = '"+incomeId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrIncome());
	}
}