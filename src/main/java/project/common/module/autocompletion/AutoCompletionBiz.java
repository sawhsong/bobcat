package project.common.module.autocompletion;

import zebra.data.ParamEntity;

public interface AutoCompletionBiz {
	public ParamEntity getUserId(ParamEntity paramEntity) throws Exception;
	public ParamEntity getLoginId(ParamEntity paramEntity) throws Exception;
	public ParamEntity getUserName(ParamEntity paramEntity) throws Exception;
	public ParamEntity getOrgName(ParamEntity paramEntity) throws Exception;
	public ParamEntity getAbn(ParamEntity paramEntity) throws Exception;
	public ParamEntity getOrgId(ParamEntity paramEntity) throws Exception;
	public ParamEntity getEmployeeSurname(ParamEntity paramEntity) throws Exception;
	public ParamEntity getEmployeeGivenName(ParamEntity paramEntity) throws Exception;
}