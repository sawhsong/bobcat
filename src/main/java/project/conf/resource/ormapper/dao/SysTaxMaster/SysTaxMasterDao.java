/**************************************************************************************************
 * Framework Generated DAO Source
 * - SYS_TAX_MASTER - Tax Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysTaxMaster;

import project.conf.resource.ormapper.dto.oracle.SysTaxMaster;
import zebra.base.IDao;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;

public interface SysTaxMasterDao extends IDao {
	public int insert(SysTaxMaster sysTaxMaster) throws Exception;
	public int insertWithExcelFile(QueryAdvisor queryAdvisor, DataSet fileDataSet) throws Exception;
	public int updateWithKey(SysTaxMaster sysTaxMaster, String taxMasterId) throws Exception;
	public int delete(String[] taxMasterIds) throws Exception;
	public int delete(String taxMasterId) throws Exception;
	public DataSet getTaxMasterDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception;
	public double getTaxAmtByTaxYearEmployeeIdIncome(String financialYear, String employeeId, double income) throws Exception;
	public SysTaxMaster getTaxMasterById(String taxMasterId) throws Exception;
}