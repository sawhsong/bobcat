/**************************************************************************************************
 * project
 * Description - Bau0202 - My Bank Accounts
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.bau.bau0202;

import zebra.data.ParamEntity;

public interface Bau0202Biz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getEdit(ParamEntity paramEntity) throws Exception;
	public ParamEntity getBankAccountInfo(ParamEntity paramEntity) throws Exception;

	public ParamEntity doSave(ParamEntity paramEntity) throws Exception;
	public ParamEntity doDelete(ParamEntity paramEntity) throws Exception;
	public ParamEntity doExport(ParamEntity paramEntity) throws Exception;
}