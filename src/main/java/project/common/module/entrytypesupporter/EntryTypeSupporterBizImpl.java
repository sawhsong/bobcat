package project.common.module.entrytypesupporter;

import org.springframework.beans.factory.annotation.Autowired;

import project.common.extend.BaseBiz;
import project.conf.resource.ormapper.dao.SysReconCategory.SysReconCategoryDao;
import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.exception.FrameworkException;

public class EntryTypeSupporterBizImpl extends BaseBiz implements EntryTypeSupporterBiz {
	@Autowired
	private SysReconCategoryDao sysReconCategoryDao;

	public ParamEntity getRconCategoryForContextMenu(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();

		try {
			queryAdvisor.setRequestDataSet(requestDataSet);

			paramEntity.setAjaxResponseDataSet(sysReconCategoryDao.getDataSetBySearchCriteria(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}