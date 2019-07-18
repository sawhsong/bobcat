<%/************************************************************************************************
* Description - Sba0406 - Expenditure Type
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	String orgCategory = requestDataSet.getValue("orgCategory");
	DataSet mainExpenseType = (DataSet)paramEntity.getObject("mainExpenseType");
	DataSet expenseType = (DataSet)paramEntity.getObject("expenseType");
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
.inserted {
	background:#F2F5F9 !important;
}
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
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0406.header.orgCategory"/></th>
			<td class="tdEdit">
				<ui:ccselect name="orgCategory" codeType="ORG_CATEGORY" status="disabled" selectedValue="<%=orgCategory%>" checkName="sba0406.header.orgCategory" options="mandatory"/>
			</td>
			<th class="thEdit Rt"></th>
			<td class="tdEdit"></td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0406.header.mainExpenseType"/></th>
			<td class="tdEdit">
				<ui:select name="mainExpenseType" checkName="sba0406.header.mainExpenseType" options="mandatory">
<%
				for (int i=0; i<mainExpenseType.getRowCnt(); i++) {
					String klass = CommonUtil.isNotBlank(mainExpenseType.getValue(i, "EXPENSE_TYPE")) ? "inserted" : " ";
					pageContext.setAttribute("klass", klass);
%>
					<ui:seloption value="<%=mainExpenseType.getValue(i, \"COMMON_CODE\")%>" attributes="class:${klass}" text="<%=mainExpenseType.getValue(i, \"DESCRIPTION\")%>"/>
<%
				}
%>
				</ui:select>
			</td>
			<th class="thEdit Rt"><mc:msg key="sba0406.header.expenseType"/></th>
			<td class="tdEdit">
				<ui:select name="expenseType" checkName="sba0406.header.expenseType">
					<ui:seloption value="" text="==Select=="/>
<%
				for (int i=0; i<expenseType.getRowCnt(); i++) {
					String isDisabled = CommonUtil.isBlank(expenseType.getValue(i, "EXPENSE_TYPE")) ? "" : "Y";
%>
					<ui:seloption value="<%=expenseType.getValue(i, \"COMMON_CODE\")%>" isDisabled="<%=isDisabled%>" text="<%=expenseType.getValue(i, \"DESCRIPTION\")%>"/>
<%
				}
%>
				</ui:select>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0406.header.description"/></th>
			<td class="tdEdit">
				<ui:text name="description" checkName="sba0406.header.description" options="mandatory"/>
			</td>
			<th class="thEdit Rt"><mc:msg key="sba0406.header.accountCode"/></th>
			<td class="tdEdit">
				<ui:text name="accountCode" checkName="sba0406.header.accountCode"/>
			</td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0406.header.isApplyGst"/></th>
			<td class="tdEdit">
				<ui:ccselect name="isApplyGst" codeType="SIMPLE_YN" selectedValue="N" checkName="sba0406.header.isApplyGst" options="mandatory"/>
			</td>
			<th class="thEdit Rt mandatory"><mc:msg key="sba0406.header.gstPercentage"/></th>
			<td class="tdEdit">
				<ui:text name="gstPercentage" className="numeric" checkName="sba0406.header.gstPercentage" options="mandatory"/>
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