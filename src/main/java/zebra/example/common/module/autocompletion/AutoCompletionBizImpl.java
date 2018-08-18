package zebra.example.common.module.autocompletion;

import org.springframework.beans.factory.annotation.Autowired;

import zebra.data.DataSet;
import zebra.data.ParamEntity;
import zebra.data.QueryAdvisor;
import zebra.example.common.extend.BaseBiz;
import zebra.example.conf.resource.ormapper.dao.ZebraDomainDictionary.ZebraDomainDictionaryDao;
import zebra.exception.FrameworkException;

public class AutoCompletionBizImpl extends BaseBiz implements AutoCompletionBiz {
	@Autowired
	private ZebraDomainDictionaryDao zebraDomainDictionaryDao;

	public ParamEntity getDomainDictionaryName(ParamEntity paramEntity) throws Exception {
		DataSet requestDataSet = paramEntity.getRequestDataSet();
		QueryAdvisor queryAdvisor = paramEntity.getQueryAdvisor();
		String inputValue = requestDataSet.getValue("inputValue");

		try {
			queryAdvisor.addAutoFillCriteria(inputValue, "lower(domain_name) like lower('"+inputValue+"%') or lower(name_abbreviation) like lower('"+inputValue+"%')");

			paramEntity.setAjaxResponseDataSet(zebraDomainDictionaryDao.getNameDataSetByName(queryAdvisor));
			paramEntity.setSuccess(true);
		} catch (Exception ex) {
			throw new FrameworkException(paramEntity, ex);
		}
		return paramEntity;
	}
}