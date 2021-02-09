/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_BANK_STATEMENT_D - Bank statement details
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankStatementD;

import project.common.extend.BaseHDao;
import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dto.oracle.UsrBankStatement;
import project.conf.resource.ormapper.dto.oracle.UsrBankStatementD;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrBankStatementDHDaoImpl extends BaseHDao implements UsrBankStatementDDao {
	public int insert(UsrBankStatement usrBankStatement, DataSet bankFileData) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		int result = 0;

		for (int i=0; i<bankFileData.getRowCnt(); i++) {
			UsrBankStatementD usrBankStatementD = new UsrBankStatementD();

			usrBankStatementD.setBankStatementDId(CommonUtil.uid());
			usrBankStatementD.setBankStatementId(usrBankStatement.getBankStatementId());
			usrBankStatementD.setRowIndex(CommonUtil.toDouble(bankFileData.getValue(i, "ROW_INDEX")));
			usrBankStatementD.setProcDate(CommonUtil.toDate(bankFileData.getValue(i, "PROC_DATE"), dateFormat));
			usrBankStatementD.setProcAmt(CommonUtil.toDouble(bankFileData.getValue(i, "PROC_AMOUNT")));
			usrBankStatementD.setProcDescription(bankFileData.getValue(i, "DESCRIPTION"));
			usrBankStatementD.setBalance(CommonUtil.toDouble(bankFileData.getValue(i, "BALANCE")));
			usrBankStatementD.setUserDescription("");
			usrBankStatementD.setIsAllocated(CommonCodeManager.getCodeByConstants("SIMPLE_YN_N"));
			usrBankStatementD.setInsertUserId(bankFileData.getValue(i, "USER_ID"));
			usrBankStatementD.setInsertDate(CommonUtil.getSysdateAsDate());

			result += insertWithDto(usrBankStatementD);
		}

		return result;
	}

	public int deleteByBankStatementId(String bankStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;

		queryAdvisor.addWhereClause("bank_statement_id = '"+bankStatementId+"'");
		result = deleteWithSQLQuery(queryAdvisor, new UsrBankStatementD());

		return result;
	}

	public DataSet getDataSetByBankStatementId(String bankStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bank_statement_id = '"+bankStatementId+"'");
		queryAdvisor.addOrderByClause("row_index");
		return selectAllAsDataSet(queryAdvisor, new UsrBankStatementD());
	}

	public DataSet getDataSetByFileDataForDupCheck(QueryAdvisor queryAdvisor) throws Exception {
		return selectAllAsDataSet(queryAdvisor, new UsrBankStatementD());
	}
}