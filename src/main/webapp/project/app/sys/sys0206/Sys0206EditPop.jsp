<%/************************************************************************************************
* Description - Sys0206 - Organisation Management
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	String mode = requestDataSet.getValue("mode");
	String orgId = requestDataSet.getValue("orgId");
	String defaultLogoPath = (String)paramEntity.getObject("defaultLogoPath");
	String dateFormat = ConfigUtil.getProperty("format.date.java");
%>
<%/************************************************************************************************
* HTML
************************************************************************************************/%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="icon" type="image/png" href="<mc:cp key="imgIcon"/>/faviconBobcat.png">
<title><mc:msg key="main.system.title"/></title>
<%/************************************************************************************************
* Stylesheet & Javascript
************************************************************************************************/%>
<%@ include file="/shared/page/incCssJs.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
var orgId = "<%=orgId%>";
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
			<col width="14%"/>
			<col width="36%"/>
			<col width="14%"/>
			<col width="36%"/>
		</colgroup>
		<tr>
			<th class="thEdit rt">Org Logo</th>
			<td class="tdEdit Lt" id="tdOrgLogo" colspan="3" style="height:94px;"></td>
		</tr>
		<tr>
			<th class="thEdit rt"><mc:msg key="sys0206.header.changeLogo"/></th>
			<td class="tdEdit"><ui:file name="logoPath" style="width:340px"/></td>
			<th class="thEdit Rt"><mc:msg key="sys0206.header.orgId"/></th>
			<td class="tdEdit"><ui:text name="orgId" status="display"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0206.header.abn"/></th>
			<td class="tdEdit"><ui:text name="abn"/></td>
			<th class="thEdit Rt">ACN</th>
			<td class="tdEdit"><ui:text name="acn"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0206.header.legalName"/></th>
			<td class="tdEdit"><ui:text name="legalName" checkName="sys0206.header.legalName" options="mandatory"/></td>
			<th class="thEdit Rt"><mc:msg key="sys0206.header.tradingName"/></th>
			<td class="tdEdit"><ui:text name="tradingName"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0206.header.email"/></th>
			<td class="tdEdit"><ui:text name="email" checkName="sys0206.header.email" option="email"/></td>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0206.header.businessType"/></th>
			<td class="tdEdit"><ui:ccselect name="businessType" codeType="BUSINESS_TYPE" checkName="sys0206.header.businessType" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt">Telephone</th>
			<td class="tdEdit"><ui:text name="telNumber" option="numeric"/></td>
			<th class="thEdit Rt">Mobile</th>
			<td class="tdEdit"><ui:text name="mobileNumber" option="numeric"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0206.header.entityType"/></th>
			<td class="tdEdit"><ui:ccselect name="entityType" codeType="ENTITY_TYPE" checkName="sys0206.header.entityType" options="mandatory"/></td>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0206.header.baseType"/></th>
			<td class="tdEdit"><ui:ccselect name="baseType" codeType="BASE_TYPE" checkName="sys0206.header.baseType" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt">Business Address</th>
			<td class="tdEdit" colspan="3"><ui:text name="address"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0206.header.registeredDate"/></th>
			<td class="tdEdit">
				<ui:text name="registeredDate" className="Ct hor" style="width:90px" value="<%=CommonUtil.getSysdate(dateFormat)%>" option="date"/>
				<ui:icon id="icnRegisteredDate" className="fa-calendar hor"/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0206.header.isActive"/></th>
			<td class="tdEdit"><ui:ccselect name="isActive" codeType="IS_ACTIVE" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt"><mc:msg key="sys0206.header.rRangeFrom"/></th>
			<td class="tdEdit"><ui:text name="rRangeFrom" className="rt numeric" option="numeric"/></td>
			<th class="thEdit Rt"><mc:msg key="sys0206.header.rRangeTo"/></th>
			<td class="tdEdit"><ui:text name="rRangeTo" className="rt numeric" option="numeric"/></td>
		</tr>
		<tr>
			<th class="thEdit rt">Last Updated By</th>
			<td class="tdEdit"><ui:text name="lastUpdatedBy" status="display"/></td>
			<th class="thEdit rt">Last Updated Date</th>
			<td class="tdEdit"><ui:text name="lastUpdatedDate" status="display"/></td>
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