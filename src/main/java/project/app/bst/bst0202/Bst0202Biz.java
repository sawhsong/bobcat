/**************************************************************************************************
 * project
 * Description - Bst0202 - Bank Transaction
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.bst.bst0202;

import zebra.data.ParamEntity;

public interface Bst0202Biz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getEdit(ParamEntity paramEntity) throws Exception;

	public ParamEntity doSave(ParamEntity paramEntity) throws Exception;
	public ParamEntity doDelete(ParamEntity paramEntity) throws Exception;
	public ParamEntity doExport(ParamEntity paramEntity) throws Exception;
}