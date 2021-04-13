package project.app.sys.sys0204;

import java.io.File;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.MimeMessageHelper;

import project.conf.resource.ormapper.dao.SysOrg.SysOrgDao;
import project.conf.resource.ormapper.dto.oracle.SysBoard;
import zebra.data.DataSet;
import zebra.mail.AbstractMessageSender;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;
import zebra.util.HtmlUtil;

@SuppressWarnings("rawtypes")
public class Sys0204MessageSender extends AbstractMessageSender implements ApplicationListener {
	@Autowired
	private SysOrgDao sysOrgDao;

	public void sendMessageForInsert(SysBoard sysBoard, DataSet fileDataSet) throws Exception {
		String defaultEncoding = ConfigUtil.getProperty("mail.default.encoding");
		String from = sysBoard.getWriterEmail();
		String subject = sysBoard.getArticleSubject();
		DataSet orgDataSet;

		try {
			orgDataSet = sysOrgDao.getDataSet();

			for (int i=0; i<orgDataSet.getRowCnt(); i++) {
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, defaultEncoding);
				String email = orgDataSet.getValue(i, "EMAIL");
				String orgName = orgDataSet.getValue(i, "LEGAL_NAME");

				if (CommonUtil.isNotBlank(email)) {
					mimeMessageHelper.setTo(new InternetAddress(email, orgName, defaultEncoding));
					mimeMessageHelper.setFrom(from);
					mimeMessageHelper.setSubject(subject);

					StringBuffer sb = new StringBuffer();
					sb.append("<html><head></head><body>");
					sb.append(HtmlUtil.stringToHtml(sysBoard.getArticleContents()));
					sb.append("</body></html>");

					mimeMessageHelper.setText(sb.toString(), sb.toString());
					if (fileDataSet != null && fileDataSet.getRowCnt() > 0) {
						for (int j=0; j<fileDataSet.getRowCnt(); j++) {
							mimeMessageHelper.addAttachment(fileDataSet.getValue(j, "ORIGINAL_NAME"), new File(fileDataSet.getValue(j, "REPOSITORY_PATH")+"/"+fileDataSet.getValue(j, "NEW_NAME")));
						}
					}

					javaMailSender.send(mimeMessage);
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
//			throw ex;
		}
	}

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
	}
}