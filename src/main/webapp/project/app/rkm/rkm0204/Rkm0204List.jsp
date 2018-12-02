<%/************************************************************************************************
* Description - Rkm0204 - Other Income T1
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	String defaultPeriodYear = (String)session.getAttribute("DefaultPeriodYear");
	String defaultQuarterName = (String)session.getAttribute("DefaultQuarterName");
	String paramFinancialYear = requestDataSet.getValue("financialYear");
	String paramQuarterName = requestDataSet.getValue("quarterName");
	String selectedFinancialYear = CommonUtil.nvl(paramFinancialYear, defaultPeriodYear);
	String selectedQuarterName = CommonUtil.nvl(paramQuarterName, defaultQuarterName);
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
<div id="divHeaderHolder" class="ui-layout-north"><%@ include file="/project/common/include/header.jsp"%></div>
<div id="divBodyHolder" class="ui-layout-center">
<div id="divBodyLeft" class="ui-layout-west"><%@ include file="/project/common/include/bodyLeft.jsp"%></div>
<div id="divBodyCenter" class="ui-layout-center">
<div id="divFixedPanel">
<div id="divLocationPathArea"><%@ include file="/project/common/include/bodyLocationPathArea.jsp"%></div>
<%/************************************************************************************************
* Real Contents - fixed panel(tab, button, search, information)
************************************************************************************************/%>
<div id="divTabArea"></div>
<div id="divButtonArea" class="areaContainer">
	<div id="divButtonAreaLeft"></div>
	<div id="divButtonAreaRight">
		<ui:buttonGroup id="buttonGroup">
			<ui:button id="btnNew" caption="button.com.new" iconClass="fa-plus-square"/>
			<ui:button id="btnDelete" caption="button.com.delete" iconClass="fa-trash"/>
			<ui:button id="btnSearch" caption="button.com.search" iconClass="fa-search"/>
			<ui:button id="btnClear" caption="button.com.clear" iconClass="fa-refresh"/>
			<ui:button id="btnExport" caption="button.com.export" iconClass="fa-download"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divAdminToolArea"><%@ include file="/project/common/include/bodyAdminToolArea.jsp"%></div>
<div id="divSearchCriteriaArea" class="areaContainer">
	<table class="tblSearch">
		<caption><mc:msg key="page.com.searchCriteria"/></caption>
		<colgroup>
			<col width="20%"/>
			<col width="20%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<td class="tdSearch">
				<label for="financialYear" class="lblEn hor mandatory"><mc:msg key="rkm0204.search.financialYear"/></label>
				<div style="float:left;padding-right:4px;">
					<ui:deSelect name="financialYear" codeType="FinancialYear" selectedValue="<%=selectedFinancialYear%>"/>
				</div>
			</td>
			<td class="tdSearch">
				<label for="quarterName" class="lblEn hor mandatory"><mc:msg key="rkm0204.search.quarter"/></label>
				<div style="float:left;padding-right:4px;">
					<ui:ccselect name="quarterName" codeType="QUARTER_NAME" selectedValue="<%=selectedQuarterName%>"/>
				</div>
			</td>
			<td class="tdSearch">
			</td>
		</tr>
	</table>
</div>
<div id="divInformArea" class="areaContainer">
	<table class="tblDataEntry">
		<caption><mc:msg key="page.com.dataEntry"/></caption>
		<colgroup>
			<col width="9%"/>
			<col width="11%"/>
			<col width="11%"/>
			<col width="11%"/>
			<col width="*"/>
			<col width="4%"/>
		</colgroup>
		<tr>
			<th class="thDataEntry mandatory"><mc:msg key="rkm0204.de.date"/></th>
			<th class="thDataEntry mandatory"><mc:msg key="rkm0204.de.grossSales"/></th>
			<th class="thDataEntry mandatory"><mc:msg key="rkm0204.de.gst"/></th>
			<th class="thDataEntry"><mc:msg key="rkm0204.de.netSales"/></th>
			<th class="thDataEntry mandatory"><mc:msg key="rkm0204.de.remark"/></th>
			<th class="thDataEntry"><mc:msg key="rkm0204.de.action"/></th>
		</tr>
		<tr>
			<td class="tdDataEntry Ct">
				<div style="display:inline-block;">
					<ui:text name="deDate" className="hor" style="width:100px" checkName="rkm0204.de.date" options="mandatory" option="date"/>
					<ui:icon id="icnDataEntryDate" className="fa-calendar hor"/>
				</div>
			</td>
			<td class="tdDataEntry Ct"><ui:text name="deGrossSales" className="rt numeric" checkName="rkm0204.de.grossSales" options="mandatory" option="numeric"/></td>
			<td class="tdDataEntry Ct"><ui:text name="deGst" className="rt numeric" checkName="rkm0204.de.gst" options="mandatory" option="numeric"/></td>
			<td class="tdDataEntry Ct"><ui:text name="deNetSales" className="rt numeric" options="mandatory" status="display"/></td>
			<td class="tdDataEntry Ct"><ui:text name="deRemark"/></td>
			<td class="tdDataEntry Ct"><ui:icon id="icnDataEntryAction" className="fa-tasks fa-lg" script="doDataEntryAction(this)"/></td>
		</tr>
	</table>
</div>
<%/************************************************************************************************
* End of fixed panel
************************************************************************************************/%>
<div class="breaker"></div>
</div>
<div id="divScrollablePanel">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainer">
	<table id="tblGrid" class="tblGrid sort autosort">
		<colgroup>
			<col width="3%"/>
			<col width="9%"/>
			<col width="9%"/>
			<col width="9%"/>
			<col width="9%"/>
			<col width="*"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="4%"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><ui:icon id="icnCheck" className="fa-check-square-o fa-lg"/></th>
				<th class="thGrid"><mc:msg key="rkm0204.grid.date"/></th>
				<th class="thGrid"><mc:msg key="rkm0204.grid.grossSales"/></th>
				<th class="thGrid"><mc:msg key="rkm0204.grid.gst"/></th>
				<th class="thGrid"><mc:msg key="rkm0204.grid.netSales"/></th>
				<th class="thGrid"><mc:msg key="rkm0204.grid.remark"/></th>
				<th class="thGrid"><mc:msg key="rkm0204.grid.isCompleted"/></th>
				<th class="thGrid"><mc:msg key="rkm0204.grid.insertDate"/></th>
				<th class="thGrid"><mc:msg key="rkm0204.grid.updateDate"/></th>
				<th class="thGrid"><mc:msg key="page.com.action"/></th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr>
				<td class="tdGridCt" colspan="10"><mc:msg key="I002"/></td>
			</tr>
		</tbody>
		<tfoot id="tblGridFoot" style="font-weight:bold;">
		</tfoot>
	</table>
</div>
<div id="divPagingArea" class="areaContainer"></div>
<%/************************************************************************************************
* Right & Footer
************************************************************************************************/%>
</div>
</div>
<div id="divBodyRight" class="ui-layout-east"><%@ include file="/project/common/include/bodyRight.jsp"%></div>
</div>
<div id="divFooterHolder" class="ui-layout-south"><%@ include file="/project/common/include/footer.jsp"%></div>
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