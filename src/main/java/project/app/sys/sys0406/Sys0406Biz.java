package project.app.sys.sys0406;

import zebra.data.ParamEntity;

public interface Sys0406Biz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getList(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception;
}