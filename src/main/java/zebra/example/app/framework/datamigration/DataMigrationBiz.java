package zebra.example.app.framework.datamigration;

import zebra.data.ParamEntity;

public interface DataMigrationBiz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getTableList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeGenerate(ParamEntity paramEntity) throws Exception;
}