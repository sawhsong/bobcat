<%/************************************************************************************************
* Description - Sys0802 - Financial Period Type
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet req = (DataSet)paramEntity.getRequestDataSet();
	int currentYear = CommonUtil.toInt(CommonUtil.getSysdate("YYYY"));
	String periodYear = req.getValue("periodYear");
	String quarterCode = req.getValue("quarterCode");
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
var periodYear = "<%=periodYear%>";
var quarterCode = "<%=quarterCode%>";
</script>
</head>
<%/************************************************************************************************
* Page & Header
************************************************************************************************/%>
<body style="overflow-y:hidden;">
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
			<ui:button id="btnAutoGenerate" caption="Auto Generate" iconClass="fa-magic"/>
			<ui:button id="btnSave" caption="button.com.save" iconClass="fa-save"/>
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea" class="areaContainerPopup">
	<table class="tblSearch">
		<caption>Please select period year and quarter code for auto generate</caption>
		<colgroup>
			<col width="15%"/>
			<col width="15%"/>
			<col width="15%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<th class="thSearch Rt">Period Year</th>
			<td class="tdSearch">
				<ui:select name="periodYearForAutoGen">
<%
				for (int i=-5; i<6; i++) {
					String seleted = ((currentYear - i) == currentYear) ? "selected" : "";
%>
					<option value="<%=currentYear - i%>" <%=seleted%>><%=currentYear - i%></option>
<%
				}
%>
				</ui:select>
			</td>
			<th class="thSearch Rt">Quarter Code</th>
			<td class="tdSearch"><ui:ccselect name="quarterCodeForAutoGen" codeType="QUARTER_CODE" caption="==All=="/></td>
		</tr>
	</table>
</div>
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
			<col width="20%"/>
			<col width="30%"/>
			<col width="20%"/>
			<col width="30%"/>
		</colgroup>
		<tr>
			<th class="thEdit Rt mandatory">Period Year</th>
			<td class="tdEdit">
				<ui:select name="periodYear" options="mandatory">
<%
				for (int i=-5; i<6; i++) {
					String seleted = ((currentYear - i) == currentYear) ? "selected" : "";
%>
					<option value="<%=currentYear - i%>" <%=seleted%>><%=currentYear - i%></option>
<%
				}
%>
				</ui:select>
			</td>
			<th class="thEdit Rt mandatory">Quarter Code</th>
			<td class="tdEdit"><ui:ccselect name="quarterCode" codeType="QUARTER_CODE" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory">Financial Year</th>
			<td class="tdEdit">
				<ui:select name="financialYearFrom" options="mandatory">
<%
				for (int i=-5; i<6; i++) {
					String seleted = ((currentYear - i) == (currentYear - 1)) ? "selected" : "";
%>
					<option value="<%=currentYear - i%>" <%=seleted%>><%=currentYear - i%></option>
<%
				}
%>
				</ui:select>
				-
				<ui:select name="financialYearTo" options="mandatory">
<%
				for (int i=-5; i<6; i++) {
					String seleted = ((currentYear - i) == currentYear) ? "selected" : "";
%>
					<option value="<%=currentYear - i%>" <%=seleted%>><%=currentYear - i%></option>
<%
				}
%>
				</ui:select>
			</td>
			<th class="thEdit Rt mandatory">Quarter Name</th>
			<td class="tdEdit"><ui:ccselect name="quarterName" codeType="QUARTER_NAME" options="mandatory"/></td>
		</tr>
		<tr>
			<th class="thEdit Rt mandatory">Date From</th>
			<td class="tdEdit"><ui:text name="dateFrom" className="Ct" style="width:90px" checkName="Date From" options="mandatory"/></td>
			<th class="thEdit Rt mandatory">Date To</th>
			<td class="tdEdit"><ui:text name="dateTo" className="Ct" style="width:90px" checkName="Date To" options="mandatory"/></td>
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