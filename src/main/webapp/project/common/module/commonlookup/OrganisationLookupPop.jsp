<%/************************************************************************************************
* Description - Sys0406 - User
*   - Generated by Source Generator
************************************************************************************************/%>
<%@ include file="/shared/page/incCommon.jsp"%>
<%/************************************************************************************************
* Declare objects & variables
************************************************************************************************/%>
<%
	ParamEntity paramEntity = (ParamEntity)request.getAttribute("paramEntity");
	DataSet requestDataSet = (DataSet)paramEntity.getRequestDataSet();
	String keyFieldId = requestDataSet.getValue("keyFieldId");
	String valueFieldId = requestDataSet.getValue("valueFieldId");
	String popupToSetValue = requestDataSet.getValue("popupToSetValue");
	String popupName = requestDataSet.getValue("popupName");
	String lookupValue = requestDataSet.getValue("lookupValue");
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
<script type="text/javascript" src="<mc:cp key="commonModuleViewPageJsName"/>"></script>
<script type="text/javascript">
var keyFieldId = "<%=keyFieldId%>";
var valueFieldId = "<%=valueFieldId%>";
var popupName = "<%=popupName%>";
var popupToSetValue = eval("<%=popupToSetValue%>");
var lookupValue = "<%=lookupValue%>";
var popupObject = eval(popupName);
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
			<ui:button id="btnSearch" caption="button.com.search" iconClass="fa-search"/>
			<ui:button id="btnClear" caption="button.com.clear" iconClass="fa-refresh"/>
			<ui:button id="btnClose" caption="button.com.close" iconClass="fa-times"/>
		</ui:buttonGroup>
	</div>
</div>
<div id="divSearchCriteriaArea" class="areaContainerPopup">
	<table class="tblSearch">
		<caption><mc:msg key="page.com.searchCriteria"/></caption>
		<colgroup>
			<col width="50%"/>
			<col width="50%"/>
		</colgroup>
		<tr>
			<td class="tdSearch">
				<label for="orgName" class="lblEn hor"><mc:msg key="orglookup.search.orgName"/></label>
				<ui:text name="orgName" style="width:250px;"/>
			</td>
			<td class="tdSearch">
				<label for="abn" class="lblEn hor"><mc:msg key="orglookup.search.abn"/></label>
				<ui:text name="abn" style="width:250px;"/>
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
<div id="divScrollablePanelPopup">
<%/************************************************************************************************
* Real Contents - scrollable panel(data, paging)
************************************************************************************************/%>
<div id="divDataArea" class="areaContainerPopup">
	<table id="tblGrid" class="tblGrid sort">
		<colgroup>
			<col width="13%"/>
			<col width="32%"/>
			<col width="13%"/>
			<col width="*"/>
		</colgroup>
		<thead>
			<tr>
				<th class="thGrid"><mc:msg key="orglookup.grid.id"/></th>
				<th class="thGrid"><mc:msg key="orglookup.grid.name"/></th>
				<th class="thGrid"><mc:msg key="orglookup.grid.abn"/></th>
				<th class="thGrid"><mc:msg key="orglookup.grid.address"/></th>
			</tr>
		</thead>
		<tbody id="tblGridBody">
			<tr>
				<td class="tdGridCt" colspan="4"><mc:msg key="I002"/></td>
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