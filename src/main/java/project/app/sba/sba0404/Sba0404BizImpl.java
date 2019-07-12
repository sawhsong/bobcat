/**************************************************************************************************
 * project
 * Description - Sba0404 - Income Type
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.sba.sba0404;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysIncomeType.SysIncomeTypeDao;
import project.conf.resource.ormapper.dto.oracle.SysIncomeType;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.util.CommonUtil;

public class Sba0404BizImpl extends BaseBiz implements Sba0404Biz {
	@Autowired
	private SysIncomeTypeDao sysIncomeTypeDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getList(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		HttpSession session = paramEntity.getSession();

		try {
			queryAdvisor.setObject("langCode", (String)session.getAttribute("langCode"));
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(sysIncomeTypeDao.getIncomeTypeDataSetByCriteria(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getUpdate(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String incomeTypeId = requestDataSet.getValue("incomeTypeId");
		String incomeTypeCode = requestDataSet.getValue("incomeTypeCode");

		try {
			paramEntity.setObject("sysIncomeType", sysIncomeTypeDao.getIncomeTypeByKeys(incomeTypeId, incomeTypeCode));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeUpdate(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		String incomeTypeId = requestDataSet.getValue("incomeTypeId");
		String incomeTypeCode = requestDataSet.getValue("incomeType");
		String loggedInUserId = (String)session.getAttribute("UserId");
		SysIncomeType sysIncomeType;
		int result = 0;

		try {
			sysIncomeType = sysIncomeTypeDao.getIncomeTypeByKeys(incomeTypeId, incomeTypeCode);

			sysIncomeType.setDescription(requestDataSet.getValue("description"));
			sysIncomeType.setIsApplyGst(requestDataSet.getValue("isApplyGst"));
			sysIncomeType.setGstPercentage(CommonUtil.toDouble(requestDataSet.getValue("gstPercentage")));
			sysIncomeType.setAccountCode(requestDataSet.getValue("accountCode"));
			sysIncomeType.setUpdateUserId(loggedInUserId);
			sysIncomeType.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysIncomeTypeDao.updateWithKey(sysIncomeType, incomeTypeId, incomeTypeCode);
			if (result <= 0) {
				throw new FrameworkException("E801", getMessage("E801", paramEntity));
			}

			paramEntity.setSuccess(true);
			paramEntity.setMessage("I801", getMessage("I801", paramEntity));
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}