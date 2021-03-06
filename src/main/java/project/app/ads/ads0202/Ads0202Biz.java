/**************************************************************************************************
 * project
 * Description - Ads0202 - Quote Management
 *	- Generated by Source Generator
 *************************************************************************************************/
package project.app.ads.ads0202;

import zebra.data.ParamEntity;

public interface Ads0202Biz {
	public ParamEntity getDefault(ParamEntity paramEntity) throws Exception;
	public ParamEntity getList(ParamEntity paramEntity) throws Exception;
	public ParamEntity getEdit(ParamEntity paramEntity) throws Exception;
	public ParamEntity getQuotationNumber(ParamEntity paramEntity) throws Exception;
	public ParamEntity getMyInfo(ParamEntity paramEntity) throws Exception;
	public ParamEntity getOrgInfo(ParamEntity paramEntity) throws Exception;
	public ParamEntity getQuotationMasterInfo(ParamEntity paramEntity) throws Exception;
	public ParamEntity getQuotationDetailInfo(ParamEntity paramEntity) throws Exception;

	public ParamEntity doRemoveLogo(ParamEntity paramEntity) throws Exception;
	public ParamEntity doSave(ParamEntity paramEntity) throws Exception;
	public ParamEntity doDelete(ParamEntity paramEntity) throws Exception;
	public ParamEntity getPreview(ParamEntity paramEntity) throws Exception;
	public ParamEntity doExport(ParamEntity paramEntity) throws Exception;
}