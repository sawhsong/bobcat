/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_BS_TRAN_ALLOC - Bank statement transaction allocation - transaction reconcilisation
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBsTranAlloc;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrBsTranAlloc;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrBsTranAllocHDaoImpl extends BaseHDao implements UsrBsTranAllocDao {
	public int insert(UsrBsTranAlloc usrBsTranAlloc) throws Exception {
		return insertWithDto(usrBsTranAlloc);
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String allocationStatus = (String)queryAdvisor.getObject("allocationStatus");
		String userId = (String)queryAdvisor.getObject("userId");
		String fromDate = (String)queryAdvisor.getObject("fromDate");
		String toDate = (String)queryAdvisor.getObject("toDate");

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(allocationStatus, "bta.status = '"+allocationStatus+"'");
		queryAdvisor.addAutoFillCriteria(userId, "bta.user_id = '"+userId+"'");
		queryAdvisor.addAutoFillCriteria(fromDate, "trunc(nvl(bta.update_date, bta.insert_date)) >= trunc(to_date('"+fromDate+"', '"+dateFormat+"'))");
		queryAdvisor.addAutoFillCriteria(toDate, "trunc(nvl(bta.update_date, bta.insert_date)) <= trunc(to_date('"+toDate+"', '"+dateFormat+"'))");

		queryAdvisor.addOrderByClause("nvl(bta.update_date, bta.insert_date) desc, bta.proc_date desc");

		return selectAsDataSet(queryAdvisor, "query.UsrBsTranAlloc.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetByFileDataForDupCheck(QueryAdvisor queryAdvisor) throws Exception {
		return selectAllAsDataSet(queryAdvisor, new UsrBsTranAlloc());
	}
}