<%/************************************************************************************************
* Description - Cce0204 - Credit Card Statement Allocation
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
	DataSet bankAccnt = (DataSet)paramEntity.getObject("bankAccnt");
	String sbaId = (String)session.getAttribute("SelectedBankAccntIdInSession");
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
#tblGrid button.bootstrapSelect.dropdown-toggle {padding:3px 20px 2px 4px;}
#tblGrid td {padding:2px 4px;}
#tblGrid .txtEn, #tblGrid .txtDpl, #tblGrid .txtDis {padding:4px 4px;}
</style>
<script type="text/javascript" src="<mc:cp key="viewPageJsName"/>"></script>
<script type="text/javascript">
var sbaId = "<%=sbaId%>";
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
			<ui:button id="btnBatch" caption="Batch Application" iconClass="fa-repeat"/>
			<ui:button id="btnSearch" caption="button.com.search" iconClass="fa-search"/>
			<ui:button id="btnClear" caption="button.com.clear" iconClass="fa-refresh"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea" class="areaContainer">
	<table class="tblSearch">
		<caption><mc:msg key="page.com.searchCriteria"/></caption>
		<colgroup>
			<col width="6%"/>
			<col width="25%"/>
			<col width="8%"/>
			<col width="8%"/>
			<col width="9%"/>
			<col width="17%"/>
			<col width="7%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<th class="thSearch rt">Bank Account</th>
			<td class="tdSearch">
				<ui:select name="bankAccntId" hasCaption="true" attribute="data-width:100%">
<%
				for (int i=0; i<bankAccnt.getRowCnt(); i++) {
%>
					<option value="<%=bankAccnt.getValue(i, "BANK_ACCNT_ID")%>" bankCode="<%=bankAccnt.getValue(i, "BANK_CODE")%>"><%=bankAccnt.getValue(i, "DESCRIPTION")%></option>
<%
				}
%>
				</ui:select>
			</td>
			<th class="thSearch rt">Allocation Status</th>
			<td class="tdSearch"><ui:ccselect name="allocationStatus" codeType="CC_ALLOC_STATUS" caption="==Select=="/></td>
			<th class="thSearch rt">Transaction Date</th>
			<td class="tdSearch">
				<ui:text name="transactionDateFrom" className="Ct hor" style="width:90px" option="date"/>
				<ui:icon id="icnTransactionDateFrom" className="fa-calendar hor"/>
				<div class="horGap20" style="padding:6px 8px 6px 0px;">-</div>
				<ui:text name="transactionDateTo" className="Ct hor" style="width:90px" option="date"/>
				<ui:icon id="icnTransactionDateTo" className="fa-calendar hor"/>
			</td>
			<th class="thSearch rt">Updated Date</th>
			<td class="tdSearch">
				<ui:text name="updatedDateFrom" value="<%=dateFrom%>" className="Ct hor" style="width:90px" option="date"/>
				<ui:icon id="icnUpdatedDateFrom" className="fa-calendar hor"/>
				<div class="horGap20" style="padding:6px 8px 6px 0px;">-</div>
				<ui:text name="updatedDateTo" value="<%=dateTo%>" className="Ct hor" style="width:90px" option="date"/>
				<ui:icon id="icnUpdatedDateTo" className="fa-calendar hor"/>
			</td>
		</tr>
	</table>
</div>
<div id="divInformArea"></div>
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
			<col width="5%"/>
			<col width="20%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="7%"/>
			<col width="*"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><ui:icon id="icnCheck" className="fa-check-square-o fa-lg"/></th>
				<th class="thGrid">Date</th>
				<th class="thGrid">Category</th>
				<th class="thGrid">Debit</th>
				<th class="thGrid">Credit</th>
				<th class="thGrid">GST Amount</th>
				<th class="thGrid">Net Amount</th>
				<th class="thGrid sortable:alphanumeric">Particular</th>
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