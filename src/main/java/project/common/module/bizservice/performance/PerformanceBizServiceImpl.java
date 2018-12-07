package project.common.module.bizservice.performance;

import org.springframework.beans.factory.annotation.Autowired;

import project.conf.resource.ormapper.dao.UsrExpense.UsrExpenseDao;
import project.conf.resource.ormapper.dao.UsrIncome.UsrIncomeDao;
import zebra.data.DataSet;
import zebra.example.common.extend.BaseBiz;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class PerformanceBizServiceImpl extends BaseBiz implements PerformanceBizService {
	@Autowired
	private UsrIncomeDao usrIncomeDao;
	@Autowired
	private UsrExpenseDao usrExpenseDao;

	/*!
	 * DTO Generator
	 */
	public DataSet getPerformanceDataSet(String orgCategory, String orgId, String financialYear, String quarterName) throws Exception {
		String header[] = new String[] {"DISPLAY_ORDER", "TYPE_NAME", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "TOT"};
		DataSet income = new DataSet();
		DataSet expense = new DataSet();
		DataSet result = new DataSet();
		double rowTot = 0, totJul = 0, totAug = 0, totSep = 0, totOct = 0, totNov = 0, totDec = 0, totJan = 0, totFeb = 0, totMar = 0, totApr = 0, totMay = 0, totJun = 0;

		try {
			income = usrIncomeDao.getIncomePerformanceDataSet(orgCategory, orgId, financialYear, quarterName);
			expense = usrExpenseDao.getExpensePerformanceDataSet(orgCategory, orgId, financialYear, quarterName);
			result.addName(header);

			for (int i=0; i<income.getRowCnt(); i++) {
				result.addRow();

				rowTot = 0;
				totJul += CommonUtil.toDouble(income.getValue(i, "JUL"));
				totAug += CommonUtil.toDouble(income.getValue(i, "AUG"));
				totSep += CommonUtil.toDouble(income.getValue(i, "SEP"));
				totOct += CommonUtil.toDouble(income.getValue(i, "OCT"));
				totNov += CommonUtil.toDouble(income.getValue(i, "NOV"));
				totDec += CommonUtil.toDouble(income.getValue(i, "DEC"));
				totJan += CommonUtil.toDouble(income.getValue(i, "JAN"));
				totFeb += CommonUtil.toDouble(income.getValue(i, "FEB"));
				totMar += CommonUtil.toDouble(income.getValue(i, "MAR"));
				totApr += CommonUtil.toDouble(income.getValue(i, "APR"));
				totMay += CommonUtil.toDouble(income.getValue(i, "MAY"));
				totJun += CommonUtil.toDouble(income.getValue(i, "JUN"));

				for (int j=0; j<income.getColumnCnt(); j++) {
					rowTot += CommonUtil.toDouble(income.getValue(i, j));
					result.setValue(result.getRowCnt()-1, j, CommonUtil.toString(rowTot, "#,###.00"));
				}
				result.setValue(result.getRowCnt()-1, "TOT", CommonUtil.toString(rowTot, "#,###.00"));
			}
			result.addRow();
			result.setValue(result.getRowCnt()-1, "DISPLAY_ORDER", "99-TOT");
			result.setValue(result.getRowCnt()-1, "TYPE_NAME", "TOT");
			result.setValue(result.getRowCnt()-1, "JUL", CommonUtil.toString(totJul, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "AUG", CommonUtil.toString(totAug, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "SEP", CommonUtil.toString(totSep, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "OCT", CommonUtil.toString(totOct, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "NOV", CommonUtil.toString(totNov, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "DEC", CommonUtil.toString(totDec, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "JAN", CommonUtil.toString(totJan, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "FEB", CommonUtil.toString(totFeb, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "MAR", CommonUtil.toString(totMar, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "APR", CommonUtil.toString(totApr, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "MAY", CommonUtil.toString(totMay, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "JUN", CommonUtil.toString(totJun, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "TOT", CommonUtil.sum(new double[] {totJul, totAug, totSep, totOct, totNov, totDec, totJan, totFeb, totMar, totApr, totMay, totJun}));

			rowTot = 0;
			totJul = totAug = totSep = totOct = totNov = totDec = totJan = totFeb = totMar = totApr = totMay = totJun = 0;

			for (int i=0; i<expense.getRowCnt(); i++) {
				result.addRow();

				rowTot = 0;
				totJul += CommonUtil.toDouble(expense.getValue(i, "JUL"));
				totAug += CommonUtil.toDouble(expense.getValue(i, "AUG"));
				totSep += CommonUtil.toDouble(expense.getValue(i, "SEP"));
				totOct += CommonUtil.toDouble(expense.getValue(i, "OCT"));
				totNov += CommonUtil.toDouble(expense.getValue(i, "NOV"));
				totDec += CommonUtil.toDouble(expense.getValue(i, "DEC"));
				totJan += CommonUtil.toDouble(expense.getValue(i, "JAN"));
				totFeb += CommonUtil.toDouble(expense.getValue(i, "FEB"));
				totMar += CommonUtil.toDouble(expense.getValue(i, "MAR"));
				totApr += CommonUtil.toDouble(expense.getValue(i, "APR"));
				totMay += CommonUtil.toDouble(expense.getValue(i, "MAY"));
				totJun += CommonUtil.toDouble(expense.getValue(i, "JUN"));

				for (int j=0; j<expense.getColumnCnt(); j++) {
					rowTot += CommonUtil.toDouble(expense.getValue(i, j));
					result.setValue(result.getRowCnt()-1, j, CommonUtil.toString(rowTot, "#,###.00"));
				}
				result.setValue(result.getRowCnt()-1, "TOT", CommonUtil.toString(rowTot, "#,###.00"));
			}
			result.addRow();
			result.setValue(result.getRowCnt()-1, "DISPLAY_ORDER", "99-TOT");
			result.setValue(result.getRowCnt()-1, "TYPE_NAME", "TOT");
			result.setValue(result.getRowCnt()-1, "JUL", CommonUtil.toString(totJul, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "AUG", CommonUtil.toString(totAug, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "SEP", CommonUtil.toString(totSep, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "OCT", CommonUtil.toString(totOct, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "NOV", CommonUtil.toString(totNov, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "DEC", CommonUtil.toString(totDec, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "JAN", CommonUtil.toString(totJan, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "FEB", CommonUtil.toString(totFeb, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "MAR", CommonUtil.toString(totMar, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "APR", CommonUtil.toString(totApr, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "MAY", CommonUtil.toString(totMay, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "JUN", CommonUtil.toString(totJun, "#,###.00"));
			result.setValue(result.getRowCnt()-1, "TOT", CommonUtil.sum(new double[] {totJul, totAug, totSep, totOct, totNov, totDec, totJan, totFeb, totMar, totApr, totMay, totJun}));
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}
}