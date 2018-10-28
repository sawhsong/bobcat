package zebra.example.app.sample.multidatasource;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseBiz;
import zebra.example.common.module.commoncode.ZebraCommonCodeManager;
import zebra.example.conf.resource.ormapper.dao.ZebraBoard.ZebraBoardDao;
import zebra.example.conf.resource.ormapper.dao.ZebraBoardFile.ZebraBoardFileDao;
import zebra.example.conf.resource.ormapper.dto.oracle.ZebraBoard;
import zebra.exception.FrameworkException;
import zebra.export.ExportHelper;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.ExportUtil;

public class MultiDatasourceBizImpl extends BaseBiz implements MultiDatasourceBiz {
	@Autowired
	private ZebraBoardDao zebraBoardDao;
	@Autowired
	private ZebraBoardFileDao zebraBoardFileDao;

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
		String defaultDbUser = ConfigUtil.getProperty("jdbc.user.name");

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);
			queryAdvisor.setPagination(true);

			zebraBoardDao.setDataSourceName(defaultDbUser);
			paramEntity.setAjaxResponseDataSet(zebraBoardDao.getNoticeBoardDataSetByCriteria(queryAdvisor));
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
		String defaultDbUser = ConfigUtil.getProperty("jdbc.user.name");

		try {
			zebraBoardDao.setDataSourceName(defaultDbUser);
			zebraBoardFileDao.setDataSourceName(defaultDbUser);

			paramEntity.setObject("noticeBoard", zebraBoardDao.getBoardByArticleId(articleId));
			paramEntity.setObject("fileDataSet", zebraBoardFileDao.getBoardFileListDataSetByArticleId(articleId));

			zebraBoardDao.updateVisitCountByArticleId(articleId);

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

	public ParamEntity getAttachedFile(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		String defaultDbUser = ConfigUtil.getProperty("jdbc.user.name");

		try {
			zebraBoardFileDao.setDataSourceName(defaultDbUser);
			paramEntity.setAjaxResponseDataSet(zebraBoardFileDao.getBoardFileListDataSetByArticleId(requestDataSet.getValue("articleId")));
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
		ZebraBoard zebraBoard = new ZebraBoard();
		String uid = CommonUtil.uid();
		String loggedInUserId = (String)session.getAttribute("UserId");
		String defaultDbUser = ConfigUtil.getProperty("jdbc.user.name");
		int result = -1;

		try {
			zebraBoard.setArticleId(uid);
			zebraBoard.setBoardType(ZebraCommonCodeManager.getCodeByConstants("BOARD_TYPE_NOTICE"));
			zebraBoard.setWriterId(loggedInUserId);
			zebraBoard.setWriterName(requestDataSet.getValue("writerName"));
			zebraBoard.setWriterEmail(requestDataSet.getValue("writerEmail"));
			zebraBoard.setWriterIpAddress(paramEntity.getRequest().getRemoteAddr());
			zebraBoard.setArticleSubject(requestDataSet.getValue("articleSubject"));
			zebraBoard.setArticleContents(requestDataSet.getValue("articleContents"));
			zebraBoard.setInsertUserId(loggedInUserId);
			zebraBoard.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));
			zebraBoard.setRefArticleId(CommonUtil.nvl(requestDataSet.getValue("articleId"), "-1"));

			zebraBoardDao.setDataSourceName(defaultDbUser);
			result = zebraBoardDao.insert(zebraBoard, fileDataSet, "Y");
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
		String defaultDbUser = ConfigUtil.getProperty("jdbc.user.name");
		ZebraBoard zebraBoard;
		int result = 0;

		try {
			zebraBoardDao.setDataSourceName(defaultDbUser);
			zebraBoard = zebraBoardDao.getBoardByArticleId(articleId);
			zebraBoard.setArticleId(articleId);
			zebraBoard.setWriterId(loggedInUserId);
			zebraBoard.setWriterName(requestDataSet.getValue("writerName"));
			zebraBoard.setWriterEmail(requestDataSet.getValue("writerEmail"));
			zebraBoard.setWriterIpAddress(paramEntity.getRequest().getRemoteAddr());
			zebraBoard.setArticleSubject(requestDataSet.getValue("articleSubject"));
			zebraBoard.setArticleContents(requestDataSet.getValue("articleContents"));
			zebraBoard.setUpdateUserId(loggedInUserId);
			zebraBoard.setUpdateDate(CommonUtil.toDate(CommonUtil.getSysdate()));

			zebraBoardDao.setDataSourceName(defaultDbUser);
			result = zebraBoardDao.update(zebraBoard, fileDataSet, "Y", fileIdsToDelete);
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
		String defaultDbUser = ConfigUtil.getProperty("jdbc.user.name");
		String[] articleIds = CommonUtil.splitWithTrim(chkForDel, ConfigUtil.getProperty("delimiter.record"));
		int result = 0;

		try {
			if (CommonUtil.isBlank(articleId)) {
				zebraBoardDao.setDataSourceName(defaultDbUser);
				result = zebraBoardDao.delete(articleIds);
			} else {
				zebraBoardDao.setDataSourceName(defaultDbUser);
				result = zebraBoardDao.delete(articleId);
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
		String dataRange = requestDataSet.getValue("dataRange");
		String defaultDbUser = ConfigUtil.getProperty("jdbc.user.name");

		try {
			String pageTitle = "Notice Board List";
			String fileName = "NoticeBoardList";
			String[] columnHeader = {"article_id", "writer_name", "writer_email", "article_subject", "created_date"};

			exportHelper = ExportUtil.getExportHelper(requestDataSet.getValue("fileType"));
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

			zebraBoardDao.setDataSourceName(defaultDbUser);
			exportHelper.setSourceDataSet(zebraBoardDao.getNoticeBoardDataSetByCriteria(queryAdvisor));

			paramEntity.setSuccess(true);
			paramEntity.setFileToExport(exportHelper.createFile());
			paramEntity.setFileNameToExport(exportHelper.getFileName());
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}

		return paramEntity;
	}
}