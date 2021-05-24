/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_CC_STATEMENT - Credit card statement master info which is uploaded by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrCcStatement;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dao.UsrCcStatementD.UsrCcStatementDDao;
import project.conf.resource.ormapper.dto.oracle.UsrCcStatement;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.FileUtil;

public class UsrCcStatementHDaoImpl extends BaseHDao implements UsrCcStatementDao {
	@Autowired
	private UsrCcStatementDDao usrCcStatementDDao;

	public int insert(UsrCcStatement usrCcStatement, DataSet ccFileData) throws Exception {
		int result = -1;

		result = insertWithSQLQuery(usrCcStatement);
		result += usrCcStatementDDao.insert(usrCcStatement, ccFileData);

		return result;
	}

	public int delete(String ccStatementIds[]) throws Exception {
		int result = 0;

		if (!(ccStatementIds == null || ccStatementIds.length == 0)) {
			for (String id : ccStatementIds) {
				result += delete(id);
			}
		}
		return result;
	}

	public int delete(String ccStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		UsrCcStatement usrCcStatement = new UsrCcStatement();
		String filePathPathAndName = "";
		int result = 0;

		usrCcStatementDDao.deleteByCcStatementId(ccStatementId);

		usrCcStatement = getByCcStatementId(ccStatementId);
		filePathPathAndName = usrCcStatement.getRepositoryPath()+"/"+usrCcStatement.getNewName();

		queryAdvisor.addWhereClause("cc_statement_id = '"+ccStatementId+"'");

		result = deleteWithSQLQuery(queryAdvisor, new UsrCcStatement());
		if (result > 0) {
			FileUtil.deleteQuietly(new File(filePathPathAndName));
		}

		return result;
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String ccStatementId = (String)queryAdvisor.getObject("ccStatementId");
		String userId = (String)queryAdvisor.getObject("userId");
		String bankAccntId = (String)queryAdvisor.getObject("bankAccntId");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(ccStatementId, "csm.cc_statement_id = '"+ccStatementId+"'");
		queryAdvisor.addAutoFillCriteria(userId, "bat.user_id = '"+userId+"'");
		queryAdvisor.addAutoFillCriteria(bankAccntId, "csm.bank_accnt_id = '"+bankAccntId+"'");
		queryAdvisor.addAutoFillCriteria(fromDate, "trunc(nvl(csm.update_date, csm.insert_date)) >= trunc(to_date('"+fromDate+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(toDate, "trunc(nvl(csm.update_date, csm.insert_date)) <= trunc(to_date('"+toDate+"', '"+dateFormat+"'))");

		queryAdvisor.addOrderByClause("nvl(csm.update_date, csm.insert_date) desc, bat.bank_name");

		return selectAsDataSet(queryAdvisor, "query.UsrCcStatement.getDataSetBySearchCriteria");
	}

	public UsrCcStatement getByCcStatementId(String ccStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("cc_statement_id = '"+ccStatementId+"'");
		return (UsrCcStatement)selectAllToDto(queryAdvisor, new UsrCcStatement());
	}

	public DataSet getDataSetByCcStatementId(String ccStatementId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("cc_statement_id = '"+ccStatementId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrCcStatement());
	}
}