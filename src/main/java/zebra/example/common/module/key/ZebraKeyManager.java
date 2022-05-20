package zebra.example.common.module.key;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zebra.example.common.extend.BaseBiz;
import zebra.example.conf.resource.ormapper.dao.Dummy.DummyDao;
import zebra.util.CommonUtil;

public class ZebraKeyManager extends BaseBiz implements ZebraKeyManagerBiz {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(ZebraKeyManager.class);
	private static DummyDao zebraDummyDao;

	public static DummyDao getZebraDummyDao() {
		return zebraDummyDao;
	}

	public static void setZebraDummyDao(DummyDao zebraDummyDao) {
		ZebraKeyManager.zebraDummyDao = zebraDummyDao;
	}

	public static String getId() throws Exception {
		return CommonUtil.uid();
	}

	public static String getId(String sequeceName) throws Exception {
		return (zebraDummyDao.getIdBySequenceName(sequeceName)).getValue(0, 0);
	}
}