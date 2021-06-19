<%/************************************************************************************************
* Description - Cce0202 - Credit Card Statement Upload
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	String bankCode = requestDataSet.getValue("bankCode");
	String ccStatementId = requestDataSet.getValue("ccStatementId");
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
var bankCode = "<%=bankCode%>";
var ccStatementId = "<%=ccStatementId%>";
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
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea"></div>
<div id="divInformArea" class="areaContainerPopup">
	<table class="tblInform">
		<caption>Bank Account Information</caption>
		<colgroup>
			<col width="17%"/>
			<col width="33%"/>
			<col width="17%"/>
			<col width="33%"/>
		</colgroup>
		<tr>
			<th class="thInform rt">Bank</th>
			<td class="tdInform" id="tdBank"></td>
			<th class="thInform rt">BSB</th>
			<td class="tdInform" id="tdBsb"></td>
		</tr>
		<tr>
			<th class="thInform rt">Account Number</th>
			<td class="tdInform" id="tdAccntNumber"></td>
			<th class="thInform rt">Account Name</th>
			<td class="tdInform" id="tdAccntName"></td>
		</tr>
		<tr>
			<th class="thInform rt">Description</th>
			<td class="tdInform" id="tdDescription" colspan="3"></td>
		</tr>
	</table>
	<div class="verGap4"></div>
	<table class="tblInform">
		<caption>Credit Card Statement File Information</caption>
		<colgroup>
			<col width="17%"/>
			<col width="33%"/>
			<col width="17%"/>
			<col width="33%"/>
		</colgroup>
		<tr>
			<th class="thInform rt">File Name</th>
			<td class="tdInform" id="tdFileName"></td>
			<th class="thInform rt">File Rows</th>
			<td class="tdInform" id="tdFileRows"></td>
		</tr>
		<tr>
			<th class="thInform rt">Date From</th>
			<td class="tdInform" id="tdDateFrom"></td>
			<th class="thInform rt">Date To</th>
			<td class="tdInform" id="tdDateTo"></td>
		</tr>
		<tr>
			<th class="thInform rt">Last Update Date</th>
			<td class="tdInform" id="tdLastUpdateDate"></td>
			<th class="thInform rt"></th>
			<td class="tdInform"></td>
		</tr>
	</table>
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
	<div id="div_CBA_ANZ_NAB" style="display:none">
		<table id="tblGrid_CBA_ANZ_NAB" class="tblGrid sort autosort">
			<colgroup>
				<col width="7%"/>
				<col width="9%"/>
				<col width="12%"/>
				<col width="*"/>
			</colgroup>
			<thead>
				<tr>
					<th class="thGrid">Row No.</th>
					<th class="thGrid">Date</th>
					<th class="thGrid">Amount</th>
					<th class="thGrid">Description</th>
				</tr>
			</thead>
			<tbody id="tblGridBody_CBA_ANZ_NAB">
			</tbody>
		</table>
	</div>
	<div id="div_WESTPAC" style="display:none">
		<div id="divGridWrapper_WESTPAC">
			<table id="tblGrid_WESTPAC" class="tblGrid sort autosort" style="width:1400px">
				<colgroup>
					<col width="5%"/>
					<col width="9%"/>
					<col width="7%"/>
					<col width="9%"/>
					<col width="9%"/>
					<col width="*"/>
					<col width="9%"/>
					<col width="9%"/>
				</colgroup>
				<thead>
					<tr>
						<th class="thGrid">Row No.</th>
						<th class="thGrid">Account Number</th>
						<th class="thGrid">Date</th>
						<th class="thGrid">Debit Amount</th>
						<th class="thGrid">Credit Amount</th>
						<th class="thGrid">Narrative</th>
						<th class="thGrid">Category</th>
						<th class="thGrid">Serial</th>
					</tr>
				</thead>
				<tbody id="tblGridBody_WESTPAC">
				</tbody>
			</table>
		</div>
	</div>
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