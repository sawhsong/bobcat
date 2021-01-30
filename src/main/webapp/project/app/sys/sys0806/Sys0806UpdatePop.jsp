<%/************************************************************************************************
* Description - Sys0806 - Expenditure Type
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	SysExpenseType sysExpenseType = (SysExpenseType)paramEntity.getObject("sysExpenseType");
	String level = "", gstFormat = "#,##0.00", disableFlag = requestDataSet.getValue("disabledStr");
	if (CommonUtil.isBlank(sysExpenseType.getParentExpenseType())) {level = "1";}
	else {level = "2";}
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
			<ui:button id="btnDelete" caption="button.com.delete" iconClass="fa-trash" status="<%=disableFlag%>"/>
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
			<th class="thEdit Rt mandatory"><mc:msg key="sys0806.header.expenseTypeId"/></th>
			<td class="tdEdit">
				<ui:text name="expenseTypeId" value="<%=sysExpenseType.getExpenseTypeId()%>" status="display" checkName="sys0806.header.expenseTypeId" options="mandatory"/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0806.header.orgCategory"/></th>
			<td class="tdEdit">
				<ui:ccselect name="orgCategory" codeType="ORG_CATEGORY" status="disabled" selectedValue="<%=sysExpenseType.getOrgCategory()%>" checkName="sys0806.header.orgCategory" options="mandatory"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0806.header.mainExpenseType"/></th>
			<td class="tdEdit">
<%
			if (CommonUtil.equals(level, "2")) {
%>
				<ui:ccselect name="mainExpenseType" codeType="EXPENSE_MAIN_TYPE" status="disabled" selectedValue="<%=sysExpenseType.getParentExpenseType()%>" caption="==Select==" checkName="sys0806.header.mainExpenseType"/>
<%
			}
%>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0806.header.expenseType"/></th>
			<td class="tdEdit">
<%
			if (CommonUtil.equals(level, "1")) {
%>
				<ui:ccselect name="expenseType" codeType="EXPENSE_MAIN_TYPE" status="disabled" selectedValue="<%=sysExpenseType.getExpenseType()%>" caption="==Select==" checkName="sys0806.header.mainExpenseType"/>
<%
			} else {
%>
				<ui:ccselect name="expenseType" codeType="EXPENSE_SUB_TYPE" status="disabled" selectedValue="<%=sysExpenseType.getExpenseType()%>" caption="==Select==" checkName="sys0806.header.expenseType" options="mandatory"/>
<%
			}
%>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0806.header.description"/></th>
			<td class="tdEdit">
				<ui:text name="description" value="<%=sysExpenseType.getDescription()%>" checkName="sys0806.header.description" options="mandatory"/>
			</td>
			<th class="thEdit Rt"><mc:msg key="sys0806.header.accountCode"/></th>
			<td class="tdEdit">
				<ui:text name="accountCode" value="<%=sysExpenseType.getAccountCode()%>" checkName="sys0806.header.accountCode"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0806.header.isApplyGst"/></th>
			<td class="tdEdit">
				<ui:ccselect name="isApplyGst" codeType="SIMPLE_YN" selectedValue="<%=sysExpenseType.getIsApplyGst()%>" checkName="sys0806.header.isApplyGst" options="mandatory"/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="sys0806.header.gstPercentage"/></th>
			<td class="tdEdit">
				<ui:text name="gstPercentage" value="<%=CommonUtil.toString(sysExpenseType.getGstPercentage(), gstFormat)%>" className="numeric" checkName="sys0806.header.gstPercentage" options="mandatory"/>
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