<%/************************************************************************************************
* Description
* - 
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity pe = (ParamEntity)request.getAttribute("paramEntity");
	DataSet dsRequest = (DataSet)pe.getRequestDataSet();
	String defaultPhotoPath = (String)pe.getObject("defaultPhotoPath");
	String userId = dsRequest.getValue("userId");
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
var userId = "<%=userId%>";
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
<div id="divInformArea">
</div>
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
			<col width="*"/>
			<col width="16%"/>
			<col width="25%"/>
			<col width="16%"/>
			<col width="25%"/>
		</colgroup>
		<tr>
			<td class="tdEdit Ct" rowspan="3">
				<img id="imgUserPhoto" src="<%=defaultPhotoPath%>" class="imgDis" style="width:100%;height:110px;"/>
			</td>
			<th class="thEdit rt">Change Photo</th>
			<td class="tdEdit" colspan="3"><ui:file name="photoPath" style="width:400px;" checkName="Photo"/></td>
		</tr>
		<tr>
			<th class="thEdit rt">User ID</th>
			<td class="tdEdit"><ui:text name="userId" status="display"/></td>
			<th class="thEdit rt mandatory">User Name</th>
			<td class="tdEdit"><ui:text name="userName" checkName="User Name" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit rt mandatory">Login ID</th>
			<td class="tdEdit"><ui:text name="loginId" checkName="Login ID" options="mandatory"/></td>
			<th class="thEdit rt mandatory">Password</th>
			<td class="tdEdit"><ui:password name="password" checkName="Password" options="mandatory"/></td>
		</tr>
	</table>
	<div class="verGap6"></div>
	<table class="tblEdit">
		<colgroup>
			<col width="20%"/>
			<col width="30%"/>
			<col width="20%"/>
			<col width="30%"/>
		</colgroup>
		<tr>
			<th class="thEdit rt mandatory">Organisation</th>
			<td class="tdEdit">
				<ui:hidden name="orgId" checkName="Organisation"/>
				<ui:text name="orgName" className="hor" style="width:250px" checkName="Organisation" options="mandatory" status="disabled"/>
			</td>
			<th class="thEdit rt mandatory">Email</th>
			<td class="tdEdit"><ui:text name="email" checkName="Email" options="mandatory" option="email"/></td>
		</tr>
		<tr>
			<th class="thEdit rt">Authentication Key</th>
			<td class="tdEdit" colspan="3">
				<ui:text name="authenticationSecretKey" value="" checkName="Authentication Key" className="hor" status="disabled" style="width:60%;"/>
				<ui:button id="btnGetAuthenticationSecretKey" caption="Generate Key" iconClass="fa-key"/>
			</td>
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