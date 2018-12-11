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

				rowTot += CommonUtil.toDouble(income.getValue(i, "JUL"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "AUG"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "SEP"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "OCT"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "NOV"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "DEC"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "JAN"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "FEB"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "MAR"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "APR"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "MAY"));
				rowTot += CommonUtil.toDouble(income.getValue(i, "JUN"));

				result.setValue(result.getRowCnt()-1, "DISPLAY_ORDER", income.getValue(i, "DISPLAY_ORDER"));
				result.setValue(result.getRowCnt()-1, "TYPE_NAME", income.getValue(i, "TYPE_NAME"));
				result.setValue(result.getRowCnt()-1, "JUL", CommonUtil.getNumberMask(income.getValue(i, "JUL"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "AUG", CommonUtil.getNumberMask(income.getValue(i, "AUG"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "SEP", CommonUtil.getNumberMask(income.getValue(i, "SEP"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "OCT", CommonUtil.getNumberMask(income.getValue(i, "OCT"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "NOV", CommonUtil.getNumberMask(income.getValue(i, "NOV"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "DEC", CommonUtil.getNumberMask(income.getValue(i, "DEC"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "JAN", CommonUtil.getNumberMask(income.getValue(i, "JAN"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "FEB", CommonUtil.getNumberMask(income.getValue(i, "FEB"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "MAR", CommonUtil.getNumberMask(income.getValue(i, "MAR"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "APR", CommonUtil.getNumberMask(income.getValue(i, "APR"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "MAY", CommonUtil.getNumberMask(income.getValue(i, "MAY"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "JUN", CommonUtil.getNumberMask(income.getValue(i, "JUN"), "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "TOT", CommonUtil.toString(rowTot, "#,##0.00"));
			}

			result.addRow();
			result.setValue(result.getRowCnt()-1, "DISPLAY_ORDER", "99-TOT");
			result.setValue(result.getRowCnt()-1, "TYPE_NAME", "Total");
			result.setValue(result.getRowCnt()-1, "JUL", CommonUtil.toString(totJul, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "AUG", CommonUtil.toString(totAug, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "SEP", CommonUtil.toString(totSep, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "OCT", CommonUtil.toString(totOct, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "NOV", CommonUtil.toString(totNov, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "DEC", CommonUtil.toString(totDec, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "JAN", CommonUtil.toString(totJan, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "FEB", CommonUtil.toString(totFeb, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "MAR", CommonUtil.toString(totMar, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "APR", CommonUtil.toString(totApr, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "MAY", CommonUtil.toString(totMay, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "JUN", CommonUtil.toString(totJun, "#,##0.00"));
			result.setValue(result.getRowCnt()-1, "TOT", CommonUtil.toString(CommonUtil.sum(new double[] {totJul, totAug, totSep, totOct, totNov, totDec, totJan, totFeb, totMar, totApr, totMay, totJun}), "#,##0.00"));

			rowTot = 0;
			totJul = totAug = totSep = totOct = totNov = totDec = totJan = totFeb = totMar = totApr = totMay = totJun = 0;

			if (expense.getRowCnt() > 0) {
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

					rowTot += CommonUtil.toDouble(expense.getValue(i, "JUL"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "AUG"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "SEP"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "OCT"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "NOV"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "DEC"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "JAN"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "FEB"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "MAR"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "APR"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "MAY"));
					rowTot += CommonUtil.toDouble(expense.getValue(i, "JUN"));

					result.setValue(result.getRowCnt()-1, "DISPLAY_ORDER", expense.getValue(i, "DISPLAY_ORDER"));
					result.setValue(result.getRowCnt()-1, "TYPE_NAME", expense.getValue(i, "TYPE_NAME"));
					result.setValue(result.getRowCnt()-1, "JUL", CommonUtil.getNumberMask(expense.getValue(i, "JUL"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "AUG", CommonUtil.getNumberMask(expense.getValue(i, "AUG"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "SEP", CommonUtil.getNumberMask(expense.getValue(i, "SEP"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "OCT", CommonUtil.getNumberMask(expense.getValue(i, "OCT"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "NOV", CommonUtil.getNumberMask(expense.getValue(i, "NOV"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "DEC", CommonUtil.getNumberMask(expense.getValue(i, "DEC"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "JAN", CommonUtil.getNumberMask(expense.getValue(i, "JAN"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "FEB", CommonUtil.getNumberMask(expense.getValue(i, "FEB"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "MAR", CommonUtil.getNumberMask(expense.getValue(i, "MAR"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "APR", CommonUtil.getNumberMask(expense.getValue(i, "APR"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "MAY", CommonUtil.getNumberMask(expense.getValue(i, "MAY"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "JUN", CommonUtil.getNumberMask(expense.getValue(i, "JUN"), "#,##0.00"));
					result.setValue(result.getRowCnt()-1, "TOT", CommonUtil.toString(rowTot, "#,##0.00"));
				}
				result.addRow();
				result.setValue(result.getRowCnt()-1, "DISPLAY_ORDER", "99-TOT");
				result.setValue(result.getRowCnt()-1, "TYPE_NAME", "Total");
				result.setValue(result.getRowCnt()-1, "JUL", CommonUtil.toString(totJul, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "AUG", CommonUtil.toString(totAug, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "SEP", CommonUtil.toString(totSep, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "OCT", CommonUtil.toString(totOct, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "NOV", CommonUtil.toString(totNov, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "DEC", CommonUtil.toString(totDec, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "JAN", CommonUtil.toString(totJan, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "FEB", CommonUtil.toString(totFeb, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "MAR", CommonUtil.toString(totMar, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "APR", CommonUtil.toString(totApr, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "MAY", CommonUtil.toString(totMay, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "JUN", CommonUtil.toString(totJun, "#,##0.00"));
				result.setValue(result.getRowCnt()-1, "TOT", CommonUtil.toString(CommonUtil.sum(new double[] {totJul, totAug, totSep, totOct, totNov, totDec, totJan, totFeb, totMar, totApr, totMay, totJun}), "#,##0.00"));
			}
		} catch (Exception ex) {
			throw new FrameworkException(ex);
		}
		return result;
	}
}