/**************************************************************************************************
 * Framework Generated DAO Source
 * - USR_FINANCE_FILE - Attached file for usr_finance table
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.UsrFinanceFile;

import project.conf.resource.ormapper.dto.oracle.UsrFinance;
import zebra.base.IDao;
import zebra.data.DataSet;

public interface UsrFinanceFileDao extends IDao {
	public int insert(UsrFinance usrFinance, DataSet fileDataSet) throws Exception;
	public int update(String financeId, UsrFinance usrFinance, DataSet fileDataSet) throws Exception;
	public int deleteByFinanceId(String financeId) throws Exception;
	public DataSet getFinanceFileDataSetByFileId(String financeFileId) throws Exception;
}