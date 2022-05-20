package project.common.module.key;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.conf.resource.ormapper.dao.ProjectDummy.ProjectDummyDao;
import zebra.example.common.extend.BaseBiz;
import zebra.util.CommonUtil;

public class KeyManager extends BaseBiz implements KeyManagerBiz {
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(KeyManager.class);
	private static ProjectDummyDao dummyDao;

	public static ProjectDummyDao getDummyDao() {
		return dummyDao;
	}

	public static void setDummyDao(ProjectDummyDao dummyDao) {
		KeyManager.dummyDao = dummyDao;
	}

	public static String getId() throws Exception {
		return CommonUtil.uid();
	}

	public static String getId(String sequeceName) throws Exception {
		return (dummyDao.getIdBySequenceName(sequeceName)).getValue(0, 0);
	}
}