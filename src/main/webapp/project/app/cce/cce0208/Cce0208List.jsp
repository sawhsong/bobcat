<%/************************************************************************************************
* Description - Cce0208 - Cash Expenses
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	String dateFormat = ConfigUtil.getProperty("format.date.java");
	String dateFrom = CommonUtil.getCalcDate("D", CommonUtil.getSysdate(dateFormat), dateFormat, -7);
	String dateTo = CommonUtil.getSysdate(dateFormat);
	String currentYear = CommonUtil.getSysdate("yyyy");
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
#tblGrid button.bootstrapSelect.dropdown-toggle {padding:3px 20px 2px 4px;}
#tblGrid td {padding:2px 4px;}
#tblGrid .txtEn, #tblGrid .txtDpl, #tblGrid .txtDis {padding:4px 4px;}
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
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea" class="areaContainer" style="float:left;width:51%;">
	<table class="tblSearch">
		<caption><mc:msg key="page.com.searchCriteria"/></caption>
		<colgroup>
			<col width="14%"/>
			<col width="12%"/>
			<col width="9%"/>
			<col width="12%"/>
			<col width="9%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<th class="thSearch rt">Financial Year</th>
			<td class="tdSearch"><ui:deSelect name="financialYear" codeType="FinancialYear" selectedValue="<%=currentYear%>" caption="==Select=="/></td>
			<th class="thSearch rt">Quarter</th>
			<td class="tdSearch"><ui:ccselect name="quarterName" codeType="QUARTER_NAME" caption="==Select=="/></td>
			<th class="thSearch rt">Period</th>
			<td class="tdSearch">
				<ui:text name="fromDate" className="Ct hor" style="width:90px" option="date"/>
				<ui:icon id="icnFromDate" className="fa-calendar hor"/>
				<div class="horGap20" style="padding:6px 8px 6px 0px;">-</div>
				<ui:text name="toDate" className="Ct hor" style="width:90px" option="date"/>
				<ui:icon id="icnToDate" className="fa-calendar hor"/>
			</td>
		</tr>
	</table>
</div>
<div id="divInformArea" class="areaContainer" style="float:right;width:49%;">
	<table class="tblInform">
		<caption>Total Amount</caption>
		<colgroup>
			<col width="15%"/>
			<col width="18%"/>
			<col width="15%"/>
			<col width="18%"/>
			<col width="15%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<th class="thInform rt">Gross Amount</th>
			<td class="tdInform"><ui:text name="totGrossAmt" className="Rt" status="display"/></td>
			<th class="thInform rt">GST Amount</th>
			<td class="tdInform"><ui:text name="totGstAmt" className="Rt" status="display"/></td>
			<th class="thInform rt">Net Amount</th>
			<td class="tdInform"><ui:text name="totNetAmt" className="Rt" status="display"/></td>
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
			<col width="2%"/>
			<col width="2%"/>
			<col width="6%"/>
			<col width="22%"/>
			<col width="9%"/>
			<col width="9%"/>
			<col width="9%"/>
			<col width="*"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><ui:icon className="fa-magic fa-lg"/></th>
				<th class="thGrid"><ui:icon id="icnCheck" className="fa-check-square-o fa-lg"/></th>
				<th class="thGrid mandatory">Date</th>
				<th class="thGrid mandatory">Category</th>
				<th class="thGrid mandatory">Gross Amount</th>
				<th class="thGrid mandatory">GST Amount</th>
				<th class="thGrid">Net Amount</th>
				<th class="thGrid">Particular</th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr>
				<td class="tdGrid Ct" colspan="8"><mc:msg key="I002"/></td>
			</tr>
		</tbody>
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