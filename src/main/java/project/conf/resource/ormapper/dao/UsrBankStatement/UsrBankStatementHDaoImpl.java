/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_BANK_STATEMENT - Bank statement master info which is uploaded by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankStatement;

import project.common.extend.BaseHDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrBankStatementHDaoImpl extends BaseHDao implements UsrBankStatementDao {
	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String userId = (String)queryAdvisor.getObject("userId");
		String bankAccntId = (String)queryAdvisor.getObject("bankAccntId");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(userId, "bat.user_id = '"+userId+"'");
		queryAdvisor.addAutoFillCriteria(bankAccntId, "bsm.bank_accnt_id = '"+bankAccntId+"'");
		queryAdvisor.addAutoFillCriteria(fromDate, "trunc(nvl(bsm.update_date, bsm.insert_date)) >= trunc(to_date('"+fromDate+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(toDate, "trunc(nvl(bsm.update_date, bsm.insert_date)) <= trunc(to_date('"+toDate+"', '"+dateFormat+"'))");

		queryAdvisor.addOrderByClause("nvl(bsm.update_date, bsm.insert_date) desc, bat.bank_name");

		return selectAsDataSet(queryAdvisor, "query.UsrBankStatement.getDataSetBySearchCriteria");
	}
}