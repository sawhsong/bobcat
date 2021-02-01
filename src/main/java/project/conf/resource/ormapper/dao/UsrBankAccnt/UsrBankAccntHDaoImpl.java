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

public class UsrBankAccntHDaoImpl extends BaseHDao implements UsrBankAccntDao {
	public int insert(DataSet bankAccntsDataSetToSave, String loggedinUserId) throws Exception {
		int result = 0;

		try {
			for (int i=0; i<bankAccntsDataSetToSave.getRowCnt(); i++) {
				UsrBankAccnt usrBankAccnt = new UsrBankAccnt();

				usrBankAccnt.setBankAccntId(CommonUtil.uid());
				usrBankAccnt.setUserId(bankAccntsDataSetToSave.getValue(i, "USER_ID"));
				usrBankAccnt.setBankCode(bankAccntsDataSetToSave.getValue(i, "BANK_CODE"));
				usrBankAccnt.setBsb(bankAccntsDataSetToSave.getValue(i, "BSB"));
				usrBankAccnt.setAccntNumber(bankAccntsDataSetToSave.getValue(i, "ACCNT_NUMBER"));
				usrBankAccnt.setAccntName(bankAccntsDataSetToSave.getValue(i, "ACCNT_NAME"));
				usrBankAccnt.setBalance(CommonUtil.toDouble(bankAccntsDataSetToSave.getValue(i, "BALANCE")));
				usrBankAccnt.setDescription(bankAccntsDataSetToSave.getValue(i, "DESCRIPTION"));
				usrBankAccnt.setInsertUserId(loggedinUserId);
				usrBankAccnt.setInsertDate(CommonUtil.getSysdateAsDate());

				result += insertWithSQLQuery(usrBankAccnt);
			}

			return result;
		} catch (Exception ex) {
			return -1;
		}
	}

	public int update(DataSet bankAccntsDataSetToSave, String loggedinUserId) throws Exception {
		int result = 0;
		String userId = bankAccntsDataSetToSave.getValue(0, "USER_ID");

		try {
			deleteByUserId(userId);

			for (int i=0; i<bankAccntsDataSetToSave.getRowCnt(); i++) {
				UsrBankAccnt usrBankAccnt = new UsrBankAccnt();

				usrBankAccnt.setBankAccntId(CommonUtil.uid());
				usrBankAccnt.setUserId(bankAccntsDataSetToSave.getValue(i, "USER_ID"));
				usrBankAccnt.setBankCode(bankAccntsDataSetToSave.getValue(i, "BANK_CODE"));
				usrBankAccnt.setBsb(bankAccntsDataSetToSave.getValue(i, "BSB"));
				usrBankAccnt.setAccntNumber(bankAccntsDataSetToSave.getValue(i, "ACCNT_NUMBER"));
				usrBankAccnt.setAccntName(bankAccntsDataSetToSave.getValue(i, "ACCNT_NAME"));
				usrBankAccnt.setBalance(CommonUtil.toDouble(bankAccntsDataSetToSave.getValue(i, "BALANCE")));
				usrBankAccnt.setDescription(bankAccntsDataSetToSave.getValue(i, "DESCRIPTION"));
				usrBankAccnt.setInsertUserId(bankAccntsDataSetToSave.getValue(i, "INSERT_USER_ID"));
				usrBankAccnt.setInsertDate(CommonUtil.toDate(bankAccntsDataSetToSave.getValue(i, "INSERT_DATE")));
				usrBankAccnt.setUpdateUserId(loggedinUserId);
				usrBankAccnt.setUpdateDate(CommonUtil.getSysdateAsDate());

				result += insertWithSQLQuery(usrBankAccnt);
			}

			return result;
		} catch (Exception ex) {
			return -1;
		}
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

	public DataSet getDataSetByUserId(String userId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("user_id = '"+userId+"'");
		queryAdvisor.addOrderByClause("bank_code");
		return selectAllAsDataSet(queryAdvisor, new UsrBankAccnt());
	}
}