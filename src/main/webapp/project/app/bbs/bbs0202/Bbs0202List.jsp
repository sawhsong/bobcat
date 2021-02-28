<%/************************************************************************************************
* Description - Bbs0202 - Announcement
*	- Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
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
			<col width="*"/>
		</colgroup>
		<tr>
			<th class="thSearch rt"><mc:msg key="bbs0202.search.searchPeriod"/></th>
			<td class="tdSearch">
				<ui:text name="fromDate" className="Ct hor" style="width:100px" checkName="bbs0202.search.searchDateFrom" option="date"/>
				<ui:icon id="icnFromDate" className="fa-calendar hor" title="bbs0202.search.searchDateFrom"/>
				<div class="horGap20" style="padding:6px 8px 6px 0px;">-</div>
				<ui:text name="toDate" className="Ct hor" style="width:100px" checkName="bbs0202.search.searchDateTo" option="date"/>
				<ui:icon id="icnToDate" className="fa-calendar hor" title="bbs0202.search.searchDateTo"/>
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
			<col width="*"/>
			<col width="6%"/>
			<col width="15%"/>
			<col width="10%"/>
			<col width="8%"/>
		</colgroup>
		<thead>
			<tr class="noBorderHor">
				<th class="thGrid sortable:alphanumeric"><mc:msg key="bbs0202.grid.subject"/></th>
				<th class="thGrid"><mc:msg key="bbs0202.grid.file"/></th>
				<th class="thGrid sortable:alphanumeric"><mc:msg key="bbs0202.grid.writerName"/></th>
				<th class="thGrid sortable:date">Last Updated Date</th>
				<th class="thGrid sortable:numeric"><mc:msg key="bbs0202.grid.hitCount"/></th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr class="noBorderHor noStripe">
				<td class="tdGrid Ct" colspan="5"><mc:msg key="I002"/></td>
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