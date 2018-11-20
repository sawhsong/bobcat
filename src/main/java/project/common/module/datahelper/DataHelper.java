package project.common.module.datahelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysAssetType.SysAssetTypeDao;
import project.conf.resource.ormapper.dao.SysAuthGroup.SysAuthGroupDao;
import project.conf.resource.ormapper.dao.SysBorrowingType.SysBorrowingTypeDao;
import project.conf.resource.ormapper.dao.SysExpenseType.SysExpenseTypeDao;
import project.conf.resource.ormapper.dao.SysFinancialPeriod.SysFinancialPeriodDao;
import project.conf.resource.ormapper.dao.SysIncomeType.SysIncomeTypeDao;
import project.conf.resource.ormapper.dao.SysLendingType.SysLendingTypeDao;
import project.conf.resource.ormapper.dao.SysOrg.SysOrgDao;
import project.conf.resource.ormapper.dao.SysRepaymentType.SysRepaymentTypeDao;
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
	private static SysIncomeTypeDao sysIncomeTypeDao;
	private static SysExpenseTypeDao sysExpenseTypeDao;
	private static SysAssetTypeDao sysAssetTypeDao;
	private static SysRepaymentTypeDao sysRepaymentTypeDao;
	private static SysBorrowingTypeDao sysBorrowingTypeDao;
	private static SysLendingTypeDao sysLendingTypeDao;

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

	public static SysIncomeTypeDao getSysIncomeTypeDao() {
		return sysIncomeTypeDao;
	}

	public static void setSysIncomeTypeDao(SysIncomeTypeDao sysIncomeTypeDao) {
		DataHelper.sysIncomeTypeDao = sysIncomeTypeDao;
	}

	public static SysExpenseTypeDao getSysExpenseTypeDao() {
		return sysExpenseTypeDao;
	}

	public static void setSysExpenseTypeDao(SysExpenseTypeDao sysExpenseTypeDao) {
		DataHelper.sysExpenseTypeDao = sysExpenseTypeDao;
	}

	public static SysAssetTypeDao getSysAssetTypeDao() {
		return sysAssetTypeDao;
	}

	public static void setSysAssetTypeDao(SysAssetTypeDao sysAssetTypeDao) {
		DataHelper.sysAssetTypeDao = sysAssetTypeDao;
	}

	public static SysRepaymentTypeDao getSysRepaymentTypeDao() {
		return sysRepaymentTypeDao;
	}

	public static void setSysRepaymentTypeDao(SysRepaymentTypeDao sysRepaymentTypeDao) {
		DataHelper.sysRepaymentTypeDao = sysRepaymentTypeDao;
	}

	public static SysBorrowingTypeDao getSysBorrowingTypeDao() {
		return sysBorrowingTypeDao;
	}

	public static void setSysBorrowingTypeDao(SysBorrowingTypeDao sysBorrowingTypeDao) {
		DataHelper.sysBorrowingTypeDao = sysBorrowingTypeDao;
	}

	public static SysLendingTypeDao getSysLendingTypeDao() {
		return sysLendingTypeDao;
	}

	public static void setSysLendingTypeDao(SysLendingTypeDao sysLendingTypeDao) {
		DataHelper.sysLendingTypeDao = sysLendingTypeDao;
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
	 * SysIncomeType - Used in Taglib
	 */
	public static DataSet getIncomeTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		return sysIncomeTypeDao.getIncomeTypeDataSetByOrgCategory(orgCategory);
	}

	/*!
	 * SysExpenseType - Used in Taglib
	 */
	public static DataSet getExpenseMainTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		return sysExpenseTypeDao.getExpenseMainTypeDataSetByOrgCategory(orgCategory);
	}

	public static DataSet getExpenseSubTypeDataSetByOrgCategoryParentTypeCode(String orgCategory, String parentTypeCode) throws Exception {
		return sysExpenseTypeDao.getExpenseSubTypeDataSetByOrgCategoryParentTypeCode(orgCategory, parentTypeCode);
	}

	/*!
	 * SysAssetType - Used in Taglib
	 */
	public static DataSet getAssetTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		return sysAssetTypeDao.getAssetTypeDataSetByOrgCategory(orgCategory);
	}

	/*!
	 * SysRepaymentType - Used in Taglib
	 */
	public static DataSet getRepaymentTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		return sysRepaymentTypeDao.getRepaymentTypeDataSetByOrgCategory(orgCategory);
	}

	/*!
	 * SysBorrowingType - Used in Taglib
	 */
	public static DataSet getBorrowingTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		return sysBorrowingTypeDao.getBorrowingTypeDataSetByOrgCategory(orgCategory);
	}

	/*!
	 * SysLendingType - Used in Taglib
	 */
	public static DataSet getLendingTypeDataSetByOrgCategory(String orgCategory) throws Exception {
		return sysLendingTypeDao.getLendingTypeDataSetByOrgCategory(orgCategory);
	}
}