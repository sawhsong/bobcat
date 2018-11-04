package project.common.module.datahelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysUser.SysUserDao;
import project.conf.resource.ormapper.dto.oracle.SysUser;
import zebra.util.CommonUtil;

public class DataHelper extends BaseBiz {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(DataHelper.class);
	private static SysUserDao sysUserDao;

	public static SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public static void setSysUserDao(SysUserDao sysUserDao) {
		DataHelper.sysUserDao = sysUserDao;
	}

	/*!
	 * SysUser
	 */
	public static SysUser getUserByUserId(String userId) throws Exception {
		if (CommonUtil.isBlank(userId)) {return new SysUser();}
		return sysUserDao.getUserByUserId(userId);
	}

	public static String getUserNameById(String userId) throws Exception {
		if (CommonUtil.isBlank(userId)) {return "";}
		return getUserByUserId(userId).getUserName();
	}
}