/**************************************************************************************************
 * project
 * Description - Rpa0206 - Profit And Loss Statement
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.rpa.rpa0206;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.common.extend.ReportExcelExportHelper;
import project.common.extend.ReportPdfExportHelper;
import project.conf.resource.ormapper.dao.Report.ReportDao;
import project.conf.resource.ormapper.dao.SysOrg.SysOrgDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class Rpa0206BizImpl extends BaseBiz implements Rpa0206Biz {
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private SysOrgDao sysOrgDao;

	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getList(ParamEntity paramEntity) throws Exception {
		DataSet request = paramEntity.getRequestDataSet();
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		String orgId = request.getValue("orgId");

		try {
			qa.setObject("orgId", orgId);
			qa.setObject("financialYear", request.getValue("financialYear"));
			qa.setObject("quarterName", request.getValue("quarterName"));
			qa.setObject("fromDate", request.getValue("fromDate"));
			qa.setObject("toDate", request.getValue("toDate"));

			paramEntity.setAjaxResponseDataSet(reportDao.getProfitAndLoss(qa));
			paramEntity.setTotalResultRows(qa.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity doExport(ParamEntity paramEntity) throws Exception {
		DataSet request = paramEntity.getRequestDataSet();
		QueryAdvisor qa = paramEntity.getQueryAdvisor();
		ExportHelper exportHelper = null;
		String dateFormat = ConfigUtil.getProperty("format.default.date");
		String orgId = request.getValue("orgId");
		String fileType = request.getValue("fileType");
		DataSet srcData;

		try {
			qa.setObject("orgId", orgId);
			qa.setObject("sysOrg", sysOrgDao.getOrgByOrgId(orgId));
			qa.setObject("financialYear", request.getValue("financialYear"));
			qa.setObject("quarterName", request.getValue("quarterName"));
			qa.setObject("fromDate", request.getValue("fromDate"));
			qa.setObject("toDate", request.getValue("toDate"));

			srcData = reportDao.getProfitAndLoss(qa);

			if (CommonUtil.equalsIgnoreCase(fileType, "pdf")) {
				exportHelper = new ReportPdfExportHelper();
				exportHelper.setFileExtention("pdf");
			} else if (CommonUtil.equalsIgnoreCase(fileType, "excel")) {
				exportHelper = new ReportExcelExportHelper();
				exportHelper.setFileExtention("xlsx");
			}

			exportHelper.setReportType("ProfitAndLoss");
			exportHelper.setFileType(fileType);
			exportHelper.setFileName("ProfitAndLoss-"+orgId+"_"+CommonUtil.getSysdate(dateFormat));
			exportHelper.setSourceDataSet(srcData);
			exportHelper.setParamEntity(paramEntity);
			exportHelper.setQueryAdvisor(qa);

			paramEntity.setFileToExport(exportHelper.createFile());
			paramEntity.setFileNameToExport(exportHelper.getFileName());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}