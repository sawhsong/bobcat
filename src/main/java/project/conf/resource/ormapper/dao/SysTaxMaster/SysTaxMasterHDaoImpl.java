/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_TAX_MASTER - Tax Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysTaxMaster;

import project.common.extend.BaseHDao;
import project.conf.resource.ormapper.dto.oracle.SysTaxMaster;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class SysTaxMasterHDaoImpl extends BaseHDao implements SysTaxMasterDao {
	public int insert(SysTaxMaster sysTaxMaster) throws Exception {
		return insertWithSQLQuery(sysTaxMaster);
	}

	public int updateWithKey(SysTaxMaster sysTaxMaster, String taxMasterId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("tax_master_id = '" + taxMasterId + "'");
		return updateWithSQLQuery(queryAdvisor, sysTaxMaster);
	}

	public int delete(String[] taxMasterIds) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		String ids = "";

		if (!(taxMasterIds == null || taxMasterIds.length == 0)) {
			for (int i=0; i<taxMasterIds.length; i++) {
				ids += CommonUtil.isBlank(ids) ? "'"+taxMasterIds[i]+"'" : ", '"+taxMasterIds[i]+"'";
			}
		}
		queryAdvisor.addWhereClause("tax_master_id in ("+ids+")");
		return deleteWithSQLQuery(queryAdvisor, new SysTaxMaster());
	}

	public int delete(String taxMasterId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("tax_master_id = '"+taxMasterId+"'");
		return deleteWithSQLQuery(queryAdvisor, new SysTaxMaster());
	}

	public DataSet getTaxMasterDataSetByCriteria(QueryAdvisor queryAdvisor) throws Exception {
		DataSet requestDataSet = queryAdvisor.getRequestDataSet();
		String taxYear = requestDataSet.getValue("taxYear");
		String wageType = requestDataSet.getValue("wageType");
		double rangeFrom = CommonUtil.toDouble(requestDataSet.getValue("rangeFrom"));
		double rangeTo = CommonUtil.toDouble(requestDataSet.getValue("rangeTo"));
		String dateFormat = ConfigUtil.getProperty("format.date.java");
		String langCode = (String)queryAdvisor.getObject("langCode");

		queryAdvisor.addWhereClause("tax.tax_year = '"+taxYear+"'");
		queryAdvisor.addAutoFillCriteria(wageType, "tax.wage_type = '"+wageType+"'");
		if (rangeFrom > 0) {
			queryAdvisor.addAutoFillCriteria(CommonUtil.toString(rangeFrom), "tax.gross >= '"+rangeFrom+"'");
		}
		if (rangeTo > 0) {
			queryAdvisor.addAutoFillCriteria(CommonUtil.toString(rangeTo), "tax.gross <= '"+rangeTo+"'");
		}
		queryAdvisor.addVariable("dateFormat", dateFormat);
		queryAdvisor.addVariable("langCode", langCode);
		queryAdvisor.addOrderByClause("tax.tax_year desc, tax.gross desc, tax.wage_type desc");

		return selectAsDataSet(queryAdvisor, "query.SysTaxMaster.getTaxMasterDataSetByCriteria");
	}

	public SysTaxMaster getTaxMasterById(String taxMasterId) throws Exception {
		QueryAdvisor queryAdvisor = new QueryAdvisor();
		queryAdvisor.addWhereClause("tax_master_id = '"+taxMasterId+"'");
		return (SysTaxMaster)selectAllToDto(queryAdvisor, new SysTaxMaster());
	}
}