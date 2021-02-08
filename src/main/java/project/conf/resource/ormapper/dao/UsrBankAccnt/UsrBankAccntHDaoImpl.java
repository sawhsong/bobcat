/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - USR_BANK_ACCNT - Bank account info by user
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrBankAccnt;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.UsrBankAccnt;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class UsrBankAccntHDaoImpl extends BaseHDao implements UsrBankAccntDao {
	public int insertOrUpdate(DataSet bankAccntsDataSetToSave, String loggedinUserId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;

		try {
			for (int i=0; i<bankAccntsDataSetToSave.getRowCnt(); i++) {
				UsrBankAccnt usrBankAccnt = new UsrBankAccnt();
				String bankAccntId = bankAccntsDataSetToSave.getValue(i, "BANK_ACCNT_ID");

				usrBankAccnt.setBsb(bankAccntsDataSetToSave.getValue(i, "BSB"));
				usrBankAccnt.setAccntNumber(bankAccntsDataSetToSave.getValue(i, "ACCNT_NUMBER"));
				usrBankAccnt.setAccntName(bankAccntsDataSetToSave.getValue(i, "ACCNT_NAME"));
				usrBankAccnt.setBalance(CommonUtil.toDouble(bankAccntsDataSetToSave.getValue(i, "BALANCE")));
				usrBankAccnt.setDescription(bankAccntsDataSetToSave.getValue(i, "DESCRIPTION"));

				if (CommonUtil.isBlank(bankAccntId)) {
					usrBankAccnt.setBankAccntId(CommonUtil.uid());
					usrBankAccnt.setUserId(bankAccntsDataSetToSave.getValue(i, "USER_ID"));
					usrBankAccnt.setBankCode(bankAccntsDataSetToSave.getValue(i, "BANK_CODE"));
					usrBankAccnt.setInsertUserId(loggedinUserId);
					usrBankAccnt.setInsertDate(CommonUtil.getSysdateAsDate());

					result += insertWithSQLQuery(usrBankAccnt);
				} else {
					usrBankAccnt.setUpdateUserId(loggedinUserId);
					usrBankAccnt.setUpdateDate(CommonUtil.getSysdateAsDate());

					usrBankAccnt.addUpdateColumnFromField();
					queryAdvisor.addWhereClause("bank_accnt_id = '"+bankAccntId+"'");
					result += updateColumns(queryAdvisor, usrBankAccnt);
				}
			}

			return result;
		} catch (Exception ex) {
			return -1;
		}
	}

	public int insert(UsrBankAccnt usrBankAccnt) throws Exception {
		return insertWithSQLQuery(usrBankAccnt);
	}

	public int update(String bankAccntId, UsrBankAccnt usrBankAccnt) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bank_accnt_id = '"+bankAccntId+"'");
		return updateColumns(queryAdvisor, usrBankAccnt);
	}

	public int delete(String bankAccntIds[]) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		int result = 0;
		String idsForDel = "";

		if (!(bankAccntIds == null || bankAccntIds.length == 0)) {
			for (String id : bankAccntIds) {
				idsForDel += CommonUtil.isBlank(idsForDel) ? "'"+id+"'" : ",'"+id+"'";
			}
			queryAdvisor.addWhereClause("bank_accnt_id in ("+idsForDel+")");
			result = deleteWithSQLQuery(queryAdvisor, new UsrBankAccnt());
		}
		return result;
	}

	public int delete(String bankAccntId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bank_accnt_id = '"+bankAccntId+"'");
		return deleteWithSQLQuery(queryAdvisor, new UsrBankAccnt());
	}

	public int deleteByUserId(String userId) throws Exception {
		try {
			QueryAdvisor queryAdvisor = new QueryAdvisor();
			queryAdvisor.addWhereClause("user_id = '"+userId+"'");
			return deleteWithSQLQuery(queryAdvisor, new UsrBankAccnt());
		} catch (Exception ex) {
			return -1;
		}
	}

	public DataSet getDataSetBySearchCriteria(QueryAdvisor queryAdvisor) throws Exception {
		String langCode = (String)queryAdvisor.getObject("langCode");
		String userId = (String)queryAdvisor.getObject("userId");
		String bankCode = (String)queryAdvisor.getObject("bankCode");

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(bankCode, "bank_code = '"+bankCode+"'");
		queryAdvisor.addAutoFillCriteria(userId, "user_id = '"+userId+"'");
		queryAdvisor.addOrderByClause("bank_name");

		return selectAsDataSet(queryAdvisor, "query.UsrBankAccnt.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetByBankAccntId(String bankAccntId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("bank_accnt_id = '"+bankAccntId+"'");
		return selectAllAsDataSet(queryAdvisor, new UsrBankAccnt());
	}

	public DataSet getDataSetByUserId(String userId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(userId, "user_id = '"+userId+"'");
		queryAdvisor.addOrderByClause("bank_name");
		return selectAsDataSet(queryAdvisor, "query.UsrBankAccnt.getDataSetBySearchCriteria");
	}

	public DataSet getDataSetForSearchCriteriaByUserId(String userId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String langCode = CommonUtil.lowerCase(ConfigUtil.getProperty("etc.default.language"));

		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addAutoFillCriteria(userId, "user_id = '"+userId+"'");
		queryAdvisor.addOrderByClause("description");

		return selectAsDataSet(queryAdvisor, "query.UsrBankAccnt.getDataSetForSearchCriteriaByUserId");
	}
}