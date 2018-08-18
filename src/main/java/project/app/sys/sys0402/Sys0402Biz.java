package project.app.sys.sys0402;

import zebra.data.ParamEntity;

public interface Sys0402Biz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception;
	public ParamEntity getInsert(ParamEntity paramEntity) throws Exception;
	public ParamEntity getUpdate(ParamEntity paramEntity) throws Exception;
	public ParamEntity getUpdateSortOrder(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeUpdate(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeDelete(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeUpdateSortOrder(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeExport(ParamEntity paramEntity) throws Exception;
}