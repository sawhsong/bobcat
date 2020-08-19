package zebra.exception;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zebra.data.ParamEntity;
import zebra.util.CommonUtil;
import zebra.util.ConfigUtil;

public class BaseException extends Exception {
	protected Logger logger = LogManager.getLogger(this.getClass());
	protected String code;
	protected String message;

	@Override
	public Throwable fillInStackTrace() {
		if (CommonUtil.toBoolean(ConfigUtil.getProperty("log.exception.stackTrace"))) {
			return super.fillInStackTrace();
		} else {
			return null;
		}
	}

	public BaseException(Exception ex) {
		if (ex instanceof SQLException) {
			this.code = CommonUtil.toString(((SQLException)ex).getErrorCode());
			this.message = CommonUtil.replace(CommonUtil.replace(CommonUtil.replace(ex.getMessage(), "\n", ""), "\"", ""),"\'","");
		} else {
			this.code = "E000";
			this.message = (ex.getMessage() == null) ? ex.toString() : ex.getMessage();
		}
	}

	public BaseException(ParamEntity paramEntity, Exception ex) {
		if (ex instanceof UndeclaredThrowableException) {
			if (((UndeclaredThrowableException)ex).getUndeclaredThrowable().getCause() instanceof SQLException) {
				this.code = "ORA-"+CommonUtil.toString(((SQLException)((UndeclaredThrowableException)ex).getUndeclaredThrowable().getCause()).getErrorCode());
			} else {
				this.code = "E000";
			}
			this.message = ((UndeclaredThrowableException)ex).getUndeclaredThrowable().getCause().getMessage();
		} else if (ex instanceof SQLException) {
			this.code = "ORA-"+CommonUtil.toString(((SQLException)ex).getErrorCode());
			this.message = CommonUtil.replace(CommonUtil.replace(CommonUtil.replace(ex.getMessage(), "\n", ""), "\"", ""),"\'","");
		} else if (ex instanceof FrameworkException) {
			this.code = ((FrameworkException)ex).getCode();
			this.message = ((FrameworkException)ex).getMessage();
		} else {
			this.code = "E000";
			this.message = (ex.getMessage() == null) ? ex.toString() : ex.getMessage();
		}

		paramEntity.setSuccess(false);
		if (CommonUtil.isEmpty(paramEntity.getMessageCode())) {
			paramEntity.setMessageCode(this.code);
		}

		if (CommonUtil.isEmpty(paramEntity.getMessage())) {
			paramEntity.setMessage(this.message);
		}
	}

	public BaseException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String toString() {
		return "[" + this.code + "] ==> " + this.message + "";
	}
}