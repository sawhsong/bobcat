/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_CC_STATEMENT_D - Credit card statement details
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrCcStatementD;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseHDao;
import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.UsrCcAlloc.UsrCcAllocDao;
import project.conf.resource.ormapper.dto.oracle.UsrCcAlloc;
import project.conf.resource.ormapper.dto.oracle.UsrCcStatement;
import project.conf.resource.ormapper.dto.oracle.UsrCcStatementD;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrCcStatementDHDaoImpl extends BaseHDao implements UsrCcStatementDDao {
	@Autowired
	private UsrCcAllocDao usrCcAllocDao;

	public int insert(UsrCcStatement usrCcStatement, DataSet ccFileData) throws Exception {
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		int result = 0;

		for (int i=0; i<ccFileData.getRowCnt(); i++) {
			String uid = CommonUtil.uid();
			UsrCcStatementD usrCcStatementD = new UsrCcStatementD();
			UsrCcAlloc usrCcAlloc = new UsrCcAlloc();

			usrCcStatementD.setCcStatementDId(uid);
			usrCcStatementD.setCcStatementId(usrCcStatement.getCcStatementId());
			usrCcStatementD.setRowIndex(CommonUtil.toDouble(ccFileData.getValue(i, "ROW_INDEX")));
			usrCcStatementD.setProcDate(CommonUtil.toDate(ccFileData.getValue(i, "PROC_DATE"), dateFormat));
			usrCcStatementD.setProcAmt(CommonUtil.toDouble(ccFileData.getValue(i, "PROC_AMOUNT")));
			usrCcStatementD.setProcDescription(ccFileData.getValue(i, "DESCRIPTION"));
			usrCcStatementD.setUserDescription("");
			usrCcStatementD.setBankAccount(ccFileData.getValue(i, "BANK_ACCOUNT"));
			usrCcStatementD.setDebitAmt(CommonUtil.toDouble(ccFileData.getValue(i, "DEBIT_AMOUNT")));
			usrCcStatementD.setCreditAmt(CommonUtil.toDouble(ccFileData.getValue(i, "CREDIT_AMOUNT")));
			usrCcStatementD.setCategory(ccFileData.getValue(i, "CATEGORIES"));
			usrCcStatementD.setSerial(ccFileData.getValue(i, "SERIAL"));
			usrCcStatementD.setInsertUserId(ccFileData.getValue(i, "USER_ID"));
			usrCcStatementD.setInsertDate(CommonUtil.getSysdateAsDate());

			usrCcAlloc.setCcAllocId(CommonUtil.uid());
			usrCcAlloc.setBankAccntId(usrCcStatement.getBankAccntId());
			usrCcAlloc.setCcStatementDId(uid);
			usrCcAlloc.setCcStatementId(usrCcStatement.getCcStatementId());
			usrCcAlloc.setUserId(ccFileData.getValue(i, "USER_ID"));
			usrCcAlloc.setRowIndex(CommonUtil.toDouble(ccFileData.getValue(i, "ROW_INDEX")));
			usrCcAlloc.setProcDate(CommonUtil.toDate(ccFileData.getValue(i, "PROC_DATE"), dateFormat));
			usrCcAlloc.setProcAmt(CommonUtil.toDouble(ccFileData.getValue(i, "PROC_AMOUNT")));
			usrCcAlloc.setNetAmt(CommonUtil.toDouble(ccFileData.getValue(i, "PROC_AMOUNT")));
			usrCcAlloc.setProcDescription(ccFileData.getValue(i, "DESCRIPTION"));
			usrCcAlloc.setBalance(CommonUtil.toDouble(ccFileData.getValue(i, "BALANCE")));
			usrCcAlloc.setUserDescription("");
			usrCcAlloc.setStatus(CommonCodeManager.getCodeByConstants("CC_ALLOC_STATUS_UP"));
			usrCcAlloc.setInsertUserId(ccFileData.getValue(i, "USER_ID"));
			usrCcAlloc.setInsertDate(CommonUtil.getSysdateAsDate());

			result += insertWithDto(usrCcStatementD);
			result += usrCcAllocDao.insert(usrCcAlloc);
		}

		return result;
	}

	public int deleteByCcStatementId(String ccStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;

		queryAdvisor.addWhereClause("cc_statement_id = '"+ccStatementId+"'");
		result = deleteWithSQLQuery(queryAdvisor, new UsrCcStatementD());

		return result;
	}

	public DataSet getDataSetByCcStatementId(String ccStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("cc_statement_id = '"+ccStatementId+"'");
		queryAdvisor.addOrderByClause("row_index");
		return selectAllAsDataSet(queryAdvisor, new UsrCcStatementD());
	}
}