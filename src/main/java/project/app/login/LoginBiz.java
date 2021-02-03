package project.app.login;

import zebra.data.ParamEntity;

public interface LoginBiz {
	public ParamEntity index(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeLogin(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeResetPassword(ParamEntity paramEntity) throws Exception;
	public ParamEntity exeRequestRegister(ParamEntity paramEntity) throws Exception;
	public ParamEntity getUserProfile(ParamEntity paramEntity) throws Exception;
	public ParamEntity getUserDetail(ParamEntity paramEntity) throws Exception;
	public ParamEntity saveUserDetail(ParamEntity paramEntity) throws Exception;
	public ParamEntity setSessionValuesForAdminTool(ParamEntity paramEntity) throws Exception;
	public ParamEntity hasAuthKey(ParamEntity paramEntity) throws Exception;
	public ParamEntity getAuthenticationSecretKey(ParamEntity paramEntity) throws Exception;
	public ParamEntity doAuthentication(ParamEntity paramEntity) throws Exception;
}