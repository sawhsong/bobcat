/**************************************************************************************************
 * project
 * Description - Rkm0806 - 
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.rkm.rkm0806;

import zebra.data.ParamEntity;

public interface Rkm0806Biz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getDetail(ParamEntity paramEntity) throws Exception;
	public ParamEntity getInsert(ParamEntity paramEntity) throws Exception;
	public ParamEntity getUpdate(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeInsert(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeUpdate(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeDelete(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeExport(ParamEntity paramEntity) throws Exception;
}