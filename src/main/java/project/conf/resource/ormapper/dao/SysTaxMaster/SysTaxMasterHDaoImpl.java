/**************************************************************************************************
 * Framework Generated HDAOImpl Source
 * - SYS_TAX_MASTER - Tax Master
 *************************************************************************************************/
package project.conf.resource.ormapper.dao.SysTaxMaster;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

	public int insertWithExcelFile(QueryAdvisor queryAdvisor, DataSet fileDataSet) throws Exception {
		HttpSession session = (HttpSession)queryAdvisor.getObject("session");
		SysTaxMaster sysTaxMaster;
		String loggedInUserId = (String)session.getAttribute("UserId");
		String filePath = fileDataSet.getValue("REPOSITORY_PATH");
		String fileName = fileDataSet.getValue("NEW_NAME");
		File file = new File(filePath+"/"+fileName);
		Workbook workbook = new XSSFWorkbook(new FileInputStream(file));
		Sheet sheet = workbook.getSheetAt(0);
		int result = 0;

		for (Iterator<Row> rowIter = sheet.iterator(); rowIter.hasNext();) {
			Row row = rowIter.next();

			if (row.getPhysicalNumberOfCells() < 5) {continue;}
			if (row.getCell(0).getCellTypeEnum() == CellType.STRING && CommonUtil.equalsIgnoreCase(row.getCell(0).getStringCellValue(), "Tax Master List")) {continue;}
			if (row.getCell(0).getCellTypeEnum() == CellType.STRING && CommonUtil.equalsIgnoreCase(row.getCell(0).getStringCellValue(), "Tax Year")) {continue;}

			sysTaxMaster = new SysTaxMaster();
			sysTaxMaster.setTaxMasterId(CommonUtil.uid());
			sysTaxMaster.setInsertUserId(loggedInUserId);
			sysTaxMaster.setInsertDate(CommonUtil.getSysdateAsDate());

			Cell cell = row.getCell(0);
			if (cell.getCellTypeEnum() == CellType.NUMERIC) {
				sysTaxMaster.setTaxYear(CommonUtil.toString(cell.getNumericCellValue(), "####"));	// cell0 : TaxYear
			} else {
				sysTaxMaster.setTaxYear(cell.getStringCellValue());	// cell0 : TaxYear
			}
			sysTaxMaster.setWageType(row.getCell(1).getStringCellValue());	// cell1 : WageType
			sysTaxMaster.setGross(row.getCell(2).getNumericCellValue());	// cell2 : Gross
			sysTaxMaster.setResident(row.getCell(3).getNumericCellValue());	// cell3 : Resident
			sysTaxMaster.setNonResident(row.getCell(4).getNumericCellValue());	// cell4 : NonResident

			result += insertWithSQLQuery(sysTaxMaster);
		}
		workbook.close();
		return result;
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