/**************************************************************************************************
 * project
 * Description - Rpu0202 - Profit And Loss Statement
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.rpu.rpu0202;

import zebra.data.ParamEntity;

public interface Rpu0202Biz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getList(ParamEntity paramEntity) throws Exception;

	public ParamEntity doExport(ParamEntity paramEntity) throws Exception;
}