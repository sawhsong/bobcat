/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_BANK_STATEMENT_D - Bank statement details
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankStatementD;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseHDao;
import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.UsrBsTranAlloc.UsrBsTranAllocDao;
import project.conf.resource.ormapper.dto.oracle.UsrBankStatement;
import project.conf.resource.ormapper.dto.oracle.UsrBankStatementD;
import project.conf.resource.ormapper.dto.oracle.UsrBsTranAlloc;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrBankStatementDHDaoImpl extends BaseHDao implements UsrBankStatementDDao {
	@Autowired
	private UsrBsTranAllocDao usrBsTranAllocDao;

	public int insert(UsrBankStatement usrBankStatement, DataSet bankFileData) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		int result = 0;

		for (int i=0; i<bankFileData.getRowCnt(); i++) {
			String uid = CommonUtil.uid();
			UsrBankStatementD usrBankStatementD = new UsrBankStatementD();
			UsrBsTranAlloc usrBsTranAlloc = new UsrBsTranAlloc();

			usrBankStatementD.setBankStatementDId(uid);
			usrBankStatementD.setBankStatementId(usrBankStatement.getBankStatementId());
			usrBankStatementD.setRowIndex(CommonUtil.toDouble(bankFileData.getValue(i, "ROW_INDEX")));
			usrBankStatementD.setProcDate(CommonUtil.toDate(bankFileData.getValue(i, "PROC_DATE"), dateFormat));
			usrBankStatementD.setProcAmt(CommonUtil.toDouble(bankFileData.getValue(i, "PROC_AMOUNT")));
			usrBankStatementD.setProcDescription(bankFileData.getValue(i, "DESCRIPTION"));
			usrBankStatementD.setBalance(CommonUtil.toDouble(bankFileData.getValue(i, "BALANCE")));
			usrBankStatementD.setUserDescription("");
			usrBankStatementD.setBankAccount(bankFileData.getValue(i, "BANK_ACCOUNT"));
			usrBankStatementD.setDebitAmt(CommonUtil.toDouble(bankFileData.getValue(i, "DEBIT_AMOUNT")));
			usrBankStatementD.setCreditAmt(CommonUtil.toDouble(bankFileData.getValue(i, "CREDIT_AMOUNT")));
			usrBankStatementD.setCategory(bankFileData.getValue(i, "CATEGORIES"));
			usrBankStatementD.setSerial(bankFileData.getValue(i, "SERIAL"));
			usrBankStatementD.setInsertUserId(bankFileData.getValue(i, "USER_ID"));
			usrBankStatementD.setInsertDate(CommonUtil.getSysdateAsDate());

			usrBsTranAlloc.setBsTranAllocId(CommonUtil.uid());
			usrBsTranAlloc.setBankAccntId(usrBankStatement.getBankAccntId());
			usrBsTranAlloc.setBankStatementDId(uid);
			usrBsTranAlloc.setBankStatementId(usrBankStatement.getBankStatementId());
			usrBsTranAlloc.setUserId(bankFileData.getValue(i, "USER_ID"));
			usrBsTranAlloc.setRowIndex(CommonUtil.toDouble(bankFileData.getValue(i, "ROW_INDEX")));
			usrBsTranAlloc.setProcDate(CommonUtil.toDate(bankFileData.getValue(i, "PROC_DATE"), dateFormat));
			usrBsTranAlloc.setProcAmt(CommonUtil.toDouble(bankFileData.getValue(i, "PROC_AMOUNT")));
			usrBsTranAlloc.setProcDescription(bankFileData.getValue(i, "DESCRIPTION"));
			usrBsTranAlloc.setBalance(CommonUtil.toDouble(bankFileData.getValue(i, "BALANCE")));
			usrBsTranAlloc.setUserDescription("");
			usrBsTranAlloc.setStatus(CommonCodeManager.getCodeByConstants("BS_TRAN_ALLOC_STATUS_UP"));
			usrBsTranAlloc.setInsertUserId(bankFileData.getValue(i, "USER_ID"));
			usrBsTranAlloc.setInsertDate(CommonUtil.getSysdateAsDate());

			result += insertWithDto(usrBankStatementD);
			result += usrBsTranAllocDao.insert(usrBsTranAlloc);
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
}