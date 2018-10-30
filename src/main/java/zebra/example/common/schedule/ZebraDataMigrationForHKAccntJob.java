package zebra.example.common.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import zebra.example.common.bizservice.framework.ZebraFrameworkBizService;
import zebra.example.common.module.commoncode.ZebraCommonCodeManager;
import zebra.example.conf.resource.ormapper.dao.Dummy.DummyDao;
import zebra.example.conf.resource.ormapper.dao.ZebraBoard.ZebraBoardDao;
import zebra.example.conf.resource.ormapper.dto.oracle.ZebraBoard;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class ZebraDataMigrationForHKAccntJob extends QuartzJobBean {
	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private ZebraFrameworkBizService zebraFrameworkBizService;
	@Autowired
	private DummyDao dummyDao;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		doMigration();
	}

	private void doMigration() {
		String uid = CommonUtil.uid();

		try {
			ZebraBoard zebraBoard = new ZebraBoard();

			zebraBoard.setArticleId(uid);
			if (CommonUtil.toInt(CommonUtil.substring(uid, uid.length()-3)) % 2 == 0) {
				zebraBoard.setBoardType(ZebraCommonCodeManager.getCodeByConstants("BOARD_TYPE_NOTICE"));
			} else {
				zebraBoard.setBoardType(ZebraCommonCodeManager.getCodeByConstants("BOARD_TYPE_FREE"));
			}
			zebraBoard.setWriterId("0");
			zebraBoard.setWriterName("ZebraBoardArticleCreationJob");
			zebraBoard.setWriterEmail(ConfigUtil.getProperty("mail.default.from"));
			zebraBoard.setWriterIpAddress("127.0.0.1");
			zebraBoard.setArticleSubject("System generated article - "+CommonUtil.getSysdate("yyyy-MM-dd HH:mm:ss"));
			zebraBoard.setArticleContents("ZebraBoardArticleCreationJob System generated article - "+CommonUtil.getSysdate("yyyy-MM-dd HH:mm:ss"));
			zebraBoard.setInsertUserId("0");
			zebraBoard.setInsertDate(CommonUtil.toDate(CommonUtil.getSysdate()));
			zebraBoard.setRefArticleId("-1");

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
	}
}