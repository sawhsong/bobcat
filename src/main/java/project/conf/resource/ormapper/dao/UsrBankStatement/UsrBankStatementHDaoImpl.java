/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_BANK_STATEMENT - Bank statement master info which is uploaded by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankStatement;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dao.UsrBankAccnt.UsrBankAccntDao;
import project.conf.resource.ormapper.dao.UsrBankStatementD.UsrBankStatementDDao;
import project.conf.resource.ormapper.dto.oracle.UsrBankAccnt;
import project.conf.resource.ormapper.dto.oracle.UsrBankStatement;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.FileUtil;

public class UsrBankStatementHDaoImpl extends BaseHDao implements UsrBankStatementDao {
	@Autowired
	private UsrBankStatementDDao usrBankStatementDDao;
	@Autowired
	private UsrBankAccntDao usrBankAccntDao;

	public int insert(UsrBankStatement usrBankStatement, DataSet bankFileData) throws Exception {
		UsrBankAccnt usrBankAccnt = new UsrBankAccnt();
		int result = -1;

		result = insertWithSQLQuery(usrBankStatement);
		result += usrBankStatementDDao.insert(usrBankStatement, bankFileData);

		usrBankAccnt.setBalance(CommonUtil.toDouble(bankFileData.getValue(0, "BALANCE")));
		usrBankAccnt.setUpdateUserId(usrBankStatement.getInsertUserId());
		usrBankAccnt.setUpdateDate(CommonUtil.getSysdateAsDate());
		usrBankAccnt.addUpdateColumnFromField();
		result += usrBankAccntDao.update(usrBankStatement.getBankAccntId(), usrBankAccnt);

		return result;
	}

	public int delete(String bankStatementIds[]) throws Exception {
		int result = 0;

		if (!(bankStatementIds == null || bankStatementIds.length == 0)) {
			for (String id : bankStatementIds) {
				result += delete(id);
			}
		}
		return result;
	}

	public int delete(String bankStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		UsrBankStatement usrBankStatement = new UsrBankStatement();
		String filePathPathAndName = "";
		int result = 0;

		usrBankStatementDDao.deleteByBankStatementId(bankStatementId);

		usrBankStatement = getByBankStatementId(bankStatementId);
		filePathPathAndName = usrBankStatement.getRepositoryPath()+"/"+usrBankStatement.getNewName();

		queryAdvisor.addWhereClause("bank_statement_id = '"+bankStatementId+"'");

		result = deleteWithSQLQuery(queryAdvisor, new UsrBankStatement());
		if (result > 0) {
			FileUtil.deleteQuietly(new File(filePathPathAndName));
		}

		return result;
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String bankStatementId = (String)queryAdvisor.getObject("bankStatementId");
		String userId = (String)queryAdvisor.getObject("userId");
		String bankAccntId = (String)queryAdvisor.getObject("bankAccntId");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(bankStatementId, "bsm.bank_statement_id = '"+bankStatementId+"'");
		queryAdvisor.addAutoFillCriteria(userId, "bat.user_id = '"+userId+"'");
		queryAdvisor.addAutoFillCriteria(bankAccntId, "bsm.bank_accnt_id = '"+bankAccntId+"'");
		queryAdvisor.addAutoFillCriteria(fromDate, "trunc(nvl(bsm.update_date, bsm.insert_date)) >= trunc(to_date('"+fromDate+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(toDate, "trunc(nvl(bsm.update_date, bsm.insert_date)) <= trunc(to_date('"+toDate+"', '"+dateFormat+"'))");

		queryAdvisor.addOrderByClause("nvl(bsm.update_date, bsm.insert_date) desc, bat.bank_name");

		return selectAsDataSet(queryAdvisor, "query.UsrBankStatement.getDataSetBySearchCriteria");
	}

	public UsrBankStatement getByBankStatementId(String bankStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bank_statement_id = '"+bankStatementId+"'");
		return (UsrBankStatement)selectAllToDto(queryAdvisor, new UsrBankStatement());
	}

	public DataSet getDataSetByBankStatementId(String bankStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bank_statement_id = '"+bankStatementId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrBankStatement());
	}
}