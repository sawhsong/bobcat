/**************************************************************************************************
 * project
 * Description - Rkm0204 - Other Income T1
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.rkm.rkm0204;

import zebra.data.ParamEntity;

public interface Rkm0204Biz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getEdit(ParamEntity paramEntity) throws Exception;
	public ParamEntity calculateDataEntry(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeSave(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeComplete(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeDelete(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeExport(ParamEntity paramEntity) throws Exception;
}