package project.common.module.datahelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysAuthGroup.SysAuthGroupDao;
import project.conf.resource.ormapper.dao.SysFinancialPeriod.SysFinancialPeriodDao;
import project.conf.resource.ormapper.dao.SysOrg.SysOrgDao;
import project.conf.resource.ormapper.dao.SysReconCategory.SysReconCategoryDao;
import project.conf.resource.ormapper.dao.SysUser.SysUserDao;
import project.conf.resource.ormapper.dto.oracle.SysAuthGroup;
import project.conf.resource.ormapper.dto.oracle.SysOrg;
import project.conf.resource.ormapper.dto.oracle.SysUser;
import zebra.data.DataSet;
import zebra.data.QueryAdvisor;
import zebra.util.CommonUtil;

public class DataHelper extends BaseBiz {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(DataHelper.class);
	private static SysUserDao sysUserDao;
	private static SysOrgDao sysOrgDao;
	private static SysAuthGroupDao sysAuthGroupDao;
	private static SysFinancialPeriodDao sysFinancialPeriodDao;
	private static SysReconCategoryDao sysReconCategoryDao;

	public static SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public static void setSysUserDao(SysUserDao sysUserDao) {
		DataHelper.sysUserDao = sysUserDao;
	}

	public static SysOrgDao getSysOrgDao() {
		return sysOrgDao;
	}

	public static void setSysOrgDao(SysOrgDao sysOrgDao) {
		DataHelper.sysOrgDao = sysOrgDao;
	}

	public static SysAuthGroupDao getSysAuthGroupDao() {
		return sysAuthGroupDao;
	}

	public static void setSysAuthGroupDao(SysAuthGroupDao sysAuthGroupDao) {
		DataHelper.sysAuthGroupDao = sysAuthGroupDao;
	}

	public static SysFinancialPeriodDao getSysFinancialPeriodDao() {
		return sysFinancialPeriodDao;
	}

	public static void setSysFinancialPeriodDao(SysFinancialPeriodDao sysFinancialPeriodDao) {
		DataHelper.sysFinancialPeriodDao = sysFinancialPeriodDao;
	}

	public static SysReconCategoryDao getSysReconCategoryDao() {
		return sysReconCategoryDao;
	}

	public static void setSysReconCategoryDao(SysReconCategoryDao sysReconCategoryDao) {
		DataHelper.sysReconCategoryDao = sysReconCategoryDao;
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

	/*!
	 * SysOrg
	 */
	public static SysOrg getOrgByOrgId(String orgId) throws Exception {
		if (CommonUtil.isBlank(orgId)) {return new SysOrg();}
		return sysOrgDao.getOrgByOrgId(orgId);
	}

	public static String getOrgNameById(String orgId) throws Exception {
		if (CommonUtil.isBlank(orgId)) {return "";}
		return getOrgByOrgId(orgId).getLegalName();
	}

	/*!
	 * SysAuthGroup
	 */
	public static DataSet getAuthGroupDataSetById(String authGroupId) throws Exception {
		if (CommonUtil.isBlank(authGroupId)) {return new DataSet();}

		QueryAdvisor qa = new QueryAdvisor();
		qa.addVariable("authGroupId", authGroupId);
		return sysAuthGroupDao.getAuthGroupDataSetByAuthGroupId(qa);
	}

	public static SysAuthGroup getSysAuthGroupById(String authGroupId) throws Exception {
		SysAuthGroup sysAuthGroup = new SysAuthGroup();

		if (CommonUtil.isBlank(authGroupId)) {return sysAuthGroup;}

		sysAuthGroup.setValues(getAuthGroupDataSetById(authGroupId));
		return sysAuthGroup;
	}

	public static String getAuthGroupNameById(String authGroupId) throws Exception {
		if (CommonUtil.isBlank(authGroupId)) {return "";}

		DataSet ds = getAuthGroupDataSetById(authGroupId);
		return CommonUtil.nvl(ds.getValue("GROUP_NAME"));
	}

	/*!
	 * SysFinancialPeriod - Used in Taglib
	 */
	public static DataSet getDistinctFinancialYearDataSet() throws Exception {
		return sysFinancialPeriodDao.getDistinctFinancialYearDataSet();
	}

	/*!
	 * SysExpenseType - Used in Taglib
	 */
	public static DataSet getMainReconCategoryDataSet() throws Exception {
		return sysReconCategoryDao.getMainCategoryDataSet();
	}

	public static DataSet getSbuReconCategoryDataSet(String parentCategoryId) throws Exception {
		return sysReconCategoryDao.getSubCategoryDataSet(parentCategoryId);
	}
}