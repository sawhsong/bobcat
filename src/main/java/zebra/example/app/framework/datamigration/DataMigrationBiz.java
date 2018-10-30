package zebra.example.app.framework.datamigration;

import zebra.data.ParamEntity;

public interface DataMigrationBiz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getTableList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception;
	public ParamEntity doMigration(ParamEntity paramEntity) throws Exception;
}