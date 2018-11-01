/**************************************************************************************************
 * project
 * Description - Rkm0206 - 
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.rkm.rkm0206;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.ExportUtil;

import project.common.extend.BaseBiz;
import project.common.module.commoncode.CommonCodeManager;
import project.conf.resource.ormapper.dao.SysBoard.SysBoardDao;
import project.conf.resource.ormapper.dao.SysBoardFile.SysBoardFileDao;
import project.conf.resource.ormapper.dto.oracle.SysBoard;

public class Rkm0206BizImpl extends BaseBiz implements Rkm0206Biz {
	@Autowired
	private SysBoardDao sysBoardDao;
	@Autowired
	private SysBoardFileDao sysBoardFileDao;

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

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);
			queryAdvisor.setPagination(true);

			paramEntity.setAjaxResponseDataSet(sysBoardDao.getNoticeBoardDataSetByCriteria(queryAdvisor));
			paramEntity.setTotalResultRows(queryAdvisor.getTotalResultRows());
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String articleId = requestDataSet.getValue("articleId");

		try {
			paramEntity.setObject("sysBoard", sysBoardDao.getBoardByArticleId(articleId));
			paramEntity.setObject("fileDataSet", sysBoardFileDao.getBoardFileListDataSetByArticleId(articleId));

			sysBoardDao.updateVisitCountByArticleId(articleId);

			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getInsert(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity getUpdate(ParamEntity paramEntity) throws Exception {
		try {
			paramEntity = getDetail(paramEntity);
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}

	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		DataSet fileDataSet = paramEntity.getRequestFileDataSet();
		SysBoard sysBoard = new SysBoard();
		String uid = CommonUtil.uid();
		String loggedInUserId = (String)session.getAttribute("UserId");
		int result = -1;

		try {
			sysBoard.setArticleId(uid);
			sysBoard.setBoardType(CommonCodeManager.getCodeByConstants("BOARD_TYPE_NOTICE"));
			sysBoard.setWriterId(loggedInUserId);
			sysBoard.setWriterName(requestDataSet.getValue("writerName"));
			sysBoard.setWriterEmail(requestDataSet.getValue("writerEmail"));
			sysBoard.setWriterIpAddress(paramEntity.getRequest().getRemoteAddr());
			sysBoard.setArticleSubject(requestDataSet.getValue("articleSubject"));
			sysBoard.setArticleContents(requestDataSet.getValue("articleContents"));
			sysBoard.setInsertUserId(loggedInUserId);
			sysBoard.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));
			sysBoard.setParentArticleId(CommonUtil.nvl(requestDataSet.getValue("articleId"), "-1"));

			result = sysBoardDao.insert(sysBoard, fileDataSet, "Y");
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

	public ParamEntity exeUpdate(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		HttpSession session = paramEntity.getSession();
		DataSet fileDataSet = paramEntity.getRequestFileDataSet();
		String chkForDel = requestDataSet.getValue("chkForDel");
		String articleId = requestDataSet.getValue("articleId");
		String fileIdsToDelete[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		String loggedInUserId = (String)session.getAttribute("UserId");
		SysBoard sysBoard;
		int result = 0;

		try {
			sysBoard = sysBoardDao.getBoardByArticleId(articleId);
			sysBoard.setArticleId(articleId);
			sysBoard.setWriterId(loggedInUserId);
			sysBoard.setWriterName(requestDataSet.getValue("writerName"));
			sysBoard.setWriterEmail(requestDataSet.getValue("writerEmail"));
			sysBoard.setWriterIpAddress(paramEntity.getRequest().getRemoteAddr());
			sysBoard.setArticleSubject(requestDataSet.getValue("articleSubject"));
			sysBoard.setArticleContents(requestDataSet.getValue("articleContents"));
			sysBoard.setUpdateUserId(loggedInUserId);
			sysBoard.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			result = sysBoardDao.update(sysBoard, fileDataSet, "Y", fileIdsToDelete);
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

	public ParamEntity exeDelete(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String articleId = requestDataSet.getValue("articleId");
		String chkForDel = requestDataSet.getValue("chkForDel");
		String articleIds[] = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		int result = 0;

		try {
			if (CommonUtil.isBlank(articleId)) {
				result = sysBoardDao.delete(articleIds);
			} else {
				result = sysBoardDao.delete(articleId);
			}

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

	public ParamEntity exeExport(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		ExportHelper exportHelper;
		String columnHeader[];
		String pageTitle, fileName;
		String fileType = requestDataSet.getValue("fileType");
		String dataRange = requestDataSet.getValue("dataRange");

		try {
			pageTitle = "Board List";
			fileName = "BoardList";
			columnHeader = new String[]{"article_id", "writer_name", "writer_email", "article_subject", "created_date"};

			exportHelper = ExportUtil.getExportHelper(fileType);
			exportHelper.setPageTitle(pageTitle);
			exportHelper.setColumnHeader(columnHeader);
			exportHelper.setFileName(fileName);
			exportHelper.setPdfWidth(1000);

			queryAdvisor.setRequestDataSet(requestDataSet);
			if (CommonUtil.containsIgnoreCase(dataRange, "all"))
				queryAdvisor.setPagination(false);
			else {
				queryAdvisor.setPagination(true);
			}

			exportHelper.setSourceDataSet(sysBoardDao.getNoticeBoardDataSetByCriteria(queryAdvisor));

			paramEntity.setSuccess(true);
			paramEntity.setFileToExport(exportHelper.createFile());
			paramEntity.setFileNameToExport(exportHelper.getFileName());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}