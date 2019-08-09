/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_FINANCE - Financial Loan Related Data Entry (Repayment / Borrowing / Lending)
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrFinance;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseHDao;
import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.UsrFinanceFile.UsrFinanceFileDao;
import project.conf.resource.ormapper.dto.oracle.UsrFinance;
import zebra.base.Dto;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.ConfigUtil;

public class UsrFinanceHDaoImpl extends BaseHDao implements UsrFinanceDao {
	@Autowired
	private UsrFinanceFileDao usrFinanceFileDao;

	public int insert(Dto dto) throws Exception {
		return insertWithSQLQuery(dto);
	}

	public int insert(Dto dto, DataSet fileDataSet) throws Exception {
		insertWithSQLQuery(dto);
		usrFinanceFileDao.insert((UsrFinance)dto, fileDataSet);
		return 1;
	}

	public int update(String financeId, Dto dto) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("finance_id = '"+financeId+"'");
		return updateWithSQLQuery(queryAdvisor, dto);
	}

	public int update(String financeId, Dto dto, DataSet fileDataSet) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("finance_id = '"+financeId+"'");
		updateWithSQLQuery(queryAdvisor, dto);
		usrFinanceFileDao.update(financeId, (UsrFinance)dto, fileDataSet);
		return 1;
	}

	public int exeCompleteByFinanceIds(String financeIds[]) throws Exception {
		QueryAdvisor queryAdvisor;
		UsrFinance usrFinance;
		int result = 0;

		for (int i=0; i<financeIds.length; i++) {
			usrFinance = new UsrFinance();
			queryAdvisor = new QueryAdvisor();

			usrFinance.addUpdateColumn("is_completed", "Y");
			queryAdvisor.addWhereClause("finance_id = '"+financeIds[i]+"'");

			result += updateColumns(queryAdvisor, usrFinance);
		}

		return result;
	}

	public int deleteByFinanceIds(String financeIds[]) throws Exception {
		QueryAdvisor queryAdvisor;
		int result = 0;

		for (int i=0; i<financeIds.length; i++) {
			queryAdvisor = new QueryAdvisor();
			queryAdvisor.addWhereClause("finance_id = '"+financeIds[i]+"'");

			result += usrFinanceFileDao.deleteByFinanceId(financeIds[i]);
			result += deleteWithSQLQuery(queryAdvisor, new UsrFinance());
		}

		return result;
	}

	public DataSet getRepaymentSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getRepaymentSummaryDataSet");
	}

	public DataSet getRepaymentDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String repaymentType = requestDataSet.getValue("repaymentType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(repaymentType, "srt.repayment_type = '"+repaymentType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addVariable("financeEntryType", CommonCodeManager.getCodeByConstants("FINANCE_ENTRY_TYPE_REP"));
		queryAdvisor.addOrderByClause("ufn.finance_date desc, srt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getRepaymentDataSetByCriteria");
	}

	public DataSet getRepaymentDataSetByFinanceIdForUpdate(String financeId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addWhereClause("ufn.finance_id = '"+financeId+"'");
		queryAdvisor.addOrderByClause("ufn.finance_date desc, srt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getRepaymentDataSetByFinanceIdForUpdate");
	}

	public DataSet getBorrowingSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getBorrowingSummaryDataSet");
	}

	public DataSet getBorrowingDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String borrowingType = requestDataSet.getValue("borrowingType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(borrowingType, "sbt.borrowing_type = '"+borrowingType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addVariable("financeEntryType", CommonCodeManager.getCodeByConstants("FINANCE_ENTRY_TYPE_BOR"));
		queryAdvisor.addOrderByClause("ufn.finance_date desc, sbt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getBorrowingDataSetByCriteria");
	}

	public DataSet getBorrowingDataSetByFinanceIdForUpdate(String financeId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addWhereClause("ufn.finance_id = '"+financeId+"'");
		queryAdvisor.addOrderByClause("ufn.finance_date desc, sbt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getBorrowingDataSetByFinanceIdForUpdate");
	}

	public DataSet getLendingSummaryDataSet(QueryAdvisor queryAdvisor) throws Exception {
		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getLendingSummaryDataSet");
	}

	public DataSet getLendingDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String orgId = (String)queryAdvisor.getObject("orgId");
		String financialYear = requestDataSet.getValue("financialYear");
		String quarterName = requestDataSet.getValue("quarterName");
		String lendingType = requestDataSet.getValue("lendingType");
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addAutoFillCriteria(lendingType, "slt.lending_type = '"+lendingType+"'");
		queryAdvisor.addVariable("orgId", orgId);
		queryAdvisor.addVariable("financialYear", financialYear);
		queryAdvisor.addVariable("quarterName", quarterName);
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addVariable("financeEntryType", CommonCodeManager.getCodeByConstants("FINANCE_ENTRY_TYPE_LEN"));
		queryAdvisor.addOrderByClause("ufn.finance_date desc, slt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getLendingDataSetByCriteria");
	}

	public DataSet getLendingDataSetByFinanceIdForUpdate(String financeId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String dateFormat = ConfigUtil.getProperty("format.date.java");

		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addWhereClause("ufn.finance_id = '"+financeId+"'");
		queryAdvisor.addOrderByClause("ufn.finance_date desc, slt.sort_order");

		return selectAsDataSet(queryAdvisor, "query.UsrFinance.getLendingDataSetByFinanceIdForUpdate");
	}

	public UsrFinance getFinanceById(String financeId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();

		queryAdvisor.addWhereClause("finance_id = '"+financeId+"'");
		return (UsrFinance)selectAllToDto(queryAdvisor, new UsrFinance());
	}
}