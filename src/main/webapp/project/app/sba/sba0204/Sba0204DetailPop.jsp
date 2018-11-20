<%/************************************************************************************************
* Description - Sba0204 - User Management
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	SysUser sysUser = (SysUser)paramEntity.getObject("sysUser");
	String dateFormat = ConfigUtil.getProperty("format.date.java");
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/faviconHKAccount.png">
<title><mc:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
var userId = "<%=sysUser.getUserId()%>";
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body>
<form id="fmDefault" name="fmDefault" method="post" action="">
<div id="divPopupWindowHolder">
<div id="divFixedPanelPopup">
<div id="divLocationPathArea"><%@ include file="/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainerPopup">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<ui:buttonGroup id="buttonGroup">
			<ui:button id="btnEdit" caption="button.com.edit" iconClass="fa-edit"/>
			<ui:button id="btnDelete" caption="button.com.delete" iconClass="fa-save"/>
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea"></div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker"></div>
</div>
<div id="divScrollablePanelPopup">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainerPopup">
	<div class="panel panel-default" style="width:120px;height:110px;">
		<div class="panel-body">
			<table class="tblDefault">
				<tr>
					<td class="tdDefault Ct">
						<img id="img<%=sysUser.getUserId()%>" src="<%=sysUser.getPhotoPath()%>" class="imgDis" style="width:90px;height:90px;" title="<%=sysUser.getUserName()%>"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<table class="tblEdit">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.userId"/></th>
			<td class="tdEdit"><%=sysUser.getUserId()%></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.userName"/></th>
			<td class="tdEdit"><%=sysUser.getUserName()%></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.loginId"/></th>
			<td class="tdEdit"><%=sysUser.getLoginId()%></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.password"/></th>
			<td class="tdEdit"><%=sysUser.getLoginPassword()%></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.org"/></th>
			<td class="tdEdit"><%=DataHelper.getOrgNameById(sysUser.getOrgId())%></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.authGroup"/></th>
			<td class="tdEdit"><%=DataHelper.getAuthGroupNameById(sysUser.getAuthGroupId())%></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.language"/></th>
			<td class="tdEdit"><%=CommonCodeManager.getCodeDescription("LANGUAGE_TYPE", sysUser.getLanguage())%></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.themeType"/></th>
			<td class="tdEdit"><%=CommonCodeManager.getCodeDescription("USER_THEME_TYPE", sysUser.getThemeType())%></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.type"/></th>
			<td class="tdEdit"><%=CommonCodeManager.getCodeDescription("USER_TYPE", sysUser.getUserType())%></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.email"/></th>
			<td class="tdEdit"><%=sysUser.getEmail()%></td>
		</tr>
		<tr>
			<th class="thEdit"><mc:msg key="sba0204.header.maxRowsPerPage"/></th>
			<td class="tdEdit"><%=CommonUtil.toString(sysUser.getMaxRowPerPage(), "#,###")%></td>
			<th class="thEdit"><mc:msg key="sba0204.header.pageNumsPerPage"/></th>
			<td class="tdEdit"><%=CommonUtil.toString(sysUser.getPageNumPerPage(), "#,###")%></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.status"/></th>
			<td class="tdEdit"><%=CommonCodeManager.getCodeDescription("USER_STATUS", sysUser.getUserStatus())%></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.active"/></th>
			<td class="tdEdit"><%=CommonCodeManager.getCodeDescription("IS_ACTIVE", sysUser.getIsActive())%></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.insertUser"/></th>
			<td class="tdEdit"><%=sysUser.getInsertUserName()%></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.insertDate"/></th>
			<td class="tdEdit"><%=CommonUtil.toString(sysUser.getInsertDate(), dateFormat)%></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sba0204.header.updateUser"/></th>
			<td class="tdEdit"><%=sysUser.getUpdateUserName()%></td>
			<th class="thEdit rt"><mc:msg key="sba0204.header.updateDate"/></th>
			<td class="tdEdit"><%=CommonUtil.toString(sysUser.getUpdateDate(), dateFormat)%></td>
		</tr>
	</table>
</div>
<div id="divPagingArea"></div>
<%/************************************************************************************************
* Right & Footer
************************************************************************************************/%>
</div>
</div>
<%/************************************************************************************************
* Additional Elements
************************************************************************************************/%>
</form>
<%/************************************************************************************************
* Additional Form
************************************************************************************************/%>
<form id="fmHidden" name="fmHidden" method="post" action=""></form>
</body>
</html>