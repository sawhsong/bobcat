package project.app.login;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.MimeMessageHelper;

import project.conf.resource.ormapper.dto.oracle.SysUser;
import zebra.mail.AbstractMessageSender;
import zebra.util.ConfigUtil;

@SuppressWarnings("rawtypes")
public class LoginMessageSender extends AbstractMessageSender implements ApplicationListener {
	public void sendResetPasswordMessage(SysUser sysUser) throws Exception {
		String defaultEncoding = ConfigUtil.getProperty("mail.default.encoding");
		String subject = "Password Reset Notice";
		String userName = sysUser.getUserName();
		String email = sysUser.getEmail();

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, defaultEncoding);

			mimeMessageHelper.setTo(new InternetAddress(email, userName, defaultEncoding));
			mimeMessageHelper.setFrom(ConfigUtil.getProperty("mail.default.from"));
			mimeMessageHelper.setSubject(subject);

			StringBuffer sb = new StringBuffer();
			sb.append("<html><head></head><body>");
			sb.append("Password Reset Notice for "+userName+"<br/><br/>");
			sb.append("Login ID : "+sysUser.getLoginId()+"<br/>");
			sb.append("Password : "+sysUser.getLoginPassword()+"<br/>");
			sb.append("</body></html>");

			mimeMessageHelper.setText(sb.toString(), sb.toString());

			javaMailSender.send(mimeMessage);
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	public void sendRequestRegisterMessage(SysUser sysUser, String toEmail) throws Exception {
		String defaultEncoding = ConfigUtil.getProperty("mail.default.encoding");
		String subject = "Request for Register";
		String userName = sysUser.getUserName();

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, defaultEncoding);

			mimeMessageHelper.setTo(new InternetAddress(toEmail, userName, defaultEncoding));
			mimeMessageHelper.setFrom(ConfigUtil.getProperty("mail.default.from"));
			mimeMessageHelper.setSubject(subject);

			StringBuffer sb = new StringBuffer();
			sb.append("<html><head></head><body>");
			sb.append("Register Request Notice for "+userName+"<br/><br/>");
			sb.append("User Name : "+sysUser.getUserName()+"<br/>");
			sb.append("Login ID : "+sysUser.getLoginId()+"<br/>");
			sb.append("Login Password : "+sysUser.getLoginPassword()+"<br/>");
			sb.append("Email Address : "+sysUser.getEmail()+"<br/><br/>");
			sb.append("Your information for register has been successfully requested to Administrator.<br/>");
			sb.append("</body></html>");

			mimeMessageHelper.setText(sb.toString(), sb.toString());

			javaMailSender.send(mimeMessage);
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	public void sendAuthKey(SysUser sysUser, String toEmail, String authKey) throws Exception {
		String defaultEncoding = ConfigUtil.getProperty("mail.default.encoding");
		String subject = "Your authentication code";
		String userName = sysUser.getUserName();

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, defaultEncoding);

			mimeMessageHelper.setTo(new InternetAddress(toEmail, userName, defaultEncoding));
			mimeMessageHelper.setFrom(ConfigUtil.getProperty("mail.default.from"));
			mimeMessageHelper.setSubject(subject);

			StringBuffer sb = new StringBuffer();
			sb.append("<html><head></head><body>");
			sb.append("Hi "+userName+",<br/><br/>");
			sb.append("Your authentication code : "+authKey+"<br/>");
			sb.append("Please enter your authentication code.<br/>");
			sb.append("</body></html>");

			mimeMessageHelper.setText(sb.toString(), sb.toString());

			javaMailSender.send(mimeMessage);
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		}
	}

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
	}
}