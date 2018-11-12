<%/************************************************************************************************
* Description - Sys0404 - Authority Group
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	SysAuthGroup sysAuthGroup = (SysAuthGroup)paramEntity.getObject("sysAuthGroup");
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
			<ui:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
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
	<table class="tblEdit">
		<colgroup>
			<col width="15%"/>
			<col width="35%"/>
			<col width="15%"/>
			<col width="35%"/>
		</colgroup>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0404.header.groupId"/></th>
			<td class="tdEdit"><ui:text name="groupId" value="<%=sysAuthGroup.getGroupId()%>" checkName="sys0404.header.groupId" status="display"/></td>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0404.header.isActive"/></th>
			<td class="tdEdit"><ui:ccradio name="isActive" codeType="IS_ACTIVE" selectedValue="<%=sysAuthGroup.getIsActive()%>" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0404.header.groupName"/></th>
			<td class="tdEdit" colspan="3"><ui:text name="groupName" value="<%=sysAuthGroup.getGroupName()%>" checkName="sys0404.header.groupName" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0404.header.description"/></th>
			<td class="tdEdit" colspan="3"><ui:text name="description" value="<%=sysAuthGroup.getDescription()%>" checkName="sys0404.header.description" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="page.com.insertUser"/></th>
			<td class="tdEdit"><ui:text name="insertUser" value="<%=sysAuthGroup.getInsertUserName()%>" status="display"/></td>
			<th class="thEdit rt"><mc:msg key="page.com.insertDate"/></th>
			<td class="tdEdit"><ui:text name="insertDate" value="<%=CommonUtil.toString(sysAuthGroup.getInsertDate(), dateFormat)%>" status="display"/></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="page.com.updateUser"/></th>
			<td class="tdEdit"><ui:text name="updateUser" value="<%=sysAuthGroup.getUpdateUserName()%>" status="display"/></td>
			<th class="thEdit rt"><mc:msg key="page.com.updateDate"/></th>
			<td class="tdEdit"><ui:text name="updateDate" value="<%=CommonUtil.toString(sysAuthGroup.getUpdateDate(), dateFormat)%>" status="display"/></td>
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